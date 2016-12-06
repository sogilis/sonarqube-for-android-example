package com.sogilis.example.android.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultSerializerTest {

    @Test
    public void test() {
        assertEquals("2", ResultSerializer.serialize(2));
    }

}