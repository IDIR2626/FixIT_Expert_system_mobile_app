package com.example.fixit;

import static com.example.fixit.R.id.no_btn_network;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NetworkProblem extends AppCompatActivity {
    Button yesBtn , noBtn;
    Boolean isLaptop, isDesktop, isWindows, isLinux, haveHardwareProblem, haveSoftwareProblem, haveNetworkProblem;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_problem);

        yesBtn = findViewById(R.id.yes_btn_network);
        noBtn = findViewById(R.id.no_btn_network);
        //get intent extra data
        isDesktop = getIntent().getBooleanExtra("isDesktop", false);
        isLaptop = getIntent().getBooleanExtra("isLaptop", true);
        isWindows = getIntent().getBooleanExtra("isWindows", true);
        isLinux = getIntent().getBooleanExtra("isLinux", false);
        haveHardwareProblem = getIntent().getBooleanExtra("haveHardwareProblem", false);
        haveSoftwareProblem = getIntent().getBooleanExtra("haveSoftwareProblem", true);


        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                haveNetworkProblem = true;
                Intent intent = new Intent(NetworkProblem.this, SymptomsActivity.class);
                intent.putExtra("isLaptop", isLaptop);
                intent.putExtra("isDesktop", isDesktop);
                intent.putExtra("isWindows", isWindows);
                intent.putExtra("isLinux", isLinux);
                intent.putExtra("haveHardwareProblem", haveHardwareProblem);
                intent.putExtra("haveSoftwareProblem", haveSoftwareProblem);
                intent.putExtra("haveNetworkProblem", haveNetworkProblem);
                startActivity(intent);
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                haveNetworkProblem = false;
                Intent intent = new Intent(NetworkProblem.this, SymptomsActivity.class);
                intent.putExtra("isLaptop", isLaptop);
                intent.putExtra("isDesktop", isDesktop);
                intent.putExtra("isWindows", isWindows);
                intent.putExtra("isLinux", isLinux);
                intent.putExtra("haveHardwareProblem", haveHardwareProblem);
                intent.putExtra("haveSoftwareProblem", haveSoftwareProblem);
                intent.putExtra("haveNetworkProblem", haveNetworkProblem);
                startActivity(intent);
            }
        });
    }
}