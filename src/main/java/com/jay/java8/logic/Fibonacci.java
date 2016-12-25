package com.jay.java8.logic;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fibo(6));

        // Java 8
        // Limit 5
        Stream.iterate(new long[]{1, 1}, f -> new long[] {f[1], f[0] + f[1]}).mapToLong(value -> value[0]).limit(5).forEach(value -> System.out.println(value));

        // nth number
        System.out.println(Stream.iterate(new long[]{1, 1}, f -> new long[] {f[1], f[0] + f[1]}).mapToLong(value -> value[0]).limit(5).max().getAsLong());
    }

    private static int fibo(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n > 1) {
            return fibo(n - 1) + fibo(n - 2);
        }
        return -1;
    }
}
