package com.prochnow.ttsnotifications.app;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prochnow.ttsnotifications.MainActivity;
import com.prochnow.ttsnotifications.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by prochnow on 25.06.15.
 */
public class AddAppFragment extends Fragment {

    private final String LOG_TAG = AddAppFragment.class.getSimpleName();

    @InjectView(R.id.appListView) RecyclerView appListView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    List<String> strings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initInstances();
    }

    private void initInstances() {

        strings = new ArrayList<>();
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List<ApplicationInfo> pkgAppsList = getActivity().getPackageManager().getInstalledApplications(0);
        for (ApplicationInfo info : pkgAppsList) {
            Log.d(LOG_TAG, "ApplicationInfoName: " + getActivity().getPackageManager().getApplicationLabel(info));
            Log.d(LOG_TAG, "ApplicationIcon: " + getActivity().getPackageManager().getApplicationIcon(info));
            Log.d(LOG_TAG, "ApplicationPackage: " + info.packageName);


            strings.add(getActivity().getPackageManager().getApplicationLabel(info).toString());
        }
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_addapp, container, false);
        ButterKnife.inject(this, rootView);

        appListView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        appListView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new AddAppRecyclerViewAdapter(strings);
        appListView.setAdapter(mAdapter);

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        // Set title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.addAppTitle);
    }
}
