package com.denie.axp.batterylevel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denie.axp.R;

/**
 * Created by DeniE46 on 12/2/2017.
 * Battery percentage custom view
 */

@SuppressLint("AppCompatCustomView")
public class BatteryPercentage extends LinearLayout{

    TextView textView;
    ProgressBar progressBar;
    BatteryBroadcastReceiver batteryBroadcastReceiver;
    HidePercentageReceiver hidePercentageReceiver;
    HideProgressReceiver hideProgressReceiver;
    boolean percentageIsHidden, progressIsHidden;

    public BatteryPercentage(Context context) {
        super(context);
        init();
    }

    public BatteryPercentage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BatteryPercentage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.battery_gauge, this);

        textView = (TextView) findViewById(R.id.text_level);
        progressBar = (ProgressBar) findViewById(R.id.progress_level);
        this.percentageIsHidden = true;
        this.progressIsHidden = true;

        batteryBroadcastReceiver = new BatteryBroadcastReceiver();
        batteryBroadcastReceiver.setOnBatteryLevel(new OnBatteryLevelReceivedListener() {
            @Override
            public void onBatteryLevelReceived(int level) {
                textView.setText(level + "%");
                progressBar.setProgress(level);
            }
        });

        hidePercentageReceiver = new HidePercentageReceiver();
        hidePercentageReceiver.setOnHidePercentageView(new HidePercentageViewListener() {
            @Override
            public void onHidePercentageViewListener() {
                if(percentageIsHidden){
                    textView.setVisibility(GONE);
                    percentageIsHidden = false;
                }
                else{
                    textView.setVisibility(VISIBLE);
                    percentageIsHidden = true;
                }
            }
        });

        hideProgressReceiver = new HideProgressReceiver();
        hideProgressReceiver.setOnHideProgressListener(new HideProgressViewListener() {
            @Override
            public void onHideProgressViewListener() {
                if(progressIsHidden){
                    progressBar.setVisibility(GONE);
                    progressIsHidden = false;
                }
                else{
                    progressBar.setVisibility(VISIBLE);
                    progressIsHidden = true;
                }
            }
        });

        IntentFilter batteryChangedFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        IntentFilter hidePercentageFilter = new IntentFilter("HIDE_NOTES_BUTTON");
        IntentFilter hideProgressFilter = new IntentFilter("HIDE_PROGRESS_VIEW");
        getContext().registerReceiver(batteryBroadcastReceiver, batteryChangedFilter);
        getContext().registerReceiver(hidePercentageReceiver, hidePercentageFilter);
        getContext().registerReceiver(hideProgressReceiver, hideProgressFilter);
    }



}
