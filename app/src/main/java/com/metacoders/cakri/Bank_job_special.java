package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Bank_job_special extends AppCompatActivity implements View.OnClickListener {

    TextView bottomText ;
    CardView adviceBtn,banglaBtn,engBtn,gkBtn,math,ictBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_job_special);

        bottomText = findViewById(R.id.page_bottom_textView);
        //id declaration
        adviceBtn = findViewById(R.id.adviceBtn);
        banglaBtn = findViewById(R.id.banglaBtn);
        engBtn = findViewById(R.id.engBtn);
        gkBtn = findViewById(R.id.gkBtn);
        math = findViewById(R.id.math);
        ictBtn = findViewById(R.id.ictBtn);


        //click listener
        adviceBtn.setOnClickListener(this);
        banglaBtn.setOnClickListener(this);
        engBtn.setOnClickListener(this);
        gkBtn.setOnClickListener(this);
        math.setOnClickListener(this);
        ictBtn.setOnClickListener(this);



        bottomText.setText("★ব্যাংক লিখিত প্রস্তুতির সাজেশন দেখুন★");
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {


            case R.id.adviceBtn:

                break;

            case R.id.banglaBtn:

                break;
            case R.id.engBtn:

                break;
            case R.id.gkBtn:


                break;
            case R.id.math:

                break;


            case R.id.ictBtn:


                break;


            default:
                break;
        }


    }
}