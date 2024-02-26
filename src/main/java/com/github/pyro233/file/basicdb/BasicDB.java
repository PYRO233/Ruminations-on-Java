package com.github.pyro233.file.basicdb;

import java.io.IOException;

/**
 * @Author: tao.zhou
 * @Date: 2024/1/30 19:47
 */
public interface BasicDB {

    // 保存键值对，键为String类型，值为byte数组
    void put(String key, byte[] value) throws IOException;

    // 根据键获取值，如果键不存在，返回null
    byte[] get(String key) throws IOException;

    // 根据键删除
    void remove(String key);

    // 确保将所有数据保存到文件
    void flush() throws IOException;

    // 关闭数据库
    void close() throws IOException;

}
