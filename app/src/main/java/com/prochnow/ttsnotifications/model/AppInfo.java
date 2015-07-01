package com.prochnow.ttsnotifications.model;

import android.graphics.drawable.Drawable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by prochnow on 25.06.15.
 */
public class AppInfo {

    @DatabaseField(id = true) private String packageString;

    @DatabaseField private String name;

    @DatabaseField private boolean active = false;

    private Drawable icon;

    private boolean selected = false;

    public AppInfo() {

    }

    public AppInfo(String name, String packageString, Drawable icon) {
        this.name = name;
        this.packageString = packageString;
        this.icon = icon;
    }

    public String getPackageString() {
        return packageString;
    }

    public void setPackageString(String packageString) {
        this.packageString = packageString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean toggleSelection() {
        selected = !selected;
        return selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
