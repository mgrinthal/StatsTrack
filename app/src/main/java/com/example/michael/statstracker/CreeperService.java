package com.example.michael.statstracker;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class CreeperService extends Service {

    private final String TAG = "CreeperService";
    private BroadcastReceiver mReceiver = new EventReceiver();
    private ContentValues content_values;
    private SQLiteDatabase db;
    //private String filename = "stat_storage";
    //private FileOutputStream outputStream;
    //protected BufferedReader buffStream;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "We created a CreeperService!");
        // register receiver that handles screen on and screen off logic
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver, filter);
        StatsDbHelper dbHelper = new StatsDbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        content_values = new ContentValues();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean screenOn = intent.getBooleanExtra("screen_state", false);
        Long time =  System.currentTimeMillis();
        content_values.put(StatsContract.StatsEntry.COLUMN_TIME, time);
        if (!screenOn) {
            content_values.put(StatsContract.StatsEntry.COLUMN_TYPE, "screen_on");
        } else {
            content_values.put(StatsContract.StatsEntry.COLUMN_TYPE, "screen_off");
        }
        db.insert(StatsContract.StatsEntry.TABLE_NAME, null, content_values);
        return 0;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
