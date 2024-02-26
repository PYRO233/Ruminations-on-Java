package com.github.pyro233.file.basicqueue;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MQ @see <a href="https://github.com/swiftma/program-logic/tree/master/src/shuo/laoma/file/c61">《Java编程的逻辑》</a>
 * MappedByteBuffer 的基本使用
 * @Author: tao.zhou
 * @Date: 2024/1/30 20:54
 */
public class BasicQueueImpl implements BasicQueue {

    // 数据文件后缀
    private static final String DATA_SUFFIX = ".data";
    // 元数据文件后缀
    public static final String META_SUFFIX = ".meta";
    // 消息体最大长度
    private static final int MAX_MSG_BODY_SIZE = 1020;
    // 队列最多消息个数
    private static final int MAX_MSG_NUM = 1020*1024;
    // 每条消息占用的空间
    private static final int MSG_SIZE = MAX_MSG_BODY_SIZE + 4;
    // 队列消息体数据文件大小
    private static final int DATA_FILE_SIZE = MAX_MSG_NUM * MSG_SIZE;
    // 队列元数据文件大小 (head + tail)
    private static final int META_SIZE = 8;

    // 固定长度存储消息
    private MappedByteBuffer dataBuf;

    // 保存 head 和 tail，指向 .data 文件中的位置
    private MappedByteBuffer metaBuf;

    public BasicQueueImpl(String path, String queueName) throws IOException {
        if (!path.endsWith(File.separator)) {
            path += File.separator;
        }
        try (RandomAccessFile dataFile = new RandomAccessFile(path + queueName + ".data", "rw");
             RandomAccessFile metaFile = new RandomAccessFile(path + queueName + ".meta", "rw")) {
            dataBuf = dataFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, DATA_FILE_SIZE);
            metaBuf = metaFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, META_SIZE);
        }
    }

    @Override
    public void enqueue(byte[] data) throws IOException {
        if (data.length > MAX_MSG_BODY_SIZE) {
            throw new IllegalArgumentException("msg size is " + data.length
                    + ", while maximum allowed length is " + MAX_MSG_BODY_SIZE);
        }
        if (isFull()) {
            throw new IllegalStateException("queue is full");
        }
        int tail = getTail();
        dataBuf.position(tail);
        dataBuf.putInt(data.length);
        dataBuf.put(data);

        if (tail + MSG_SIZE >= DATA_FILE_SIZE) {
            setTail(0);
        } else {
            setTail(tail + MSG_SIZE);
        }
    }

    @Override
    public byte[] dequeue() throws IOException {
        if (isEmpty()) {
            return null;
        }
        int head = getHead();
        dataBuf.position(head);
        int length = dataBuf.getInt();
        byte[] data = new byte[length];
        dataBuf.get(data);

        if (head + MSG_SIZE >= DATA_FILE_SIZE) {
            setHead(0);
        } else {
            setHead(head + MSG_SIZE);
        }
        return data;
    }

    private int getHead() {
        return metaBuf.getInt(0);
    }

    private void setHead(int newHead) {
        metaBuf.putInt(0, newHead);
    }

    private int getTail() {
        return metaBuf.getInt(4);
    }

    private void setTail(int newTail) {
        metaBuf.putInt(4, newTail);
    }

    private boolean isEmpty(){
        return getHead() == getTail();
    }

    private boolean isFull(){
        return ((getTail() + MSG_SIZE) % DATA_FILE_SIZE) == getHead();
    }
}
