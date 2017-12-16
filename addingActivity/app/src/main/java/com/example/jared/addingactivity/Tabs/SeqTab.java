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
import com.example.jared.addingactivity.Task;

import java.util.ArrayList;

/**
 * Created by Pons on 15/12/2017.
 */

public class SeqTab extends Fragment {
    private static final String TAG = "SeqFragment";
    public static final int REQUEST_EDIT = 51;

    RecyclerView rvTasks;
    public static ArrayList<List> lists;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_seq,container,false);

        lists = new ArrayList<>();
        rvTasks = view.findViewById(R.id.rv_tasks);

        try {
            ArrayList<List> allLists = MainActivity.db.queryAllLists();

            for (List l : allLists) {
                if (l.getType().equals("seq"))
                    lists.add(l);
            }
        }catch(Exception e){}

        SeqTabAdapter adapter = new SeqTabAdapter(lists,this.getActivity());
        rvTasks.setAdapter(adapter);
        rvTasks.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL, false));

        return view;
    }
}
