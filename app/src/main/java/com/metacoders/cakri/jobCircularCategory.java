package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.TextView;

import com.metacoders.cakri.Adapter.categorylistAdapter;

public class jobCircularCategory extends AppCompatActivity {

    TextView headerTitle , bottomHeader ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_circular_category);
        headerTitle = findViewById(R.id.page_title_textView) ;
        bottomHeader = findViewById(R.id.page_bottom_textView);

        headerTitle.setText("চাকরির ক্যাটাগরী");
        bottomHeader.setSelected(true);
        bottomHeader.setText("⭐চলতি সপ্তাহের চাকরি পত্রিকার রিভিউ দেখুন ⭐");
    }
}