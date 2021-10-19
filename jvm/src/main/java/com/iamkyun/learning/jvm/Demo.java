package com.iamkyun.learning.jvm;

public class Demo {
    public final static int _1M = 1024 * 1024;


    /**
     * -XX:NewSize=5242880
     * -XX:MaxNewSize=5242880
     * -XX:InitialHeapSize=10485760
     * -XX:MaxHeapSize=10485760
     * -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=10485760
     * -XX:+UseParNewGC
     * -XX:+UseConcMarkSweepGC
     * -XX:+PrintGCDetails
     * -XX:+PrintGCTimeStamps
     * -Xloggc:gc.log
     */
    public static void main(String[] args) {
        byte[] array1 = new byte[_1M];
        byte[] array2 = new byte[_1M];
        byte[] array3 = new byte[_1M];

        byte[] array4 = new byte[4 * _1M];
        System.out.println(array1);
        System.out.println(array2);
        System.out.println(array3);
        System.out.println(array4);
    }
}
