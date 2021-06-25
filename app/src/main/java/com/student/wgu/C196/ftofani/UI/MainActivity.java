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
    //55:50 1:11 Create Landscape variation

    //Goto Terms Intent
    public void goToTerms(View view) {
        Intent intent = new Intent(MainActivity.this, ListTerm.class);
        startActivity(intent);
    }
    //Goto Courses Intent
    public void goToCourse(View view) {
        Intent intent = new Intent(MainActivity.this, ListCourse.class);
        startActivity(intent);
    }
    //Goto Assessment Intent
    public void goToAssessment(View view) {
        Intent intent = new Intent(MainActivity.this, ListAssessment.class);
        startActivity(intent);
    }
}