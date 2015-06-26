package com.prochnow.ttsnotifications.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

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

    @InjectView(R.id.appListView) RecyclerView appListView;
    @InjectView(R.id.searchView) SearchView searchView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.progressBar) ProgressBar progressBar;

    private List<AppInfo> appList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addapp);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        mLayoutManager = new LinearLayoutManager(this);
        appListView.setLayoutManager(mLayoutManager);

        searchView.setFocusable(false);
        initInstances();


    }

    private void initInstances() {
        LoadAppTask load = new LoadAppTask(this);
        load.execute();

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

    private class LoadAppTask extends AsyncTask<Void, Void, ArrayList<AppInfo>> {


        Context context;

        public LoadAppTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<AppInfo> doInBackground(Void... voids) {
            ArrayList<AppInfo> appList;
            final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            PackageManager pkgManager = context.getPackageManager();
            List<ApplicationInfo> pkgAppsList = pkgManager.getInstalledApplications(0);
            appList = new ArrayList<>(pkgAppsList.size());
            //TODO 25.06.15 filter already added apps
            for (ApplicationInfo info : pkgAppsList) {
                appList.add(new AppInfo(pkgManager.getApplicationLabel(info).toString(), info.packageName, pkgManager.getApplicationIcon(info)));
            }

            return appList;
        }

        @Override
        protected void onPostExecute(ArrayList<AppInfo> appInfos) {
            Log.d(LOG_TAG, "onPostExecute ");
            progressBar.setVisibility(View.GONE);
            appListView.setHasFixedSize(true);

            // specify an adapter (see also next example)
            mAdapter = new AddAppRecyclerViewAdapter(appInfos);
            appListView.setAdapter(mAdapter);

            mAdapter.notifyDataSetChanged();

        }
    }
}
