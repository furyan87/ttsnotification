package com.prochnow.ttsnotifications.app;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private List<AppInfo> appList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInstances();
    }

    private void initInstances() {
        FloatingActionButton fabBtn = ButterKnife.findById(getActivity(), R.id.fabBtn);
        fabBtn.setVisibility(View.GONE);


        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pkgManager = getActivity().getPackageManager();
        List<ApplicationInfo> pkgAppsList = pkgManager.getInstalledApplications(0);
        appList = new ArrayList<>(pkgAppsList.size());
        //TODO 25.06.15 filter already added apps
        for (ApplicationInfo info : pkgAppsList) {
            appList.add(new AppInfo(pkgManager.getApplicationLabel(info).toString(), info.packageName, pkgManager.getApplicationIcon(info)));
        }
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
        mAdapter = new AddAppRecyclerViewAdapter(appList);
        appListView.setAdapter(mAdapter);

        return rootView;
    }


}
