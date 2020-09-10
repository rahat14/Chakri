package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;

public class bcsSpecialPage extends AppCompatActivity implements View.OnClickListener {

    CardView adviceC,banglaC,englishC,bangladesh,internationl,geoC,gk_C,ict_C,math_logics,mental_skill,ethics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcs_special_page);

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

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.adviceC:   //advise card View

                break;

            case R.id.banglaC:

                break;
            case R.id.englishC:

                break;
            case R.id.bangladesh:


                break;
            case R.id.internationl:

                break;


            case R.id.geoC:


                break;
            case R.id.gk_C:

                break;

            case R.id.ict_C:

                break;
            case R.id.math_logics:

                break;
            case R.id.mental_skill:


                break;
            case R.id.ethics:

                break;

            default:
                break;
        }
    }
}