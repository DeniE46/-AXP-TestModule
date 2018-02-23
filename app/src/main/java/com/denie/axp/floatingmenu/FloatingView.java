package com.denie.axp.floatingmenu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.denie.axp.R;

import rjsv.floatingmenu.floatingmenubutton.FloatingMenuButton;
import rjsv.floatingmenu.floatingmenubutton.subbutton.FloatingSubButton;

/**
 * Created by DeniE46 on 1/15/2018.
 */

public class FloatingView extends LinearLayout {

    public FloatingView(Context context) {
        super(context);
        init();
    }

    public FloatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FloatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.floating_view, this);
        FloatingMenuButton floatingMenuButton = (FloatingMenuButton)view.findViewById(R.id.my_floating_button);
        floatingMenuButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "sub button 1", Toast.LENGTH_SHORT).show();
            }
        });
        FloatingSubButton floatingSubButton = (FloatingSubButton)view.findViewById(R.id.sub_button_1);
//        floatingSubButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "sub button 1", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
