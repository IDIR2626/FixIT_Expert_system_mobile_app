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
                // Laptop hardware problems
                if (parentSymptomName.equals("Computer On Screen Off")) {
                    if (subSymptomName.equals("RAM not working")) {
                        solution = "Reseat the RAM: Power off the laptop, unplug it from the power source, and remove the battery (if removable). Open the back panel and locate the RAM modules. Carefully remove them and blow any dust from the connectors. Reinsert the RAM modules firmly, ensuring they are properly seated. Close the back panel, reconnect the battery and power supply, and turn on the laptop.\n" +
                                "\n" +
                                "Test with One RAM Module (if applicable): If the laptop has multiple RAM modules, try removing all but one and powering on the laptop. If it boots up successfully, replace the removed RAM module with another one and test again. This can help identify a faulty RAM module.\n" +
                                "\n" +
                                "Run Hardware Diagnostics: Most laptops come with built-in hardware diagnostic tools. Consult your laptop's manual to access these tools and run a memory diagnostic test. This can provide further confirmation if the RAM is malfunctioning.\n" +
                                "\n" +
                                "Consider Replacing the RAM: If reseating and testing with individual modules don't resolve the issue, the RAM might be damaged beyond repair. You'll likely need to replace the RAM with a compatible module based on your laptop's specifications. Consult your laptop's manual or manufacturer's website for guidance on compatible RAM types and upgrade procedures.";
                    } else if (subSymptomName.equals("VGA cable issue (Desktop)")) {
                        solution = "Could you please review your selection again? It appears that there may be an issue with the computer type you've chosen. This problem seems to be related to desktop computers, but you have selected a laptop instead.";

                    } else if (subSymptomName.equals("Power cable issue (Desktop)")) {
                        solution = "Could you please review your selection again? It appears that there may be an issue with the computer type you've chosen. This problem seems to be related to desktop computers, but you have selected a laptop instead.";
                    } else if (subSymptomName.equals("Monitor cable issue (Laptop)")) {
                        solution = "The monitor cable connecting your laptop to the screen might be loose, damaged, or faulty. Here's how to troubleshoot:\n" +
                                "\n" +
                                "1. Check Connection: Ensure the monitor cable is securely plugged into both the laptop and the screen. Unplug and replug the cable at both ends to ensure a proper connection.\n" +
                                "\n" +
                                "2. Inspect for Damage: Look for any visible signs of damage on the cable, such as fraying, cuts, or bends. A damaged cable might need replacement.\n" +
                                "\n" +
                                "3. Try External Monitor (if possible): If available, connect an external monitor to your laptop using a different cable (e.g., HDMI). If the external monitor displays correctly, it suggests a problem with the laptop's internal monitor cable.\n" +
                                "\n" +
                                "4. Consult a Technician: If the above steps don't resolve the issue, consider seeking help from a qualified technician who can diagnose the problem further and potentially replace the monitor cable if necessary.";
                        
                    } else if (subSymptomName.equals("Graphic card not working")) {
                        
                    } else if (subSymptomName.equals("Battery issue (Laptop)")) {
                        solution = "Check Battery Connection: Ensure that the battery is properly connected to the laptop. Turn off the laptop, unplug it, and remove the battery. Clean the battery contacts and reinsert the battery securely. Plug in the laptop and try turning it on again.\n" +
                                "\n" +
                                "Test with AC Adapter Only: Remove the battery and try powering on the laptop with just the AC adapter connected. If it boots up, the battery might need replacement.\n" +
                                "\n" +
                                "Replace the Battery: If the laptop still doesn't power on after these steps, consider replacing the battery with a new one compatible with your laptop model.";
                    }
                } else if (parentSymptomName.equals("Computer Off Screen Off")) {
                    if (subSymptomName.equals("Power adapter issue (Laptop)")) {
                        solution = "Check Power Adapter: Ensure that the power adapter is functioning properly. Test the adapter using a multimeter to verify the correct voltage output.\n" +
                                "\n" +
                                "Replace the Power Adapter: If the power adapter is faulty or damaged, replace it with a compatible adapter based on your laptop's specifications.";
                    } else if (subSymptomName.equals("Battery issue (Laptop)")) {
                        solution = "Check Battery Connection: Ensure that the battery is properly connected to the laptop. Turn off the laptop, unplug it, and remove the battery. Clean the battery contacts and reinsert the battery securely. Plug in the laptop and try turning it on again.\n" +
                                "\n" +
                                "Test with AC Adapter Only: Remove the battery and try powering on the laptop with just the AC adapter connected. If it boots up, the battery might need replacement.\n" +
                                "\n" +
                                "Replace the Battery: If the laptop still doesn't power on after these steps, consider replacing the battery with a new one compatible with your laptop model.";
                    } else if (subSymptomName.equals("Cable connection issue (Desktop)")) {
                        solution = "Could you please review your selection again? It appears that there may be an issue with the computer type you've chosen. This problem seems to be related to desktop computers, but you have selected a laptop instead.";
                    } else if (subSymptomName.equals("Power supply issue (Desktop)")) {
                        solution = "Could you please review your selection again? It appears that there may be an issue with the computer type you've chosen. This problem seems to be related to desktop computers, but you have selected a laptop instead.";
                    }
                }
            } else if (haveSoftwareProblem) {
                // Laptop software problems
                if (parentSymptomName.equals("Computer On Screen Error")) {
                    if (subSymptomName.equals("Boot MGR Missing")) {
                        solution = "Perform Startup Repair: Use your Windows installation disc or recovery drive to access the 'Startup Repair' tool. Follow the on-screen instructions to repair the Boot Manager.\n" +
                                "\n" +
                                "Check Boot Sequence: Access the BIOS settings during startup (usually by pressing a specific key like F2 or Del). Ensure that the correct boot device (usually the hard drive with the operating system) is set as the first boot priority.";
                    }
                }
            } else if (haveNetworkProblem) {
                // Laptop network problems
                // Add network-related solutions for laptops
            }
        } else if (isDesktop) {
            if (haveHardwareProblem) {
                // Desktop hardware problems
                if (parentSymptomName.equals("Computer On Screen Off")) {
                    if (subSymptomName.equals("VGA cable issue (Desktop)")) {
                        solution = "Check VGA Cable Connection: Ensure that the VGA cable is securely connected to both the desktop and the monitor. Disconnect and reconnect the VGA cable at both ends to ensure a proper connection.\n" +
                                "\n" +
                                "Test with Different Cable: If available, try using a different VGA cable to rule out cable damage.\n" +
                                "\n" +
                                "Inspect Graphics Card: Open the desktop case and inspect the graphics card. Ensure it's properly seated in the PCI slot and there are no visible signs of damage. Reseat the graphics card if necessary.";
                    } else if (subSymptomName.equals("Power cable issue (Desktop)")) {
                        solution = "The power cable connecting your computer to the wall outlet might be loose, damaged, or faulty. This could lead to the computer turning on momentarily but then losing power and display.\n" +
                                "\n" +
                                "1. Check Power Cable Connection: Ensure the power cable is securely plugged into both the wall outlet and the back of your computer. Inspect the cable for any visible signs of damage like fraying or cuts.\n" +
                                "\n" +
                                "2. Try a Different Outlet: If possible, connect the computer to a different wall outlet to rule out an issue with the original outlet.\n" +
                                "\n" +
                                "3. Test with a Replacement Cable (if possible): If you have a spare compatible power cable, try using it to connect the computer to the wall outlet. If the computer functions normally with the replacement cable, it suggests an issue with the original cable.\n" +
                                "\n" +
                                "4. Consult a Technician: If the above steps don't resolve the problem, consider seeking help from a qualified computer technician. They can diagnose the issue further and potentially replace the power cable if necessary.";
                    } else if (subSymptomName.equals("Monitor cable issue (Laptop)")) {
                        solution = "Could you please review your selection again? It appears that there may be an issue with the computer type you've chosen. This problem seems to be related to laptop computers, but you have selected a desktop instead.";
                    }
                } else if (parentSymptomName.equals("Computer Off Screen Off")) {
                    if (subSymptomName.equals("Cable connection issue (Desktop)")){
                        solution = "There might be a loose or faulty cable connection within your desktop computer. Here's how to troubleshoot:\n" +
                                "\n" +
                                "1. Check Power Connections: Ensure the power cable is securely plugged into both the wall outlet and the back of your computer. Also, tighten any connections to power supply units (PSU) inside the case.\n" +
                                "\n" +
                                "2. Inspect Data and Display Cables: Open your computer case (if comfortable) and check for loose or disconnected data cables (e.g., SATA cables for storage drives) and display cables (e.g., HDMI, VGA) connecting components like the motherboard, graphics card, and storage drives. Securely re-plug any loose cables.\n" +
                                "\n" +
                                "3. Test with Minimal Setup (if possible): Disconnect any non-essential peripherals like extra monitors or external devices. This can help isolate if a faulty cable connected to an external device is causing the issue.\n" +
                                "\n" +
                                "4. Consult a Technician: If you're uncomfortable opening the case or the problem persists after checking connections, seek help from a qualified computer technician. They can diagnose the issue more thoroughly and identify the specific cable that needs replacement.";
                    } else if (subSymptomName.equals("Power supply issue (Desktop)")) {
                        solution = "The power supply unit (PSU), responsible for providing power to all components in your computer, might be malfunctioning. Here's some troubleshooting advice:\n" +
                                "\n" +
                                "1. Check Power Connections and Switch: Ensure the power cable is securely plugged into both the wall outlet and the back of your computer. Also, verify that the power supply unit's (PSU) on/off switch is turned on.\n" +
                                "\n" +
                                "2. Listen for Fan Noise: If the computer is plugged in but there are no fans spinning or lights turning on, it suggests a potential PSU issue.\n" +
                                "\n" +
                                "3. Test with a Replacement PSU (if possible): If you have a compatible spare PSU, try temporarily replacing the existing one to see if the computer powers on. **WARNING:** Ensure the replacement PSU is compatible with your computer's wattage requirements.\n" +
                                "**WARNING:** Opening a computer case and replacing components can be risky if not done carefully. Only attempt this step if you're comfortable with hardware troubleshooting.\n" +
                                "\n" +
                                "4. Consult a Technician: If you're uncomfortable troubleshooting the PSU yourself or the replacement test doesn't resolve the issue, it's strongly recommended to seek help from a qualified computer technician. They can diagnose the problem definitively and replace the PSU if necessary.";
                    } else if (subSymptomName.equals("Battery issue (Laptop)")) {
                        solution = "Could you please review your selection again? It appears that there may be an issue with the computer type you've chosen. This problem seems to be related to laptop computers, but you have selected a desktop instead.";
                    } else if (subSymptomName.equals("Power adapter issue (Laptop)")) {
                        solution = "Could you please review your selection again? It appears that there may be an issue with the computer type you've chosen. This problem seems to be related to laptop computers, but you have selected a desktop instead.";
                    }
                }
            }
            if (haveSoftwareProblem) {
                // Desktop software problems
                if (parentSymptomName.equals("Computer On Screen Error")) {
                    if (subSymptomName.equals("Windows Failed to start")) {
                        solution = "Use System Restore: Boot into the 'Advanced Startup Options' (accessible by repeatedly pressing F8 during startup) and choose 'Repair Your Computer'. From there, select 'System Restore' to roll back to a previous working state.\n" +
                                "\n" +
                                "Check Disk for Errors: Use the Windows 'Check Disk' utility (chkdsk) to scan and fix errors on your hard drive. Open Command Prompt as administrator and run 'chkdsk /f /r'.";
                    }
                }
            }
            if (haveNetworkProblem) {
                // Desktop network problems
                // Add network-related solutions for desktops
            }
        }

            if (haveHardwareProblem){
                if (parentSymptomName.equals("Computer On Screen Off")){
                    if (subSymptomName.equals("RAM not working")){
                        solution = "Reseat the RAM: Power off the laptop, unplug it from the power source, and remove the battery (if removable). Open the back panel and locate the RAM modules. Carefully remove them and blow any dust from the connectors. Reinsert the RAM modules firmly, ensuring they are properly seated. Close the back panel, reconnect the battery and power supply, and turn on the laptop.\n" +
                                "\n" +
                                "Test with One RAM Module (if applicable): If the laptop has multiple RAM modules, try removing all but one and powering on the laptop. If it boots up successfully, replace the removed RAM module with another one and test again. This can help identify a faulty RAM module.\n" +
                                "\n" +
                                "Run Hardware Diagnostics: Most laptops come with built-in hardware diagnostic tools. Consult your laptop's manual to access these tools and run a memory diagnostic test. This can provide further confirmation if the RAM is malfunctioning.\n" +
                                "\n" +
                                "Consider Replacing the RAM: If reseating and testing with individual modules don't resolve the issue, the RAM might be damaged beyond repair. You'll likely need to replace the RAM with a compatible module based on your laptop's specifications. Consult your laptop's manual or manufacturer's website for guidance on compatible RAM types and upgrade procedures.";
                    } else if (subSymptomName.equals("Graphic card not working")) {
                        solution = "The graphics card, responsible for displaying visuals on your computer, might be malfunctioning. Here are some troubleshooting steps:\n" +
                                "\n" +
                                "1. Update Drivers: Outdated or corrupted graphics card drivers can cause display issues. Update the drivers by visiting the website of your graphics card manufacturer (e.g., NVIDIA, AMD, Intel) and downloading the latest drivers compatible with your specific card model.\n" +
                                "\n" +
                                "2. Reseat the Card (Desktop): If you have a desktop computer, open the case and carefully remove the graphics card from its PCI-Express slot. Clean the contacts on the card and the slot with compressed air, and then firmly reseat the card in the slot.\n" +
                                "\n" +
                                "3. Test with Minimal Setup (if possible): Temporarily disconnect any non-essential hardware like extra monitors or external devices. This can help isolate if a conflicting device is causing the graphics card issue.\n" +
                                "\n" +
                                "4. Consider Replacing the Card: If the above steps don't resolve the problem, the graphics card itself might be faulty. Replacing the card with a compatible model might be necessary. Consult a technician or your computer's manual for guidance on compatible graphics cards and replacement procedures.\n" +
                                "\n" +
                                "5. Consult a Technician: If you're uncomfortable troubleshooting the hardware yourself, seek help from a qualified computer technician. They can diagnose the problem more thoroughly and recommend appropriate repairs or replacements.";

                    } else if (subSymptomName.equals("Monitor itself damaged")) {
                        solution = "The monitor itself might be damaged and require replacement. Here's what you can do:\n" +
                                "\n" +
                                "1. Inspect for Damage: Look for any visible signs of damage on the monitor, such as cracks, lines, flickering, or discoloration on the screen. Physical damage usually suggests a replacement is necessary.\n" +
                                "\n" +
                                "2. Try Different Inputs (if possible): If your monitor has multiple input ports (e.g., HDMI, VGA), try connecting a different device (e.g., another computer or gaming console) using a different cable. If the display works correctly with another device, it indicates an issue with the original computer's output or cable.\n" +
                                "\n" +
                                "3. Check Warranty: If your monitor is under warranty, contact the manufacturer for a replacement or repair.\n" +
                                "\n" +
                                "4. Consider Replacement: If the monitor is outside warranty or not repairable, you'll likely need to purchase a new monitor compatible with your computer.";
                    }
                } else if (parentSymptomName.equals("Computer Off Screen Off")) {
                    if (subSymptomName.equals("Power button issue")){
                        solution = "The power button, responsible for turning on your computer, might be malfunctioning. Here are some troubleshooting steps:\n" +
                                "\n" +
                                "1. Check for Obstructions (Especially Laptops): For laptops, ensure there's nothing blocking the power button, such as dust, debris, or a damaged hinge. Clean the area around the button with compressed air.\n" +
                                "\n" +
                                "2. Test with External Power Button (Desktops): Some desktop cases have a reset switch or a power button connected to the motherboard via a cable. Try using these buttons (if available) to power on the computer. If it turns on, the issue might be with the main power button on the case.\n" +
                                "\n" +
                                "3. Boot with a Screwdriver (Last Resort): This is an advanced step and should be done with caution. If the above steps fail and you're comfortable, briefly touch the two power button connector pins on the motherboard with a metallic screwdriver while the power supply is plugged in (but computer is off). If the computer turns on, it confirms a power button issue.\n" +
                                "**WARNING:** Performing this step incorrectly can damage your computer. It's recommended only if you're comfortable with hardware troubleshooting and at your own risk.\n" +
                                "\n" +
                                "4. Consult a Technician: If the troubleshooting steps don't resolve the problem, consider seeking help from a qualified computer technician. They can diagnose the power button issue further and potentially repair or replace the button or associated components.";
                    }
                }
            }

            if (haveSoftwareProblem){
                if (parentSymptomName.equals("Computer On Screen Error")){
                    if (subSymptomName.equals("Boot MGR Missing")){
                        solution = "The Boot Manager, a critical software component responsible for loading the operating system, might be missing on your computer. Here's how to attempt a repair:\n" +
                                "\n" +
                                "**Automatic Repair (Windows):**\n" +
                                "1. Insert your Windows installation media (USB drive or DVD). If you don't have it, you can create one using another computer and Microsoft's Media Creation Tool (https://support.microsoft.com/en-us/windows/create-installation-media-for-windows-99a58364-8c02-206f-aa6f-40c3b507420d).\n" +
                                "2. Boot your computer from the installation media. This usually involves pressing a specific key (e.g., F12, Del) during startup to enter the boot menu and selecting the media.\n" +
                                "3. On the initial installation screen, click on \"Repair your computer\" instead of \"Install Now.\"\n" +
                                "4. Select \"Troubleshoot\" and then \"Advanced options.\"\n" +
                                "5. Choose \"Startup Repair\" and follow the on-screen instructions. This process will attempt to automatically diagnose and fix the Boot Manager issue.\n" +
                                "\n" +
                                "**Bootrec.exe (Advanced):**\n" +
                                "**WARNING:**  These steps involve using command-line tools and modifying system configuration. Proceed with caution and only if comfortable. If unsure, consult a technician.\n" +
                                "\n" +
                                "1. Follow steps 1 and 2 from the Automatic Repair section to boot from your Windows installation media.\n" +
                                "2. Click on \"Repair your computer\" on the initial installation screen.\n" +
                                "3. Select \"Troubleshoot\" and then \"Command Prompt.\"\n" +
                                "4. In the Command Prompt window, type the following commands one by one, pressing Enter after each command:\n" +
                                "     - bootrec /fixmbr\n" +
                                "     - bootrec /fixboot\n" +
                                "     - bootrec /rebuildbcd\n" +
                                "5. These commands attempt to repair the Master Boot Record (MBR), Boot Sector, and rebuild the Boot Configuration Data (BCD) for your system.\n" +
                                "6. After running the commands, type \"exit\" and restart your computer.\n" +
                                "\n" +
                                "**Consult a Technician:**  If the above steps don't resolve the issue, consider seeking help from a qualified computer technician. They can diagnose the problem further and potentially reinstall the operating system if necessary.";
                    } else if (subSymptomName.equals("Boot Device Not Found")) {
                        solution = "The computer might not be detecting a bootable device (like a hard drive or SSD) containing your operating system. Here's how to troubleshoot:\n" +
                                "\n" +
                                "1. Check Boot Order: Enter your computer's BIOS or UEFI setup menu (usually by pressing a specific key during startup, like F2, Del, or Esc). Consult your computer's manual or manufacturer's website for specific instructions on accessing the BIOS/UEFI menu.\n" +
                                "\n" +
                                "2. Ensure the boot drive is listed first in the boot order priority. The boot order determines which device the computer attempts to load the operating system from first.\n" +
                                "\n" +
                                "3. Check Boot Device Connection: If the boot drive is listed correctly in the BIOS/UEFI but not detected, open your computer case (if comfortable) and ensure the data cable (e.g., SATA) connecting the storage drive to the motherboard is securely plugged in at both ends.\n" +
                                "\n" +
                                "4. Test with Another Drive (if possible): If you have another bootable drive (like a USB drive with a live operating system), try connecting it and setting it as the first boot priority in the BIOS/UEFI. If the computer boots from the other drive, it suggests an issue with your original boot drive.\n" +
                                "\n" +
                                "5. Consult a Technician: If the above steps don't resolve the problem, consider seeking help from a qualified computer technician. They can diagnose the issue further, such as checking for hardware failures in the storage drive or potential operating system corruption that might require reinstallation.";
                    } else if (subSymptomName.equals("Windows Failed to start")) {
                        solution = "Use System Restore: Boot into the 'Advanced Startup Options' (accessible by repeatedly pressing F8 during startup) and choose 'Repair Your Computer'. From there, select 'System Restore' to roll back to a previous working state.\n" +
                                "\n" +
                                "Check Disk for Errors: Use the Windows 'Check Disk' utility (chkdsk) to scan and fix errors on your hard drive. Open Command Prompt as administrator and run 'chkdsk /f /r'.";
                    } else if (subSymptomName.equals("Missing system file (\\system32\\hal.dll)")) {
                        solution = "A critical system file named 'hal.dll' located in \\system32\\ is missing. This file is essential for Windows to boot up properly. Here's what you can try:\n" +
                                "\n" +
                                "**WARNING:**  These steps involve modifying system files and might require administrative privileges. Proceed with caution and only if comfortable. If unsure, consult a technician.\n" +
                                "\n" +
                                "1. System Restore (if possible): If you recently created a system restore point before the issue occurred, you can attempt to restore your system to that point. This might revert any changes that caused the hal.dll file to go missing.\n" +
                                "\n" +
                                "2. System File Checker (SFC):** (Windows Vista and later)**\n" +
                                "     - Open Command Prompt as administrator (right-click Command Prompt and select \"Run as administrator\").\n" +
                                "     - Type the following command and press Enter:\n" +
                                "           sfc /scannow\n" +
                                "     - This command scans for missing or corrupt system files, including hal.dll, and attempts to repair them.\n" +
                                "\n" +
                                "3. DISM (Deployment Image Servicing and Management):** (if SFC fails)**\n" +
                                "     - If SFC doesn't resolve the issue, you can try DISM, a more robust tool for repairing system image corruption.\n" +
                                "     - Open Command Prompt as administrator (refer to step 2. above).\n" +
                                "     - Type the following command and press Enter:\n" +
                                "           DISM /Online /Cleanup-Image /RestoreHealth\n" +
                                "     - This command can take longer to run than SFC.\n" +
                                "\n" +
                                "4. Reinstall Windows (Last Resort): If the above steps fail, reinstalling Windows might be necessary to replace the missing system file. This will erase all your data and applications, so ensure you have a backup before proceeding.\n" +
                                "\n" +
                                "5. Consult a Technician: If you're uncomfortable with these steps or they don't resolve the issue, consider seeking help from a qualified computer technician. They can diagnose the problem further and potentially repair the system or reinstall Windows if necessary.";
                    } else if (subSymptomName.equals("Missing system file (\\Windows\\System32\\Config\\System)")) {
                        solution = "A critical system file named 'system' located in \\Windows\\System32\\Config\\ is missing. This file plays a vital role in system configuration and booting. Here's what you can try:\n" +
                                "\n" +
                                "**WARNING:**  These steps involve modifying system files and might require administrative privileges. Proceed with caution and only if comfortable. If unsure, consult a technician.\n" +
                                "\n" +
                                "1. System Restore (if possible): If you recently created a system restore point before the issue occurred, you can attempt to restore your system to that point. This might revert any changes that caused the system file to go missing.\n" +
                                "\n" +
                                "2. Boot to Safe Mode with Command Prompt:** Booting into Safe Mode with Command Prompt loads a minimal set of drivers and programs, allowing you to potentially access and repair system files.\n" +
                                "     - Consult your computer's manual or manufacturer's website for specific instructions on entering Safe Mode with Command Prompt. It often involves pressing a key during startup (like F8).\n" +
                                "     - Once in Safe Mode with Command Prompt, you might be able to use commands like `copy` or `system file checker` (SFC) to attempt copying a healthy system file from another location or running an SFC scan to repair corrupted system files.\n" +
                                "\n" +
                                "3. Automatic Startup Repair (Windows):** (Windows Vista and later)**\n" +
                                "     - Insert your Windows installation media (USB drive or DVD). If you don't have it, you can create one using another computer and Microsoft's Media Creation Tool (https://support.microsoft.com/en-us/windows/create-installation-media-for-windows-99a58364-8c02-206f-aa6f-40c3b507420d).\n" +
                                "     - Boot your computer from the installation media.\n" +
                                "     - On the initial installation screen, click on \"Repair your computer\" instead of \"Install Now.\"\n" +
                                "     - Select \"Troubleshoot\" and then \"Advanced options.\"\n" +
                                "     - Choose \"Startup Repair\" and follow the on-screen instructions. This process will attempt to automatically diagnose and fix the missing system file issue.\n" +
                                "\n" +
                                "4. Consult a Technician: If the above steps don't resolve the problem, consider seeking help from a qualified computer technician. They can diagnose the problem further, potentially attempt data recovery from the existing system files, or reinstall Windows as a last resort.";
                    }
                }
            }else {
                if (parentSymptomName.equals("Computer On Screen Error")){
                    if (subSymptomName.equals("Boot MGR Missing")){
                        solution = "The Boot Manager, a critical software component responsible for loading the operating system, might be missing on your computer. Here's how to attempt a repair:\n" +
                                "\n" +
                                "**Automatic Repair (Windows):**\n" +
                                "1. Insert your Windows installation media (USB drive or DVD). If you don't have it, you can create one using another computer and Microsoft's Media Creation Tool (https://support.microsoft.com/en-us/windows/create-installation-media-for-windows-99a58364-8c02-206f-aa6f-40c3b507420d).\n" +
                                "2. Boot your computer from the installation media. This usually involves pressing a specific key (e.g., F12, Del) during startup to enter the boot menu and selecting the media.\n" +
                                "3. On the initial installation screen, click on \"Repair your computer\" instead of \"Install Now.\"\n" +
                                "4. Select \"Troubleshoot\" and then \"Advanced options.\"\n" +
                                "5. Choose \"Startup Repair\" and follow the on-screen instructions. This process will attempt to automatically diagnose and fix the Boot Manager issue.\n" +
                                "\n" +
                                "**Bootrec.exe (Advanced):**\n" +
                                "**WARNING:**  These steps involve using command-line tools and modifying system configuration. Proceed with caution and only if comfortable. If unsure, consult a technician.\n" +
                                "\n" +
                                "1. Follow steps 1 and 2 from the Automatic Repair section to boot from your Windows installation media.\n" +
                                "2. Click on \"Repair your computer\" on the initial installation screen.\n" +
                                "3. Select \"Troubleshoot\" and then \"Command Prompt.\"\n" +
                                "4. In the Command Prompt window, type the following commands one by one, pressing Enter after each command:\n" +
                                "     - bootrec /fixmbr\n" +
                                "     - bootrec /fixboot\n" +
                                "     - bootrec /rebuildbcd\n" +
                                "5. These commands attempt to repair the Master Boot Record (MBR), Boot Sector, and rebuild the Boot Configuration Data (BCD) for your system.\n" +
                                "6. After running the commands, type \"exit\" and restart your computer.\n" +
                                "\n" +
                                "**Consult a Technician:**  If the above steps don't resolve the issue, consider seeking help from a qualified computer technician. They can diagnose the problem further and potentially reinstall the operating system if necessary.";
                    } else if (subSymptomName.equals("Boot Device Not Found")) {
                        solution = "The computer might not be detecting a bootable device (like a hard drive or SSD) containing your operating system. Here's how to troubleshoot:\n" +
                                "\n" +
                                "1. Check Boot Order: Enter your computer's BIOS or UEFI setup menu (usually by pressing a specific key during startup, like F2, Del, or Esc). Consult your computer's manual or manufacturer's website for specific instructions on accessing the BIOS/UEFI menu.\n" +
                                "\n" +
                                "2. Ensure the boot drive is listed first in the boot order priority. The boot order determines which device the computer attempts to load the operating system from first.\n" +
                                "\n" +
                                "3. Check Boot Device Connection: If the boot drive is listed correctly in the BIOS/UEFI but not detected, open your computer case (if comfortable) and ensure the data cable (e.g., SATA) connecting the storage drive to the motherboard is securely plugged in at both ends.\n" +
                                "\n" +
                                "4. Test with Another Drive (if possible): If you have another bootable drive (like a USB drive with a live operating system), try connecting it and setting it as the first boot priority in the BIOS/UEFI. If the computer boots from the other drive, it suggests an issue with your original boot drive.\n" +
                                "\n" +
                                "5. Consult a Technician: If the above steps don't resolve the problem, consider seeking help from a qualified computer technician. They can diagnose the issue further, such as checking for hardware failures in the storage drive or potential operating system corruption that might require reinstallation.";
                    } else if (subSymptomName.equals("Windows Failed to start")) {
                        solution = "Use System Restore: Boot into the 'Advanced Startup Options' (accessible by repeatedly pressing F8 during startup) and choose 'Repair Your Computer'. From there, select 'System Restore' to roll back to a previous working state.\n" +
                                "\n" +
                                "Check Disk for Errors: Use the Windows 'Check Disk' utility (chkdsk) to scan and fix errors on your hard drive. Open Command Prompt as administrator and run 'chkdsk /f /r'.";
                    } else if (subSymptomName.equals("Missing system file (\\system32\\hal.dll)")) {
                        solution = "A critical system file named 'hal.dll' located in \\system32\\ is missing. This file is essential for Windows to boot up properly. Here's what you can try:\n" +
                                "\n" +
                                "**WARNING:**  These steps involve modifying system files and might require administrative privileges. Proceed with caution and only if comfortable. If unsure, consult a technician.\n" +
                                "\n" +
                                "1. System Restore (if possible): If you recently created a system restore point before the issue occurred, you can attempt to restore your system to that point. This might revert any changes that caused the hal.dll file to go missing.\n" +
                                "\n" +
                                "2. System File Checker (SFC):** (Windows Vista and later)**\n" +
                                "     - Open Command Prompt as administrator (right-click Command Prompt and select \"Run as administrator\").\n" +
                                "     - Type the following command and press Enter:\n" +
                                "           sfc /scannow\n" +
                                "     - This command scans for missing or corrupt system files, including hal.dll, and attempts to repair them.\n" +
                                "\n" +
                                "3. DISM (Deployment Image Servicing and Management):** (if SFC fails)**\n" +
                                "     - If SFC doesn't resolve the issue, you can try DISM, a more robust tool for repairing system image corruption.\n" +
                                "     - Open Command Prompt as administrator (refer to step 2. above).\n" +
                                "     - Type the following command and press Enter:\n" +
                                "           DISM /Online /Cleanup-Image /RestoreHealth\n" +
                                "     - This command can take longer to run than SFC.\n" +
                                "\n" +
                                "4. Reinstall Windows (Last Resort): If the above steps fail, reinstalling Windows might be necessary to replace the missing system file. This will erase all your data and applications, so ensure you have a backup before proceeding.\n" +
                                "\n" +
                                "5. Consult a Technician: If you're uncomfortable with these steps or they don't resolve the issue, consider seeking help from a qualified computer technician. They can diagnose the problem further and potentially repair the system or reinstall Windows if necessary.";
                    } else if (subSymptomName.equals("Missing system file (\\Windows\\System32\\Config\\System)")) {
                        solution = "A critical system file named 'system' located in \\Windows\\System32\\Config\\ is missing. This file plays a vital role in system configuration and booting. Here's what you can try:\n" +
                                "\n" +
                                "**WARNING:**  These steps involve modifying system files and might require administrative privileges. Proceed with caution and only if comfortable. If unsure, consult a technician.\n" +
                                "\n" +
                                "1. System Restore (if possible): If you recently created a system restore point before the issue occurred, you can attempt to restore your system to that point. This might revert any changes that caused the system file to go missing.\n" +
                                "\n" +
                                "2. Boot to Safe Mode with Command Prompt:** Booting into Safe Mode with Command Prompt loads a minimal set of drivers and programs, allowing you to potentially access and repair system files.\n" +
                                "     - Consult your computer's manual or manufacturer's website for specific instructions on entering Safe Mode with Command Prompt. It often involves pressing a key during startup (like F8).\n" +
                                "     - Once in Safe Mode with Command Prompt, you might be able to use commands like `copy` or `system file checker` (SFC) to attempt copying a healthy system file from another location or running an SFC scan to repair corrupted system files.\n" +
                                "\n" +
                                "3. Automatic Startup Repair (Windows):** (Windows Vista and later)**\n" +
                                "     - Insert your Windows installation media (USB drive or DVD). If you don't have it, you can create one using another computer and Microsoft's Media Creation Tool (https://support.microsoft.com/en-us/windows/create-installation-media-for-windows-99a58364-8c02-206f-aa6f-40c3b507420d).\n" +
                                "     - Boot your computer from the installation media.\n" +
                                "     - On the initial installation screen, click on \"Repair your computer\" instead of \"Install Now.\"\n" +
                                "     - Select \"Troubleshoot\" and then \"Advanced options.\"\n" +
                                "     - Choose \"Startup Repair\" and follow the on-screen instructions. This process will attempt to automatically diagnose and fix the missing system file issue.\n" +
                                "\n" +
                                "4. Consult a Technician: If the above steps don't resolve the problem, consider seeking help from a qualified computer technician. They can diagnose the problem further, potentially attempt data recovery from the existing system files, or reinstall Windows as a last resort.";
                    }
                }
            }

        return solution;
    }




}
