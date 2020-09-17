package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.metacoders.cakri.Activities.lists.All_Circular_List;


public class jobCircularCategory extends AppCompatActivity implements View.OnClickListener {

    TextView headerTitle, bottomHeader;
    CardView govtJob, bankJob, non_govtJob, teacherBank, pasrtTimeJob, garmentJob, ngoJob,
            defence_railJob, healthJob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_circular_category);
        headerTitle = findViewById(R.id.page_title_textView);
        bottomHeader = findViewById(R.id.page_bottom_textView);
        // tomake id o declare korte hobe
        govtJob = findViewById(R.id.govt_job);
        bankJob = findViewById(R.id.bank_job);
        non_govtJob = findViewById(R.id.non_govt_job);
        teacherBank = findViewById(R.id.tcr_job);
        pasrtTimeJob = findViewById(R.id.part_time_job);
        garmentJob = findViewById(R.id.garments_job);
        ngoJob = findViewById(R.id.ngo_job);
        defence_railJob = findViewById(R.id.defence_job);
        healthJob = findViewById(R.id.health_job);

        // click listener toma eta add korte hobe
        headerTitle.setOnClickListener(this);
        govtJob.setOnClickListener(this);
        bankJob.setOnClickListener(this);
        non_govtJob.setOnClickListener(this);
        teacherBank.setOnClickListener(this);
        pasrtTimeJob.setOnClickListener(this);
        garmentJob.setOnClickListener(this);
        ngoJob.setOnClickListener(this);
        defence_railJob.setOnClickListener(this);
        healthJob.setOnClickListener(this);


        headerTitle.setText("চাকরির ক্যাটাগরী");
        bottomHeader.setSelected(true);
        bottomHeader.setText("⭐চলতি সপ্তাহের চাকরি পত্রিকার রিভিউ দেখুন ⭐");

        bottomHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), Master_List_Page.class);
                p.putExtra("SUB_CAT_ID", "11");
                startActivity(p);
            }
        });


    }

    @Override
    public void onClick(View v) {
        Intent p = new Intent(getApplicationContext(), All_Circular_List.class);
        switch (v.getId()) {
            case R.id.govt_job: // govt job card view
                p.putExtra("SUB_CAT_ID", "1");
                startActivity(p);
                break;
            case R.id.non_govt_job: // non govt job card view
                p.putExtra("SUB_CAT_ID", "3");
                startActivity(p);
                break;
            case R.id.bank_job: // bank job card view
                p.putExtra("SUB_CAT_ID", "2");
                startActivity(p);
                break;
            case R.id.tcr_job: // teacher job card view
                p.putExtra("SUB_CAT_ID", "4");
                startActivity(p);
                break;
            case R.id.part_time_job: // Part-Time Teacher job card view
                p.putExtra("SUB_CAT_ID", "5");
                startActivity(p);
                break;
            case R.id.ngo_job: // ngo job card view
                p.putExtra("SUB_CAT_ID", "7");
                startActivity(p);
                break;
            case R.id.garments_job: // garments job card view
                p.putExtra("SUB_CAT_ID", "6");
                startActivity(p);
                break;
            case R.id.defence_job: // defence/railway job card view
                p.putExtra("SUB_CAT_ID", "8");
                startActivity(p);
                break;
            case R.id.health_job: // medical/health job card view
                p.putExtra("SUB_CAT_ID", "9");
                startActivity(p);
                break;

            default:
                break;

        }
    }
}