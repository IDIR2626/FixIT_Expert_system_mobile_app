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
    String computer, operatingSystem, hardware, software, network, parentProb, subProb;

    @SuppressLint("MissingInflatedId")
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

        if (isLaptop){
            computer = "laptop";
        }else {
            computer = "desktop";
        }


    }
}