package com.iamkyun.algorithm;

import java.time.Duration;
import java.time.LocalDateTime;

public class InsertionSort {
    public static void main(String[] args) {
        LocalDateTime begin = LocalDateTime.now();

        for (int i = 0; i < 1000000; i++) {
            int[] arr = {6, 2, 8, 4, 5, 1, 7, 3, 9, 0};
            sort(arr);
        }

        System.out.println(Duration.between(begin, LocalDateTime.now()).toMillis());
    }

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            boolean move = false;
            int temp = arr[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; j--) {
                // 数据移动
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                    move = true;
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;
            if (!move) {
                break;
            }
        }
    }
}
