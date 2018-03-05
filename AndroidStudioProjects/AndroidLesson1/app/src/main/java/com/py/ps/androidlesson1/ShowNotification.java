package com.py.ps.androidlesson1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class ShowNotification extends AppCompatActivity {

    private NotificationManager nManager;
    private static String Channel_ID = "1000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notification);

        String ns = Context.NOTIFICATION_SERVICE;
        nManager = (NotificationManager)getSystemService(ns);

        final Notification msg = new Notification(R.drawable.common_google_signin_btn_icon_dark_normal,
                "New event of importance", System.currentTimeMillis());

        Button start = findViewById(R.id.startNotification);
        Button cancel = findViewById(R.id.cancelNotification);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent msgIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pearson.com"));
                msgIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent pIntent = PendingIntent.getActivity(ShowNotification.this, 0, msgIntent,
                        0);

//                msg.defaults |= Notification.DEFAULT_SOUND;
//                msg.flags |= Notification.FLAG_AUTO_CANCEL;


                android.support.v4.app.NotificationCompat.Builder builder = new
                        android.support.v4.app.NotificationCompat.Builder(getApplicationContext(), Channel_ID);
                builder.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_normal);
                builder.setContentTitle("New Notification from ShowNotification");
                builder.setContentText("Content Text");
                builder.setContentIntent(pIntent);

                //Create a channel and register with the system

                nManager.notify();

            }
        });

    }
}
