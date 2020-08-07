package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.metacoders.cakri.Adapter.categorylistAdapter;

public class qus_bank_bcs_govt_list_page extends AppCompatActivity {

    TextView headerTitle ;
    RecyclerView list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qus_bank_bcs_govt_list_page);

      //   headerTitle = findViewById(R.id.page_title_textView) ;
         list = findViewById(R.id.categoryList) ;
         list.setLayoutManager(new LinearLayoutManager(this));
         list.setAdapter(new categorylistAdapter(this , true));
       //  headerTitle.setText("চাকরির ক্যাটাগরী দেখুন");
    }
}