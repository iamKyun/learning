package com.iamkyun.java.features.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapAndFlatMap {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("1", "2", "3", "4", "5");

        List<List<String>> embeddedStringList = Arrays.asList(
                Arrays.asList("11", "12", "13", "14", "15"),
                Arrays.asList("21", "22", "23", "24", "25"),
                Arrays.asList("31", "32", "33", "34", "35"),
                Arrays.asList("41", "42", "43", "44", "45"),
                Arrays.asList("51", "52", "53", "54", "55"));

        // to number
        List<Integer> numberList = stringList.stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println(numberList);
        List<List<Integer>> embeddedNumberList =
                embeddedStringList.stream().map(l -> l.stream().map(Integer::valueOf).collect(Collectors.toList()))
                                  .collect(Collectors.toList());
        System.out.println(embeddedNumberList);
        List<Integer> flattedNumberList =
                embeddedStringList.stream().flatMap(l -> l.stream().map(Integer::valueOf)).collect(Collectors.toList());
        System.out.println(flattedNumberList);

    }
}
