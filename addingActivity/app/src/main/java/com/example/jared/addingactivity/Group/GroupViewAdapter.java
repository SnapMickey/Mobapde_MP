package com.example.jared.addingactivity.Group;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jared.addingactivity.List;
import com.example.jared.addingactivity.R;
import com.example.jared.addingactivity.Task;

/**
 * Created by Jared on 12/12/2017.
 */

public class GroupViewAdapter extends RecyclerView.Adapter<GroupViewAdapter.GroupTaskViewHolder>{

    private List list;
    private Activity activity;

    public GroupViewAdapter(List list, Activity activity){
        this.list = list;
        this.activity = activity;
    }

    @Override
    public GroupTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_group_item, parent, false);
        return new GroupTaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GroupTaskViewHolder holder, int position) {
        final int index = position;
        final Task task = list.getTasks().get(index);
        holder.tvTaskDescription.setText(task.getDescription());

        holder.box.setChecked(task.isDone());
        holder.box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setDone(!task.isDone());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.getTasks().size();
    }

    /** VIEW HOLDER */
    public class GroupTaskViewHolder extends RecyclerView.ViewHolder{
        TextView tvTaskDescription;
        View container;
        CheckBox box;

        public GroupTaskViewHolder(View itemView) {
            super(itemView);
            tvTaskDescription = itemView.findViewById(R.id.taskDesc);
            box = itemView.findViewById(R.id.done);
            container = itemView;
        }
    }
}

