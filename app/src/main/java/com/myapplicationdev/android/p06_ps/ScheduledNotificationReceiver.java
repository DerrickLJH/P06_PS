package com.myapplicationdev.android.p06_ps;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;

public class ScheduledNotificationReceiver extends BroadcastReceiver {

    int reqCode = 12345;
    ArrayList<Task> al;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new
                    NotificationChannel("default","Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel);
        }
        DBHelper db = new DBHelper(context);
        al.addAll(db.getAllTask());
        db.close();

        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, reqCode, i, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.setBigContentTitle(al.get(-1).getName());// Retrieve name from db;
        bigText.bigText(al.get(-1).getDesc());// Retrieve desc from db;

        // build notification
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(context,"default");
        builder.setContentTitle("Task Manager Reminder");
        builder.setContentText(al.get(-1).getName()); // Retrieve name from db.
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setContentIntent(pIntent);
        builder.setStyle(bigText);
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);
        Uri uri= RingtoneManager.getDefaultUri
                (RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);
        long[] vibrate = {500,1000};
        builder.setVibrate(vibrate);
        builder.setLights(Color.RED, 300, 100);

        Notification n = builder.build();
        notificationManager.notify(123,n);
    }
}
