package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.metacoders.cakri.Adapter.categorylistAdapter;


public class jobCircularCategory extends AppCompatActivity implements View.OnClickListener {

    TextView headerTitle, bottomHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_circular_category);
        headerTitle = findViewById(R.id.page_title_textView);
        bottomHeader = findViewById(R.id.page_bottom_textView);

        headerTitle.setText("চাকরির ক্যাটাগরী");
        bottomHeader.setSelected(true);
        bottomHeader.setText("⭐চলতি সপ্তাহের চাকরি পত্রিকার রিভিউ দেখুন ⭐");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.govt_job: // govt job card view

                break;
            case R.id.non_govt_job: // non govt job card view

                break;
            case R.id.bank_job: // bank job card view

                break;
            case R.id.tcr_job: // teacher job card view

                break;
            case R.id.part_tcr_job: // Part-Time Teacher job card view

                break;
            case R.id.ngo_job: // ngo job card view

                break;
            case R.id.garments_job: // garments job card view

                break;
            case R.id.defence_job: // defence/railway job card view

                break;
            case R.id.health_job: // medical/health job card view

                break;

            default:
                break;

        }
    }
}