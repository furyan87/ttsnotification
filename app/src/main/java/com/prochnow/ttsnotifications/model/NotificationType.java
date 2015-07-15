package com.prochnow.ttsnotifications.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by prochnow on 03.07.15.
 */
public class NotificationType {

    @DatabaseField(generatedId = true) public int id;

    @DatabaseField String templateString;
    //
    @DatabaseField boolean packageNameOutput = false;

    @DatabaseField boolean notificationTitleOutput = false;

    @DatabaseField boolean notificationTextOutput = false;

    @DatabaseField boolean notificationSubtextOutput = false;

    @DatabaseField boolean isDefaultAttributes = true;

    @DatabaseField boolean isDefaultTemplate = true;


    public NotificationType() {

    }

    public String getTemplateString() {
        return templateString;
    }

    public void setTemplateString(String templateString) {
        this.templateString = templateString;
    }

    private boolean isDefaultTemplate() {
        return isDefaultTemplate;
    }

    private void setIsDefaultTemplate(boolean isDefaultTemplate) {
        this.isDefaultTemplate = isDefaultTemplate;
    }

    private boolean isDefaultAttributes() {
        return isDefaultAttributes;
    }

    private void setIsDefaultAttributes(boolean isDefaultAttributes) {
        this.isDefaultAttributes = isDefaultAttributes;
    }

    public boolean isPackageNameOutput() {
        return packageNameOutput;
    }

    public void setPackageNameOutput(boolean packageNameOutput) {
        this.packageNameOutput = packageNameOutput;
    }

    public boolean isNotificationTitleOutput() {
        return notificationTitleOutput;
    }

    public void setNotificationTitleOutput(boolean notificationTitleOutput) {
        this.notificationTitleOutput = notificationTitleOutput;
    }

    public boolean isNotificationTextOutput() {
        return notificationTextOutput;
    }

    public void setNotificationTextOutput(boolean notificationTextOutput) {
        this.notificationTextOutput = notificationTextOutput;
    }

    public boolean isNotificationSubtextOutput() {
        return notificationSubtextOutput;
    }

    public void setNotificationSubtextOutput(boolean notificationSubtextOutput) {
        this.notificationSubtextOutput = notificationSubtextOutput;
    }

}
