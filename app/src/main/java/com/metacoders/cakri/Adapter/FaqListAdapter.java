package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.Models.FaqModel;
import com.metacoders.cakri.Models.LeaderBoardModel;
import com.metacoders.cakri.R;

import java.util.ArrayList;
import java.util.List;

public class FaqListAdapter extends RecyclerView.Adapter<FaqListAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    Context context;
    boolean hideIcon = false;
    List<FaqModel> list = new ArrayList<>();


    public FaqListAdapter(Context context, List<FaqModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public FaqListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.row_faq
                , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FaqListAdapter.ViewHolder holder, int position) {


        FaqModel model = list.get(position) ;
        holder.qus.setText(model.getQus());
        if(!model.getAns().isEmpty()){
            holder.ans.setText(model.getAns());
        }
        else  holder.ans.setText("Qustion Will Be Answered Shortly");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ans , qus ;


        public ViewHolder(View itemView) {
            super(itemView);

            ans = itemView.findViewById(R.id.ans);
            qus = itemView.findViewById(R.id.qus);



        }
    }
}

