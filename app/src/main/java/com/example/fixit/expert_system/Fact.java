package com.example.fixit.expert_system;

public class Fact {
    private String name;
    private Object value;

    public Fact(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }


}
