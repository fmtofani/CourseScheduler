package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.student.wgu.C196.ftofani.Database.Repository;
import com.student.wgu.C196.ftofani.Entities.Assessment;
import com.student.wgu.C196.ftofani.Entities.Course;
import com.student.wgu.C196.ftofani.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EditAssessment extends AppCompatActivity {

    Repository repository;
    int Id;
    int numCourseID;
    EditText editName;
    EditText editDate;
    Assessment currentAssessment;
    public static int numAssessments;
    Course currentCourse;

    //For Spinners
    private Spinner typeSpinner;
    private Spinner courseSpinner;

    //For calender
    final Calendar calDateOn = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener dateOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);
        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Id = getIntent().getIntExtra("assessmentID", -1);
        repository = new Repository(getApplication());
        for (Assessment p : repository.getAllAssessments()) {
            if (p.getAssessmentID() == Id) {
                currentAssessment = p;
            }
        }

        numCourseID = getIntent().getIntExtra("assessmentCourseID", -1);
        repository = new Repository(getApplication());
        for (Course c : repository.getAllCourses()) {
            if (c.getCourseID() == numCourseID) {
                currentCourse = c;
            }
        }

        editName = findViewById(R.id.editTextAssessmentName);

        // Setup Spinner for Types of Assessments
        typeSpinner = findViewById(R.id.spinnerAssessmentType);
        List<String> typeList = new ArrayList<>();
        typeList.add("Performance");
        typeList.add("Objective");

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, typeList);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);


        // Setup Spinner to Select CourseID
        courseSpinner = findViewById(R.id.spinnerAssessmentCourseID);
        List<String> courseList = new ArrayList<>();
        repository = new Repository(getApplication());
        for (Course p : repository.getAllCourses()) {
            courseList.add(p.getCourseName());
        }

        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, courseList);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);


        editDate = findViewById(R.id.editTextAssessmentDate);
        dateOn = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calDateOn.set(Calendar.YEAR, year);
                calDateOn.set(Calendar.MONTH, monthOfYear);
                calDateOn.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Intent intent = new Intent(EditAssessment.this, MyReceiver.class);
                intent.putExtra("key", sdf.format(calDateOn.getTime()));
                updateDateOn();
            }

        };

        editDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditAssessment.this, dateOn, calDateOn
                        .get(Calendar.YEAR), calDateOn.get(Calendar.MONTH),
                        calDateOn.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        if (currentAssessment != null) {
            editName.setText(currentAssessment.getAssessmentName());
            editDate.setText(currentAssessment.getAssessmentDate());
            // Index the Type Spinner to the correct type if Editing an Assessment
            int numType = 0;
            if (currentAssessment.getAssessmentType().equals("Performance")) numType = 0;
            if (currentAssessment.getAssessmentType().equals("Objective")) numType = 1;
            typeSpinner.setSelection(numType);

            // Index the Course Spinner to the correct ID if Editing an Assessment
            courseSpinner.setSelection(numCourseID-1);

            // Keep track of how many Assessments
            List<Assessment> filteredAssessments = new ArrayList<>();
            repository = new Repository(getApplication());
            for (Assessment p : repository.getAllAssessments()) {
                if (p.getAssessmentID() == Id) filteredAssessments.add(p);
            }
            numAssessments = filteredAssessments.size();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notify_assessment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            // Setup the notify method which calls the MyReceiver.class
            case R.id.notifyAssessment:
                String message = "The WGU assessment, " + editName.getText().toString() + " ,is due today.";
                String dateFromScreen = editDate.getText().toString();
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Intent intent = new Intent(EditAssessment.this, MyReceiver.class);
                intent.putExtra("key", message);
                PendingIntent sender = PendingIntent.getBroadcast(EditAssessment.this, ++MainActivity.numAlert, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void updateDateOn() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDate.setText(sdf.format(calDateOn.getTime()));
    }


    public void addAssessment(View view) {
        String tryName = editName.getText().toString();
        String goodName = "";
        if (tryName.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter a NAME", Toast.LENGTH_LONG).show();
            return;
        } else goodName = tryName;
        String tryDate = editDate.getText().toString();
        String goodDate = "";
        if (tryDate.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please select a DATE", Toast.LENGTH_LONG).show();
            return;
        } else goodDate = tryDate;

        String type = String.valueOf(typeSpinner.getSelectedItem());
        String courseName = (String) courseSpinner.getSelectedItem();
        int courseID = -1;
        repository = new Repository(getApplication());
        for (Course p : repository.getAllCourses()) {
            if (p.getCourseName().equals(courseName)) {
                courseID = p.getCourseID();
            }
        }

        if (Id == -1) {
            Id = setAssessmentID();
            Assessment p = new Assessment(Id, goodName, type, goodDate, courseID);
            repository.insert(p);
        } else {
            Assessment p = new Assessment(Id, goodName, type, goodDate, courseID);
            repository.update(p);
        }
        Intent intent = new Intent(EditAssessment.this, ListAssessment.class);
        startActivity(intent);
    }

    int num = 0;
    public int setAssessmentID() {
        num = 0;
        for (Assessment p : repository.getAllAssessments()) {
            if (p.getAssessmentID() >= num) {
                num = p.getAssessmentID();
                num++;
            }
        }
        return num;
    }

    public void deleteAssessment(View view) {
        if (Id == -1) {
            Intent intent = new Intent(EditAssessment.this, ListAssessment.class);
            startActivity(intent);
        } else {
            repository.delete(currentAssessment);
            Toast.makeText(getApplicationContext(), "Assessment Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(EditAssessment.this, ListAssessment.class);
            startActivity(intent);
        }
    }


}