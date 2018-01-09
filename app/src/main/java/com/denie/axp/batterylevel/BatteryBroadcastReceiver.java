package com.denie.axp.batterylevel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by DeniE46 on 12/28/2017.
 * BroadcastReceiver for battery level
 */

public class BatteryBroadcastReceiver extends BroadcastReceiver {
    private static final String BATTERY_LEVEL = "level";
    private OnBatteryLevelReceivedListener listener = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BATTERY_LEVEL, 0);
        if(listener != null){
            listener.onBatteryLevelReceived(level);
        }
    }

    public void setOnBatteryLevel(OnBatteryLevelReceivedListener onBatteryLevel){
        listener = onBatteryLevel;
    }
}
