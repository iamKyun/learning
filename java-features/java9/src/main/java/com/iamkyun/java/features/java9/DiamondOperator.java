package com.iamkyun.java.features.java9;

class FooClass<T> {
    public FooClass(T i) {

    }
}

public class DiamondOperator {
    public static void main(String[] args) {
        FooClass<Integer> fc = new FooClass<>(1) { // anonymous inner class
        };

        FooClass<? extends Integer> fc0 = new FooClass<>(1) {
            // anonymous inner class
        };

        FooClass<?> fc1 = new FooClass<>(1) { // anonymous inner class
        };
    }
}
