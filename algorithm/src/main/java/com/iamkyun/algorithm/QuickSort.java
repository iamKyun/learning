package com.iamkyun.algorithm;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {6, 2, 8, 4, 5, 1, 7, 3, 9, 0};
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }
        int low = left;
        int high = right;
        // 选择首个元素作为pivot
        int pivot = arr[left];
        printArr(arr, left, right, low, high, "started ! pivot :" + pivot + " , low:" + low + ", high:" + high);
        while (low < high) {
            //先看右边，依次往左递减
            while (pivot <= arr[high] && low < high) {
                high--;
                printArr(arr, left, right, low, high, "right moved");
            }
            if (pivot > arr[high] && low < high) {
                printArr(arr, left, right, low, high,
                        "right FOUND! arr[" + high + "](" + arr[high] + ") < " + "pivot(" + pivot + ")");
            }

            //再看左边，依次往右递增
            while (pivot >= arr[low] && low < high) {
                low++;
                printArr(arr, left, right, low, high, "left moved");
            }

            if (pivot < arr[low] && low < high) {
                printArr(arr, left, right, low, high,
                        "left FOUND! arr[" + low + "](" + arr[low] + ") > " + "pivot(" + pivot + ")");
            }
            //如果满足条件则交换
            if (low < high) {
                swap(arr, low, high);
                printArr(arr, left, right, low, high, "left right swapped");
            } else {
                printArr(arr, left, right, low, high, "left right met! DONE !");
            }
        }
        printArr(arr, left, right, low, high, "last step !");
        //最后将基准为与i和j相等位置的数字交换
        arr[left] = arr[low];
        arr[low] = pivot;
        printArr(arr, left, right, low, high, "ALL DONE !");

        if (high > 0 && high < right) {
            //递归调用左半数组
            sort(arr, left, high - 1);
            //递归调用右半数组
            sort(arr, high + 1, right);
        }
    }

    private static void printArr(int[] arr, int low, int high, int left, int right, String msg) {
        for (int i = 0; i < arr.length; i++) {
            if (i == low) {
                System.out.print("|[" + arr[i] + "]  ");
            } else if (i == high) {
                System.out.print(arr[i] + "|  ");
            } else {
                System.out.print(arr[i] + "   ");
            }
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            if (i == left && i == right) {
                System.out.print(" (lr)");
            } else if (i == left) {
                System.out.print(" (l)");
            } else if (i == right) {
                System.out.print(" (r)");
            } else {
                System.out.print("    ");
            }
        }
        System.out.print("       => " + msg + "\n\n");
    }

    private static void swap(int[] arr, int low, int high) {
        int temp = arr[high];
        arr[high] = arr[low];
        arr[low] = temp;
    }


}
