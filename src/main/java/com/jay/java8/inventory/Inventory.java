package com.jay.java8.inventory;

import com.jay.java8.model.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Inventory {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple("Swis", "red", 123, 150));
        apples.add(new Apple("Smila", "brown", 150, 120));
        apples.add(new Apple("Himalaya", "grren", 175, 200));

        Stream<Apple> appleStream = apples.stream().filter(apple -> apple.getWeight() >= 150);

        System.out.print(appleStream.toArray());
    }
}
