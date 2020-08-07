package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.metacoders.cakri.Adapter.allJobSolution;

public class Job_prep_page extends AppCompatActivity {

    CardView job_prep_card  , bcs_special  , all_Job_solution , Bcs ;

    Intent  nextPage  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_prep_page);

        setUpView() ;

        job_prep_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextPage = new Intent(getApplicationContext() , Bank_job_special.class);
                startActivity(nextPage);

            }
        });

        bcs_special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextPage = new Intent(getApplicationContext() , bcsSpecialPage.class);
                startActivity(nextPage);
            }
        });


        all_Job_solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nextPage = new Intent(getApplicationContext() , allJobSolution.class);
                startActivity(nextPage);
            }
        });

    }

    private void setUpView() {
        job_prep_card = findViewById(R.id.bankJobPrep) ;
        bcs_special = findViewById(R.id.bcs_special);
        all_Job_solution = findViewById(R.id.allJobSolution) ;


    }


}