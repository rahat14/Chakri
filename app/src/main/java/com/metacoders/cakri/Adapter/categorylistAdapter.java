package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.R;

public  class categorylistAdapter extends RecyclerView.Adapter<categorylistAdapter.ViewHolder> {

private LayoutInflater mInflater;
        Context context;

public categorylistAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);

        this.context = context;
        }

@Override
public categorylistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_category
        , parent, false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(categorylistAdapter.ViewHolder holder, int position) {


        }

@Override
public int getItemCount() {
        return 15;
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public CardView container;

    public ViewHolder(View itemView) {
        super(itemView);


    }
}
}

