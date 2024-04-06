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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_problem);

        yesBtn = findViewById(R.id.yes_btn_network);
        noBtn = findViewById(R.id.no_btn_network);

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NetworkProblem.this, SymptomsActivity.class);
                startActivity(intent);
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NetworkProblem.this, SymptomsActivity.class);
                startActivity(intent);
            }
        });
    }
}