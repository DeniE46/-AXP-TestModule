package com.denie.axp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class StartNotesButton extends FrameLayout implements OnClickListener {

    String appPackage = "com.sonyericsson.notes";
    PackageManager pm = getContext().getPackageManager();


    public StartNotesButton(Context context) {
        super(context);
        init();
    }

    public StartNotesButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StartNotesButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        setOnClickListener(this);
        IntentFilter intentFilter = new IntentFilter("SET_BUTTONS_VISIBILITY");
        getContext().registerReceiver(broadcastReceiver, intentFilter);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int mode = intent.getIntExtra("State", 0);
            switch(mode){
                case 0:
                    setVisibility(GONE);
                    break;
                case 1:
                    setVisibility(VISIBLE);
                    break;
                case 2:
                    appPackage = "com.denie.axp";
                    break;
                case 3:
                    appPackage = "com.sonyericsson.notes";
                    break;
            }

        }
    };


    @Override
    public void onClick(View view) {
        StartNotes();
        CloseNotificationPanel();
    }


    public void CloseNotificationPanel() {
        Intent intent = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        getContext().sendBroadcast(intent);

    }

    public void StartNotes() {
        if (IsPackageInstalled(appPackage, pm)) {
            Intent intent = getContext().getPackageManager().getLaunchIntentForPackage(appPackage);
            getContext().startActivity(intent);
        } else {
            Toast.makeText(getContext(), "App is not installed", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean IsPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
