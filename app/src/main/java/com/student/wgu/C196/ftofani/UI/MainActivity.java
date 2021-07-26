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
        Term term = new Term(1, "1", "01-01-21", "01-02-21");
        repository.insert(term);
        term = new Term(2, "2", "02-01-21", "02-02-21");
        repository.insert(term);
        Course course = new Course(1, "English", "in-progress", "01-01-21", "01-02-21", 1);
        repository.insert(course);
        course = new Course(2, "Biology", "in-progress", "01-01-21", "01-02-21", 1);
        repository.insert(course);
        Assessment assessment = new Assessment(1, "Algebra", "Exam", "01-03-21", 1);
        repository.insert(assessment);
        assessment = new Assessment(2, "Biology", "Project", "03-02-21", 1);
        repository.insert(assessment);
        Mentor mentor = new Mentor(1, "John Smith", "123-123-1232", "john.smith@wgu.edu", 1);
        repository.insert(mentor);
        mentor = new Mentor(2, "Joan Sanders", "212-123-3232", "joan.sanders@wgu.edu", 2);
        repository.insert(mentor);
        Note note = new Note(1, "CH2 study", "Ch2 page 98 needs refreshed", 1);
        repository.insert(note);
        note = new Note(2, "Ch3 exam", "I need to take more time in Ch3", 2);
        repository.insert(note);

    }

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

    ---------   TIPS    ----------

ctrl+alt+L ==  CLean up indentation

<!--   --> comment out xml

Change name in manifest for title bar




 */


//Lesson 1
//55:50 1:11 Create Landscape variation
//
//Lesson 4 35:00
//44:15 datepicker
//1:08:00 NOTIFY for different
//1:14 debug
//
//Lesson 4 1:36 delete with something in the repository
//
//
//    EditCourse has menu actions for notify and share commented out
//    ListCourse has refresh method for menu
//
//    At least 5 assessments for each course
//
//    Make sure the EditTerm Shows the Terms Name, StartDate and EndDate
//
//    Allow user to enter alert for start and end dates for each course
//
//    Allow user to enter alert for start and end dates for each assessment
//
//    Notes can be shared
//
//    Allow user to enter alert for start and end dates for each assessment
//
//
//
/*
    Term Names: Term 1, Term 2, Spring

    Course Status: in-progress, completed, dropped, plan to take

    Assessments: Performance or Objective

    Detailed: Course, Term and Assessment


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


D.  Create a storyboard to demonstrate application flow that includes each of the menus and screens from part B.


E.  Provide screen shots of generating the signed APK to demonstrate that you have created a deployment package.


Note: Verify that all the required functions of your application are working by executing the apk file.


F.  Reflect on the creation of your mobile application by doing the following:

1.  Explain how your application would be different if it were developed for a tablet rather than a phone, including a discussion of fragments and layouts.

2.  Identify the minimum and target operating system your application was developed under and is compatible with.

3.  Describe (suggested length of 1–2 paragraphs) the challenges you faced during the development of the mobile application.

4.  Describe (suggested length of 1–2 paragraphs) how you overcame each challenge discussed in part F3.

5.  Discuss (suggested length of 1–2 paragraphs) what you would do differently if you did the project again.

6.  Describe how emulators are used and the pros and cons of using an emulator versus using a development device.




 */