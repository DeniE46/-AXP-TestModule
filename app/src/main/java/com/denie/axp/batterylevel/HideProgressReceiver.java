package com.denie.axp.batterylevel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class HideProgressReceiver extends BroadcastReceiver {

    HideProgressViewListener listener = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(listener != null) {
            listener.onHideProgressViewListener();
        }
    }

    public void setOnHideProgressListener(HideProgressViewListener hideProgressListener){
        listener = hideProgressListener;
    }
}
