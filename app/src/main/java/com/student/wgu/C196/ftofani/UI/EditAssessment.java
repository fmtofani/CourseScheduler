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
import com.student.wgu.C196.ftofani.Entities.Assessment;
import com.student.wgu.C196.ftofani.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EditAssessment extends AppCompatActivity {

    Repository repository;
    List<Assessment> allAssessments;
    int Id;
    String name;
    String type;
    String date;
    EditText editName;
    EditText editType;
    EditText editDate;
    EditText editCourseID;
    Assessment currentAssessment;
    public static int numAssessments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);
        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Id = getIntent().getIntExtra("assessmentID", -1);

        repository = new Repository(getApplication());
        allAssessments = repository.getAllAssessments();
        for (Assessment p : allAssessments) {
            if (p.getAssessmentID() == Id) {
                currentAssessment = p;
            }
        }
        editName = findViewById(R.id.editTextAssessmentName);
        editType = findViewById(R.id.editTextAssessmentType);
        editDate = findViewById(R.id.editTextAssessmentDate);
        editCourseID = findViewById(R.id.editTextAssessmentCourseID);
        if (currentAssessment != null) {
            editName.setText(currentAssessment.getAssessmentName());
            editType.setText(currentAssessment.getAssessmentType());
            editDate.setText(currentAssessment.getAssessmentDate());
            editCourseID.setText(Integer.toString(currentAssessment.getAssessmentCourseID()));
        }

        List<Assessment> filteredAssessments = new ArrayList<>();
        for (Assessment p : repository.getAllAssessments()) {
            if (p.getAssessmentID() == Id) filteredAssessments.add(p);
        }
        numAssessments = filteredAssessments.size();

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
                String dateFromScreen=editDate.getText().toString();
                String myFormat ="MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate=null;
                try {
                    myDate=sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger= myDate.getTime();
                Intent intent = new Intent(EditAssessment.this, MyReceiver.class);
                intent.putExtra("key", message);
                PendingIntent sender=PendingIntent.getBroadcast(EditAssessment.this, ++MainActivity.numAlert,intent, 0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void addAssessment(View view) {
        if (Id == -1) {
            Id = allAssessments.get(allAssessments.size() - 1).getAssessmentID();
            Assessment p = new Assessment(++Id, editName.getText().toString(),
                    editType.getText().toString(), editDate.getText().toString(),
                    Integer.parseInt(editCourseID.getText().toString()));
            repository.insert(p);
        } else {
            Assessment p = new Assessment(Id, editName.getText().toString(),
                    editType.getText().toString(), editDate.getText().toString(),
                    Integer.parseInt(editCourseID.getText().toString()));
            repository.update(p);
        }
        Intent intent = new Intent(EditAssessment.this, ListAssessment.class);
        startActivity(intent);
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