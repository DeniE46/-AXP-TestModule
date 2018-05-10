package com.denie.axp.launcherDock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.denie.axp.R;

import java.util.ArrayList;


public class LauncherDockView extends LinearLayout implements LauncherRecyclerViewAdapter.AdapterCallback{
    RecyclerView recyclerView;
    ArrayList<LauncherModel> arrayList;
    LauncherRecyclerViewAdapter launcherRecyclerViewAdapter;
    boolean hasFilter = false;
    Intent intent;

    public LauncherDockView(Context context) {
        super(context);
        init();
    }

    public LauncherDockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LauncherDockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init(){
        View view = inflate(getContext(), R.layout.launcher_dock_view, this);
        arrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        collectLaunchers();
        launcherRecyclerViewAdapter = new LauncherRecyclerViewAdapter(getContext(), arrayList, LauncherDockView.this);
        recyclerView.setAdapter(launcherRecyclerViewAdapter);
        if (broadcastReceiver != null) {
            IntentFilter intentFilter = new IntentFilter("FILTER_LAUNCHER");
            getContext().registerReceiver(broadcastReceiver, intentFilter);
        }


    }

    void collectLaunchers(){
        final PackageManager pm = getContext().getPackageManager();
        ArrayList<ResolveInfo> applicationInfos = (ArrayList<ResolveInfo>) getContext().getPackageManager().queryIntentActivities(intent,0);
        for(ResolveInfo resolveInfo: applicationInfos){


            try {
                if(hasFilter){
                    if((!resolveInfo.activityInfo.packageName.contains("sonyericsson"))&&(!resolveInfo.activityInfo.packageName.contains("sonymobile"))){
                        arrayList.add(new LauncherModel(pm.getApplicationLabel(pm.getApplicationInfo(resolveInfo.activityInfo.packageName, PackageManager.GET_META_DATA)).toString(), pm.getApplicationIcon(resolveInfo.activityInfo.packageName), resolveInfo.activityInfo.packageName));
                        Toast.makeText(getContext(), "adding " + resolveInfo.activityInfo.packageName, Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    arrayList.add(new LauncherModel(pm.getApplicationLabel(pm.getApplicationInfo(resolveInfo.activityInfo.packageName, PackageManager.GET_META_DATA)).toString(), pm.getApplicationIcon(resolveInfo.activityInfo.packageName), resolveInfo.activityInfo.packageName));
                }

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            hasFilter = intent.getBooleanExtra("Value", false);
            Toast.makeText(getContext(), "filter received", Toast.LENGTH_SHORT).show();
            arrayList.clear();
            collectLaunchers();
            launcherRecyclerViewAdapter.notifyDataSetChanged();
            recyclerView.invalidate();
        }
    };


    @Override
    public void onItemClicked(int position) {
        //Toast.makeText(getContext(), "in custom view pos is " + position, Toast.LENGTH_SHORT).show();
        Intent intent = getContext().getPackageManager().getLaunchIntentForPackage(arrayList.get(position).getPackageName());
        getContext().startActivity(intent);
    }
}
