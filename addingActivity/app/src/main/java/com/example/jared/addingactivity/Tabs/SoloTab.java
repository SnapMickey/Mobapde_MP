package com.example.jared.addingactivity.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jared.addingactivity.R;
/**
 * Created by Pons on 15/12/2017.
 */

public class SoloTab extends Fragment {
    private static final String TAG = "SoloFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_solo,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
