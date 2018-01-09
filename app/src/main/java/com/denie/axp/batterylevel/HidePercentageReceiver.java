package com.denie.axp.batterylevel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by DeniE46 on 1/2/2018.
 */

public class HidePercentageReceiver extends BroadcastReceiver{

    HidePercentageViewListener listener = null;

    @Override
    public void onReceive(Context context, Intent intent) {


            if (listener != null) {
                listener.onHidePercentageViewListener();
            }

    }

    void setOnHidePercentageView(HidePercentageViewListener hidePercentageView){
        listener = hidePercentageView;
    }

}
