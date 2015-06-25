package com.prochnow.ttsnotifications.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.prochnow.ttsnotifications.R;
import com.prochnow.ttsnotifications.database.DatabaseHelper;
import com.prochnow.ttsnotifications.database.SimpleData;

import java.util.List;
import java.util.Random;

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
        String[] strings = {"dsad", "sadsad", "dsad", "sadsad", "dsad", "sadsad", "dsad", "sadsad", "dsad", "sadsad", "dsad", "sadsad"};
        mAdapter = new MyAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void applyFABButton(FloatingActionButton fabButton) {
        fabButton.setVisibility(View.VISIBLE);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get our dao
                RuntimeExceptionDao<SimpleData, Integer> simpleDao = getHelper().getSimpleDataDao();
                // query for all of the data objects in the database
                List<SimpleData> list = simpleDao.queryForAll();
                // our string builder for building the content-view
                StringBuilder sb = new StringBuilder();
                sb.append("Found ").append(list.size()).append(" entries in DB").append("()\n");

                // if we already have items in the database
                int simpleC = 1;
                for (SimpleData simple : list) {
                    sb.append('#').append(simpleC).append(": ").append(simple).append('\n');
                    simpleC++;
                }
                sb.append('\n');
                sb.append("------------------------------------------\n");

                int createNum;
                do {
                    createNum = new Random().nextInt(MAX_NUM_TO_CREATE) + 1;
                } while (createNum == list.size());
                sb.append("Creating ").append(createNum).append(" new entries:\n");
                for (int i = 0; i < createNum; i++) {
                    // create a new simple object
                    long millis = System.currentTimeMillis();
                    SimpleData simple = new SimpleData(millis);
                    // store it in the database
                    simpleDao.create(simple);
                    Log.i(LOG_TAG, "created simple(" + millis + ")");
                    // output it
                    sb.append('#').append(i + 1).append(": ");
                    sb.append(simple).append('\n');
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        // ignore
                    }
                }

                Log.d(LOG_TAG, "onClick " + sb.toString());
                Log.i(LOG_TAG, "Done with page at " + System.currentTimeMillis());
            }
        });
    }
}
