package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.Models.BookmarkModel;
import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.R;

import java.util.List;

public class BookMarkAdaper extends RecyclerView.Adapter<BookMarkAdaper.ViewHolder> {
    private Context ctx;
    private List<BookmarkModel> mData;
    private List<BookmarkModel> mDataFiltered;
    private ItemClickLisnter itemClickLisnter;


    public BookMarkAdaper(Context ctx, List<BookmarkModel> mData, ItemClickLisnter itemClickLisnter) {
        this.ctx = ctx;
        this.mData = mData;
        this.mDataFiltered = mData;
        this.itemClickLisnter = itemClickLisnter;
    }


    @NonNull
    @Override
    public BookMarkAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.row_post, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookMarkAdaper.ViewHolder holder, final int position) {

        BookmarkModel model = mDataFiltered.get(position);
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
        void onItemClick(BookmarkModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.heading_text);


        }


    }


}





