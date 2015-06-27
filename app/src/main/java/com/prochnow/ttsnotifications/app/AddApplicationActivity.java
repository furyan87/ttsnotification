package com.prochnow.ttsnotifications.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.prochnow.ttsnotifications.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by prochnow on 25.06.15.
 */
public class AddApplicationActivity extends AppCompatActivity {

    private final String LOG_TAG = AddApplicationActivity.class.getSimpleName();

    @InjectView(R.id.searchView) SearchView searchView;
    @InjectView(R.id.toolbar) Toolbar toolbar;

    private List<AppInfo> appList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addapp);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        searchView.setFocusable(false);

        initInstances();


    }

    private void initInstances() {


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onResume() {
        super.onResume();
        // Set title
//        getSupportActionBar().setTitle(R.string.appTitle);
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
