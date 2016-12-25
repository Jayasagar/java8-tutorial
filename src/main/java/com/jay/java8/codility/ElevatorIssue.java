package com.jay.java8.codility;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ElevatorIssue {
    public static void main(String[] args) {
        System.out.println("output");
        int[] A = new int[]{1, 2, 3, 4, 5};
        int[] B = new int[]{1, 1, 1, 1, 1};

        System.out.println("count:" + solution(A, B, 1, 1, 1));
    }

    public static int solution(int[] personWeights, int[] personTargetFloor, int floors, int capacity, int weightLimit) {
        // write your code in Java SE 8

        int totalCount = 0;
        int queuePosition = 0;
        boolean singlePersonWeightCondition = false;

        if (personWeights.length != personTargetFloor.length) {
            return -1;
        }
        while(true) {
            int currentFloor = 0;
            int totalWeight = 0;
            // Run Elevator
            for (int i = 0; i < capacity && queuePosition<personWeights.length; i++) {
                if (personWeights[queuePosition] > weightLimit) {
                    singlePersonWeightCondition = true;
                    break;
                }
                totalWeight = totalWeight + personWeights[queuePosition];
                if (totalWeight>weightLimit) {
                    break;
                }

                if (currentFloor != personTargetFloor[queuePosition] && totalWeight <= weightLimit) {
                    totalCount++;
                }

                currentFloor = personTargetFloor[queuePosition];
                queuePosition++;
            }

            // Ground floor
            totalCount++;
            if (singlePersonWeightCondition || queuePosition >= personWeights.length) {
                break;
            }
            // Reset
            totalWeight = 0;
            currentFloor = 0;
        }

        if (singlePersonWeightCondition) {
            return -1;
        }
        return totalCount;
    }
}
