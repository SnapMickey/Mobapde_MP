package com.example.jared.addingactivity.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jared.addingactivity.MainActivity;
import com.example.jared.addingactivity.R;
import com.example.jared.addingactivity.Task;

import java.util.ArrayList;

/**
 * Created by Pons on 15/12/2017.
 */

public class SoloTab extends Fragment {
    private static final String TAG = "SoloFragment";
    public static final int REQUEST_EDIT = 50;

    RecyclerView rvTasks;
    public static ArrayList<Task> tasks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Starting");
        View view = inflater.inflate(R.layout.view_solo,container,false);

        rvTasks = view.findViewById(R.id.rv_tasks);

        tasks = new ArrayList<>();

        try{
            ArrayList<Task> allTasks = MainActivity.db.queryAllTasks();

            for(Task t : allTasks){
                if(t.getListId() == -1)
                    tasks.add(t);
            }

        }catch(Exception e){}

        SoloTabAdapter adapter = new SoloTabAdapter(tasks, this.getActivity());
        rvTasks.setAdapter(adapter);
        rvTasks.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL, false));

        return view;
    }
}
