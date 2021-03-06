package com.prochnow.ttsnotifications.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.prochnow.ttsnotifications.service.TTSService;

/**
 * Created by prochnow on 24.06.15.
 */
public class NotificationBroadcastReceiver extends BroadcastReceiver {

    private final String LOG_TAG = NotificationBroadcastReceiver.class.getSimpleName();

    //TTS object
    private TextToSpeech myTTS;
    //status check code
    private int MY_DATA_CHECK_CODE = 0;


    @Override
    public void onReceive(Context context, Intent msgIntent) {


        String pack = msgIntent.getStringExtra("package");
        String title = msgIntent.getStringExtra("title");
        String text = msgIntent.getStringExtra("text");

        Log.d(LOG_TAG, "onReceive ");


        Intent intent = new Intent(context, TTSService.class);
        intent.putExtra("msgText", title);

        Log.d(LOG_TAG, "Start TTS Service");
        context.startService(intent);


    }
}
