package com.github.pyro233.file.basicqueue;

import java.io.IOException;

/**
 * @Author: tao.zhou
 * @Date: 2024/1/30 20:50
 */
public interface BasicQueue {

    //入队
    void enqueue(byte[] data) throws IOException;

    //出队
    byte[] dequeue() throws IOException;

}