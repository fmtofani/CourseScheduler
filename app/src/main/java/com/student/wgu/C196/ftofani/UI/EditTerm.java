package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.student.wgu.C196.ftofani.Database.Repository;
import com.student.wgu.C196.ftofani.Entities.Course;
import com.student.wgu.C196.ftofani.Entities.Term;
import com.student.wgu.C196.ftofani.R;

import java.util.ArrayList;
import java.util.List;

public class EditTerm extends AppCompatActivity {

    Repository repository;
    List<Term> allTerms;
    int Id;
    EditText editName;
    EditText editStart;
    EditText editEnd;
    Term currentTerm;
    public static int numTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);
        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Id = getIntent().getIntExtra("termID", -1);

        repository = new Repository(getApplication());
        allTerms = repository.getAllTerms();
        for (Term p : allTerms) {
            if (p.getTermID() == Id) {
                currentTerm = p;
            }
        }
        editName = findViewById(R.id.editTextTermName);
        editStart = findViewById(R.id.editTextTermStartDate);
        editEnd = findViewById(R.id.editTextTermEndDate);
        if (currentTerm != null) {
            editName.setText(currentTerm.getTermName());
            editStart.setText(currentTerm.getTermStartDate());
            editEnd.setText(currentTerm.getTermEndDate());
        }

        List<Term> filteredTerms = new ArrayList<>();
        for (Term p : repository.getAllTerms()) {
            if (p.getTermID() == Id) filteredTerms.add(p);
        }
        numTerms = filteredTerms.size();

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

    public void addTerm(View view) {
        if (Id == -1) {
            Id = allTerms.get(allTerms.size() - 1).getTermID();
            Term p = new Term(++Id, editName.getText().toString(),
                    editStart.getText().toString(), editEnd.getText().toString());
            repository.insert(p);
        } else {
            Term p = new Term(Id, editName.getText().toString(),
                    editStart.getText().toString(), editEnd.getText().toString());
            repository.update(p);
        }
        Intent intent = new Intent(EditTerm.this, ListTerm.class);
        startActivity(intent);
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