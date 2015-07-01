package com.prochnow.ttsnotifications.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.prochnow.ttsnotifications.R;
import com.prochnow.ttsnotifications.adapter.AppListRecyclerViewAdapter;
import com.prochnow.ttsnotifications.model.AppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class AppListFragment extends Fragment {


    private final String LOG_TAG = AppListFragment.class.getSimpleName();

    @Bind(R.id.appListView) RecyclerView appListView;
    private AppListRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Bind(R.id.progressBar) ProgressBar progressBar;
    @Bind(R.id.progressBarText) TextView progressBarText;

    View rootView;

    public AppListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_applist, container, false);
        ButterKnife.bind(this, rootView);

        setHasOptionsMenu(true);

        initInstances();

        return rootView;
    }

    private void initInstances() {
        initRecyclerView();

        loadAppList();
    }

    private void loadAppList() {
        LoadAppTask load = new LoadAppTask(getActivity());
        load.execute();
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        appListView.setLayoutManager(mLayoutManager);
        appListView.setHasFixedSize(true);
        // specify an adapter (see also next example)
        mAdapter = new AppListRecyclerViewAdapter();
        appListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.acceptMenu) {

        }

        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.applist_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        super.onResume();

        SearchView searchView = ButterKnife.findById(getActivity(), R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(LOG_TAG, "onQueryTextChange ");
                mAdapter.getFilter().filter(newText);

                return true;
            }
        });
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

    private class LoadAppTask extends AsyncTask<Void, Integer, ArrayList<AppInfo>> {


        Context context;
        int count;

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
            count = pkgAppsList.size();
            for (ApplicationInfo info : pkgAppsList) {
                appList.add(new AppInfo(pkgManager.getApplicationLabel(info).toString(), info.packageName, pkgManager.getApplicationIcon(info)));
            }

            return appList;
        }

        @Override
        protected void onPostExecute(ArrayList<AppInfo> appInfos) {
            progressBar.setVisibility(View.GONE);
            progressBarText.setVisibility(View.GONE);
            mAdapter.setData(appInfos);
            mAdapter.notifyDataSetChanged();

            Snackbar.make(appListView, "Loaded " + count + " apps", Snackbar.LENGTH_SHORT).show();
        }
    }

}
