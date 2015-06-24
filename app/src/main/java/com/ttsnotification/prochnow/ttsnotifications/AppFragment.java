package com.ttsnotification.prochnow.ttsnotifications;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by prochnow on 23.06.15.
 */
public class AppFragment extends Fragment {

    private final String LOG_TAG = AppFragment.class.getSimpleName();

    @InjectView(R.id.appListView) RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
        mAdapter = new MyAdapter(strings);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void applyFABButton(FloatingActionButton fabButton) {
        fabButton.setVisibility(View.VISIBLE);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, "FAB clicked");
            }
        });
    }
}
