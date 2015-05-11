package com.example.michael.statstracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class EventReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, CreeperService.class);
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            serviceIntent.putExtra("screen_state", true);
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            serviceIntent.putExtra("screen_state", false);
        }
        context.startService(serviceIntent);
    }
}
