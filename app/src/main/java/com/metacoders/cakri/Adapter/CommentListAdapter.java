package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.Models.CommentResponse;
import com.metacoders.cakri.R;

import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    Context context;
    List<CommentResponse.CommentModel>list ;


    public CommentListAdapter( Context context, List<CommentResponse.CommentModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public CommentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater
                .from(parent.getContext()).inflate(R.layout.row_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentListAdapter.ViewHolder holder, int position) {

        holder.content.setText(list.get(position).getContent());
        holder.commenter_name.setText(list.get(position).getUserName());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView content, commenter_name;


        public ViewHolder(View itemView) {
            super(itemView);

            content = itemView.findViewById(R.id.tv_comment);
            commenter_name = itemView.findViewById(R.id.tv_commentName);


        }
    }
}

