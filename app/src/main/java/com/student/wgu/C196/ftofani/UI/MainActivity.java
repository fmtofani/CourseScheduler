package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.student.wgu.C196.ftofani.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Add new intent to switch screens 46:00 Lesson 1
    //Add new Empty Activity
    //Always Import R with alt+enter
    //Change name in manifest for title bar
    //55:50 Create Landscape variation
    public void goToTerms(View view) {
        Intent intent = new Intent(MainActivity.this, TermsList.class);
        startActivity(intent);
    }

    public void goToCourse(View view) {
        Intent intent = new Intent(MainActivity.this, CourseList.class);
        startActivity(intent);
    }

    public void goToAssessment(View view) {
        Intent intent = new Intent(MainActivity.this, AssessmentList.class);
        startActivity(intent);
    }
}