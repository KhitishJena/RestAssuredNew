package com.satish.Serialization;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Employee {

    private int id;
    private String name;
    private String role;
    private Address address;
    private List<String> skills;

}
