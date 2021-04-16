package com.iamkyun.learning.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StreamThreadSaveOperationExample {

    public static void main(String[] args) {
        // non thread safe
        List<Integer> numbers = new ArrayList<>();
        // thread sage
        // List<Integer> numbers = new CopyOnWriteArrayList<>();

        List<List<Integer>> numbersList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            List<Integer> partOfNumbers = new ArrayList<>();
            int begin = i * 100;
            for (int j = begin; j < begin + 1000; j++) {
                partOfNumbers.add(j);
            }
            numbersList.add(partOfNumbers);
        }

        // numbersList.parallelStream().forEach(numbers::addAll);
        numbers = numbersList.parallelStream().flatMap(Collection::stream).collect(Collectors.toList());

        System.out.println(numbers.size());
    }

}
