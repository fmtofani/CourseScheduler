package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.student.wgu.C196.ftofani.Database.Repository;
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

public class EditTerm extends AppCompatActivity {

    Repository repository;
    int Id;
    EditText editName;
    EditText editStart;
    EditText editEnd;
    Term currentTerm;
    public static int numTerms;

    //For calender
    final Calendar calStart = Calendar.getInstance();
    final Calendar calEnd = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener dateStart;
    DatePickerDialog.OnDateSetListener dateEnd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);
        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Id = getIntent().getIntExtra("termID", -1);

        repository = new Repository(getApplication());
        for (Term p : repository.getAllTerms()) {
            if (p.getTermID() == Id) {
                currentTerm = p;
            }
        }

        editName = findViewById(R.id.editTextTermName);
        editStart = findViewById(R.id.editTextTermStartDate);
        dateStart = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calStart.set(Calendar.YEAR, year);
                calStart.set(Calendar.MONTH, monthOfYear);
                calStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Intent intent = new Intent(EditTerm.this, MyReceiver.class);
                intent.putExtra("key", sdf.format(calStart.getTime()));
                updateStartDate();
            }
        };
        editStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditTerm.this, dateStart, calStart
                        .get(Calendar.YEAR), calStart.get(Calendar.MONTH),
                        calStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editEnd = findViewById(R.id.editTextTermEndDate);
        dateEnd = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calEnd.set(Calendar.YEAR, year);
                calEnd.set(Calendar.MONTH, monthOfYear);
                calEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Intent intent = new Intent(EditTerm.this, MyReceiver.class);
                intent.putExtra("key", sdf.format(calEnd.getTime()));
                updateEndDate();
            }

        };
        editEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditTerm.this, dateEnd, calEnd
                        .get(Calendar.YEAR), calEnd.get(Calendar.MONTH),
                        calEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        if (currentTerm != null) {
            editName.setText(currentTerm.getTermName());
            editStart.setText(currentTerm.getTermStartDate());
            editEnd.setText(currentTerm.getTermEndDate());
        }

        // Keep track of how many Terms
        List<Term> filteredTerms = new ArrayList<>();
        for (Term p : repository.getAllTerms()) {
            if (p.getTermID() == Id) filteredTerms.add(p);
        }
        numTerms = filteredTerms.size();

    }

    private void updateStartDate() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editStart.setText(sdf.format(calStart.getTime()));
    }

    private void updateEndDate() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editEnd.setText(sdf.format(calEnd.getTime()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.notifyCourseStart:
                String messageStart = "The WGU course, " + editName.getText().toString() + " ,is set to end today.";
                String startDateFromScreen = editStart.getText().toString();
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate = null;
                try {
                    myDate = sdf.parse(startDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Intent intent = new Intent(EditTerm.this, MyReceiver.class);
                intent.putExtra("key", messageStart);
                PendingIntent sender = PendingIntent.getBroadcast(EditTerm.this, ++MainActivity.numAlert, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;

            case R.id.notifyCourseEnd:
                String messageEnd = "The WGU course," + editName.getText().toString() + ",is set to end today.";
                String endDateFromScreen = editEnd.getText().toString();
                String myFormat1 = "MM/dd/yy";
                SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);
                Date myDate1 = null;
                try {
                    myDate1 = sdf1.parse(endDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger1 = myDate1.getTime();
                Intent intent1 = new Intent(EditTerm.this, MyReceiver.class);
                intent1.putExtra("key", messageEnd);
                PendingIntent sender1 = PendingIntent.getBroadcast(EditTerm.this, ++MainActivity.numAlert, intent1, 0);
                AlarmManager alarmManager1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager1.set(AlarmManager.RTC_WAKEUP, trigger1, sender1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void addTerm(View view) throws ParseException {
        String tryName = editName.getText().toString();
        String goodName = "";
        if (tryName.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter a NAME", Toast.LENGTH_LONG).show();
            return;
        } else goodName = tryName;


        // Make sure Start date comes before End date
        String startDate = editStart.getText().toString();
        String endDate = editEnd.getText().toString();
        if(!checkDate(startDate, endDate)) {
            Toast.makeText(getApplicationContext(), "Start Date must come BEFORE End Date", Toast.LENGTH_LONG).show();
            return;
        }

        if (Id == -1) {
            Id = setTermID();
            Term p = new Term(Id, goodName, startDate, endDate);
            repository.insert(p);
        } else {
            Term p = new Term(Id, goodName, startDate, endDate);
            repository.update(p);
        }
        Intent intent = new Intent(EditTerm.this, ListTerm.class);
        startActivity(intent);
    }

    public boolean checkDate(String start, String end) throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
            Date date1 = sdf.parse(start);
            Date date2 = sdf.parse(end);

            int result = date1.compareTo(date2);

            if (result == 0) {
                return false;
            } else if (result > 0) {
                return false;
            }
            return true;
    }

    int num = 0;
    public int setTermID() {
        num = 0;
        for (Term p : repository.getAllTerms()) {
            if (p.getTermID() >= num) {
                num = p.getTermID();
                num++;
            }
        }
        return num;
    }


    public void deleteTerm(View view) {
        if (Id == -1) {
            Intent intent = new Intent(EditTerm.this, ListTerm.class);
            startActivity(intent);
        }
        else if(isCourse()) {
            Toast.makeText(getApplicationContext(), "Terms with assigned Courses can not be deleted. Please remove Courses from this Term to delete.", Toast.LENGTH_LONG).show();
        }
        else {
            repository.delete(currentTerm);
            Toast.makeText(getApplicationContext(), "Term Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(EditTerm.this, ListTerm.class);
            startActivity(intent);
        }
    }

    // This method verifies that there are no courses attached to the term marked for deletion
    //
    // Another way to do it
    // Lesson 4 1:36
    //
    //      for(Course p:repository.getAllCourses()) {
    //          if(p.getCourseTermID()==getIntent().getIntExtra("id, -1"))currentTerm = p;
    //      }
    //      if(p.getCourse().size==0) {
    //          repository.delete(currentTerm);
    //      }
    //      else {
    //          Toast.makeText(getApplicationContext(), "Can't delete course attached to term", Toast.LENGTH_LONG).show());
    //      }
    //
    //
    private boolean isCourse() {
        List<Course> checkCourse;
        Repository r = new Repository(getApplication());
        checkCourse = r.getAllCourses();
        for (Course p : checkCourse) {
            if (p.getCourseTermID() == Id) {
                return true;
            }
        }
        return false;
    }


}