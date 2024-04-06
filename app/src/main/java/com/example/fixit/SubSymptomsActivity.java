package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SubSymptomsActivity extends AppCompatActivity {

    TextView mainProblemTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_symptoms);

        mainProblemTitle = findViewById(R.id.mainProblemTitle);
    }
}