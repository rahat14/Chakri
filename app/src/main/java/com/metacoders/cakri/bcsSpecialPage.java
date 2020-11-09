package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.metacoders.cakri.Activities.Contact_us;
import com.metacoders.cakri.Activities.Profile_Activity;
import com.metacoders.cakri.Activities.Search;
import com.metacoders.cakri.Activities.lists.All_Job_Prep;
import com.metacoders.cakri.Activities.lists.Book_Mark_List;
import com.metacoders.cakri.Activities.lists.FaqList;
import com.metacoders.cakri.Activities.lists.NotificaitonList;
import com.metacoders.cakri.Activities.login_activity;
import com.metacoders.cakri.Utils.Constants;
import com.metacoders.cakri.Utils.Utilities;

public class bcsSpecialPage extends AppCompatActivity implements View.OnClickListener {

    CardView adviceC,banglaC,englishC,bangladesh,internationl,geoC,gk_C,ict_C,math_logics,mental_skill,ethics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcs_special_page);

        //id declaration
        adviceC = findViewById(R.id. adviceC);
        banglaC = findViewById(R.id.banglaC);
        englishC = findViewById(R.id.englishC);
        bangladesh = findViewById(R.id.bangladesh);
        internationl = findViewById(R.id.internationl);
        geoC = findViewById(R.id.geoC);
        gk_C = findViewById(R.id.gk_C);
        ict_C = findViewById(R.id.ict_C);
        math_logics = findViewById(R.id.math_logics);
        mental_skill = findViewById(R.id.mental_skill);
        ethics = findViewById(R.id.ethics);

        //click listener
        adviceC.setOnClickListener(this);
        banglaC.setOnClickListener(this);
        englishC.setOnClickListener(this);
        bangladesh.setOnClickListener(this);
        internationl.setOnClickListener(this);
        geoC.setOnClickListener(this);
        gk_C.setOnClickListener(this);
        ict_C.setOnClickListener(this);
        math_logics.setOnClickListener(this);
        mental_skill.setOnClickListener(this);
        ethics.setOnClickListener(this);

        findViewById(R.id.bottomContainer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext() , All_Job_Prep.class) ;
                p.putExtra("cat_id" , "20" ) ;
                p.putExtra("sub_cat_id" , "0") ;
                startActivity(p);
            }
        });

    }
    public void setUpSideBar() {
        NavigationView navigationView ;
        DrawerLayout drawerLayout;
        TextView name , phone ;

        LinearLayout bcs_model_test, bank_model_test , daily_news,bcs_preparation,bank_preparation,teacher_preparation,current_qus_sol,all_job_sol
                ,viva_expi,interview_tip,application_cv,job_qus,inspratn,age_cal,prblms_update,notifi
                , love , share , bookmark , contact ;

        ImageView hamburger_Btn;

        hamburger_Btn = findViewById(R.id.hamburgerBtn);
        drawerLayout = findViewById(R.id.drawer_layout);
        contact =findViewById(R.id.contact) ;
        daily_news = drawerLayout.findViewById(R.id.daily_news);
        bcs_preparation = drawerLayout.findViewById(R.id.bcs_preparation);
        bcs_model_test = drawerLayout.findViewById(R.id.bcs_model_test);
        bank_preparation = drawerLayout.findViewById(R.id.bank_preparation);
        bank_model_test = drawerLayout.findViewById(R.id.bank_model_test);
        teacher_preparation = drawerLayout.findViewById(R.id.teacher_preparation);
        current_qus_sol = drawerLayout.findViewById(R.id.current_qus_sol);
        all_job_sol = drawerLayout.findViewById(R.id.all_job_sol);
        viva_expi = drawerLayout.findViewById(R.id.viva_expi);
        interview_tip = drawerLayout.findViewById(R.id.interview_tip);
        application_cv = drawerLayout.findViewById(R.id.application_cv);
        job_qus = drawerLayout.findViewById(R.id.job_qus);
        inspratn = drawerLayout.findViewById(R.id.inspratn);
        age_cal = drawerLayout.findViewById(R.id.age_cal);
        prblms_update = drawerLayout.findViewById(R.id.prblms_update);
        notifi = drawerLayout.findViewById(R.id.notifi);
        navigationView=findViewById(R.id.nav_view);
        love = findViewById(R.id.heart) ;
        bookmark = findViewById(R.id.bookmark) ;
        share = findViewById(R.id.share) ;




        name = drawerLayout.findViewById(R.id.name_on_header) ;
        phone = drawerLayout.findViewById(R.id.ph_on_header);

        SearchView searchView = findViewById(R.id.search_ed) ;


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent p = new Intent(getApplicationContext() , Search.class);
                p.putExtra("search" , query) ;
                startActivity(p);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        Utilities utilities = new Utilities() ;

        int id  =0 ;

        id =  utilities.isUserSignedIn(getApplicationContext()) ;
        if(id == 0 ){
            name.setText("Login");

            phone.setText("");
        }
        else {
            name.setText(utilities.getSavedName(getApplicationContext()));
            phone.setText(utilities.getSavedContacts(getApplicationContext()));
        }

        // app bar section
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext() , Book_Mark_List.class);
                startActivity(p);
            }
        });

        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));

                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));

                }
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s ="https://play.google.com/store/apps/details?id=" + getPackageName();
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share This App");
                shareIntent.putExtra(Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
            }
        });




        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id =  utilities.isUserSignedIn(getApplicationContext()) ;
                if(id==0){
                    Intent p = new Intent(getApplicationContext(), login_activity.class) ;
                    startActivity(p);

                }
                else {

                    Intent p = new Intent(getApplicationContext(), Profile_Activity.class) ;
                    startActivity(p);

                }

            }
        });



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



        //set click listener

        daily_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "4");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  p = new Intent(getApplicationContext() , Contact_us.class);
                startActivity(p);
            }
        });
        bcs_preparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  nextPage = new Intent(getApplicationContext(), bcsSpecialPage.class);
                startActivity(nextPage);
            }
        });

        bcs_model_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), modelTestCategory.class);
                p.putExtra("TITLE", "বিসিএস মডেল টেস্ট");
                p.putExtra("CAT_ID", Constants.MODEL_QUSTION_BCS_CATGORY);
                startActivity(p);

            }
        });

        bank_preparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(getApplicationContext(), Bank_job_special.class);
                startActivity(nextPage);


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


        teacher_preparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "6");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        current_qus_sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "7");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);

            }
        });

        all_job_sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(getApplicationContext(), allJobSolution.class);
                startActivity(nextPage);
            }
        });

        viva_expi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "14");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        interview_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "15");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        application_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "22");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        job_qus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(getApplicationContext(), FaqList.class);
                startActivity(nextPage);
            }
        });

        inspratn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "13");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        age_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), AgeCalculator.class);
                startActivity(p);
            }
        });

        prblms_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://docs.google.com/document/d/19Fast0IlEUd2XC5hPpNDP038DQKBYjNkCZ4EpLbFdWQ/edit?usp=sharing" ;

                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext() , NotificaitonList.class) ;
                startActivity(p);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpSideBar();
    }


    @Override
    public void onClick(View v) {
        // here in this  page category id is = 2
        Intent p  = new Intent(getApplicationContext() , All_Job_Prep.class);
        switch (v.getId()) {


            case R.id.adviceC:   //advise card View
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "1") ;
                startActivity(p);
                break;

            case R.id.banglaC:
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "2") ;
                startActivity(p);
                break;
            case R.id.englishC:
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "3") ;
                startActivity(p);
                break;
            case R.id.bangladesh:
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "4") ;
                startActivity(p);

                break;
            case R.id.internationl:
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "5") ;
                startActivity(p);
                break;


            case R.id.geoC:
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "6") ;
                startActivity(p);

                break;
            case R.id.gk_C:
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "7") ;
                startActivity(p);
                break;

            case R.id.ict_C:
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "8") ;
                startActivity(p);
                break;
            case R.id.math_logics:
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "9") ;
                startActivity(p);
                break;
            case R.id.mental_skill:
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "10") ;
                startActivity(p);

                break;
            case R.id.ethics:
                p.putExtra("cat_id" , "2" ) ;
                p.putExtra("sub_cat_id" , "11") ;
                startActivity(p);
                break;

            default:
                break;
        }
    }
}