package com.example.java.library;

import java.util.Random;

public class Computer {

    private Random random;

    public Computer() {
        random = new Random();
    }

    public Integer compute() {
        return random.nextInt(100);
    }
}
