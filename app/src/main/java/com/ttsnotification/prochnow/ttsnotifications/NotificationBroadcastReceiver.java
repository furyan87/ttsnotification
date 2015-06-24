package com.ttsnotification.prochnow.ttsnotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.util.Log;

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
//            TableRow tr = new TableRow(getApplicationContext());
//            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//            TextView textview = new TextView(getApplicationContext());
//            textview.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
//            textview.setTextSize(20);
//            textview.setTextColor(Color.parseColor("#0B0719"));
//            textview.setText(Html.fromHtml(pack + "<br><b>" + title + " : </b>" + text));
//            tr.addView(textview);
//            tab.addView(tr);

        Intent intent = new Intent(context, TTSService.class);
        intent.putExtra("msgText", title);

        Log.d(LOG_TAG, "Start TTS Service");
        context.startService(intent);


    }
}
