package com.github.pyro233.file.basicdb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @Author: tao.zhou
 * @Date: 2024/1/30 19:49
 */
public class BasicDBImpl implements BasicDB {

    // 数据文件后缀
    private static final String DATA_SUFFIX = ".data";
    // 元数据文件后缀，包括索引和空白空间数据
    public static final String META_SUFFIX = ".meta";

    private static final int MAX_DATA_LENGTH = 1020;
    // 补白字节
    private static final byte[] ZERO_BYTES = new byte[MAX_DATA_LENGTH];;

    // 索引信息，键->值在.data文件中的位置
    private Map<String, Long> indexMap;
    // 空白空间，值为在.data文件中的位置
    private Queue<Long> gaps;

    // 值数据文件
    RandomAccessFile db;
    // 元数据文件
    private File metaFile;

    public BasicDBImpl(String path, String name) throws IOException {
        File dataFile = new File(path + name + DATA_SUFFIX);
        metaFile = new File(path + name + META_SUFFIX);
        db = new RandomAccessFile(dataFile, "rw");

        if (metaFile.exists()) {
            // 从元数据文件中读取索引信息
            loadMeta();
        } else {
            // 创建元数据文件
            indexMap = new HashMap<>();
            gaps = new ArrayDeque<>();
        }
    }

    @Override
    public void put(String key, byte[] value) throws IOException {
        Long index = indexMap.get(key);
        if (index == null) {
            index = nextAvailablePos();
            indexMap.put(key, index);
        }
        writeData(index, value);
    }

    @Override
    public byte[] get(String key) throws IOException {
        Long index = indexMap.get(key);
        if (index != null) {
            return getData(index);
        }
        return null;
    }

    @Override
    public void remove(String key) {
        // 不修改 .data 文件，只修改索引信息
        Long index = indexMap.remove(key);
        if (index != null) {
            gaps.offer(index);
        }
    }

    @Override
    public void flush() throws IOException {
        saveMeta();
        db.getFD().sync();
    }

    @Override
    public void close() throws IOException {
        flush();
        db.close();
    }

    private void loadMeta() throws IOException {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(metaFile)))) {
            loadIndex(in);
            loadGaps(in);
        }
    }

    private void loadIndex(DataInputStream in) throws IOException {
        int size = in.readInt();
        indexMap = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            String key = in.readUTF();
            long index = in.readLong();
            indexMap.put(key, index);
        }
    }

    private void loadGaps(DataInputStream in) throws IOException {
        int size = in.readInt();
        gaps = new ArrayDeque<>(size);
        for (int i = 0; i < size; i++) {
            long index = in.readLong();
            gaps.add(index);
        }
    }

    private Long nextAvailablePos() throws IOException {
        if (!gaps.isEmpty()) {
            return gaps.poll();
        }
        return db.length();
    }

    private void writeData(Long pos, byte[] data) throws IOException {
        if (data.length > MAX_DATA_LENGTH) {
            throw new IllegalArgumentException("maximum allowed length is " + MAX_DATA_LENGTH + ", data length is " + data.length);
        }
        db.seek(pos);
        // 实际长度，4字节
        db.writeInt(data.length);
        db.write(data);
        // value 固定长度为 1020
        db.write(ZERO_BYTES, 0, MAX_DATA_LENGTH - data.length);
    }

    private byte[] getData(Long pos) throws IOException {
        db.seek(pos);
        int length = db.readInt();
        byte[] data = new byte[length];
        db.readFully(data);
        return data;
    }

    private void saveMeta() throws IOException {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(metaFile)))) {
            saveIndex(out);
            saveGaps(out);
        }
    }

    private void saveIndex(DataOutputStream out) throws IOException {
        out.writeInt(indexMap.size());
        for (Map.Entry<String, Long> entry : indexMap.entrySet()) {
            out.writeUTF(entry.getKey());
            out.writeLong(entry.getValue());
        }
    }

    private void saveGaps(DataOutputStream out) throws IOException {
        out.writeInt(gaps.size());
        for (Long pos : gaps) {
            out.writeLong(pos);
        }
    }
}
