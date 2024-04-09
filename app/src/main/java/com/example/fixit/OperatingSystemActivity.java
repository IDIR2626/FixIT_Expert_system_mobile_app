package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OperatingSystemActivity extends AppCompatActivity {
    Button windowsBtn, linuxBtn;
    Boolean isLaptop, isDesktop, isWindows, isLinux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operating_system);

        windowsBtn = findViewById(R.id.windows_btn);
        linuxBtn = findViewById(R.id.Linux_btn);

        isDesktop = getIntent().getBooleanExtra("isDesktop", false);
        isLaptop = getIntent().getBooleanExtra("isLaptop", true);

        windowsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isWindows = true;
                isLinux = false;
                Intent intent = new Intent(OperatingSystemActivity.this, HardwareProblem.class);
                intent.putExtra("isLaptop", isLaptop);
                intent.putExtra("isDesktop", isDesktop);
                intent.putExtra("isWindows", isWindows);
                intent.putExtra("isLinux", isLinux);
                startActivity(intent);
            }
        });

        linuxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isWindows = false;
                isLinux = true;
                Intent intent = new Intent(OperatingSystemActivity.this, HardwareProblem.class);
                intent.putExtra("isLaptop", isLaptop);
                intent.putExtra("isDesktop", isDesktop);
                intent.putExtra("isWindows", isWindows);
                intent.putExtra("isLinux", isLinux);
                startActivity(intent);
            }
        });
    }
}