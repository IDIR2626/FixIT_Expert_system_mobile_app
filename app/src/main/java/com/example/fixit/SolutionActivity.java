package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class SolutionActivity extends AppCompatActivity {
    String parentSymptomName,subSymptomName;
    Boolean isLaptop, isDesktop, isWindows, isLinux, haveHardwareProblem, haveSoftwareProblem, haveNetworkProblem;
    TextView solutionTextView;
    String solutionText ;
    String computer, operatingSystem, hardware, software, network;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        solutionTextView = findViewById(R.id.solotion_txt);

        //get intent extra data
        isDesktop = getIntent().getBooleanExtra("isDesktop", false);
        isLaptop = getIntent().getBooleanExtra("isLaptop", true);
        isWindows = getIntent().getBooleanExtra("isWindows", true);
        isLinux = getIntent().getBooleanExtra("isLinux", false);
        haveHardwareProblem = getIntent().getBooleanExtra("haveHardwareProblem", false);
        haveSoftwareProblem = getIntent().getBooleanExtra("haveSoftwareProblem", true);
        haveNetworkProblem = getIntent().getBooleanExtra("haveNetworkProblem", false);
        parentSymptomName = getIntent().getStringExtra("parentSymptom");
        subSymptomName = getIntent().getStringExtra("subSymptom");

        //computer
        if (isLaptop){computer = "laptop";}else {computer = "desktop";}
        //operating system
        if (isWindows){operatingSystem = "Windows";}else {operatingSystem="Linux";}
        //hardware
        if (haveHardwareProblem){hardware ="Yes";}else {hardware = "No";}
        //software
        if (haveSoftwareProblem){software = "Yes";}else {software ="No";}
        //Network
        if (haveNetworkProblem){network = "Yes";}else {network = "No";}
        //Parent Problem


        solutionTextView.setText(
                "1-Computer: " + computer + "\n"
                        +"2-Operating System: " + operatingSystem + "\n"
                        +"3-Have a hardware problem: " + hardware + "\n"
                        +"4-Have a software problem: " + software + "\n"
                        +"5-Have a network problem" + network + "\n"
                        +"6-The main Symptom: " + parentSymptomName + "\n"
                        +"7-The sub Symptom: " + subSymptomName + "\n"
        );

    }
}