package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.AppBarLayout;

public class MainActivity extends AppCompatActivity {

    AppBarLayout appBar;
    Button laptopBtn, DesktopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        appBar = findViewById(R.id.topAppBar);
        laptopBtn = findViewById(R.id.Linux_btn);
        DesktopBtn = findViewById(R.id.windows_btn);

        laptopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OperatingSystemActivity.class);
                startActivity(intent);
            }
        });

        DesktopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OperatingSystemActivity.class);
                startActivity(intent);
            }
        });
    }
}