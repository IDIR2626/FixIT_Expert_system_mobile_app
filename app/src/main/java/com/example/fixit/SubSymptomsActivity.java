package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fixit.expert_system.ExpertSystem;
import com.example.fixit.expert_system.Problem;
import com.example.fixit.expert_system.SubProblem;
import com.example.fixit.helper_class.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class SubSymptomsActivity extends AppCompatActivity {

    List<String> subProblems;
    ListView listView;

    TextView mainProblemTextView;
    String mainProblemTitle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_symptoms);

        listView = findViewById(R.id.list_view_sub);
        mainProblemTextView = findViewById(R.id.mainProblemTitle);
        mainProblemTitle = getIntent().getStringExtra("ParentSymptom");

        mainProblemTextView.setText(mainProblemTitle);

        //get the subSymptoms according to the ParentSymptom name
        ExpertSystem expertSystem = new ExpertSystem();
        List<Problem> mainProblems = expertSystem.initializeKnowledgeBase();
        List<SubProblem> subProblemList = new ArrayList<>();
        for (Problem problem : mainProblems){
            if (problem.getName().equals(mainProblemTitle)){
               subProblemList = problem.getSubProblems();
            }
        }

        // displaying the list of the subSymptoms
        List<String> subProblemNames = new ArrayList<>();
        for (SubProblem subProblem: subProblemList){
            subProblemNames.add(subProblem.getDescription());
        }

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), subProblemNames, R.drawable.baseline_arrow_forward_ios_24);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });

    }
}