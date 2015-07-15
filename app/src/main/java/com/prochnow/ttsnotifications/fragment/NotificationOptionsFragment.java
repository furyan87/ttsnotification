package com.prochnow.ttsnotifications.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.prochnow.ttsnotifications.R;
import com.prochnow.ttsnotifications.adapter.OptionsRecyclerViewAdapter;
import com.prochnow.ttsnotifications.model.AppInfo;
import com.prochnow.ttsnotifications.model.DatabaseHelper;

import java.sql.SQLException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class NotificationOptionsFragment extends Fragment {


    private final String LOG_TAG = NotificationOptionsFragment.class.getSimpleName();

    @Bind(R.id.appListView) RecyclerView appListView;

    private DatabaseHelper databaseHelper = null;
    private View rootView;
    private OptionsRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String packageName;
    private AppInfo app;


    public NotificationOptionsFragment() {
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_notification_options, container, false);
        ButterKnife.bind(this, rootView);

        packageName = getArguments().getString("package");

        try {
            app = getHelper().getAppInfoDao().queryBuilder().where().eq(AppInfo.PACKAGE_NAME, packageName).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setHasOptionsMenu(true);
        initInstances();

        return rootView;
    }

    private void initInstances() {
        initRecyclerView();

    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        appListView.setLayoutManager(mLayoutManager);
        appListView.setHasFixedSize(true);
        mAdapter = new OptionsRecyclerViewAdapter(getActivity(), app);
        appListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.acceptMenu) {
            Intent resultIntent = new Intent();
            getActivity().finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.applist_menu, menu);
        //TODO 01.07.15 add correct color for menu items 
        //        int color = getResources().getColor(R.color.light_primary_dark);
        //        int alpha = -1; // 80% alpha
        //        MenuColorizer.colorMenu(getActivity(), menu, color, alpha);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
