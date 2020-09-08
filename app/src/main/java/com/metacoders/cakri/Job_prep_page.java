package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Job_prep_page extends AppCompatActivity implements View.OnClickListener{

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            /* case R.id.bcs_special: // bcs special  card view

                break;

            case R.id.bankJobPrep: // bank job  card view

                break;
            case R.id.allJobSolution: // All Job Solution card view

                break;  */
            case R.id.career_guide: // Career Guide card view

                break;
            case R.id.protidin_news: // Everyday News card view

                break;


            case R.id.teacher_prp: // teacher job preparation card view

                break;
            case R.id.rcnt_qus_soln: // Recent Question Solution card view

                break;
            case R.id.trnslt_practice: // Translate Practice card view

                break;

            case R.id.focus_writing: // Focus Writing  card view

                break;
            case R.id.exclu_vuca: // Exclusive Vocabulary  card view

                break;
            case R.id.srt_tcnq: //  Shot-Cut Technique card view

                break;
            case R.id.xm_sgsn: // Exam Suggestion  card view

                break;
            case R.id.inspiration: // Inspirations card view

                break;
            case R.id.viva_exp: // Viva Experience   card view

                break;
            case R.id.interview_tips: //  Interview Tips card view

                break;
            case R.id.important_news: // Important News  card view

                break;
            case R.id.dwnld_zn: // Download Zone  card view

                break;
            case R.id.others: //  Others card view

                break;

            default:
                break;
        }
    }
}