package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;

import com.metacoders.cakri.R;

public class allJobSolution extends AppCompatActivity  implements View.OnClickListener{

    CardView bcs_qustion_bank, teacher_reg_qustion_bank,primary_qus_bank,ministry_qus_bank,bank_qus_bank,gk_qus_bank,other_qus_bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_job_solution);


        //id declaration
        bcs_qustion_bank = findViewById(R.id.bcs_qustion_bank);
        teacher_reg_qustion_bank = findViewById(R.id.teacher_reg_qustion_bank);
        primary_qus_bank = findViewById(R.id.primary_qus_bank);
        ministry_qus_bank = findViewById(R.id.ministry_qus_bank);
        bank_qus_bank = findViewById(R.id.bank_qus_bank);
        gk_qus_bank = findViewById(R.id.gk_qus_bank);
        other_qus_bank = findViewById(R.id.other_qus_bank);

        //click listener
        bcs_qustion_bank.setOnClickListener(this);
        teacher_reg_qustion_bank.setOnClickListener(this);
        primary_qus_bank.setOnClickListener(this);
        ministry_qus_bank.setOnClickListener(this);
        bank_qus_bank.setOnClickListener(this);
        gk_qus_bank.setOnClickListener(this);
        other_qus_bank.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.bcs_qustion_bank: // BCS ques bank  card view

                break;

            case R.id.teacher_reg_qustion_bank: // Teacher reg qus bank  card view

                break;
            case R.id.primary_qus_bank: // Primary qus bank card view

                break;
            case R.id.ministry_qus_bank: //ministry qus bank card view

                break;
            case R.id.bank_qus_bank: //  bank qus bank card view

                break;


            case R.id.gk_qus_bank: // gk qus bank card view

                break;
            case R.id.other_qus_bank: // other qus bank card view

                break;

            default:
                break;
        }
    }
}