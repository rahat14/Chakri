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

public class BcsModelQusPrep extends AppCompatActivity implements  View.OnClickListener {
    TextView page_title_textView ;
    CardView adviceC,banglaC,englishC,bangladesh,internationl,geoC,gk_C,ict_C,math_logics,mental_skill,ethics;
    CardView adviceBtn;
    LinearLayout bottomContainer  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcs_special_page);

        page_title_textView = findViewById(R.id.page_title_textView);
        adviceBtn = findViewById(R.id.adviceC);
        bottomContainer = findViewById(R.id.bottomContainer);
        //id declaration
        adviceC = findViewById(R.id. adviceC);
        banglaC = findViewById(R.id.banglaC);
        englishC = findViewById(R.id.englishC);
        bangladesh = findViewById(R.id.bangladesh);
        internationl = findViewById(R.id.internationl);
        geoC = findViewById(R.id.geoC);
        gk_C = findViewById(R.id.gk_C);
        ict_C = findViewById(R.id.ict_C);
        math_logics = findViewById(R.id.math_logics);
        mental_skill = findViewById(R.id.mental_skill);
        ethics = findViewById(R.id.ethics);

        //click listener
        adviceC.setOnClickListener(this);
        banglaC.setOnClickListener(this);
        englishC.setOnClickListener(this);
        bangladesh.setOnClickListener(this);
        internationl.setOnClickListener(this);
        geoC.setOnClickListener(this);
        gk_C.setOnClickListener(this);
        ict_C.setOnClickListener(this);
        math_logics.setOnClickListener(this);
        mental_skill.setOnClickListener(this);
        ethics.setOnClickListener(this);



        page_title_textView.setText("\uD83C\uDF1Fবিষয়ভিত্তিক মডেল টেস্ট\uD83C\uDF1F");
        adviceBtn.setVisibility(View.GONE);
        bottomContainer.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        // here in this  page category id is = 2
        //TODO CHANGES WITH MODEL TEST LIST
        
        Intent p  = new Intent(getApplicationContext() , AllModelQusList.class);
        p.putExtra("qus_name" , 1) ;
        switch (v.getId()) {


            case R.id.adviceC:   //advise card View
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "1") ;
                startActivity(p);
                break;

            case R.id.banglaC:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "2") ;
                startActivity(p);
                break;
            case R.id.englishC:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "3") ;
                startActivity(p);
                break;
            case R.id.bangladesh:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "4") ;
                startActivity(p);

                break;
            case R.id.internationl:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "5") ;
                startActivity(p);
                break;


            case R.id.geoC:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "6") ;
                startActivity(p);

                break;
            case R.id.gk_C:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "7") ;
                startActivity(p);
                break;

            case R.id.ict_C:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "8") ;
                startActivity(p);
                break;
            case R.id.math_logics:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "9") ;
                startActivity(p);
                break;
            case R.id.mental_skill:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "10") ;
                startActivity(p);

                break;
            case R.id.ethics:
                p.putExtra("qus_type" , "subjective");
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "11") ;
                startActivity(p);
                break;

            default:
                break;
        }
    }
}
