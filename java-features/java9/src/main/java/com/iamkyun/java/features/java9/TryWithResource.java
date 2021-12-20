package com.iamkyun.java.features.java9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class TryWithResource {
    public static void main(String[] args) throws IOException {
        Reader inputString = new StringReader("message");
        BufferedReader br = new BufferedReader(inputString);
        try (br) {
            //do something
        }
    }
}
