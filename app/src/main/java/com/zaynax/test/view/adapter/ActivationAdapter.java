package com.zaynax.test.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaynax.test.Network.callback.ActItemClickListener;
import com.zaynax.test.databinding.RowActivationBinding;
import com.zaynax.test.model.ActivationList;
import com.zaynax.test.model.Item;

import java.util.List;

public class ActivationAdapter extends RecyclerView.Adapter<ActivationAdapter.ViewHolder> {
    public List<Item> itemList;
    Context mContext;
    ActItemClickListener itemClickListener;

    public ActivationAdapter(Context mContext,List<Item> itemList) {
        this.itemList = itemList;
        this.mContext = mContext;
    }

//    public ActivationAdapter(Context mContext) {
//        this.mContext = mContext;
//    }
    @NonNull
    @Override
    public ActivationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActivationAdapter.ViewHolder(RowActivationBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActivationAdapter.ViewHolder holder, final int position) {
        Item newsItem = itemList.get(position);
        holder.rowActivationBinding.tvName.setText(newsItem.getName());
        holder.rowActivationBinding.tvNumber.setText(newsItem.getPhoneNumber());
        holder.rowActivationBinding.tvWorkId.setText(newsItem.getId());




//
        holder.rowActivationBinding.btnPay.setOnClickListener(v -> {
            itemClickListener.onItemClick(v,position);
        });
        
        
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 :
                itemList.size();
    }
//    @Override
//    public int getItemCount() {
//        return 10;
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RowActivationBinding rowActivationBinding;

        public ViewHolder(RowActivationBinding rowActivationBinding) {
            super(rowActivationBinding.getRoot());
            this.rowActivationBinding = rowActivationBinding;
        }
    }

    public void setClickListener(ActItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
