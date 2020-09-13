package com.metacoders.cakri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.metacoders.cakri.Activities.lists.AllModelQusList;
import com.metacoders.cakri.Activities.lists.All_Job_Prep;

public  class BankModelQusPrep extends AppCompatActivity  implements  View.OnClickListener{
    TextView page_title_textView;

    CardView adviceBtn;
    LinearLayout bottomContainer;
    CardView banglaBtn,engBtn,gkBtn,math,ictBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_job_special);
        page_title_textView = findViewById(R.id.page_title_textView);
        adviceBtn = findViewById(R.id.adviceBtn);
        bottomContainer = findViewById(R.id.bottomContainer);
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
        page_title_textView.setText("\uD83C\uDF1Fবিষয়ভিত্তিক মডেল টেস্ট\uD83C\uDF1F");
        adviceBtn.setVisibility(View.GONE);
        bottomContainer.setVisibility(View.GONE);

    }
    @Override
    public void onClick(View v) {


        // here in this  page category id is = 3
        // here go to the model qustion list
        Intent p  = new Intent(getApplicationContext() , AllModelQusList.class);
        switch (v.getId()) {


            case R.id.adviceBtn:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "3" ) ;
                p.putExtra("sub_cat_id" , "12") ;
                startActivity(p);
                break;

            case R.id.banglaBtn:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "3" ) ;
                p.putExtra("sub_cat_id" , "13") ;
                startActivity(p);
                break;
            case R.id.engBtn:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "3" ) ;
                p.putExtra("sub_cat_id" , "14") ;
                startActivity(p);
                break;
            case R.id.gkBtn:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "3" ) ;
                p.putExtra("sub_cat_id" , "15") ;
                startActivity(p);

                break;
            case R.id.math:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "3" ) ;
                p.putExtra("sub_cat_id" , "16") ;
                startActivity(p);
                break;


            case R.id.ictBtn:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "3" ) ;
                p.putExtra("sub_cat_id" , "17") ;
                startActivity(p);

                break;


            default:
                break;
        }


    }
}