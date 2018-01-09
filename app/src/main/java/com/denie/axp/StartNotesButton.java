package com.denie.axp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.Toast;


public class StartNotesButton extends FrameLayout implements OnClickListener {

    String AppPackage = "com.sonyericsson.notes";
    PackageManager pm = getContext().getPackageManager();
    SetNotesVisibility setNotesVisibility;

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


    public void init(){
      setOnClickListener(this);
        setNotesVisibility = new SetNotesVisibility();
    }


    @Override
    public void onClick(View view) {
        StartNotes();
        CloseNotificationPanel();
    }


    public void CloseNotificationPanel(){
        Intent intent = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        getContext().sendBroadcast(intent);

    }

    public void StartNotes(){
        if(IsPackageInstalled(AppPackage, pm)) {
            Intent intent = getContext().getPackageManager().getLaunchIntentForPackage(AppPackage);
            getContext().startActivity(intent);
        }
        else{
            Toast.makeText(getContext(),"App is not installed", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean IsPackageInstalled(String packageName, PackageManager packageManager){
        try{
           packageManager.getPackageInfo(packageName,PackageManager.GET_ACTIVITIES);
           return true;
        }
        catch(PackageManager.NameNotFoundException e){
           return false;
        }
    }


}
