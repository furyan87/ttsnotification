package com.prochnow.ttsnotifications.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.prochnow.ttsnotifications.AddApplicationActivity;
import com.prochnow.ttsnotifications.R;
import com.prochnow.ttsnotifications.adapter.ActiveAppsRecyclerViewAdapter;
import com.prochnow.ttsnotifications.model.AppInfo;
import com.prochnow.ttsnotifications.model.DatabaseHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prochnow on 23.06.15.
 */
public class AppFragment extends Fragment {

    private final String LOG_TAG = AppFragment.class.getSimpleName();

    public static final String NUMBER_OF_ADDITIONS = "numberOfAdditions";
    public static final int PICK_NEW_APPS = 1;

    @Bind(R.id.appListView) RecyclerView appListView;


    List<AppInfo> list;
    View rootView;
    private DatabaseHelper databaseHelper = null;
    private ActiveAppsRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<AppInfo> activeApps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_app, container, false);
        ButterKnife.bind(this, rootView);
        initInstances();

        return rootView;
    }

    private void initInstances() {
        loadActiveApps();

        initRecyclerView();

        FloatingActionButton fabBtn = ButterKnife.findById(getActivity(), R.id.fabBtn);
        fabBtn.setVisibility(View.VISIBLE);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddApplicationActivity.class);
                startActivityForResult(intent, PICK_NEW_APPS);
            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            int appNumber = data.getIntExtra(NUMBER_OF_ADDITIONS, 0);
            //TODO 01.07.15 implement undo for adding operation in snackbar 
            Snackbar.make(rootView, "Added " + appNumber + " apps", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }


    private void loadActiveApps() {
        activeApps = getHelper().getAppInfoRuntimeDao().queryForAll();

        for (AppInfo app : activeApps) {
            try {
                ApplicationInfo applicationInfo = getActivity().getPackageManager().getApplicationInfo(app.getPackageString(), PackageManager.GET_META_DATA);
                app.setIcon(getActivity().getPackageManager().getApplicationIcon(applicationInfo));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void initRecyclerView() {

        mLayoutManager = new LinearLayoutManager(getActivity());
        appListView.setLayoutManager(mLayoutManager);
        appListView.setHasFixedSize(true);
        mAdapter = new ActiveAppsRecyclerViewAdapter();
        mAdapter.setData(activeApps);
        appListView.setAdapter(mAdapter);
    }
}
