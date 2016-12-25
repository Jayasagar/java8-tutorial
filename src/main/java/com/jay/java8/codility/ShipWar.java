package com.jay.java8.codility;

import java.util.*;
import java.util.stream.IntStream;

public class ShipWar {
    public static void main(String[] args) {
        System.out.println("output");

        //System.out.println(solution(3, "1A 1B,2C 2C", "1B"));
        System.out.println(solution(4, "1B 2C,2D 4D", "2B 2D 3D 4D 4A"));
    }

    public static String solution(int N, String S, String T) {
        // write your code in Java SE 8

        //
        Map<Character, Integer> cellNumber = new HashMap<>();
        cellNumber.put('A', 1);
        cellNumber.put('B', 2);
        cellNumber.put('C', 3);
        cellNumber.put('D', 4);
        cellNumber.put('E', 5);

        int[][] board = new int[N][N];

        String[] shipsPair = S.split(",");


        String[] hits = T.split(" ");

        int sunksCount = 0;
        int notSunkCount = 0;

        for (int i = 0; i< shipsPair.length;i++) {
            String[] shipCells = shipsPair[i].split(" ");
            int topLeft = Character.getNumericValue(shipCells[0].charAt(0));
            char topLeftChar = shipCells[0].charAt(1);
            int bottomRight = Character.getNumericValue(shipCells[1].charAt(0));
            char bottomRightChar = shipCells[1].charAt(1);

            Set<String> allShipCells = new HashSet<>();
            for (int k = topLeft; k <= bottomRight; k++) {
                allShipCells.add("" + k+topLeftChar);
                allShipCells.add("" + k+bottomRightChar);
            }

            boolean allMatch = false;
            int matchCount = 0;

            for(int hit = 0; hit < hits.length; hit++) {
                // Check whether all hits for given Ship ?
                if (allShipCells.contains(hits[hit])) {
                    matchCount++;
                }
            }

            if (matchCount == shipCells.length) {
                sunksCount++;
            } else if(matchCount>0){
                notSunkCount++;
            }

        }

        return String.format("%d,%d", sunksCount, notSunkCount);
    }

    class Ship {
        int hits = 0;
        List<Integer> positions = Arrays.asList();

       public boolean isSunk() {
           return hits == positions.size();
       }

        public void markPositin() {

        }
        public void addPosition()  {

        }
    }
}
