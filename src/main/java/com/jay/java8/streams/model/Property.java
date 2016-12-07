package com.jay.java8.streams.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @Builder @ToString
public class Property {
    private String name;
    private String value;
}
