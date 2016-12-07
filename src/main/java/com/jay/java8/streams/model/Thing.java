package com.jay.java8.streams.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter @Getter @Builder @ToString(of = {"name"})
public class Thing {
    private String id;
    private String name;
    private boolean isRunning;
    private Type type;
    private List<Event> events;
    private List<Property> properties;
    private int cost;
}

