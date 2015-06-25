package com.prochnow.ttsnotifications;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by prochnow on 24.06.15.
 */
public class TTSService extends Service implements TextToSpeech.OnInitListener {

    private static boolean TTSFLAG = false;
    private final String LOG_TAG = TTSService.class.getSimpleName();

    private TextToSpeech mTts;
    private String spokenText;

    HashMap<String, String> map = new HashMap<String, String>();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        spokenText = intent.getStringExtra("msgText");

        if (TTSFLAG) {
            mTts.speak(spokenText, TextToSpeech.QUEUE_ADD, map);
        }
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        mTts = new TextToSpeech(this, this);
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "UniqueID");
        mTts.setOnUtteranceProgressListener(new NotificationUtteranceProgressListener());
        // This is a good place to set spokenText
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = mTts.setLanguage(Locale.US);
            if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                mTts.speak(spokenText, TextToSpeech.QUEUE_FLUSH, map);

                TTSFLAG = true;
            }
        }
    }

    @Override
    public void onDestroy() {
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }


    private class NotificationUtteranceProgressListener extends UtteranceProgressListener {
        @Override
        public void onStart(String s) {

        }

        @Override
        public void onDone(String s) {

        }

        @Override
        public void onError(String s) {

        }
    }
}
