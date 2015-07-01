package com.prochnow.ttsnotifications.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.prochnow.ttsnotifications.MainActivity;
import com.prochnow.ttsnotifications.receiver.NotificationBroadcastReceiver;
import com.prochnow.ttsnotifications.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {


    private final String LOG_TAG = HomeFragment.class.getSimpleName();

    @Bind(R.id.serviceEnablerSwitch) Switch serviceEnabledSwitch;


    private static NotificationBroadcastReceiver receiver;

    SharedPreferences editor;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editor = getActivity().getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        initInstances();


        return rootView;
    }

    private void initInstances() {
        boolean isServiceRunning = editor.getBoolean(getString(R.string.serviceEnabledKey), false);
        serviceEnabledSwitch.setChecked(isServiceRunning);

        FloatingActionButton floatingActionButton = ButterKnife.findById(getActivity(), R.id.fabBtn);
        floatingActionButton.setVisibility(View.GONE);

    }

    public String getTitle() {
        return getResources().getString(R.string.homeTitle);
    }

    @OnCheckedChanged(R.id.serviceEnablerSwitch)
    void onCheckedChanged(boolean checked) {
        if (checked) {
            Log.d(LOG_TAG, "Register receiver");
            editor.edit().putBoolean(getString(R.string.serviceEnabledKey), true).apply();

        } else {
            editor.edit().putBoolean(getString(R.string.serviceEnabledKey), false).apply();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        // Set title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.homeTitle);
    }

//    @OnClick(R.id.speakButton)
//    void onClick() {
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(getActivity())
//                        .setSmallIcon(R.drawable.notification_template_icon_bg)
//                        .setContentTitle("My notification")
//                        .setContentText("Hello World!");
//        // Sets an ID for the notification
//        int mNotificationId = 001;
//// Gets an instance of the NotificationManager service
//        NotificationManager mNotifyMgr =
//                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//// Builds the notification and issues it.
//        mNotifyMgr.notify(mNotificationId, mBuilder.build());
//        Log.d(LOG_TAG, "onClick ");
//
//    }


}
