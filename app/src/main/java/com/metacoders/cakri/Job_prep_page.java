package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.metacoders.cakri.Activities.lists.All_Job_Prep;

public class Job_prep_page extends AppCompatActivity implements View.OnClickListener {

    CardView job_prep_card, bcs_special, all_Job_solution, Bcs, career_guide, protidin_news, teacher_prp, rcnt_qus_soln, trnslt_practice, focus_writing, exclu_vuca, srt_tcnq, xm_sgsn, inspiration, viva_exp, interview_tips, important_news, dwnld_zn, other;

    Intent nextPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_prep_page);


        career_guide = findViewById(R.id.career_guide);
        protidin_news = findViewById(R.id.protidin_news);
        teacher_prp = findViewById(R.id.teacher_prp);
        rcnt_qus_soln = findViewById(R.id.rcnt_qus_soln);
        trnslt_practice = findViewById(R.id.trnslt_practice);
        focus_writing = findViewById(R.id.focus_writing);
        exclu_vuca = findViewById(R.id.exclu_vuca);
        srt_tcnq = findViewById(R.id.srt_tcnq);
        xm_sgsn = findViewById(R.id.xm_sgsn);
        viva_exp = findViewById(R.id.viva_exp);
        inspiration = findViewById(R.id.inspiration);
        interview_tips = findViewById(R.id.interview_tips);
        important_news = findViewById(R.id.important_news);
        dwnld_zn = findViewById(R.id.dwnld_zn);
        other = findViewById(R.id.others);


        //set click Listener
        career_guide.setOnClickListener(this);
        protidin_news.setOnClickListener(this);
        teacher_prp.setOnClickListener(this);
        rcnt_qus_soln.setOnClickListener(this);
        trnslt_practice.setOnClickListener(this);
        focus_writing.setOnClickListener(this);
        exclu_vuca.setOnClickListener(this);
        srt_tcnq.setOnClickListener(this);
        xm_sgsn.setOnClickListener(this);
        viva_exp.setOnClickListener(this);
        inspiration.setOnClickListener(this);
        important_news.setOnClickListener(this);
        interview_tips.setOnClickListener(this);
        dwnld_zn.setOnClickListener(this);
        other.setOnClickListener(this);

        setUpView();

        job_prep_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextPage = new Intent(getApplicationContext(), Bank_job_special.class);
                startActivity(nextPage);

            }
        });

        bcs_special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextPage = new Intent(getApplicationContext(), bcsSpecialPage.class);
                startActivity(nextPage);
            }
        });


        all_Job_solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nextPage = new Intent(getApplicationContext(), allJobSolution.class);
                startActivity(nextPage);
            }
        });

    }

    private void setUpView() {
        job_prep_card = findViewById(R.id.bankJobPrep);
        bcs_special = findViewById(R.id.bcs_special);
        all_Job_solution = findViewById(R.id.allJobSolution);


    }


    @Override
    public void onClick(View v) {
        Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
        switch (v.getId()) {


            /* case R.id.bcs_special: // bcs special  card view

                break;

            case R.id.bankJobPrep: // bank job  card view

                break;
            case R.id.allJobSolution: // All Job Solution card view

                break;  */

            /*
            Here We Need Only CAT_ID  as SUb _cat _id  = 0 for  all this

             */
            case R.id.career_guide: // Career Guide card view
                p.putExtra("cat_id", "1");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;
            case R.id.protidin_news: // Everyday News card view
                p.putExtra("cat_id", "4");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;
            case R.id.teacher_prp: // teacher job preparation card view
                p.putExtra("cat_id", "6");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;
            case R.id.rcnt_qus_soln: // Recent Question Solution card view
                p.putExtra("cat_id", "7");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;
            case R.id.trnslt_practice: // Translate Practice card view
                p.putExtra("cat_id", "8");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;

            case R.id.focus_writing: // Focus Writing  card view
                p.putExtra("cat_id", "9");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;
            case R.id.exclu_vuca: // Exclusive Vocabulary  card view
                p.putExtra("cat_id", "10");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;
            case R.id.srt_tcnq: //  Shot-Cut Technique card view
                p.putExtra("cat_id", "11");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;
            case R.id.xm_sgsn: // Exam Suggestion  card view
                p.putExtra("cat_id", "12");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;
            case R.id.inspiration: // Inspirations card view
                p.putExtra("cat_id", "13");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;
            case R.id.viva_exp: // Viva Experience   card view
                p.putExtra("cat_id", "0");
                p.putExtra("sub_cat_id", "14");
                startActivity(p);
                break;
            case R.id.interview_tips: //  Interview Tips card view
                p.putExtra("cat_id", "15");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;
            case R.id.important_news: // Important News  card view
                p.putExtra("cat_id", "16");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;
            case R.id.dwnld_zn: // Download Zone  card view
                p.putExtra("cat_id", "18");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;
            case R.id.others: //  Others card view
                p.putExtra("cat_id", "17");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
                break;

            default:
                break;
        }
    }
}