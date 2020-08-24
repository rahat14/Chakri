package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.R;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    Context context;
    private ItemClickListenter itemClickListenter;

    public listAdapter(Context context , ItemClickListenter itemClickListenter) {
        this.mInflater = LayoutInflater.from(context);
        this.itemClickListenter = itemClickListenter  ;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_post
                , parent, false);
        return new ViewHolder(view, itemClickListenter);
    }

    public interface ItemClickListenter {
        void onItemClick(View view, int pos);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements  RecyclerView.OnClickListener{
        public ImageView imageView;
        public CardView container;
        ItemClickListenter itemClickListenter;

        public ViewHolder(View itemView, ItemClickListenter itemClickListenter) {
            super(itemView);

            itemView.setOnClickListener(this );
            this.itemClickListenter = itemClickListenter;
        }

        @Override
        public void onClick(View v) {
            itemClickListenter.onItemClick(v, getAdapterPosition());
        }
    }
}

