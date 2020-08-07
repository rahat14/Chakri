package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.TextView;

import com.metacoders.cakri.Adapter.categorylistAdapter;

public class jobCircularCategory extends AppCompatActivity {

    TextView headerTitle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_circular_category);
        headerTitle = findViewById(R.id.page_title_textView) ;

        headerTitle.setText("চাকরির ক্যাটাগরী");
    }
}