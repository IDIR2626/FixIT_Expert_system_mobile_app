package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoftwareProblem extends AppCompatActivity {
    Button yesBtn , noBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software_problem);

        yesBtn = findViewById(R.id.yes_btn_software);
        noBtn = findViewById(R.id.no_btn_software);

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoftwareProblem.this, NetworkProblem.class);
                startActivity(intent);
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoftwareProblem.this, NetworkProblem.class);
                startActivity(intent);
            }
        });
    }
}