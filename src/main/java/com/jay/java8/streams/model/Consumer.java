package com.jay.java8.streams.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @Builder @ToString(of = {"name", "city"})
public class Consumer {
    private String name;
    private String city;
    private int age;
    private List<Thing> things;
}
