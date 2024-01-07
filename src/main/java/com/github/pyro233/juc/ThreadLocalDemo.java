package com.github.pyro233.juc;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于 云时代的 JVM 原理与实战 的内容，结合网上的博客。
 * @Author: tao.zhou
 * @Date: 2023/12/25 20:03
 */
public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        testInheritableThreadLocal();
        inheritableThreadLocalInThreadPool();
        transmittableThreadLocalInThreadPool();
    }

    private static void testInheritableThreadLocal() {
        // 一般定义为 static，这里只是演示
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        threadLocal.set("baby");
        inheritableThreadLocal.set("baby");
        Runnable task = () -> {
            printThreadLocalValue(threadLocal, "ThreadLocal", "baby");
            printThreadLocalValue(inheritableThreadLocal, "InheritableThreadLocal", "baby");
        };
        new Thread(task).start();
    }

    private static void printThreadLocalValue(ThreadLocal<String> threadLocal, String threadLocalType, String expectedValue) {
        System.out.println(Thread.currentThread().getName() + " , " + threadLocalType + ": " + threadLocal.get() + ", expected: " + expectedValue);
    }

    private static void inheritableThreadLocalInThreadPool() {
        // 新建线程则能成功传递
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        InheritableThreadLocal<String> ohbaby = new InheritableThreadLocal<>();
        ohbaby.set("baby");
        fixedThreadPool.submit(() -> printThreadLocalValue(ohbaby, "InheritableThreadLocal", "baby"));
        ohbaby.set("oh baby");
        fixedThreadPool.submit(() -> printThreadLocalValue(ohbaby, "InheritableThreadLocal", "oh baby"));

        // 重用线程则会失败
        ohbaby.set("oh no");
        fixedThreadPool.submit(() -> printThreadLocalValue(ohbaby, "InheritableThreadLocal", "oh no"));
        fixedThreadPool.shutdown();
    }

    private static void transmittableThreadLocalInThreadPool() {
        // InheritableThreadLocal 失败的场景是线程池复用了线程，TransmittableThreadLocal 解决了这个问题，但还需要包装线程池。
        final ExecutorService fixedThreadPool = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1));
        TransmittableThreadLocal<String> ohbaby = new TransmittableThreadLocal<>();
        ohbaby.set("baby");
        fixedThreadPool.submit(() -> printThreadLocalValue(ohbaby, "TransmittableThreadLocal", "baby"));
        ohbaby.set("oh baby");
        fixedThreadPool.submit(() -> printThreadLocalValue(ohbaby, "TransmittableThreadLocal", "oh baby"));
        fixedThreadPool.shutdown();
    }


}