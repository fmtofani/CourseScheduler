package com.student.wgu.C196.ftofani.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.student.wgu.C196.ftofani.Database.Repository;
import com.student.wgu.C196.ftofani.Entities.Term;
import com.student.wgu.C196.ftofani.R;

import java.util.List;

public class ListTerm extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);
        //add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        repository=new Repository(getApplication());
        final TermAdapter termAdapter=new TermAdapter(this);
        List<Term> allTerms = repository.getAllTerms();

        RecyclerView recyclerView=findViewById((R.id.recyclerviewListTerm));
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);

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

    public void goToEditTerms(View view) {
        Intent intent = new Intent(ListTerm.this, EditTerm.class);
        startActivity(intent);
    }
}