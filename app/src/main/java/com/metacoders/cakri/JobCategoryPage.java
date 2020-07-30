package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.metacoders.cakri.Adapter.categorylistAdapter;

public class JobCategoryPage extends AppCompatActivity {

    TextView headerTitle ;
    RecyclerView list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

         headerTitle = findViewById(R.id.page_title_textView) ;
         list = findViewById(R.id.categoryList) ;
         list.setLayoutManager(new LinearLayoutManager(this));
         list.setAdapter(new categorylistAdapter(this));
         headerTitle.setText("চাকরির ক্যাটাগরী দেখুন");
    }
}