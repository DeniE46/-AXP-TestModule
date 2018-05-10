package com.denie.axp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StartNotesButtonReceiver extends BroadcastReceiver {
    SetNotesVisibility setNotesVisibility = new SetNotesVisibility();


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Got new intent!", Toast.LENGTH_SHORT).show();
        SetNotesVisibility.setNotesVisible(false);
    }
}
