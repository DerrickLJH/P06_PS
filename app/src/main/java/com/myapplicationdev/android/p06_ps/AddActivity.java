package com.myapplicationdev.android.p06_ps;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    int reqCode = 12345;
    EditText etName , etDesc ,ettime;
    Button btnadd , btncancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etName =findViewById(R.id.editTextName);
        etDesc = findViewById(R.id.editTextDesc);
        ettime= findViewById(R.id.editTexttime);
        btnadd = findViewById(R.id.btnAdd);
        btncancel=findViewById(R.id.btnCancel);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();
                int time = Integer.valueOf(ettime.getText().toString());
                DBHelper dbh = new DBHelper(AddActivity.this);
                Long row_affected =dbh.insertTask(name, desc,time);
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);
                Intent intent = new Intent(AddActivity.this,
                        ScheduledNotificationReceiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AddActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
                finish();
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
