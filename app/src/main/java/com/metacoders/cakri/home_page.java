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
import com.google.android.material.navigation.NavigationView;
import com.metacoders.cakri.Adapter.listAdapter;

public class home_page extends AppCompatActivity {


    RecyclerView latestUpdate , latestCircular , latestJobPrep ;
    DrawerLayout drawerLayout ;
    ImageView hamburger_Btn  ;
    ActionBarDrawerToggle toggle;
    MaterialToolbar toolbar ;
    listAdapter.ItemClickListenter itemClickListenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        latestCircular = findViewById(R.id.latestCircular) ;
        drawerLayout = findViewById(R.id.drawer_layout) ;
        toolbar = findViewById(R.id.toolbar) ;
        hamburger_Btn = findViewById(R.id.hamburgerBtn);
        latestJobPrep = findViewById(R.id.latestJobPrep) ;

        itemClickListenter = new listAdapter.ItemClickListenter() {
            @Override
            public void onItemClick(View view, int pos) {

                Intent p = new Intent(getApplicationContext() , PostDetailActivity.class);
                startActivity(p);
            }
        };

        latestJobPrep.setLayoutManager(new LinearLayoutManager(this));
//        latestUpdate.setLayoutManager(new LinearLayoutManager(this));
        latestCircular.setLayoutManager(new LinearLayoutManager(this));


     //   latestUpdate.setAdapter(new listAdapter(this));
        latestJobPrep.setAdapter(new listAdapter(this,itemClickListenter));
        latestCircular.setAdapter(new listAdapter(this, itemClickListenter));
//        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

//        drawerLayout.addDrawerListener(toggle);
//
//        toggle.syncState();
//        toggle.setDrawerSlideAnimationEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//       toolbar.setNavigationIcon(R.drawable.hamburber_menu);
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
//                    drawerLayout.closeDrawer(Gravity.LEFT);
//                } else {
//                    drawerLayout.openDrawer(Gravity.LEFT);
//                }
//            }
//        });
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

                Intent p = new Intent(getApplicationContext() , jobCircularCategory.class);
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


    public  void setUpSideBar(){

        LinearLayout bcs_model_test , bank_model_test ;

        bcs_model_test = drawerLayout.findViewById(R.id.bcs_model_test) ;
        bank_model_test = drawerLayout.findViewById(R.id.bank_model_test) ;


        bcs_model_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext() , modelTestCategory.class);
                p.putExtra("TITLE" , "বিসিএস মডেল টেস্ট") ;
                p.putExtra("CAT_ID" , "1") ;
                startActivity(p);

            }
        });

        bank_model_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext() , modelTestCategory.class);
                p.putExtra("TITLE" , "ব্যাংক মডেল টেস্ট") ;
                p.putExtra("CAT_ID" , "2") ;
                startActivity(p);

            }
        });


    }
}