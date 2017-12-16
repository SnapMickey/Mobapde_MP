package com.example.jared.addingactivity.Group;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jared.addingactivity.List;
import com.example.jared.addingactivity.R;
import com.example.jared.addingactivity.Solo.SoloEditActivity;
import com.example.jared.addingactivity.Task;
import com.example.jared.addingactivity.TemporaryMarkerRepo;

/**
 * Created by Jared on 12/12/2017.
 */

public class GroupTasksAddAdapter extends RecyclerView.Adapter<GroupTasksAddAdapter.GroupAddTaskViewHolder> {

    private List list;
    private Activity activity;

    public GroupTasksAddAdapter(List list, Activity activity){
        this.list = list;
        this.activity = activity;
    }

    @Override
    public GroupAddTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list, parent, false);
        return new GroupAddTaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GroupAddTaskViewHolder holder, int position) {
        final int index = position;
        final Task task = list.getTasks().get(index);
        holder.tvTaskDescription.setText(task.getDescription());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(activity,SoloEditActivity.class);
                page.putExtra("requestCode",GroupAddActivity.REQUEST_EDIT_TASK);
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
        return list.getTasks().size();
    }

    /** VIEW HOLDER */
    public class GroupAddTaskViewHolder extends RecyclerView.ViewHolder{
        Button btnEdit;
        TextView tvTaskDescription;
        View container;

        public GroupAddTaskViewHolder(View itemView) {
            super(itemView);
            tvTaskDescription = itemView.findViewById(R.id.taskDesc);
            btnEdit = itemView.findViewById(R.id.btn_edittask);
            container = itemView;
        }
    }
}

