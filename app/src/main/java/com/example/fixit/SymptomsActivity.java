package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fixit.expert_system.ExpertSystem;
import com.example.fixit.expert_system.Problem;
import com.example.fixit.helper_class.CustomAdapter;

import java.util.ArrayList;
import java.util.Formattable;
import java.util.List;

public class SymptomsActivity extends AppCompatActivity {
    ListView listView;
    Boolean isLaptop, isDesktop, isWindows, isLinux, haveHardwareProblem, haveSoftwareProblem, haveNetworkProblem;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        //get intent extra data
        isDesktop = getIntent().getBooleanExtra("isDesktop", false);
        isLaptop = getIntent().getBooleanExtra("isLaptop", true);
        isWindows = getIntent().getBooleanExtra("isWindows", true);
        isLinux = getIntent().getBooleanExtra("isLinux", false);
        haveHardwareProblem = getIntent().getBooleanExtra("haveHardwareProblem", false);
        haveSoftwareProblem = getIntent().getBooleanExtra("haveSoftwareProblem", true);
        haveNetworkProblem = getIntent().getBooleanExtra("haveNetworkProblem", false);

        //getting the main symptoms
        ExpertSystem expertSystem = new ExpertSystem();
        List<Problem> symptoms = expertSystem.initializeKnowledgeBase();
        List<String> symptomsNames = new ArrayList<>();
        for (Problem problem : symptoms){
            symptomsNames.add(problem.getName());
        }

        listView = findViewById(R.id.list_view);
        CustomAdapter customAdapter = new CustomAdapter(this, symptomsNames, R.drawable.baseline_arrow_forward_ios_24);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String parentSymptomName = customAdapter.getItem(position);

                Intent intent = new Intent(SymptomsActivity.this, SubSymptomsActivity.class);
                intent.putExtra("isLaptop", isLaptop);
                intent.putExtra("isDesktop", isDesktop);
                intent.putExtra("isWindows", isWindows);
                intent.putExtra("isLinux", isLinux);
                intent.putExtra("haveHardwareProblem", haveHardwareProblem);
                intent.putExtra("haveSoftwareProblem", haveSoftwareProblem);
                intent.putExtra("haveNetworkProblem", haveNetworkProblem);
                intent.putExtra("parentSymptom", parentSymptomName);
                startActivity(intent);


            }
        });


    }
}