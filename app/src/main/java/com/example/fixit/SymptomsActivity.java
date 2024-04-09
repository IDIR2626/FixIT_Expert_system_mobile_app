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
    List<String> symptoms ;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        listView = findViewById(R.id.list_view);
//        symptoms = new ArrayList<String>();
////        symptoms.add("Computer on screen of");
////        symptoms.add("Computer on screen ERROR");
////        symptoms.add("Computer off screen of");
////        symptoms.add("Blue screens");
////        symptoms.add("Beeps");

        ExpertSystem expertSystem = new ExpertSystem();

        List<Problem> symptoms = expertSystem.initializeKnowledgeBase();

        List<String> symptomsNames = new ArrayList<>();
        for (Problem problem : symptoms){
            symptomsNames.add(problem.getName());
        }

        CustomAdapter customAdapter = new CustomAdapter(this, symptomsNames, R.drawable.baseline_arrow_forward_ios_24);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String symptomName = customAdapter.getItem(position);

                Intent intent = new Intent(SymptomsActivity.this, SubSymptomsActivity.class);
                intent.putExtra("ParentSymptom", symptomName);
                startActivity(intent);


            }
        });


    }
}