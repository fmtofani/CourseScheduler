package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.student.wgu.C196.ftofani.Database.Repository;
import com.student.wgu.C196.ftofani.Entities.Course;
import com.student.wgu.C196.ftofani.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EditCourse extends AppCompatActivity {

    
    Repository repository;
    List<Course> allCourses;
    int Id;
    String name;
    String progress;
    String startDate;
    String endDate;
    EditText editName;
    EditText editProgress;
    EditText editStartDate;
    EditText editEndDate;
    EditText editCourseTermID;
    Course currentCourse;
    public static int numCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Id = getIntent().getIntExtra("courseID", -1);

        repository = new Repository(getApplication());
        allCourses = repository.getAllCourses();
        for (Course p : allCourses) {
            if (p.getCourseID() == Id) {
                currentCourse = p;
            }
        }

//        name = getIntent().getStringExtra("name");
//        editName.setText(name);

        editName = findViewById(R.id.editTextCourseName);
        editProgress = findViewById(R.id.editTextCourseProgress);
        editStartDate = findViewById(R.id.editTextCourseStartDate);
        editEndDate = findViewById(R.id.editTextCourseEndDate);
        editCourseTermID = findViewById(R.id.editTextCourseTermID);
        if (currentCourse != null) {
            editName.setText(currentCourse.getCourseName());
            editProgress.setText(currentCourse.getCourseProgress());
            editStartDate.setText(currentCourse.getCourseStartDate());
            editEndDate.setText(currentCourse.getCourseEndDate());
            editCourseTermID.setText(Integer.toString(currentCourse.getCourseTermID()));
        }

        List<Course> filteredCourses = new ArrayList<>();
        for (Course p : repository.getAllCourses()) {
            if (p.getCourseID() == Id) filteredCourses.add(p);
        }
        numCourses = filteredCourses.size();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notify_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String messageStart = "The WGU course, " + editName.getText().toString() + " ,is set to begin today.";
        String messageEnd = "The WGU course, " + editName.getText().toString() + " ,is set to end today.";
        String startDateFromScreen = editStartDate.getText().toString();
        String endDateFromScreen = editEndDate.getText().toString();
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date myDate = null;
        Long trigger = myDate.getTime();
        Intent intent = new Intent(EditCourse.this, MyReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(EditCourse.this, ++MainActivity.numAlert, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            // Setup the notify method which calls the MyReceiver.class for the start of a course
            case R.id.notifyCourseStart:
                try {
                    myDate = sdf.parse(startDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                intent.putExtra("key", messageStart);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;

            // Setup the notify method which calls the MyReceiver.class for the end of a course
            case R.id.notifyCourseEnd:
                try {
                    myDate = sdf.parse(endDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                intent.putExtra("key", messageStart);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;

        }
        return super.onOptionsItemSelected(item);

    }

    public void addCourse(View view) {
        if (Id == -1) {
            Id = allCourses.get(allCourses.size() - 1).getCourseID();
            Course p = new Course(++Id, editName.getText().toString(),
                    editProgress.getText().toString(), editStartDate.getText().toString(),
                    editEndDate.getText().toString(),
                    Integer.parseInt(editCourseTermID.getText().toString()));
            repository.insert(p);
        } else {
            Course p = new Course(++Id, editName.getText().toString(),
                    editProgress.getText().toString(), editStartDate.getText().toString(),
                    editEndDate.getText().toString(),
                    Integer.parseInt(editCourseTermID.getText().toString()));
            repository.update(p);
        }
        Intent intent = new Intent(EditCourse.this, ListCourse.class);
        startActivity(intent);
    }

    public void deleteCourse(View view) {
        if (Id == -1) {
            Intent intent = new Intent(EditCourse.this, ListCourse.class);
            startActivity(intent);
        } else {
            repository.delete(currentCourse);
            Toast.makeText(getApplicationContext(), "Course Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(EditCourse.this, ListCourse.class);
            startActivity(intent);
        }
    }

    public void goToNote(View view) {
        Intent intent = new Intent(EditCourse.this, ListNote.class);
        startActivity(intent);
    }


    public void goToMentor(View view) {
        Intent intent = new Intent(EditCourse.this, ListMentor.class);
        startActivity(intent);
    }


    public void date(View view) {
    }


}