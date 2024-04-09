package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HardwareProblem extends AppCompatActivity {
    Button yesBtn , noBtn;
    Boolean isLaptop, isDesktop, isWindows, isLinux, haveHardwareProblem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware_problem);

        yesBtn = findViewById(R.id.yes_btn_hardware);
        noBtn = findViewById(R.id.no_btn_hardware);
        //get intent extra data
        isDesktop = getIntent().getBooleanExtra("isDesktop", false);
        isLaptop = getIntent().getBooleanExtra("isLaptop", true);
        isWindows = getIntent().getBooleanExtra("isWindows", true);
        isLinux = getIntent().getBooleanExtra("isLinux", false);


        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                haveHardwareProblem = true;
                Intent intent = new Intent(HardwareProblem.this, SoftwareProblem.class);
                intent.putExtra("haveHardwareProblem", haveHardwareProblem);
                intent.putExtra("isLaptop", isLaptop);
                intent.putExtra("isDesktop", isDesktop);
                intent.putExtra("isWindows", isWindows);
                intent.putExtra("isLinux", isLinux);
                startActivity(intent);
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                haveHardwareProblem = false;
                Intent intent = new Intent(HardwareProblem.this, SoftwareProblem.class);
                intent.putExtra("haveHardwareProblem", haveHardwareProblem);
                intent.putExtra("isLaptop", isLaptop);
                intent.putExtra("isDesktop", isDesktop);
                intent.putExtra("isWindows", isWindows);
                intent.putExtra("isLinux", isLinux);
                startActivity(intent);
            }
        });
    }
}