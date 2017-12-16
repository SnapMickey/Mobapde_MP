package com.example.jared.addingactivity.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jared.addingactivity.List;
import com.example.jared.addingactivity.MainActivity;
import com.example.jared.addingactivity.R;

import java.util.ArrayList;

/**
 * Created by Pons on 15/12/2017.
 */

public class GroupTab extends Fragment {
    private static final String TAG = "GroupFragment";
    public static final int REQUEST_EDIT = 52;

    RecyclerView rvTasks;
    public static ArrayList<List> lists;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_group,container,false);

        lists = new ArrayList<>();
        rvTasks = view.findViewById(R.id.rv_tasks);

        try {
            ArrayList<List> allLists = MainActivity.db.queryAllLists();

            for (List l : allLists) {
                if (l.getType().equals("grp"))
                    lists.add(l);
            }
        }catch(Exception e){}

        GroupTabAdapter adapter = new GroupTabAdapter(lists,this.getActivity());
        rvTasks.setAdapter(adapter);
        rvTasks.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL, false));


        return view;
    }
}
