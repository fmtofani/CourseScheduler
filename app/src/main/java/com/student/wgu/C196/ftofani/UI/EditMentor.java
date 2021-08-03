package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.student.wgu.C196.ftofani.Database.Repository;
import com.student.wgu.C196.ftofani.Entities.Course;
import com.student.wgu.C196.ftofani.Entities.Mentor;
import com.student.wgu.C196.ftofani.R;

import java.util.ArrayList;
import java.util.List;

public class EditMentor extends AppCompatActivity {

    Repository repository;
    int Id;
    int numCourseID;
    EditText editName;
    EditText editPhone;
    EditText editEmail;
    Mentor currentMentor;
    Course currentCourse;
    public static int numMentors;

    // For Spinner
    private Spinner courseSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mentor);
        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Id = getIntent().getIntExtra("mentorID", -1);
        repository = new Repository(getApplication());
        for (Mentor p : repository.getAllMentors()) {
            if (p.getMentorID() == Id) {
                currentMentor = p;
            }
        }

        numCourseID = getIntent().getIntExtra("mentorCourseID", -1);
        repository = new Repository(getApplication());
        for (Course c : repository.getAllCourses()) {
            if (c.getCourseID() == numCourseID) {
                currentCourse = c;
            }
        }

        editName = findViewById(R.id.editTextMentorName);
        editPhone = findViewById(R.id.editTextMentorPhone);
        editEmail = findViewById(R.id.editTextMentorEmail);

        // Setup Spinner to Select CourseID
        courseSpinner = findViewById(R.id.spinnerMentorCourseID);
        List<String> courseList = new ArrayList<>();
        repository = new Repository(getApplication());
        for (Course p : repository.getAllCourses()) {
            courseList.add(p.getCourseName());
        }

        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(this,
        android.R.layout.simple_spinner_item, courseList);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);

        if (currentMentor != null) {
            editName.setText(currentMentor.getMentorName());
            editPhone.setText(currentMentor.getMentorPhone());
            editEmail.setText(currentMentor.getMentorEmail());
            courseSpinner.setSelection(numCourseID-1);
        }

        // Keep track of how many Mentors

        List<Mentor> filteredMentors = new ArrayList<>();
        for (Mentor p : repository.getAllMentors()) {
            if (p.getMentorID() == Id) filteredMentors.add(p);
        }
        numMentors = filteredMentors.size();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void addMentor(View view) {

        String tryName = editName.getText().toString();
        String goodName = "";
        if (tryName.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter a NAME", Toast.LENGTH_LONG).show();
            return;
        } else goodName = tryName;

        String tryPhone = editPhone.getText().toString();
        String goodPhone = "";
        if (tryPhone.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter a PHONE NUMBER", Toast.LENGTH_LONG).show();
            return;
        } else goodPhone = tryPhone;

        String tryEmail = editEmail.getText().toString();
        String goodEmail = "";
        if (tryEmail.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter an EMAIL ADDRESS", Toast.LENGTH_LONG).show();
            return;
        } else goodEmail = tryEmail;

        String courseName = (String) courseSpinner.getSelectedItem();
        int courseID = -1;
        repository = new Repository(getApplication());
        for (Course p : repository.getAllCourses()) {
            if (p.getCourseName().equals(courseName)) {
                courseID = p.getCourseID();
            }
        }


        if (Id == -1) {
            Id = setMentorID();
            Mentor p = new Mentor(Id, goodName, goodPhone, goodEmail, courseID);
            repository.insert(p);
        } else {
            Mentor p = new Mentor(Id, goodName, goodPhone, goodEmail, courseID);
            repository.update(p);
        }
        Intent intent = new Intent(EditMentor.this, ListMentor.class);
        startActivity(intent);

    }

    int num = 0;
    public int setMentorID() {
        num = 0;
        for (Mentor m : repository.getAllMentors()) {
            if (m.getMentorID() >= num) {
                num = m.getMentorID();
                num++;
            }
        }
        return num;

    }

    public void deleteMentor(View view) {

        if (Id == -1) {
            Intent intent = new Intent(EditMentor.this, EditCourse.class);
            startActivity(intent);
        } else {
            repository.delete(currentMentor);
            Toast.makeText(getApplicationContext(), "Mentor Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(EditMentor.this, ListMentor.class);
            startActivity(intent);
        }

    }


}