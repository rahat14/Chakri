package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.JobPrepModel;
import com.metacoders.cakri.PostDetailActivity;
import com.metacoders.cakri.R;

import java.util.ArrayList;
import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    Context context;
    private ItemClickListenter itemClickListenter;
    private List<JobCircularReponseModel.Job_Circular_Model> list  ;

    public listAdapter( Context context, ItemClickListenter itemClickListenter, List<JobCircularReponseModel.Job_Circular_Model> lisxt) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.itemClickListenter = itemClickListenter;
        this.list = lisxt;
    }
    @NonNull
    @Override
    public listAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.row_post, parent, false);
        return new ViewHolder(view, itemClickListenter);
    }


    @Override
    public void onBindViewHolder(listAdapter.ViewHolder holder,final int position) {

        holder.title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements  View.OnClickListener{

        TextView title ;

        ItemClickListenter itemClickListenter;

        public ViewHolder(View itemView, ItemClickListenter itemClickListenter) {
            super(itemView);
            itemView.setOnClickListener(this );
            title = itemView.findViewById(R.id.heading_text);
            this.itemClickListenter = itemClickListenter;
        }

        @Override
        public void onClick(View v) {

            Intent p = new Intent(context, PostDetailActivity.class);
            p.putExtra("MODEL", list.get(getAdapterPosition()));
            context.startActivity(p);

        }
    }

    public interface ItemClickListenter {
        void onItemClick(View view, int pos);
    }

}

