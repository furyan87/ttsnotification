package com.prochnow.ttsnotifications.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.prochnow.ttsnotifications.AddApplicationActivity;
import com.prochnow.ttsnotifications.MainActivity;
import com.prochnow.ttsnotifications.R;
import com.prochnow.ttsnotifications.model.DatabaseHelper;
import com.prochnow.ttsnotifications.model.AppInfo;

import java.sql.SQLException;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by prochnow on 23.06.15.
 */
public class AppFragment extends Fragment {

    private static final int MAX_NUM_TO_CREATE = 10;
    private final String LOG_TAG = AppFragment.class.getSimpleName();

    List<AppInfo> list;
    View rootView;

    private DatabaseHelper databaseHelper = null;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dao<AppInfo, String> simpleDao = null;
        try {
            simpleDao = getHelper().getAppInfoDao();
        } catch (SQLException e) {
            //TODO 01.07.15 error handling
            e.printStackTrace();
        }
        // query for all of the data objects in the database
        try {
            list = simpleDao.queryForAll();
        } catch (SQLException e) {
            //TODO 01.07.15 error handling
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_app, container, false);
        ButterKnife.bind(this, rootView);
        initInstances();

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        // Set title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.appTitle);
    }

    private void initInstances() {
        FloatingActionButton fabBtn = ButterKnife.findById(getActivity(), R.id.fabBtn);
        fabBtn.setVisibility(View.VISIBLE);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddApplicationActivity.class);
                startActivity(intent);
            }

        });
    }

}