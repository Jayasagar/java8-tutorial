package com.jay.java8.explore;

import com.jay.java8.model.User;

import java.util.Arrays;
import java.util.List;

public class JavaStreams {
    public static void main(String[] args) {

        List<User> users = Arrays.asList(
                new User("Bob", "Prof", "Hyd"),
                new User("March", "Uncle", "Berlin"),
                new User("Tele", "Sir", "Hyd"));

        // Get users by city
        users.stream().filter(user -> "Hyd".equals(user.getCity())).forEach(user -> System.out.println(user.getFName()));

        // Users by comma seperated
        users.stream()
                .filter(user -> "Hyd".equals(user.getCity()))
                .map(user -> user.getFName() + " " + user.getLName())
                .reduce((prevUser, currentUser) -> prevUser + "," + currentUser)
                .ifPresent(System.out::println);
    }
}
