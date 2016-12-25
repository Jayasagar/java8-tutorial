package com.jay.java8.codility;

public class MissingElement {
    public static void main(String[] args) {

    }

    public static int solution(int[] A) {
        // write your code in Java SE 8

        if (A == null || A.length == 0) {
            return -1;
        }
        int length = A.length;
        int sum = 0;
        for (int i = 0 ; i < length ; i++) {
            sum = sum + A[i];
        }

        return (length * length + 1) / 2 - sum;
    }
}
