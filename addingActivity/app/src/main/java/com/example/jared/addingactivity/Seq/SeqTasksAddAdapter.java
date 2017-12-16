package com.example.jared.addingactivity.Seq;

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
import com.example.jared.addingactivity.SimpleItemTouchHelperCallback;
import com.example.jared.addingactivity.Solo.SoloEditActivity;
import com.example.jared.addingactivity.Task;

import java.util.Collections;

/**
 * Created by Jared on 12/12/2017.
 */

public class SeqTasksAddAdapter extends RecyclerView.Adapter<SeqTasksAddAdapter.SeqAddTaskViewHolder> implements SimpleItemTouchHelperCallback.ItemTouchHelperAdapter{

    private List list;
    private Activity activity;

    public SeqTasksAddAdapter(List list, Activity activity){
        this.list = list;
        this.activity = activity;
    }

    @Override
    public SeqAddTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_seq, parent, false);
        return new SeqAddTaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SeqAddTaskViewHolder holder, int position) {
        final int index = position;
        final Task task = list.getTasks().get(index);
        task.setSeq(position);
        holder.tvTaskDescription.setText(task.getDescription());
        holder.tvSeq.setText("" + position);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(activity,SoloEditActivity.class);
                page.putExtra("requestCode", SeqAddActivity.REQUEST_EDIT_TASK);
                page.putExtra("position", index);
                page.putExtra("desc", task.getDescription());
                page.putExtra("lat", task.getLatitude());
                page.putExtra("lng", task.getLongtitude());
                activity.startActivityForResult(page, SeqAddActivity.REQUEST_EDIT_TASK);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.getTasks().size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Task temp = list.getTasks().get(fromPosition);
        list.getTasks().set(fromPosition,list.getTasks().get(toPosition));
        list.getTasks().set(toPosition, temp);

        list.getTasks().get(fromPosition).setSeq(toPosition);
        list.getTasks().get(toPosition).setSeq(fromPosition);

        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDismiss(int position) {


        list.removeTasks(position);
        notifyItemRemoved(position);

        for(int i = position; i < list.getTasks().size();i++){
            list.getTasks().get(i).setSeq(list.getTasks().get(i).getSeq()-1);
            notifyItemChanged(position);
        }

    }

    /** VIEW HOLDER */
    public class SeqAddTaskViewHolder extends RecyclerView.ViewHolder{
        Button btnEdit;
        TextView tvTaskDescription;
        TextView tvSeq;
        View container;

        public SeqAddTaskViewHolder(View itemView) {
            super(itemView);
            tvTaskDescription = itemView.findViewById(R.id.taskDesc);
            tvSeq = itemView.findViewById(R.id.taskNum);
            btnEdit = itemView.findViewById(R.id.btn_edittask);
            container = itemView;
        }
    }
}

