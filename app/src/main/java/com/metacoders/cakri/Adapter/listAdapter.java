package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.R;

public  class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {

private LayoutInflater mInflater;
        Context context;

public listAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);

        this.context = context;
        }

@Override
public listAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_post
        , parent, false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(listAdapter.ViewHolder holder, int position) {


        }

@Override
public int getItemCount() {
        return 5;
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public CardView container;

    public ViewHolder(View itemView) {
        super(itemView);


    }
}
}

