package com.prochnow.ttsnotifications;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.prochnow.ttsnotifications.fragment.NotificationOptionsFragment;
import com.prochnow.ttsnotifications.fragment.TemplateOptionsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prochnow on 03.07.15.
 */
public class NotificationPrefsActivity extends AppCompatActivity {

    private final String LOG_TAG = NotificationPrefsActivity.class.getSimpleName();

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_prefs);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initInstances();
    }


    private void initInstances() {
        boolean startTemplateFragment = getIntent().getExtras().getBoolean("template");

        if (startTemplateFragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            TemplateOptionsFragment fragment = new TemplateOptionsFragment();
            fragmentTransaction.replace(R.id.contentArea, fragment, null);
            fragmentTransaction.commit();
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            NotificationOptionsFragment fragment = new NotificationOptionsFragment();
            fragmentTransaction.replace(R.id.contentArea, fragment, null);
            fragmentTransaction.commit();
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
