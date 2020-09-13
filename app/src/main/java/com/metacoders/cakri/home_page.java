package com.metacoders.cakri;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.metacoders.cakri.Activities.Details.PostDetailActivity;
import com.metacoders.cakri.Adapter.JobCircularAdaper;
import com.metacoders.cakri.Adapter.listAdapter;
import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.StartUpResponse;
import com.metacoders.cakri.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class home_page extends AppCompatActivity implements JobCircularAdaper.ItemClickLisnter {


    RecyclerView latestUpdate, latestCircular, latestJobPrep;
    DrawerLayout drawerLayout;
    ImageView hamburger_Btn;
    ActionBarDrawerToggle toggle;
    MaterialToolbar toolbar;
    listAdapter.ItemClickListenter itemClickListenter;
    StartUpResponse startUpResponse = null;
    List<JobCircularReponseModel.Job_Circular_Model> circularList = new ArrayList<>();
    List<JobCircularReponseModel.Job_Circular_Model> prepList = new ArrayList<>();
    listAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // recive the data
        startUpResponse = (StartUpResponse) getIntent().getSerializableExtra("DATA");


        setupUI(startUpResponse);




        hamburger_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
      
        setUpSideBar();

        findViewById(R.id.job_category_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext(), jobCircularCategory.class);
                startActivity(p);

            }
        });

        findViewById(R.id.job_prep).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext(), Job_prep_page.class);
                startActivity(p);

            }
        });

        findViewById(R.id.running_job_notice_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext(), Runinng_Exam_notice_and_result_page.class);
                p.putExtra("cat_id", "19");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);


            }
        });
        //best_job_circular
        findViewById(R.id.best_job_circular).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext(), Master_List_Page.class);

                startActivity(p);


            }
        });
    }

    private void setupUI(StartUpResponse startUpResponse) {
        latestCircular = findViewById(R.id.latestCircular);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        hamburger_Btn = findViewById(R.id.hamburgerBtn);
        latestJobPrep = findViewById(R.id.latestJobPrep);

        // setting the list
        prepList.addAll(startUpResponse.getPreparations());
        circularList.addAll(startUpResponse.getCircular());

        //setting llm
        latestJobPrep.setLayoutManager(new LinearLayoutManager(this));
        latestCircular.setLayoutManager(new LinearLayoutManager(this));
        //adapter
        adapter = new listAdapter(home_page.this, itemClickListenter, prepList) ;
        // setting adapter
        latestJobPrep.setAdapter(adapter);
        latestCircular.setAdapter(new JobCircularAdaper(getApplicationContext(), circularList, this));
    }


    public void setUpSideBar() {

        LinearLayout bcs_model_test, bank_model_test;

        bcs_model_test = drawerLayout.findViewById(R.id.bcs_model_test);
        bank_model_test = drawerLayout.findViewById(R.id.bank_model_test);


        bcs_model_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), modelTestCategory.class);
                p.putExtra("TITLE", "বিসিএস মডেল টেস্ট");
                p.putExtra("CAT_ID", Constants.MODEL_QUSTION_BCS_CATGORY);
                startActivity(p);

            }
        });

        bank_model_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), modelTestCategory.class);
                p.putExtra("TITLE", "ব্যাংক মডেল টেস্ট");
                p.putExtra("CAT_ID", Constants.MODEL_QUSTION_BANK_CATGORY);
                startActivity(p);

            }
        });


    }

    @Override
    public void onItemClick(JobCircularReponseModel.Job_Circular_Model model) {

        Intent p = new Intent(getApplicationContext(), PostDetailActivity.class);
        p.putExtra("MODEL", model);
        startActivity(p);


    }
}