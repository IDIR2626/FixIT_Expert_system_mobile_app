package com.example.fixit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

    ListView listView;

    TextView mainProblemTextView;
    String parentSymptomName,subSymptomName;
    Boolean isLaptop, isDesktop, isWindows, isLinux, haveHardwareProblem, haveSoftwareProblem, haveNetworkProblem;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_symptoms);

        listView = findViewById(R.id.list_view_sub);
        mainProblemTextView = findViewById(R.id.mainProblemTitle);

        //get intent extra data
        isDesktop = getIntent().getBooleanExtra("isDesktop", false);
        isLaptop = getIntent().getBooleanExtra("isLaptop", true);
        isWindows = getIntent().getBooleanExtra("isWindows", true);
        isLinux = getIntent().getBooleanExtra("isLinux", false);
        haveHardwareProblem = getIntent().getBooleanExtra("haveHardwareProblem", false);
        haveSoftwareProblem = getIntent().getBooleanExtra("haveSoftwareProblem", true);
        haveNetworkProblem = getIntent().getBooleanExtra("haveNetworkProblem", false);
        parentSymptomName = getIntent().getStringExtra("parentSymptom");


        mainProblemTextView.setText(parentSymptomName);

        //get the subSymptoms according to the ParentSymptom name
        ExpertSystem expertSystem = new ExpertSystem();
        List<Problem> mainProblems = expertSystem.initializeKnowledgeBase();
        List<SubProblem> subProblemList = new ArrayList<>();
        for (Problem problem : mainProblems){
            if (problem.getName().equals(parentSymptomName)){
               subProblemList = problem.getSubProblems();
            }
        }
        List<String> subProblemNames = new ArrayList<>();
        for (SubProblem subProblem: subProblemList){
            subProblemNames.add(subProblem.getDescription());
        }

        // displaying the list of the subSymptoms
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), subProblemNames, R.drawable.baseline_arrow_forward_ios_24);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                subSymptomName = customAdapter.getItem(position);
                Intent intent = new Intent(SubSymptomsActivity.this, SolutionActivity.class);

                intent.putExtra("isLaptop", isLaptop);
                intent.putExtra("isDesktop", isDesktop);
                intent.putExtra("isWindows", isWindows);
                intent.putExtra("isLinux", isLinux);
                intent.putExtra("haveHardwareProblem", haveHardwareProblem);
                intent.putExtra("haveSoftwareProblem", haveSoftwareProblem);
                intent.putExtra("haveNetworkProblem", haveNetworkProblem);
                intent.putExtra("parentSymptom", parentSymptomName);
                intent.putExtra("subSymptom", subSymptomName);
                startActivity(intent);

            }
        });

    }
}