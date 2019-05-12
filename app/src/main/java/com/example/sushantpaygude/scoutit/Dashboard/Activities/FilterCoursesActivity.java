package com.example.sushantpaygude.scoutit.Dashboard.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.sushantpaygude.scoutit.R;
import com.example.sushantpaygude.scoutit.Utilities.Utils;

public class FilterCoursesActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout layoutComputerScience;
    private RelativeLayout layoutInformationSystem;
    private RelativeLayout layoutElectricalEngineering;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_courses);
        layoutComputerScience = findViewById(R.id.layoutComputerScience);
        layoutInformationSystem = findViewById(R.id.layoutInformationSystem);
        layoutElectricalEngineering = findViewById(R.id.layoutElectricalEngineering);
        layoutComputerScience.setOnClickListener(this);
        layoutInformationSystem.setOnClickListener(this);
        layoutElectricalEngineering.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.layoutComputerScience:
                intent.putExtra("CATEGORY", Utils.CourseType.COMPUTER_SCIENCE.toString());
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.layoutInformationSystem:
                intent.putExtra("CATEGORY", Utils.CourseType.INFORMATION_SYSTEM.toString());
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.layoutElectricalEngineering:
                intent.putExtra("CATEGORY", Utils.CourseType.ELECTRICAL_ENGINEERING.toString());
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
