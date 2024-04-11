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

    public List<Problem> initializeKnowledgeBase() {
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

    public String diagnose(
            boolean isLaptop,
            boolean isDesktop,
            boolean haveHardwareProblem,
            boolean haveSoftwareProblem,
            boolean haveNetworkProblem,
            String parentSymptomName,
            String subSymptomName
    ) {
        String solution = "";

        if (isLaptop) {
            if (haveHardwareProblem) {
                if (parentSymptomName.equals("Computer On Screen Off") && subSymptomName.equals("RAM not working")) {
                    solution = "Reseat the RAM: Power off the laptop, unplug it from the power source, and remove the battery (if removable). Open the back panel and locate the RAM modules. Carefully remove them and blow any dust from the connectors. Reinsert the RAM modules firmly, ensuring they are properly seated. Close the back panel, reconnect the battery and power supply, and turn on the laptop.\n" +
                            "\n" +
                            "Test with One RAM Module (if applicable): If the laptop has multiple RAM modules, try removing all but one and powering on the laptop. If it boots up successfully, replace the removed RAM module with another one and test again. This can help identify a faulty RAM module.\n" +
                            "\n" +
                            "Run Hardware Diagnostics: Most laptops come with built-in hardware diagnostic tools. Consult your laptop's manual to access these tools and run a memory diagnostic test. This can provide further confirmation if the RAM is malfunctioning.\n" +
                            "\n" +
                            "Consider Replacing the RAM: If reseating and testing with individual modules don't resolve the issue, the RAM might be damaged beyond repair. You'll likely need to replace the RAM with a compatible module based on your laptop's specifications. Consult your laptop's manual or manufacturer's website for guidance on compatible RAM types and upgrade procedures.";
                } else if (parentSymptomName.equals("Computer Off Screen Off") && subSymptomName.equals("Battery issue (Laptop)")) {
                    solution = "Check Battery Connection: Ensure that the battery is properly connected to the laptop. Turn off the laptop, unplug it, and remove the battery. Clean the battery contacts and reinsert the battery securely. Plug in the laptop and try turning it on again.\n" +
                            "\n" +
                            "Test with AC Adapter Only: Remove the battery and try powering on the laptop with just the AC adapter connected. If it boots up, the battery might need replacement.\n" +
                            "\n" +
                            "Replace the Battery: If the laptop still doesn't power on after these steps, consider replacing the battery with a new one compatible with your laptop model.";
                }
            } else if (haveSoftwareProblem) {
                if (parentSymptomName.equals("Computer On Screen Error") && subSymptomName.equals("Boot MGR Missing")) {
                    solution = "Perform Startup Repair: Use your Windows installation disc or recovery drive to access the 'Startup Repair' tool. Follow the on-screen instructions to repair the Boot Manager.\n" +
                            "\n" +
                            "Check Boot Sequence: Access the BIOS settings during startup (usually by pressing a specific key like F2 or Del). Ensure that the correct boot device (usually the hard drive with the operating system) is set as the first boot priority.";
                }
            } else if (haveNetworkProblem) {
                // Add network-related solutions for laptops
            }
        } else if (isDesktop) {
            if (haveHardwareProblem) {
                if (parentSymptomName.equals("Computer On Screen Off") && subSymptomName.equals("VGA cable issue (Desktop)")) {
                    solution = "Check VGA Cable Connection: Ensure that the VGA cable is securely connected to both the desktop and the monitor. Disconnect and reconnect the VGA cable at both ends to ensure a proper connection.\n" +
                            "\n" +
                            "Test with Different Cable: If available, try using a different VGA cable to rule out cable damage.\n" +
                            "\n" +
                            "Inspect Graphics Card: Open the desktop case and inspect the graphics card. Ensure it's properly seated in the PCI slot and there are no visible signs of damage. Reseat the graphics card if necessary.";
                } else if (parentSymptomName.equals("Computer Off Screen Off") && subSymptomName.equals("Cable connection issue (Desktop)")) {
                    solution = "Check Internal Connections: Open the desktop case and inspect all internal cable connections. Ensure that all power supply cables, SATA cables, and other internal connections are securely connected.\n" +
                            "\n" +
                            "Test with Minimal Configuration: Disconnect all unnecessary peripherals (like optical drives, extra hard drives) and try booting the desktop with only essential components (CPU, RAM, motherboard, power supply). This can help identify if the issue is caused by a specific component or connection.";
                }
            } else if (haveSoftwareProblem) {
                if (parentSymptomName.equals("Computer On Screen Error") && subSymptomName.equals("Windows Failed to start")) {
                    solution = "Use System Restore: Boot into the 'Advanced Startup Options' (accessible by repeatedly pressing F8 during startup) and choose 'Repair Your Computer'. From there, select 'System Restore' to roll back to a previous working state.\n" +
                            "\n" +
                            "Check Disk for Errors: Use the Windows 'Check Disk' utility (chkdsk) to scan and fix errors on your hard drive. Open Command Prompt as administrator and run 'chkdsk /f /r'.";
                }
            } else if (haveNetworkProblem) {
                // Add network-related solutions for desktops
            }
        }

        return solution;
    }



}
