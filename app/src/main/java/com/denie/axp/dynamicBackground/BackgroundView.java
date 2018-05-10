package com.denie.axp.dynamicBackground;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.denie.axp.R;



public class BackgroundView extends RelativeLayout {
    public BackgroundView(Context context) {
        super(context);
        this.init();
    }

    public BackgroundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public BackgroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }


    void init(){
        initReceiver();

    }

    @SuppressLint("RestrictedApi")
    void initReceiver(){
        @SuppressLint("RestrictedApi") final ContextThemeWrapper wrapper = new ContextThemeWrapper(getContext(), R.style.DayLight);
        changeTheme(wrapper.getTheme());
        if(broadcastReceiver != null){
            IntentFilter intentFilter = new IntentFilter("CHANGE_WALL");
            getContext().registerReceiver(broadcastReceiver, intentFilter);
        }
    }

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onReceive(Context context, Intent intent) {
                int mode = intent.getIntExtra("Value", 0);
                if(mode == 0){
                    @SuppressLint("RestrictedApi") final ContextThemeWrapper wrapper = new ContextThemeWrapper(getContext(), R.style.DayLight);
                    changeTheme(wrapper.getTheme());
                }
                else{
                    final ContextThemeWrapper wrapper = new ContextThemeWrapper(getContext(), R.style.NightLight);
                    changeTheme(wrapper.getTheme());
                }
            }
        };

    void changeTheme(@SuppressLint("SupportAnnotationUsage") @StyleRes final Resources.Theme theme){
        @SuppressLint("RestrictedApi") final Drawable drawable = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.ic_mountain_landscape, theme);
        setBackground(drawable);
    }
}
