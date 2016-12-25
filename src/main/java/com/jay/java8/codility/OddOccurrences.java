package com.jay.java8.codility;

import java.util.Arrays;
import java.util.stream.IntStream;

public class OddOccurrences {
    public static void main(String[] args) {
        int[] list = new int[]{1, 2, 3, 2, 1};
        IntStream intStream = IntStream.of(list);

        int[] toArray = intStream.sorted().toArray();

        System.out.println(toArray.length);
        for (int i = 0; i < toArray.length; i++) {
            System.out.println(toArray[i]);
        }

        int oddElement = -1;
        for (int i = 0; i < toArray.length; i++) {
            System.out.println("sorted:" + toArray[i]);
            if (i+1!=toArray.length && toArray[i] != toArray[i + 1]) {
                oddElement = toArray[i];
                break;
            }
            i++;
        }

        if (oddElement == -1 && toArray.length%2 == 1) {
            oddElement = toArray[toArray.length - 1];
        }
        System.out.println(oddElement);
    }
}
