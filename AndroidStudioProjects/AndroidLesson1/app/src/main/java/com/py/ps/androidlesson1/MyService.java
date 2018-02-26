package com.py.ps.androidlesson1;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public ComponentName startService(Intent service) {
        super.startService(service);
        Log.d("MyService", "startService");

        Toast.makeText(this, "Service started", Toast.LENGTH_LONG).show();
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d("MyService", "onStartCommand");

        Toast.makeText(this, "onStartCommand", Toast.LENGTH_LONG).show();
        return 1;
    }

    @Override
    public boolean stopService(Intent name) {

        Log.d("MyService", "stopService");

        Toast.makeText(this, "stopService", Toast.LENGTH_LONG).show();
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        Log.d("MyService", "onDestroy");

        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();

        super.onDestroy();
    }
}
