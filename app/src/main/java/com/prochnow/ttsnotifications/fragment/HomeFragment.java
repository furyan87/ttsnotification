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
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.prochnow.ttsnotifications.MainActivity;
import com.prochnow.ttsnotifications.R;
import com.prochnow.ttsnotifications.receiver.NotificationBroadcastReceiver;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {


    private final String LOG_TAG = HomeFragment.class.getSimpleName();

    @Bind(R.id.serviceEnablerSwitch) Switch serviceEnabledSwitch;
    @Bind(R.id.settingsContent) LinearLayout settingsContent;


    private static NotificationBroadcastReceiver receiver;
    LayoutInflater inflater;
    View rootView;

    SharedPreferences editor;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editor = getActivity().getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        this.inflater = inflater;
        initInstances();


        return rootView;
    }

    private void initInstances() {
        boolean isServiceRunning = editor.getBoolean(getString(R.string.serviceEnabledKey), false);
        serviceEnabledSwitch.setChecked(isServiceRunning);

        FloatingActionButton floatingActionButton = ButterKnife.findById(getActivity(), R.id.fabBtn);
        floatingActionButton.setVisibility(View.GONE);

        addSettingsItem();

    }

    private void addSettingsItem() {

        View defaultModeItem = inflater.inflate(R.layout.list_row_main_settings, settingsContent, false);
        TextView defaultModeLabel = ButterKnife.findById(defaultModeItem, R.id.settingsTitle);
        defaultModeLabel.setText(getString(R.string.defaultModeLabel));
        TextView defaultModeDescr = ButterKnife.findById(defaultModeItem, R.id.settingsDesc);
        defaultModeDescr.setText(getString(R.string.defaultModeDesc));
        settingsContent.addView(defaultModeItem);

        View defaultNotificationItem = inflater.inflate(R.layout.list_row_main_settings, settingsContent, false);
        TextView defaultNotificationLabel = ButterKnife.findById(defaultNotificationItem, R.id.settingsTitle);
        defaultNotificationLabel.setText(getString(R.string.defaultNotificationLabel));
        TextView defaultNotificationDescr = ButterKnife.findById(defaultNotificationItem, R.id.settingsDesc);
        defaultNotificationDescr.setText(getString(R.string.defaultNotificationDesc));
        settingsContent.addView(defaultNotificationItem);

        View defaultTemplateItem = inflater.inflate(R.layout.list_row_main_settings, settingsContent, false);
        TextView defaultTemplateLabel = ButterKnife.findById(defaultTemplateItem, R.id.settingsTitle);
        defaultTemplateLabel.setText(getString(R.string.defaultTemplateLabel));
        TextView defaultTemplateDescr = ButterKnife.findById(defaultTemplateItem, R.id.settingsDesc);
        defaultTemplateDescr.setText(getString(R.string.defaultTemplateDesc));
        settingsContent.addView(defaultTemplateItem);

        View audioStreamItem = inflater.inflate(R.layout.list_row_main_settings, settingsContent, false);
        TextView audioStreamLabel = ButterKnife.findById(audioStreamItem, R.id.settingsTitle);
        audioStreamLabel.setText(getString(R.string.audioStreamTitle));
        TextView audioStreamDescr = ButterKnife.findById(audioStreamItem, R.id.settingsDesc);
        audioStreamDescr.setText(getString(R.string.audioStreamDesc));
        settingsContent.addView(audioStreamItem);

        View deviceStateItem = inflater.inflate(R.layout.list_row_main_settings, settingsContent, false);
        TextView deviceStateLabel = ButterKnife.findById(deviceStateItem, R.id.settingsTitle);
        deviceStateLabel.setText(getString(R.string.deviceStateTitle));
        TextView deviceStateDescr = ButterKnife.findById(deviceStateItem, R.id.settingsDesc);
        deviceStateDescr.setText(getString(R.string.deviceStateDesc));
        settingsContent.addView(deviceStateItem);


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
