package com.denie.axp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AXPReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent axpService = new Intent(context, AXPService.class);
        context.startService(axpService);
    }
}
