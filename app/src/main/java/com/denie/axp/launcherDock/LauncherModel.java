package com.denie.axp.launcherDock;

import android.graphics.drawable.Drawable;


public class LauncherModel {
    private String name;
    private Drawable icon;
    private String packageName;

    LauncherModel(String name, Drawable icon, String pckName) {
        this.name = name;
        this.icon = icon;
        this.packageName = pckName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
