package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.student.wgu.C196.ftofani.Database.Repository;
import com.student.wgu.C196.ftofani.Entities.Assessment;
import com.student.wgu.C196.ftofani.Entities.Course;
import com.student.wgu.C196.ftofani.Entities.Mentor;
import com.student.wgu.C196.ftofani.Entities.Note;
import com.student.wgu.C196.ftofani.Entities.Term;
import com.student.wgu.C196.ftofani.R;

public class MainActivity extends AppCompatActivity {
    public static int numAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = new Repository(getApplication());
        Term term = new Term(1, "Math");
        repository.insert(term);
        Course course = new Course(1,"English");
        repository.insert(course);
        Assessment assessment = new Assessment(3, "project");
        repository.insert(assessment);
    }

    //Add new intent to switch screens 46:00 Lesson 1
    //Add new Empty Activity
    //Always Import R with alt+enter
    //Change name in manifest for title bar
    //55:50 1:11 Create Landscape variation
    //
    //Lesson 3
    //8:00 Intents to refresh screens
    //
    //Lesson 3 contrinue at 1:14.50
    // 
    //Lesson 4 35:00
    //44:15 datepicker
    //1:08:00 NOTIFY for different
    //1:14 debug
    //
    //  <!--   --> comment out xml
    // learn style


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


/*
                ---   TIPS    ---
ctrl+alt+L ==  CLean up indentation



 */