package com.iamkyun.algorithm;

import org.apache.commons.lang3.StringUtils;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {6, 2, 8, 4, 5, 1, 7, 3, 9, 0};
        sort(arr);

    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            System.out.println(StringUtils.join(arr, ' '));
        }
    }
}
