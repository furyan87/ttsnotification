package com.prochnow.ttsnotifications.app;

import android.graphics.drawable.Drawable;

/**
 * Created by prochnow on 25.06.15.
 */
public class AppInfo {

    public String name;
    public String packageString;
    public Drawable icon;
    boolean selected = false;

    public AppInfo(String name, String packageString, Drawable icon) {
        this.name = name;
        this.packageString = packageString;
        this.icon = icon;
    }
}
