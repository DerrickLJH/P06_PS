package com.myapplicationdev.android.p06_ps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTasks;
    ArrayAdapter aa;
    ArrayList<Task> task;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTasks = this.findViewById(R.id.lvTasks);
        btnAdd = this.findViewById(R.id.btnAdd);

        DBHelper db = new DBHelper(MainActivity.this);
        task = db.getAllTask();


        aa = new TaskAdapter(this, R.layout.row, task);
        lvTasks.setAdapter(aa);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = Intent(MainActivity.this, AddActivity.class);
                //startActivity(intent);

            }
        });
    }
}
