package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.Models.qusizModel;
import com.metacoders.cakri.Models.userAnsModel;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class solutionAdapter extends RecyclerView.Adapter<solutionAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    Context context;
    List<qusizModel> qusList = new ArrayList<>();
    List<userAnsModel> userResPonseList = new ArrayList<>();
    int qusType;
    double wrong_ans_value = 0;
    double right_ans_value = 0;


    public solutionAdapter(Context context, List<qusizModel> qusList, List<userAnsModel> userResPonseList , int qusType) {
        this.context = context;
        this.qusList = qusList;
        this.userResPonseList = userResPonseList;
        this.qusType = qusType ;
    }


    @Override
    public solutionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qus_sol, parent, false);
        return new solutionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final solutionAdapter.ViewHolder holder, final int position) {

        holder.title.setText(" "+ qusList.get(position).getTitle());
        holder.my_resp.setText("Your Ans : " +userResPonseList.get(position).getSelectedAns());
        holder.r8_ans.setText("Right Ans : "+ qusList.get(position).getRightAns());

    }

    public List<userAnsModel> getUserResPonseList() {

        return userResPonseList;
    }

    @Override
    public int getItemCount() {
        return qusList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title , my_resp , r8_ans;


        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.qus);
            my_resp = itemView.findViewById(R.id.your_response);
            r8_ans = itemView.findViewById(R.id.rightAns);


        }
    }
}

