package com.denie.axp;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class AXPService extends Service {

    BroadcastReceiver broadcastReceiver;
    private static final String BATTERY_LEVEL = "level";

    public AXPService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "AXP App Service started", Toast.LENGTH_SHORT).show();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "onReceive in Service", Toast.LENGTH_SHORT).show();
                int level = intent.getIntExtra(BATTERY_LEVEL, 0);
                Toast.makeText(context, level + "% in service", Toast.LENGTH_SHORT).show();
            }
        };
        registerReceiver(broadcastReceiver, intentFilter);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return Service.START_STICKY;
        //TODO: add notification to status bar when the services are running (small falcon icon) will go to the foreground
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
