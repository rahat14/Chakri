package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.metacoders.cakri.Models.ModelQustionListResponse;
import com.metacoders.cakri.R;

import java.util.List;

public class ModelQustionListAdapter extends RecyclerView.Adapter<ModelQustionListAdapter.ViewHolder> {
    private Context ctx;
    private List<ModelQustionListResponse.singleModelQus> mData;
    private List<ModelQustionListResponse.singleModelQus> mDataFiltered;
    private ItemClickLisnter itemClickLisnter;


    public ModelQustionListAdapter(Context ctx, List<ModelQustionListResponse.singleModelQus> mData, ItemClickLisnter itemClickLisnter) {
        this.ctx = ctx;
        this.mData = mData;
        this.mDataFiltered = mData;
        this.itemClickLisnter = itemClickLisnter;
    }


    @NonNull
    @Override
    public ModelQustionListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.row_post, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelQustionListAdapter.ViewHolder holder, final int position) {

        ModelQustionListResponse.singleModelQus model = mDataFiltered.get(position);
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
        void onItemClick(ModelQustionListResponse.singleModelQus model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.heading_text);


        }


    }


}





