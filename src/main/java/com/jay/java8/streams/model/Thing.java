package com.jay.java8.streams.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class Thing {
    private String id;
    private String name;
    private boolean isRunning;
    private Type type;
    private List<Event> events;
    private int cost;

}

