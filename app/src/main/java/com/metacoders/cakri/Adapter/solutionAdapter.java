package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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


    public solutionAdapter(Context context, List<qusizModel> qusList, List<userAnsModel> userResPonseList, int qusType) {
        this.context = context;
        this.qusList = qusList;
        this.userResPonseList = userResPonseList;
        this.qusType = qusType;
    }


    @Override
    public solutionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qus, parent, false);
        return new solutionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final solutionAdapter.ViewHolder holder, final int position) {

        holder.title.setText(" " + qusList.get(position).getTitle());
//        holder.my_resp.setText("Your Ans : " +userResPonseList.get(position).getSelectedAns());
//        holder.r8_ans.setText("Right Ans : "+ qusList.get(position).getRightAns());
        int pos = userResPonseList.get(position).getChoosePos();
        double mark = userResPonseList.get(position).getMark();

//        Log.d("TAG", "onBindViewHolder: " + position + qusList.get(0).getRightAns() + " " + userResPonseList.get(0).get);
        // check if its bcs = 1 and bank = 0

        if (qusType == 1) {
            holder.op5.setVisibility(View.GONE);
        } else {
            holder.op5.setVisibility(View.VISIBLE);
        }
        if (pos == 1 && mark > 0) {
            holder.op1.setBackgroundColor(Color.GREEN);
            holder.op1.setChecked(true);
        } else if (pos == 2 && mark > 0) {
            holder.op2.setBackgroundColor(Color.GREEN);
            holder.op2.setChecked(true);
        } else if (pos == 3 && mark > 0) {
            holder.op3.setBackgroundColor(Color.GREEN);
            holder.op3.setChecked(true);
        } else if (pos == 4 && mark > 0) {
            holder.op4.setBackgroundColor(Color.GREEN);
            holder.op4.setChecked(true);
        } else if (pos == 5 && mark > 0) {
            holder.op5.setBackgroundColor(Color.GREEN);
            holder.op5.setChecked(true);
        }
        // now get the wrong answer sorte
        if (pos == 1 && mark < 0) {

            holder.op1.setBackgroundColor(Color.RED);
            holder.op1.setChecked(true);
        } else if (pos == 2 && mark < 0) {
            holder.op2.setBackgroundColor(Color.RED);
            holder.op2.setChecked(true);
        } else if (pos == 3 && mark < 0) {
            holder.op3.setBackgroundColor(Color.RED);
            holder.op3.setChecked(true);
        } else if (pos == 4 && mark < 0) {
            holder.op4.setBackgroundColor(Color.RED);
            holder.op4.setChecked(true);
        } else if (pos == 5 && mark < 0) {
            holder.op5.setBackgroundColor(Color.RED);
            holder.op5.setChecked(true);
        }

        holder.op1.setText(qusList.get(position).getOptionOne());
        holder.op2.setText(qusList.get(position).getOptionTwo());
        holder.op3.setText(qusList.get(position).getOptionThree());
        holder.op4.setText(qusList.get(position).getOptionFour());
        holder.op5.setText(qusList.get(position).getOptionFive());

        // mark  right ans

        String answer = qusList.get(position).getRightAns();

        if (answer.equals(qusList.get(position).getOptionOne())) {
            holder.op1.setBackgroundColor(Color.GREEN);
        } else if (answer.equals(qusList.get(position).getOptionTwo())) {
            holder.op2.setBackgroundColor(Color.GREEN);
        } else if (answer.equals(qusList.get(position).getOptionThree())) {
            holder.op3.setBackgroundColor(Color.GREEN);
        } else if (answer.equals(qusList.get(position).getOptionFour())) {
            holder.op4.setBackgroundColor(Color.GREEN);
        } else if (answer.equals(qusList.get(position).getOptionFive())) {
            holder.op5.setBackgroundColor(Color.GREEN);
        }


    }



    @Override
    public int getItemCount() {
        return qusList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title, my_resp, r8_ans;
        public CheckBox op1, op2, op3, op4, op5;


        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.qus);
            op1 = itemView.findViewById(R.id.op1);
            op2 = itemView.findViewById(R.id.op2);
            op3 = itemView.findViewById(R.id.op3);
            op4 = itemView.findViewById(R.id.op4);
            op5 = itemView.findViewById(R.id.op5);


        }
    }
}

