package com.cdutcm.party.utils;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CodeUtilsTest {
    @Test
    public void getNumberCode() {
        System.out.println("Result = " + new Random().nextInt(10));
    }

    @Test
    public void test() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int x = 48 + random.nextInt(62);
            System.out.println((char) (x + (x / 58) * 7 + (x / 84) * 6));
        }
    }
}