package com.iamkyun.algorithm;

import java.time.Duration;
import java.time.LocalDateTime;

public class ShellSort {

    public static void main(String[] args) {
        LocalDateTime begin = LocalDateTime.now();

        for (int i = 0; i < 1000000; i++) {
            int[] arr = {6, 2, 8, 4, 5, 1, 7, 3, 9, 0};
            sort(arr);
        }

        System.out.println(Duration.between(begin, LocalDateTime.now()).toMillis());

    }

    public static void sort(int[] arr) {
        for (int gap = arr.length; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];

                int j;

                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }

                arr[j] = temp;

            }
        }
    }

}
