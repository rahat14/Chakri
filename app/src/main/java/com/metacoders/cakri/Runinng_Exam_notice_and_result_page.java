package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.metacoders.cakri.Adapter.categorylistAdapter;
import com.metacoders.cakri.Adapter.listAdapter;

public class Runinng_Exam_notice_and_result_page extends AppCompatActivity {
    TextView headerTitle ;
    RecyclerView list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runinng__exam_notice_and_result_page);


        headerTitle = findViewById(R.id.page_title_textView) ;
        list = findViewById(R.id.list) ;
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new listAdapter(this));
        headerTitle.setText("চলমান চাকরি পরীক্ষার নোটিশ ও রেজাল্ট দেখুন");
        headerTitle.setSelected(true);

    }
}