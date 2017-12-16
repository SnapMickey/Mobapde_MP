package com.example.jared.addingactivity.Tabs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jared.addingactivity.Group.GroupAddActivity;
import com.example.jared.addingactivity.List;
import com.example.jared.addingactivity.R;
import com.example.jared.addingactivity.Seq.SeqEditActivity;
import com.example.jared.addingactivity.Solo.SoloEditActivity;
import com.example.jared.addingactivity.Task;

import java.util.ArrayList;

/**
 * Created by Jared on 12/12/2017.
 */

public class SeqTabAdapter extends RecyclerView.Adapter<SeqTabAdapter.SeqViewHolder>{

    private ArrayList<List> lists;
    private Activity activity;

    public SeqTabAdapter(ArrayList<List> lists, Activity activity){
        this.lists = lists;
        this.activity = activity;
    }

    @Override
    public SeqViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_seq_title, parent, false);
        return new SeqViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SeqViewHolder holder, int position) {
        final int index = position;
        final List list = lists.get(index);
        holder.tvTitle.setText(list.getTitle());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(activity,SeqEditActivity.class);
                page.putExtra("listId", list.getListId());
                activity.startActivity(page);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    /** VIEW HOLDER */
    public class SeqViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        View container;

        public SeqViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.seqTitle);
            container = itemView;
        }
    }
}

