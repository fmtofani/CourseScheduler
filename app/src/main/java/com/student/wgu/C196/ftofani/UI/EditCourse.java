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
import com.student.wgu.C196.ftofani.Entities.Term;
import com.student.wgu.C196.ftofani.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    //For Spinners
    private Spinner progressSpinner;
    private Spinner termSpinner;

    //For calender
    final Calendar calStart = Calendar.getInstance();
    final Calendar calEnd = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener dateStart;
    DatePickerDialog.OnDateSetListener dateEnd;


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

        progressSpinner = findViewById(R.id.spinnerProgress);
        List<String> progressList = new ArrayList<>();
        progressList.add("Plan-to-Take");
        progressList.add("In-Progress");
        progressList.add("Completed");
        progressList.add("Dropped");
        ArrayAdapter<String> progressAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, progressList);
        progressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        progressSpinner.setAdapter(progressAdapter);

        editStartDate = findViewById(R.id.editTextCourseStartDate);
        dateStart = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calStart.set(Calendar.YEAR, year);
                calStart.set(Calendar.MONTH, monthOfYear);
                calStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Intent intent=new Intent(EditCourse.this,MyReceiver.class);
                intent.putExtra("key",sdf.format(calStart.getTime()));
                updateStartDate();
            }
        };
        editStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditCourse.this, dateStart, calStart
                        .get(Calendar.YEAR), calStart.get(Calendar.MONTH),
                        calStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editEndDate = findViewById(R.id.editTextCourseEndDate);
        dateEnd = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calEnd.set(Calendar.YEAR, year);
                calEnd.set(Calendar.MONTH, monthOfYear);
                calEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Intent intent=new Intent(EditCourse.this,MyReceiver.class);
                intent.putExtra("key",sdf.format(calEnd.getTime()));
                updateEndDate();
            }

        };
        editEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditCourse.this, dateEnd, calEnd
                        .get(Calendar.YEAR), calEnd.get(Calendar.MONTH),
                        calEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editCourseTermID = findViewById(R.id.editTextCourseTermID);
        termSpinner = findViewById(R.id.spinnerTermID);
        repository = new Repository(getApplication());
        List<Term> allTerms = repository.getAllTerms();
        ArrayAdapter<Term> termAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, allTerms);
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(termAdapter);


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


    private void updateStartDate() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editStartDate.setText(sdf.format(calStart.getTime()));
    }

    private void updateEndDate() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editStartDate.setText(sdf.format(calEnd.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notify_course, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.notifyCourseStart:
                String messageStart = "The WGU course, " + editName.getText().toString() + " ,is set to end today.";
                String startDateFromScreen = editStartDate.getText().toString();
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate = null;
                try {
                    myDate = sdf.parse(startDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Intent intent = new Intent(EditCourse.this, MyReceiver.class);
                intent.putExtra("key", messageStart);
                PendingIntent sender = PendingIntent.getBroadcast(EditCourse.this, ++MainActivity.numAlert, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;

            case R.id.notifyCourseEnd:
                String messageEnd = "The WGU course," + editName.getText().toString() + ",is set to end today.";
                String endDateFromScreen = editEndDate.getText().toString();
                String myFormat1 = "MM/dd/yy";
                SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);
                Date myDate1 = null;
                try {
                    myDate1 = sdf1.parse(endDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger1 = myDate1.getTime();
                Intent intent1 = new Intent(EditCourse.this, MyReceiver.class);
                intent1.putExtra("key", messageEnd);
                PendingIntent sender1 = PendingIntent.getBroadcast(EditCourse.this, ++MainActivity.numAlert, intent1, 0);
                AlarmManager alarmManager1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager1.set(AlarmManager.RTC_WAKEUP, trigger1, sender1);
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

    public void saveCourse(View view) {
        // Add checks and balances
        if (Id == -1) {
            Id = setCourseID();
            int goodCourseID = 0; //Integer.parseInt(editCourseID.getText().toString());
            String goodName = tryCourseName();
            String goodProgress = ""; //editProgress.getText().toString()
            String goodStartDate = ""; //editStartDate.getText().toString()
            String goodEndDate = ""; //editEndDate.getText().toString()
            int goodCourseTermID = 0; // Integer.parseInt(editCourseID.getText().toString());

            Course p = new Course(++Id, goodName,
                    goodProgress, goodStartDate, goodEndDate, goodCourseTermID);
            repository.insert(p);
        } else {
            Course p = new Course(Id, editName.getText().toString(),
                    editProgress.getText().toString(), editStartDate.getText().toString(),
                    editEndDate.getText().toString(),
                    Integer.parseInt(editCourseTermID.getText().toString()));
            repository.update(p);
        }
        Intent intent = new Intent(EditCourse.this, ListCourse.class);
        startActivity(intent);
    }

    public int setCourseID(){
        int num=0;
        for (Course p : allCourses) {
            if (p.getCourseID() > num) {
                num++;
            }
        }
        return num;
    }

    public String tryCourseName() {
        String goodName="";
        if (editName.getText().toString() == null) {
            Toast.makeText(getApplicationContext(),"Please enter a name",Toast.LENGTH_LONG).show();
        }
        else {
            goodName = editName.getText().toString();
        }
        return goodName;
    }

    public Date tryStartDate() {
        Date goodStartDate = null;
        return goodStartDate;
    }

    public Date tryEndDate() {
        //make sure end date is after start
        Date goodEndDate = null;
        return goodEndDate;
    }

    public int tryCourseTermID() {
        int goodCourseTermID = 0;
        return goodCourseTermID;
    }


}