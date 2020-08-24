 package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.metacoders.cakri.Adapter.categorylistAdapter;
import com.metacoders.cakri.Adapter.listAdapter;

 public class Master_List_Page extends AppCompatActivity {

     TextView headerTitle ;
     RecyclerView list ;
     listAdapter.ItemClickListenter itemClickListenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master__list__page);

        headerTitle = findViewById(R.id.page_title_textView) ;
        list = findViewById(R.id.categoryList) ;
        list.setLayoutManager(new LinearLayoutManager(this));


        itemClickListenter = new listAdapter.ItemClickListenter() {
            @Override
            public void onItemClick(View view, int pos) {

                Intent p = new Intent(getApplicationContext() , PostDetailActivity.class);
                startActivity(p);
            }
        };
        list.setAdapter(new listAdapter(this ,itemClickListenter));
        headerTitle.setText("বাছাইকৃত বেষ্ট ১০০০+ পদে চাকরির বিজ্ঞপ্তি দেখুন");
        headerTitle.setSelected(true);
    }
}