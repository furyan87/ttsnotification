package com.prochnow.ttsnotifications.service;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.prochnow.ttsnotifications.R;

/**
 * Created by prochnow on 21.06.15.
 */
public class NotificationService extends NotificationListenerService implements TextToSpeech.OnInitListener {

    private final String LOG_TAG = NotificationService.class.getSimpleName();
    SharedPreferences editor;


    @Override
    public void onCreate() {
        super.onCreate();
        editor = getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
    }

    @Override
    public void onInit(int i) {

    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        boolean serviceEnabeld = editor.getBoolean(getString(R.string.serviceEnabledKey), false);

        if (!serviceEnabeld) {
            return;
        }

        Bundle extras = sbn.getNotification().extras;
        String notificationTitle = extras.getString(Notification.EXTRA_TITLE);
        CharSequence notificationText = extras.getCharSequence(Notification.EXTRA_TEXT);
        CharSequence notificationSubText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT);
        String packageName = sbn.getPackageName();


        Intent msgrcv = new Intent("Msg");

        //        msgrcv.putExtra("title", title);
        //msgrcv.putExtra("text", text);
        Log.d(LOG_TAG, "onNotificationPosted ");
        getApplicationContext().sendBroadcast(msgrcv);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }
}
