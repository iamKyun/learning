package com.iamkyun.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {

    /**
     * 每改完5张试卷时间就去发
     */
    public static void main(String[] args) {
        // 5个老师同时改卷
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("改好了5张");
        });
        for (int i = 0; i < 25; i++) {
            System.out.println("提交试卷" + i);
            pool.execute(() -> {
                try {
                    System.out.println("批改：" + Thread.currentThread().getName());
                    System.out.println("完成" + Thread.currentThread().getName());
                    cyclicBarrier.await();
                    System.out.println("发试卷：" + Thread.currentThread().getName());
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            //lambda中只能只用final的变量
        }
        pool.shutdown();
    }
}
