package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdaper extends RecyclerView.Adapter<SearchAdaper.ViewHolder> implements Filterable {
    private Context ctx;
    private List<JobCircularReponseModel.Job_Circular_Model> mData;
    private List<JobCircularReponseModel.Job_Circular_Model> mDataFiltered;
    private ItemClickLisnter itemClickLisnter;


    public SearchAdaper(Context ctx, List<JobCircularReponseModel.Job_Circular_Model> mData, ItemClickLisnter itemClickLisnter) {
        this.ctx = ctx;
        this.mData = mData;
        this.mDataFiltered = mData;
        this.itemClickLisnter = itemClickLisnter;
    }


    @NonNull
    @Override
    public SearchAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.row_post, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdaper.ViewHolder holder, final int position) {

        JobCircularReponseModel.Job_Circular_Model model = mDataFiltered.get(position);
        holder.title.setText(model.getTitle());

        holder.itemView.setOnClickListener(v -> {
            itemClickLisnter.onItemClick(model);
        });

    }


    @Override
    public int getItemCount() {
        return  mDataFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String Key = constraint.toString();
                if (Key.isEmpty()) {

                    mDataFiltered = mData;

                } else {
                    List<JobCircularReponseModel.Job_Circular_Model> lstFiltered = new ArrayList<>();
                    for (JobCircularReponseModel.Job_Circular_Model row : mData) {
                        //Log.d("TAG", "Filtering : " + row.getProductTitle());
                        if (row.getTitle().toLowerCase().contains(Key.toLowerCase()) ||
                                row.getKeywords().toLowerCase().contains(Key.toLowerCase())) {

                            //  Log.d("TAG", "Fillered: " + row.getProductTitle());
                            lstFiltered.add(row);
                        }

                    }
                    // Log.d("TAG", "Size: " + lstFiltered.size());
                    mDataFiltered = lstFiltered;
                    // Log.d("TAG", "dataset Size: " + mDataFiltered.size());

                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataFiltered;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {


                mDataFiltered = (List<JobCircularReponseModel.Job_Circular_Model>) results.values;
                notifyDataSetChanged();

            }
        };
    }

    public interface ItemClickLisnter {
        void onItemClick(JobCircularReponseModel.Job_Circular_Model model);
    }


    public void addItems(List<JobCircularReponseModel.Job_Circular_Model> newItems) {
        //Log.d("TAG", "addItems: " + newItems.size());
        mData.addAll(newItems);
        mDataFiltered = mData;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.heading_text);


        }


    }


}





