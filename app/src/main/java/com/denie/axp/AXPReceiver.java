package com.denie.axp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by DeniE46 on 11/18/2017.
 */

public class AXPReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "AXP Service started", Toast.LENGTH_SHORT).show();
        Intent axpService = new Intent(context, AXPService.class);
        context.startService(axpService);
    }


}
