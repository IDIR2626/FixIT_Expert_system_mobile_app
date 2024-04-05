package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HardwareProblem extends AppCompatActivity {
    Button yesBtn , noBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware_problem);

        yesBtn = findViewById(R.id.yes_btn_hardware);
        noBtn = findViewById(R.id.no_btn_hardware);

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HardwareProblem.this, SoftwareProblem.class);
                startActivity(intent);
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HardwareProblem.this, SoftwareProblem.class);
                startActivity(intent);
            }
        });
    }
}