package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.student.wgu.C196.ftofani.Database.Repository;
import com.student.wgu.C196.ftofani.Entities.Mentor;
import com.student.wgu.C196.ftofani.R;
import java.util.List;

public class ListMentor extends AppCompatActivity {

    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Setup Repository
        repository = new Repository(getApplication());
        List<Mentor> allMentors = repository.getAllMentors();
        final MentorAdapter adapter = new MentorAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewListMentor);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setMentors(allMentors);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem Item) {
        switch (Item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(Item);
    }

    public void goToEditNotes(View view) {
        Intent intent = new Intent(ListMentor.this, EditMentor.class);
        startActivity(intent);
    }

}