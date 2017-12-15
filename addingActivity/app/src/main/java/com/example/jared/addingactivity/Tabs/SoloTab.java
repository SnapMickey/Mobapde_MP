package com.example.jared.addingactivity.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jared.addingactivity.R;
/**
 * Created by Pons on 15/12/2017.
 */

public class SoloTab extends Fragment {
    private static final String TAG = "SoloFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Starting");
        View view = inflater.inflate(R.layout.view_solo,container,false);
        return view;
    }
}
