package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.Models.LeaderBoardModel;
import com.metacoders.cakri.R;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    Context context;
    boolean hideIcon = false;
    List<LeaderBoardModel> list = new ArrayList<>();


    public LeaderBoardAdapter( Context context, List<LeaderBoardModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public LeaderBoardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_qus_sol
                , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LeaderBoardAdapter.ViewHolder holder, int position) {


        LeaderBoardModel model = list.get(position) ;
        holder.score.setText(model.getScore());
        holder.name.setText(model.getUserName());
        holder.rank.setText((position+1)+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name , rank , score;


        public ViewHolder(View itemView) {
            super(itemView);

            score = itemView.findViewById(R.id.score);
            name = itemView.findViewById(R.id.name);
            rank = itemView.findViewById(R.id.rank);


        }
    }
}

