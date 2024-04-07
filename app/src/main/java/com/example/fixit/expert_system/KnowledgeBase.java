package com.example.fixit.expert_system;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeBase {
    private List<Rule> rules;

    public KnowledgeBase() {
        rules = new ArrayList<>();
        // Initialize the list of rules with the rules (using the Rule constructor)
    }

    public String diagnoseProblem(WorkingMemory workingMemory) {
        // Implement forward chaining logic here
        for (Rule rule : rules) {
            if (allConditionsMet(rule, workingMemory)) {
                return rule.getSolution();
            }
        }
        return "**Diagnosis:** We couldn't diagnose the problem yet. Consider providing more information.";
    }

    private boolean allConditionsMet(Rule rule, WorkingMemory workingMemory) {
        // Check if all conditions (facts) in the rule are met based on the working memory
        for (Fact fact : rule.getConditions()) {
            // Implement logic to check if the fact exists in working memory with the same value
            if (!workingMemory.hasFact(fact.getName()) || !workingMemory.getFact(fact.getName()).equals(fact.getValue())) {
                return false; // A condition is not met, move on to the next rule
            }
        }
        return true; // All conditions are met for this rule
    }

}
