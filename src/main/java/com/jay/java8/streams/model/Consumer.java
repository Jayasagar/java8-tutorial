package com.jay.java8.streams.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Consumer {
    private String name;
    private String city;
    private int age;
    private List<Thing> things;

}
