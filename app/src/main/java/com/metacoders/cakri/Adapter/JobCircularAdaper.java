package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.R;

import java.util.List;

public class JobCircularAdaper extends RecyclerView.Adapter<JobCircularAdaper.ViewHolder> {
    private Context ctx;
    private List<JobCircularReponseModel.Job_Circular_Model> mData;
    private List<JobCircularReponseModel.Job_Circular_Model> mDataFiltered;
    private ItemClickLisnter itemClickLisnter;


    public JobCircularAdaper(Context ctx, List<JobCircularReponseModel.Job_Circular_Model> mData, ItemClickLisnter itemClickLisnter) {
        this.ctx = ctx;
        this.mData = mData;
        this.mDataFiltered = mData;
        this.itemClickLisnter = itemClickLisnter;
    }


    @NonNull
    @Override
    public JobCircularAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.row_post, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobCircularAdaper.ViewHolder holder, final int position) {

        JobCircularReponseModel.Job_Circular_Model model = mDataFiltered.get(position);
        holder.title.setText(model.getTitle());

        holder.itemView.setOnClickListener(v -> {
            itemClickLisnter.onItemClick(model);
        });

    }


    @Override
    public int getItemCount() {
        return mDataFiltered == null ? 0 : mDataFiltered.size();
    }

    public interface ItemClickLisnter {
        void onItemClick(JobCircularReponseModel.Job_Circular_Model model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.heading_text);


        }


    }


}





