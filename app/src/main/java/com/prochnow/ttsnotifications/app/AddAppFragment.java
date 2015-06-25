package com.prochnow.ttsnotifications.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prochnow.ttsnotifications.MainActivity;
import com.prochnow.ttsnotifications.R;

import butterknife.ButterKnife;

/**
 * Created by prochnow on 25.06.15.
 */
public class AddAppFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_addapp, container, false);
        ButterKnife.inject(this, rootView);

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        // Set title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.addAppTitle);
    }
}
