package com.jay.java8.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private String fName;
    private String lName;
    private String city;

    public User(String fName, String lName, String city) {
        this.fName = fName;
        this.lName = lName;
        this.city = city;
    }
}
