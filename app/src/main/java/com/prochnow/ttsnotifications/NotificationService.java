package com.prochnow.ttsnotifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.speech.tts.TextToSpeech;
import android.util.Log;

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
        String title = extras.getString("android.title");
        //String text = extras.getCharSequence("android.text").toString();

        Log.i("Title", title);
        //Log.i("Text", text);

        Intent msgrcv = new Intent("Msg");

        msgrcv.putExtra("title", title);
        //msgrcv.putExtra("text", text);
        Log.d(LOG_TAG, "onNotificationPosted ");
        getApplicationContext().sendBroadcast(msgrcv);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }
}
