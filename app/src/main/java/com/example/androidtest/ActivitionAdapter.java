package com.example.androidtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtest.model.WorkList;

import java.util.ArrayList;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class ActivitionAdapter extends RecyclerView.Adapter<ActivitionAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<WorkList> workLists;
    private OnItemClickListener onItemClickListener;

    public ActivitionAdapter(ArrayList<WorkList> workLists) {
        this.workLists = workLists;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(workLists.get(position).getName());
        holder.contact.setText(workLists.get(position).getPhoneNumber());
        holder.workId.setText(workLists.get(position).getId());

      //  holder.itemView.setTag(item);
    }


    @Override
    public int getItemCount() {
       // return workLists.size();
        return 5;
    }


    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
//        if (onItemClickListener != null) {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    onItemClickListener.onItemClick(v, (ViewModel) v.getTag());
//                }
//            }, 0);
//        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, contact, btnPay,workId;

        public ViewHolder(View itemView) {
            super(itemView);
            name =  itemView.findViewById(R.id.tv_name);
            contact = itemView.findViewById(R.id.tv_number);
            btnPay =  itemView.findViewById(R.id.title);
            workId  = itemView.findViewById(R.id.tv_workId);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, ViewModel viewModel);

    }
}