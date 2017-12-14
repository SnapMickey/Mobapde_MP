package com.example.jared.addingactivity.Seq;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jared.addingactivity.List;
import com.example.jared.addingactivity.R;
import com.example.jared.addingactivity.Task;

/**
 * Created by Jared on 12/12/2017.
 */

public class SeqTasksAddAdapter extends RecyclerView.Adapter<SeqTasksAddAdapter.SeqAddTaskViewHolder> {

    private List list;
    private Activity activity;

    public SeqTasksAddAdapter(List list, Activity activity){
        this.list = list;
        this.activity = activity;
    }

    @Override
    public SeqAddTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list, parent, false);
        return new SeqAddTaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SeqAddTaskViewHolder holder, int position) {
        final int index = position;
        final Task task = list.getTasks().get(index);
        holder.etTaskDescription.setText(task.getDescription());

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.getTasks().remove(index);
                SeqTasksAddAdapter.this.notifyItemRemoved(index);
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(activity,SeqEditActivity.class);
                page.putExtra("requestCode", SeqAddActivity.REQUEST_EDIT_TASK);
                page.putExtra("position", index);
                activity.startActivityForResult(page, SeqAddActivity.REQUEST_EDIT_TASK);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.getTasks().size();
    }

    /** VIEW HOLDER */
    public class SeqAddTaskViewHolder extends RecyclerView.ViewHolder{
        Button btnRemove, btnEdit;
        EditText etTaskDescription;
        View container;

        public SeqAddTaskViewHolder(View itemView) {
            super(itemView);
            etTaskDescription = null;
            btnRemove = itemView.findViewById(R.id.btn_deletetask);
            btnEdit = itemView.findViewById(R.id.btn_edittask);
            container = itemView;
        }
    }
}

