package com.metacoders.cakri.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.metacoders.cakri.Models.qusizModel;
import com.metacoders.cakri.Models.userAnsModel;
import com.metacoders.cakri.R;

import java.util.ArrayList;
import java.util.List;

public class qusAdapter extends RecyclerView.Adapter<qusAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    Context context;
    List<qusizModel> qusList = new ArrayList<>();
    List<userAnsModel> userResPonseList = new ArrayList<>();


    public qusAdapter(Context context, List<qusizModel> qusList, List<userAnsModel> userResPonseList) {
        this.context = context;
        this.qusList = qusList;
        this.userResPonseList = userResPonseList;
    }


    @Override
    public qusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qus, parent, false);
        return new qusAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final qusAdapter.ViewHolder holder, final int position) {

        holder.title.setText(" "+(position+1) +" : " +  qusList.get(position).getQus());

        holder.op1.setText(qusList.get(position).getOp1());
        holder.op2.setText(qusList.get(position).getOp2());
        holder.op3.setText(qusList.get(position).getOp3());
        holder.op4.setText(qusList.get(position).getOp4());

        // now check for if the pos has been checked or not

        if (userResPonseList.get(position).getChoosePos() == 1) {
            holder.op1.setChecked(true);

            holder.op2.setChecked(false);
            holder.op3.setChecked(false);
            holder.op4.setChecked(false);
        } else if (userResPonseList.get(position).getChoosePos() == 2) {
            holder.op2.setChecked(true);

            holder.op1.setChecked(false);
            holder.op3.setChecked(false);
            holder.op4.setChecked(false);
        } else if (userResPonseList.get(position).getChoosePos() == 3) {
            holder.op3.setChecked(true);

            holder.op2.setChecked(false);
            holder.op1.setChecked(false);
            holder.op4.setChecked(false);
        } else if (userResPonseList.get(position).getChoosePos() == 4) {

            holder.op4.setChecked(true);

            holder.op2.setChecked(false);
            holder.op3.setChecked(false);
            holder.op1.setChecked(false);
        } else {
            holder.op4.setChecked(false);
            holder.op2.setChecked(false);
            holder.op3.setChecked(false);
            holder.op1.setChecked(false);
        }

        holder.op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.op1.setChecked(true);
                // other is uncehcked

                holder.op2.setChecked(false);
                holder.op3.setChecked(false);
                holder.op4.setChecked(false);

                //checking the answer
                if (qusList.get(position).getAns().equals(holder.op1.getText())) {
                    // right answer
                    userResPonseList.get(position).setMark(10);
                    userResPonseList.get(position).setPos(position);
                    userResPonseList.get(position).setChoosePos(1);
                    userResPonseList.get(position).setSelectedAns(holder.op1.getText().toString());
                } else {

                    userResPonseList.get(position).setMark(-10);
                    userResPonseList.get(position).setPos(position);
                    userResPonseList.get(position).setChoosePos(1);
                    userResPonseList.get(position).setSelectedAns(holder.op1.getText().toString());
                }

            }
        });

        holder.op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.op2.setChecked(true);
                // other is uncehcked

                holder.op1.setChecked(false);
                holder.op3.setChecked(false);
                holder.op4.setChecked(false);

                if (qusList.get(position).getAns().equals(holder.op2.getText())) {
                    // right answer
                    userResPonseList.get(position).setMark(10);
                    userResPonseList.get(position).setPos(position);
                    userResPonseList.get(position).setChoosePos(2);
                    userResPonseList.get(position).setSelectedAns(holder.op2.getText().toString());
                } else {


                    userResPonseList.get(position).setMark(-10);
                    userResPonseList.get(position).setPos(position);
                    userResPonseList.get(position).setChoosePos(2);
                    userResPonseList.get(position).setSelectedAns(holder.op2.getText().toString());
                }

            }
        });
        holder.op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.op3.setChecked(true);
                // other is uncehcked

                holder.op2.setChecked(false);
                holder.op1.setChecked(false);
                holder.op4.setChecked(false);

                if (qusList.get(position).getAns().equals(holder.op3.getText())) {
                    // right answer
                    userResPonseList.get(position).setMark(10);
                    userResPonseList.get(position).setPos(position);
                    userResPonseList.get(position).setChoosePos(3);
                    userResPonseList.get(position).setSelectedAns(holder.op3.getText().toString());
                } else {


                    userResPonseList.get(position).setMark(-10);
                    userResPonseList.get(position).setPos(position);
                    userResPonseList.get(position).setChoosePos(3);
                    userResPonseList.get(position).setSelectedAns(holder.op3.getText().toString());
                }

            }
        });
        holder.op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.op4.setChecked(true);
                // other is uncehcked

                holder.op2.setChecked(false);
                holder.op3.setChecked(false);
                holder.op1.setChecked(false);

                if (qusList.get(position).getAns().equals(holder.op4.getText())) {
                    // right answer
                    userResPonseList.get(position).setMark(10);
                    userResPonseList.get(position).setPos(position);
                    userResPonseList.get(position).setChoosePos(4);
                    userResPonseList.get(position).setSelectedAns(holder.op4.getText().toString());
                } else {
                    userResPonseList.get(position).setMark(-10);
                    userResPonseList.get(position).setChoosePos(4);
                    userResPonseList.get(position).setPos(position);
                    userResPonseList.get(position).setSelectedAns(holder.op4.getText().toString());
                }

            }
        });


    }

    public  List<userAnsModel> getUserResPonseList(){

        return userResPonseList ;
    }

    @Override
    public int getItemCount() {
        return qusList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox op1, op2, op3, op4;

        public CardView container;
        public TextView title;


        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.qus);
            op1 = itemView.findViewById(R.id.op1);
            op2 = itemView.findViewById(R.id.op2);
            op3 = itemView.findViewById(R.id.op3);
            op4 = itemView.findViewById(R.id.op4);

        }
    }
}

