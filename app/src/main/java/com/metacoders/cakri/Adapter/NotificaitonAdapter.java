package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.Activities.Details.PostDetailActivity;
import com.metacoders.cakri.Activities.lists.NotificaitonList;
import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.NotificaitonResponse;
import com.metacoders.cakri.R;

import java.util.List;

public class NotificaitonAdapter extends RecyclerView.Adapter<NotificaitonAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    Context context;
    private ItemClickListenter itemClickListenter;
    private List<NotificaitonResponse.NotificationModel> list  ;

    public NotificaitonAdapter(Context context, ItemClickListenter itemClickListenter, List<NotificaitonResponse.NotificationModel> lisxt) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.itemClickListenter = itemClickListenter;
        this.list = lisxt;
    }
    @NonNull
    @Override
    public NotificaitonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.row_nottificaiion, parent, false);
        return new ViewHolder(view, itemClickListenter);
    }


    @Override
    public void onBindViewHolder(NotificaitonAdapter.ViewHolder holder, final int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.details.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements  View.OnClickListener{

        TextView title  , details;

        ItemClickListenter itemClickListenter;

        public ViewHolder(View itemView, ItemClickListenter itemClickListenter) {
            super(itemView);
            itemView.setOnClickListener(this );
            title = itemView.findViewById(R.id.heading_text);
            details = itemView.findViewById(R.id.heading_desp) ;
            this.itemClickListenter = itemClickListenter;
        }

        @Override
        public void onClick(View v) {

            itemClickListenter.onItemClick(v , getAdapterPosition());

        }
    }

    public interface ItemClickListenter {
        void onItemClick(View view, int pos);
    }

}

