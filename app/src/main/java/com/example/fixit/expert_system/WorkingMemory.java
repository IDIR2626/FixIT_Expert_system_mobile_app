package com.example.fixit.expert_system;

import java.util.HashMap;

public class WorkingMemory {

    private HashMap<String, Object> facts;

    public WorkingMemory() {
        facts = new HashMap<>();
    }

    public void addFact(String factName, Object factValue) {
        facts.put(factName, factValue);
    }

    public Object getFact(String factName) {
        return facts.get(factName);
    }

    public boolean hasFact(String factName) {
        return facts.containsKey(factName);
    }
}
