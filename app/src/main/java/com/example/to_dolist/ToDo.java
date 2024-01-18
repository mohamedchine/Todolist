package com.example.to_dolist;

import static com.example.to_dolist.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ToDo extends AppCompatActivity {
     ListView l ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.accueil);
        l = findViewById(R.id.todolist);
            sqlitedatabasescheme db = new sqlitedatabasescheme(this);
            db.repopulate();
        myadapter ad = new myadapter(this, task.tasklist);
        l.setAdapter(ad);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                task selectedTask = task.tasklist.get(position);
                Intent intent = new Intent(ToDo.this, tskdetail.class);
                intent.putExtra("taskId", selectedTask.getId());
                startActivity(intent);
            }
        });
    }

    public void newTask(View view) {
        Intent i = new Intent(this, tskdetail.class);
        startActivity(i);
    }

}