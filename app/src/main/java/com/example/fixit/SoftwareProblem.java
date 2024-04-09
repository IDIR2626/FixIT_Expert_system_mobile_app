package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoftwareProblem extends AppCompatActivity {
    Button yesBtn , noBtn;
    Boolean isLaptop, isDesktop, isWindows, isLinux, haveHardwareProblem, haveSoftwareProblem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software_problem);

        yesBtn = findViewById(R.id.yes_btn_software);
        noBtn = findViewById(R.id.no_btn_software);

        //get intent extra data
        isDesktop = getIntent().getBooleanExtra("isDesktop", false);
        isLaptop = getIntent().getBooleanExtra("isLaptop", true);
        isWindows = getIntent().getBooleanExtra("isWindows", true);
        isLinux = getIntent().getBooleanExtra("isLinux", false);
        haveHardwareProblem = getIntent().getBooleanExtra("haveHardwareProblem", false);


        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                haveSoftwareProblem = true;
                Intent intent = new Intent(SoftwareProblem.this, NetworkProblem.class);
                intent.putExtra("isLaptop", isLaptop);
                intent.putExtra("isDesktop", isDesktop);
                intent.putExtra("isWindows", isWindows);
                intent.putExtra("isLinux", isLinux);
                intent.putExtra("haveHardwareProblem", haveHardwareProblem);
                intent.putExtra("haveSoftwareProblem", haveSoftwareProblem);
                startActivity(intent);
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                haveSoftwareProblem = false;
                Intent intent = new Intent(SoftwareProblem.this, NetworkProblem.class);
                intent.putExtra("isLaptop", isLaptop);
                intent.putExtra("isDesktop", isDesktop);
                intent.putExtra("isWindows", isWindows);
                intent.putExtra("isLinux", isLinux);
                intent.putExtra("haveHardwareProblem", haveHardwareProblem);
                intent.putExtra("haveSoftwareProblem", haveSoftwareProblem);
                startActivity(intent);
            }
        });
    }
}