package com.example.jared.addingactivity.Tabs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jared.addingactivity.Group.GroupAddActivity;
import com.example.jared.addingactivity.R;
import com.example.jared.addingactivity.Seq.SeqAddActivity;
import com.example.jared.addingactivity.Solo.SoloEditActivity;
import com.example.jared.addingactivity.Task;

import java.util.ArrayList;

/**
 * Created by Jared on 12/12/2017.
 */

public class SoloTabAdapter extends RecyclerView.Adapter<SoloTabAdapter.SoloTaskViewHolder>{

    private ArrayList<Task> tasks;
    private Activity activity;

    public SoloTabAdapter(ArrayList<Task> tasks, Activity activity){
        this.tasks = tasks;
        this.activity = activity;
    }

    @Override
    public SoloTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_solotasks, parent, false);
        return new SoloTaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SoloTaskViewHolder holder, int position) {
        final int index = position;
        final Task task = tasks.get(index);
        holder.tvTaskDescription.setText(task.getDescription());

        holder.box.setChecked(task.isDone());
        holder.box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setDone(!task.isDone());
            }
        });

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(activity,SoloEditActivity.class);
                page.putExtra("requestCode", SoloTab.REQUEST_EDIT);
                page.putExtra("position", index);
                page.putExtra("desc", task.getDescription());
                page.putExtra("lat", task.getLatitude());
                page.putExtra("lng", task.getLongtitude());
                activity.startActivityForResult(page, GroupAddActivity.REQUEST_EDIT_TASK);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    /** VIEW HOLDER */
    public class SoloTaskViewHolder extends RecyclerView.ViewHolder{
        TextView tvTaskDescription;
        View container;
        CheckBox box;

        public SoloTaskViewHolder(View itemView) {
            super(itemView);
            tvTaskDescription = itemView.findViewById(R.id.taskDesc);
            box = itemView.findViewById(R.id.done);
            container = itemView;
        }
    }
}

