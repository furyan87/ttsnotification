package com.prochnow.ttsnotifications;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.prochnow.ttsnotifications.model.AppInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prochnow on 25.06.15.
 */
public class AddApplicationActivity extends AppCompatActivity {

    private final String LOG_TAG = AddApplicationActivity.class.getSimpleName();

    @Bind(R.id.searchView) SearchView searchView;
    @Bind(R.id.toolbar) Toolbar toolbar;

    private List<AppInfo> appList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addapp);
        ButterKnife.bind(this);
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
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
