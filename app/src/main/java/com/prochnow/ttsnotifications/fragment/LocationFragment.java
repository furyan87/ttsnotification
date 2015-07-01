package com.prochnow.ttsnotifications.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prochnow.ttsnotifications.MainActivity;
import com.prochnow.ttsnotifications.R;

import butterknife.ButterKnife;

/**
 * Created by prochnow on 23.06.15.
 */
public class LocationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.bind(this, rootView);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.locationTitle);
    }
}
