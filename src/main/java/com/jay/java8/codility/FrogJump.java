package com.jay.java8.codility;

public class FrogJump {
    public static void main(String[] args) {

    }

    public static int solution(int X, int Y, int D) {
        // write your code in Java SE 8
        if (X > Y || D < 1) {
            return -1;
        }
        if (X == Y) {
            return 0;
        }
        return (int)Math.ceil((double)(Y-X)/D);
//        int jumps = 0;
//        while (X < Y) {
//            X = X + D;
//            jumps++;
//        }
//        return jumps;
    };
}
