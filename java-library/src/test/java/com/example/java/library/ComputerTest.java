package com.example.java.library;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ComputerTest {

    private Computer computer;

    @Before
    public void setUp() {
        computer = new Computer();
    }

    @Test
    public void compute_should_not_return_same_result_twice() {
        assertFalse(computer.compute().equals(computer.compute()));
    }
}