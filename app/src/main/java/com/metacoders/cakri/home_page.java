package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.metacoders.cakri.Adapter.listAdapter;

public class home_page extends AppCompatActivity {


    RecyclerView latestUpdate , latestCircular , latestJobPrep ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        latestCircular = findViewById(R.id.latestCircular) ;
        latestUpdate= findViewById(R.id.latestData) ;
        latestJobPrep = findViewById(R.id.latestJobPrep) ;

        latestJobPrep.setLayoutManager(new LinearLayoutManager(this));
        latestUpdate.setLayoutManager(new LinearLayoutManager(this));
        latestCircular.setLayoutManager(new LinearLayoutManager(this));


        latestUpdate.setAdapter(new listAdapter(this));
        latestJobPrep.setAdapter(new listAdapter(this));
        latestCircular.setAdapter(new listAdapter(this));

        findViewById(R.id.job_category_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext() , JobCategoryPage.class);
                startActivity(p);

            }
        });

        findViewById(R.id.job_prep).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext() , Job_prep_page.class);
                startActivity(p);

            }
        });

        findViewById(R.id.running_job_notice_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext() , Runinng_Exam_notice_and_result_page.class);
                startActivity(p);

            }
        });
        //best_job_circular
        findViewById(R.id.best_job_circular).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext() , Master_List_Page.class);
                startActivity(p);

            }
        });
    }
}