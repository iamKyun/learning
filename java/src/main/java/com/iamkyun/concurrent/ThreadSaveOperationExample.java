package com.iamkyun.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadSaveOperationExample {
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, new SynchronousQueue<>());

    public static void main(String[] args) {
        // non thread safe
        // List<Integer> numbers = new ArrayList<>();
        // thread sage
        List<Integer> numbers = new CopyOnWriteArrayList<>();
        List<Future<?>> tasks = new ArrayList<>();
        // 10 threads produce numbers and add into numbers list
        for (int i = 0; i < 10; i++) {
            int fi = i;
            try {
                Future<?> submit = executor.submit(() -> {
                    System.out.println("begin task " + fi);
                    int begin = fi * 1000;
                    for (int j = begin; j < begin + 1000; j++) {
                        numbers.add(j);
                    }
                    System.out.println("end   task " + fi);
                });
                tasks.add(submit);
            } catch (RejectedExecutionException e) {
                System.err.println("task" + fi + " has been rejected");
            }

        }
        for (Future<?> task : tasks) {
            if (task.isCancelled()) {
                System.out.println("task has been cancelled");
                continue;
            }

            try {
                System.out.println("DONE");
                task.get();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println(numbers.size());
        executor.shutdown();
    }

}
