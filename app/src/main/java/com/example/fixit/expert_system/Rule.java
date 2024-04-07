package com.example.fixit.expert_system;

import java.util.List;

public class Rule {
    private String name;
    private List<Fact> conditions; // List of facts (primitive variables or Fact objects) for the IF part
    private String solution; // The solution or diagnosis message for the THEN part

    public Rule(String name, List<Fact> conditions, String solution) {
        this.name = name;
        this.conditions = conditions;
        this.solution = solution;
    }

    public String getName() {
        return name;
    }

    public List<Fact> getConditions() {
        return conditions;
    }

    public String getSolution() {
        return solution;
    }
}
