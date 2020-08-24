package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.R;

public class categorylistAdapter extends RecyclerView.Adapter<categorylistAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    Context context;
    boolean hideIcon = false;



    public categorylistAdapter(Context context, boolean hideIcon) {
        this.context = context;
        this.hideIcon = hideIcon;
    }

    @Override
    public categorylistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_category
                , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(categorylistAdapter.ViewHolder holder, int position) {

        if (hideIcon) {

            holder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        }


    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public CardView container;
        public TextView title;


        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.heading_text);


        }
    }
}

