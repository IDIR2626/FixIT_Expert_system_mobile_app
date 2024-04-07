package com.example.fixit.expert_system;

import java.util.List;

public class Problem {
    private String name;
    private List<SubProblem> subProblems;

    public Problem(String name, List<SubProblem> subProblems) {
        this.name = name;
        this.subProblems = subProblems;
    }

    public String getName() {
        return name;
    }

    public List<SubProblem> getSubProblems() {
        return subProblems;
    }
}
