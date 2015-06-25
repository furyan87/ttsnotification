package com.prochnow.ttsnotifications.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.prochnow.ttsnotifications.MainActivity;
import com.prochnow.ttsnotifications.R;
import com.prochnow.ttsnotifications.database.DatabaseHelper;
import com.prochnow.ttsnotifications.database.SimpleData;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by prochnow on 23.06.15.
 */
public class AppFragment extends Fragment {

    private static final int MAX_NUM_TO_CREATE = 10;
    private final String LOG_TAG = AppFragment.class.getSimpleName();


    @InjectView(R.id.appListView) RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<SimpleData> list;


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
            databaseHelper =
                    OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RuntimeExceptionDao<SimpleData, Integer> simpleDao = getHelper().getSimpleDataDao();
        // query for all of the data objects in the database
        list = simpleDao.queryForAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_app, container, false);
        ButterKnife.inject(this, rootView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new AppRecyclerViewAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

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
                // Create new fragment and transaction
                AddAppFragment newFragment = new AddAppFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.contentArea, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();

            }
        });

    }

}
