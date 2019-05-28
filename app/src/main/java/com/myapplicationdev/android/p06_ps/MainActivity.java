package com.myapplicationdev.android.p06_ps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        task = new ArrayList<Task>();
        task.add(new Task("1 Buy Milk", "Low Fat"));


        aa = new TaskAdapter(this, R.layout.row, task);
        lvTasks.setAdapter(aa);
    }
}
