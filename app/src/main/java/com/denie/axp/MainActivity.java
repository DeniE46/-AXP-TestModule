package com.denie.axp;


import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.denie.axp.launcherDock.LauncherModel;
import com.denie.axp.launcherDock.LauncherRecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    ArrayList<LauncherModel> arrayList;
    LauncherRecyclerViewAdapter launcherRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        arrayList = new ArrayList<>();
//        final PackageManager pm = getPackageManager();
//        recyclerView = findViewById(R.id.recycler_view);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        ArrayList<ResolveInfo> applicationInfos = (ArrayList<ResolveInfo>) getPackageManager().queryIntentActivities(intent,0);
//        for(ResolveInfo resolveInfo: applicationInfos){
//
//
//            try {
//                arrayList.add(new LauncherModel(pm.getApplicationLabel(pm.getApplicationInfo(resolveInfo.activityInfo.packageName, PackageManager.GET_META_DATA)).toString(), pm.getApplicationIcon(resolveInfo.activityInfo.packageName), resolveInfo.activityInfo.packageName));
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        launcherRecyclerViewAdapter = new LauncherRecyclerViewAdapter(this, arrayList, MainActivity.this);
//        recyclerView.setAdapter(launcherRecyclerViewAdapter);



    }


    public boolean IsPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    void appOne(View v){
        if (IsPackageInstalled("com.sonyericsson.android.camera", getPackageManager())) {
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.sonyericsson.android.camera");
            startActivity(intent);
        } else {
            Toast.makeText(this, "App is not installed", Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    public void onItemClicked(int position) {
//        Toast.makeText(this, "in activity, position is " + position, Toast.LENGTH_SHORT).show();
//        //Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage(arrayList.get(position).getPackageName());
//        //startActivity(intent);
//    }
}
