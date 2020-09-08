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

            default:
                break;

        }
    }
}