package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.student.wgu.C196.ftofani.Database.Repository;
import com.student.wgu.C196.ftofani.Entities.Assessment;
import com.student.wgu.C196.ftofani.Entities.Note;
import com.student.wgu.C196.ftofani.R;

import java.util.ArrayList;
import java.util.List;

public class EditNote extends AppCompatActivity {

//
//
//
// Pull courseID From Intent
//
//
//
//
    Repository repository;
    List<Note> allNotes;
    int Id;
    String name;
    String body;
    EditText editName;
    EditText editBody;
    int courseID;
    Note currentNote;
    public static int numNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Id = getIntent().getIntExtra("noteID", -1);

        repository = new Repository(getApplication());
        allNotes = repository.getAllNotes();
        for (Note p : allNotes) {
            if (p.getNoteID() == Id) {
                currentNote = p;
            }
        }
        editName = findViewById(R.id.editTextNoteName);
        editBody = findViewById(R.id.editTextNoteBody);
        if (currentNote != null) {
            editName.setText(currentNote.getNoteName());
            editBody.setText(currentNote.getNoteBody());
        }

        List<Note> filteredNotes = new ArrayList<>();
        for (Note p : repository.getAllNotes()) {
            if (p.getNoteID() == Id) filteredNotes.add(p);
        }
        numNotes = filteredNotes.size();

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

    public void addNote(View view) {
        if (Id == -1) {
            Id = allNotes.get(allNotes.size() - 1).getNoteID();
            Note p = new Note(++Id, editName.getText().toString(),
                    editBody.getText().toString(), courseID);
            repository.insert(p);
        } else {
            Note p = new Note(Id, editName.getText().toString(),
                    editBody.getText().toString(), courseID);
            repository.update(p);
        }
        Intent intent = new Intent(EditNote.this, EditCourse.class);
        startActivity(intent);
    }

    public void deleteNote(View view) {
        if (Id == -1) {
            Intent intent = new Intent(EditNote.this, EditCourse.class);
            startActivity(intent);
        } else {
            repository.delete(currentNote);
            Toast.makeText(getApplicationContext(), "Note Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(EditNote.this, EditCourse.class);
            startActivity(intent);
        }
    }


}