package com.ttsnotification.prochnow.ttsnotifications;

import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by prochnow on 21.06.15.
 */
public class NotificationService extends NotificationListenerService implements TextToSpeech.OnInitListener {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onInit(int i) {

    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.title");
        //String text = extras.getCharSequence("android.text").toString();

        Log.i("Title", title);
        //Log.i("Text", text);

        Intent msgrcv = new Intent("Msg");

        msgrcv.putExtra("title", title);
        //msgrcv.putExtra("text", text);

        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(msgrcv);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }
}
