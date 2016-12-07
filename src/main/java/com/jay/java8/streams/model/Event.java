package com.jay.java8.streams.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @Builder @ToString(of = {"name"})
public class Event {
    private String name;
    private boolean isActive;
}
