package com.metacoders.cakri;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.metacoders.cakri.Activities.Contact_us;
import com.metacoders.cakri.Activities.Details.PostDetailActivity;
import com.metacoders.cakri.Activities.Profile_Activity;
import com.metacoders.cakri.Activities.Search;
import com.metacoders.cakri.Activities.Details.SinglePostDownloadArea;
import com.metacoders.cakri.Activities.lists.All_Job_Prep;
import com.metacoders.cakri.Activities.lists.All_List_Page;
import com.metacoders.cakri.Activities.lists.Book_Mark_List;
import com.metacoders.cakri.Activities.lists.FaqList;
import com.metacoders.cakri.Activities.lists.NotificaitonList;
import com.metacoders.cakri.Activities.login_activity;
import com.metacoders.cakri.Adapter.JobCircularAdaper;
import com.metacoders.cakri.Adapter.listAdapter;
import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.StartUpResponse;
import com.metacoders.cakri.Utils.Constants;
import com.metacoders.cakri.Utils.FireBase_notification;
import com.metacoders.cakri.Utils.Utilities;
import com.onesignal.OSNotificationAction;

import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class home_page extends AppCompatActivity implements JobCircularAdaper.ItemClickLisnter {


    RecyclerView latestUpdate, latestCircular, latestJobPrep;

    ImageView hamburger_Btn;
    ActionBarDrawerToggle toggle;
    MaterialToolbar toolbar;
    listAdapter.ItemClickListenter itemClickListenter;
    StartUpResponse startUpResponse = null;
    List<JobCircularReponseModel.Job_Circular_Model> circularList = new ArrayList<>();
    List<JobCircularReponseModel.Job_Circular_Model> prepList = new ArrayList<>();
    TextView allCircular , allPrep ;

    listAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

//        OneSignal.startInit(this)
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .setNotificationOpenedHandler(new notificationOpenHandler())
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .init();
        // recive the data
        startUpResponse = (StartUpResponse) getIntent().getSerializableExtra("DATA");

      //  startService(new Intent(this, FireBase_notification.class));
     //   startService(new Intent(this, FireBase_notification.class));
        setupUI(startUpResponse);

        startService(new Intent(this, FireBase_notification.class));


        setUpSideBar();


        findViewById(R.id.allBtnForCircular).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext(), All_List_Page.class);
                p.putExtra("TYPE" , Constants.CIRCULAR_TYPE+"") ;
                startActivity(p);

            }
        });

        findViewById(R.id.allBtnForPrep).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext(), All_List_Page.class);
                p.putExtra("TYPE" , Constants.JOB_PREP_TYPE+"") ;
                startActivity(p);



            }
        });


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
        NavigationView navigationView ;
        DrawerLayout drawerLayout;
        TextView name , phone ;

        LinearLayout bcs_model_test, bank_model_test , daily_news,bcs_preparation,bank_preparation,teacher_preparation,current_qus_sol,all_job_sol
                        ,viva_expi,interview_tip,application_cv,job_qus,inspratn,age_cal,prblms_update,notifi
                , love , share , bookmark , contact ;


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
    public void onItemClick(JobCircularReponseModel.Job_Circular_Model model) {

        Intent p = new Intent(getApplicationContext(), PostDetailActivity.class);
        p.putExtra("MODEL", model);
        startActivity(p);


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpSideBar();
    }


//    public class notificationOpenHandler implements OneSignal.NotificationOpenedHandler {
//        @Override
//        public void notificationOpened(OSNotificationOpenResult result) {
//            //   String title = result.notification.payload.title;
//            String desc = result.notification.payload.body;
//            //  String f = result.notification.payload.groupKey
//
//           // Intent intent = new Intent(getApplicationContext(), NottificationPage.class);
//          //  intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//          //  startActivity(intent);
//            OSNotificationAction.ActionType actionType = result.action.type;
//            JSONObject data = result.notification.payload.additionalData;
//            String post_type  , id ;
//            Log.d("TAG", "opended");
//
//            Log.d("TAG", "result.notification.payload.toJSONObject().toString(): " + result.notification.payload.toJSONObject().toString());
//
//            if (data != null) {
//                post_type = data.optString("post_type", null);
//                id = data.optString("id" , null) ;
//                if (!post_type.equals("000"))
//                {
//                    Log.d("TAG", "key set with value: " + post_type);
//                    Log.d("TAG", "key set with value: " + id);
//                    Intent intent = new Intent(getApplicationContext(), SinglePostDownloadArea.class);
//                    intent.putExtra("POST_TYPE" , post_type) ;
//                    intent.putExtra("ID" , id ) ;
//                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                }
//                else {
//
//                    //Log.d("TAG", "key set with value: " + post_type);
//                    Intent intent = new Intent(getApplicationContext(), NotificaitonList.class);
//                    startActivity(intent);
//                }
//
//
//
//            }
//
//
//
//
//        }
//
//    }
}