package com.iamkyun.java.features.java9;

interface InterfaceWithPrivateMethods {
    private static String staticPrivate() {
        return "static private";
    }

    private String instancePrivate() {
        return "instance private";
    }

    default void check() {
        String result = staticPrivate();
        InterfaceWithPrivateMethods pvt = new InterfaceWithPrivateMethods() {
            // anonymous class
        };
        result = pvt.instancePrivate();
    }
}
