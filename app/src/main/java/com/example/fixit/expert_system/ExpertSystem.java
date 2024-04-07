package com.example.fixit.expert_system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpertSystem {
    List<Problem> problems;
    WorkingMemory workingMemory;

    public ExpertSystem() {
        problems = initializeKnowledgeBase();
        workingMemory = new WorkingMemory();
    }

    private List<Problem> initializeKnowledgeBase() {
        List<Problem> problems = new ArrayList<>();

        // Problem 1 (Computer On Screen Off)
        List<SubProblem> subProblems1 = new ArrayList<>();
        subProblems1.add(new SubProblem("RAM not working", Arrays.asList(new Fact("isRamFunctional", false))));
        subProblems1.add(new SubProblem("VGA cable issue (Desktop)", Arrays.asList(new Fact("isDesktop", true), new Fact("isVgaCableFunctional", false))));
        subProblems1.add(new SubProblem("Power cable issue (Desktop)", Arrays.asList(new Fact("isDesktop", true), new Fact("isPowerCableFunctional", false))));
        subProblems1.add(new SubProblem("Monitor cable issue (Laptop)", Arrays.asList(new Fact("isLaptop", true), new Fact("isMonitorCableFunctional", false))));
        subProblems1.add(new SubProblem("Graphic card not working", Arrays.asList(new Fact("isGraphicCardFunctional", false))));
        subProblems1.add(new SubProblem("Monitor itself damaged", Arrays.asList(new Fact("isMonitorDamaged", true))));
        problems.add(new Problem("Computer On Screen Off", subProblems1));

        // Problem 2 (Computer On Screen Error)
        List<SubProblem> subProblems2 = new ArrayList<>();
        subProblems2.add(new SubProblem("Boot MGR Missing", Arrays.asList(new Fact("isBootMgrMissing", true))));
        subProblems2.add(new SubProblem("Boot Device Not Found", Arrays.asList(new Fact("isBootDeviceNotFound", true))));
        subProblems2.add(new SubProblem("Windows Failed to start", Arrays.asList(new Fact("isWindowsFailedToStart", true))));
        subProblems2.add(new SubProblem("Missing system file (\\system32\\hal.dll)", Arrays.asList(new Fact("isMissingSystemFile", true), new Fact("missingSystemFile", "\\system32\\hal.dll"))));
        subProblems2.add(new SubProblem("Missing system file (\\Windows\\System32\\Config\\System)", Arrays.asList(new Fact("isMissingSystemFile", true), new Fact("missingSystemFile", "\\Windows\\System32\\Config\\System"))));
        problems.add(new Problem("Computer On Screen Error", subProblems2));


        // Problem 3 (Computer Off Screen Off)
        List<SubProblem> subProblems3 = new ArrayList<>();
        subProblems3.add(new SubProblem("Battery issue (Laptop)", Arrays.asList(new Fact("isLaptop", true), new Fact("isBatteryFaulty", true))));
        subProblems3.add(new SubProblem("Cable connection issue (Desktop)", Arrays.asList(new Fact("isDesktop", true), new Fact("isCableConnectionFaulty", true))));
        subProblems3.add(new SubProblem("Power supply issue (Desktop)", Arrays.asList(new Fact("isDesktop", true), new Fact("isPowerSupplyFaulty", true))));
        subProblems3.add(new SubProblem("Power adapter issue (Laptop)", Arrays.asList(new Fact("isLaptop", true), new Fact("isPowerAdapterFaulty", true))));
        subProblems3.add(new SubProblem("Power button issue", Arrays.asList(new Fact("isPowerButtonFaulty", true))));
        problems.add(new Problem("Computer Off Screen Off", subProblems3));

        // Problem 4 (Beeps)
        List<SubProblem> subProblems4 = new ArrayList<>();
        subProblems4.add(new SubProblem("AMI: 1 short (DRAM refresh failure)", Arrays.asList(new Fact("isBiosBeepCode", 1), new Fact("isBiosVendor", "AMI"))));
        subProblems4.add(new SubProblem("AMI: 2 short (Parity circuit failure)", Arrays.asList(new Fact("isBiosBeepCode", 2), new Fact("isBiosVendor", "AMI"))));
        subProblems4.add(new SubProblem("AMI: 3 short (Base 64K RAM failure)", Arrays.asList(new Fact("isBiosBeepCode", 3), new Fact("isBiosVendor", "AMI"))));
        subProblems4.add(new SubProblem("AMI: 4 short (System timer failure)", Arrays.asList(new Fact("isBiosBeepCode", 4), new Fact("isBiosVendor", "AMI"))));
        subProblems4.add(new SubProblem("AMI: 5 short (Process failure)", Arrays.asList(new Fact("isBiosBeepCode", 5), new Fact("isBiosVendor", "AMI"))));
        problems.add(new Problem("Beeps", subProblems4));

        return problems;
    }

    public String diagnoseProblem(String symptomName, Object symptomValue) {
        workingMemory.addFact(symptomName, symptomValue); // Update working memory with user selection

        for (Problem problem : problems) {
            for (SubProblem subProblem : problem.getSubProblems()) {
                boolean allConditionsMet = true;
                for (Fact condition : subProblem.getConditions()) {
                    if (!workingMemory.hasFact(condition.getName()) || !workingMemory.getFact(condition.getName()).equals(condition.getValue())) {
                        allConditionsMet = false;
                        break;
                    }
                }
                if (allConditionsMet) {
                    return subProblem.getDescription(); // Diagnosis found, return sub-problem description
                }
            }
        }

        return "**Diagnosis:** We couldn't diagnose the problem yet. Consider providing more information.";
    }
}
