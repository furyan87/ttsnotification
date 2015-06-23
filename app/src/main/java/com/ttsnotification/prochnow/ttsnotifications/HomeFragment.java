package com.ttsnotification.prochnow.ttsnotifications;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {


    private final String LOG_TAG = HomeFragment.class.getSimpleName();

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);


        return rootView;
    }

    public String getTitle() {
        return getResources().getString(R.string.homeTitle);
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
