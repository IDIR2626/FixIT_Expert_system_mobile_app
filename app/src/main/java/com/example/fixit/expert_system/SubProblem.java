package com.example.fixit.expert_system;

import java.util.List;

public class SubProblem {
    private String description;
    private List<Fact> conditions;

    public SubProblem(String description, List<Fact> conditions) {
        this.description = description;
        this.conditions = conditions;
    }

    public String getDescription() {
        return description;
    }

    public List<Fact> getConditions() {
        return conditions;
    }
}
