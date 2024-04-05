package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OperatingSystemActivity extends AppCompatActivity {
    Button windowsBtn, linuxBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operating_system);

        windowsBtn = findViewById(R.id.windows_btn);
        linuxBtn = findViewById(R.id.Linux_btn);

        windowsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OperatingSystemActivity.this, HardwareProblem.class);
                startActivity(intent);
            }
        });

        linuxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OperatingSystemActivity.this, HardwareProblem.class);
                startActivity(intent);
            }
        });
    }
}