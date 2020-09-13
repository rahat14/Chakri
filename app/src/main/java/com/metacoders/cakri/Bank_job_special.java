package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.metacoders.cakri.Activities.lists.All_Job_Prep;

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


        // here in this  page category id is = 3
        Intent p  = new Intent(getApplicationContext() , All_Job_Prep.class);
        switch (v.getId()) {


            case R.id.adviceBtn:
                p.putExtra("cat_id" , "3" ) ;
                p.putExtra("sub_cat_id" , "12") ;
                startActivity(p);
                break;

            case R.id.banglaBtn:
                p.putExtra("cat_id" , "3" ) ;
                p.putExtra("sub_cat_id" , "13") ;
                startActivity(p);
                break;
            case R.id.engBtn:
                p.putExtra("cat_id" , "3" ) ;
                p.putExtra("sub_cat_id" , "14") ;
                startActivity(p);
                break;
            case R.id.gkBtn:
                p.putExtra("cat_id" , "3" ) ;
                p.putExtra("sub_cat_id" , "15") ;
                startActivity(p);

                break;
            case R.id.math:
                p.putExtra("cat_id" , "3" ) ;
                p.putExtra("sub_cat_id" , "16") ;
                startActivity(p);
                break;


            case R.id.ictBtn:
                p.putExtra("cat_id" , "3" ) ;
                p.putExtra("sub_cat_id" , "17") ;
                startActivity(p);

                break;


            default:
                break;
        }


    }
}