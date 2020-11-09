package com.metacoders.cakri.Activities.Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.metacoders.cakri.Activities.Contact_us;
import com.metacoders.cakri.Activities.ModelTestSummery;
import com.metacoders.cakri.Activities.Profile_Activity;
import com.metacoders.cakri.Activities.Search;
import com.metacoders.cakri.Activities.lists.All_Job_Prep;
import com.metacoders.cakri.Activities.lists.Book_Mark_List;
import com.metacoders.cakri.Activities.lists.FaqList;
import com.metacoders.cakri.Activities.lists.NotificaitonList;
import com.metacoders.cakri.Activities.login_activity;
import com.metacoders.cakri.Adapter.qusAdapter;
import com.metacoders.cakri.AgeCalculator;
import com.metacoders.cakri.Bank_job_special;
import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.ModelQustion;
import com.metacoders.cakri.Models.ModelQustionListResponse;
import com.metacoders.cakri.Models.qusizModel;
import com.metacoders.cakri.Models.userAnsModel;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;
import com.metacoders.cakri.Utils.Constants;
import com.metacoders.cakri.Utils.Utilities;
import com.metacoders.cakri.allJobSolution;
import com.metacoders.cakri.bcsSpecialPage;
import com.metacoders.cakri.modelTestCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.mustafagercek.library.LoadingButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelQustionDetails extends AppCompatActivity {

    TextView descp;
    ModelQustionListResponse.singleModelQus model;
    TextView title, date;
    List<qusizModel> qusList = new ArrayList<>();
    List<userAnsModel> userResPonseList = new ArrayList<>();
    List<userAnsModel> andsResPonseList = new ArrayList<>();
    qusAdapter adapter ;
    RecyclerView recyclerView  ;
    FloatingActionButton floatingActionButton ;
    int qus_name   = 0 ;
    LoadingButton loadingButton;
    RelativeLayout startContainer ;
    TextView startDwld;
    private   int EXAM_MAX_TIME  = 600000;
    TextView timer ;
    CountDownTimer countDownTimer  ;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_qustion_details);


        descp = findViewById(R.id.desc);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        startDwld = findViewById(R.id.startDwld) ;
        progressBar = findViewById(R.id.pbar) ;
        floatingActionButton = findViewById(R.id.goToOtherPage) ;
        floatingActionButton.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.list) ;
        loadingButton = findViewById(R.id.loading_button);
        startContainer =findViewById(R.id.startModelTstContainer) ;
        startContainer.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));
        recyclerView.setVisibility(View.INVISIBLE);
        timer =findViewById(R.id.timer);

        loadingButton.setButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startContainer.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                floatingActionButton.setVisibility(View.VISIBLE);
                // start exam
                startTimer() ;


            }
        });


        // getting the model
        Intent i = getIntent();

        model = (ModelQustionListResponse.singleModelQus) i.getSerializableExtra("MODEL");
        qus_name = getIntent().getIntExtra("qus_name" , 0 ) ;
        if (model != null) {
            setUpUI(model);
        }


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TriggerDialouge();
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
                String s =Constants.SHARE_TEXT + " https://play.google.com/store/apps/details?id=" + getPackageName();
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
    private void startTimer() {

        if(model.getSubCatId().equals("0")){

// full qustion
            if(qus_name==Constants.QUS_TYPE_BCS){
                // bcs fulll
                EXAM_MAX_TIME = Constants.BCS_FULL_TIME ;

            }
            else EXAM_MAX_TIME =Constants.BANK_FULL_TIME ;

        }
        else {
            EXAM_MAX_TIME = Constants.BANK_HALF_TIME ;
        }
        progressBar.setMax(EXAM_MAX_TIME);
        progressBar.setProgress(EXAM_MAX_TIME);
        countDownTimer  =   new CountDownTimer(EXAM_MAX_TIME, 1000) {

            public void onTick(long millisUntilFinished) {
                // timer.setText("seconds remaining: " + millisUntilFinished / 1000);

//                            long  min =  TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)  ;
//                            long ss =TimeUnit.MINUTES.toMillis(min);
//                            String sec = TimeUnit.MILLISECONDS.toSeconds(ss) + "" ;

                long rest =(millisUntilFinished /  EXAM_MAX_TIME ) * 100;

                progressBar.setProgress((int) millisUntilFinished);
                timer.setText(getIntervalTime(millisUntilFinished));


            }

            public void onFinish() {
                progressBar.setProgress(0);
                timer.setText("Time Up!");
                // TODO show A dialouge that times up
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ModelQustionDetails.this);
                dialogBuilder.setTitle("Times Up!!!");
                dialogBuilder.setMessage("Are You Sure You Want To Submit ?");
                dialogBuilder.setCancelable(false);
                dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // go to other acitivty
                        countDownTimer.cancel();
                        Intent p = new Intent(getApplicationContext() , ModelTestSummery.class) ;
                        ModelQustion qustion  = new ModelQustion( userResPonseList , qusList  , model.getId() ,qus_name) ;
                        p.putExtra("MODEL" , qustion) ;
                        finish();
                        startActivity(p);

                    }
                });
                dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialogBuilder.create().show();
            }
        }.start();

    }

    private void setUpUI(ModelQustionListResponse.singleModelQus model) {
        date.setText(model.getDate());
        title.setText(model.getTitle());
        descp.setText(model.getDescription());

        requestForQiestion(model.getId());


    }

    private void requestForQiestion(Integer id) {
        loadingButton.onStartLoading();

        // here we request for question
        Call<List<qusizModel>> qusListCall = RetrofitClient.getInstance()
                .getApi()
                .getQusListByModelQustionId(id);


        qusListCall.enqueue(new Callback<List<qusizModel>>() {
            @Override
            public void onResponse(Call<List<qusizModel>> call, Response<List<qusizModel>> response) {
                if (response.code() == 200) {
                    // load the qus list and ans list
                    qusList.addAll(response.body());
                    try {
                        Log.d("TAG", "onResponse: LOAD DONE " );
                        if (qusList.size() > 0) {
                            // qus is loaded
                            //now generate answer-sheet
                            /// need to generate this for answer sheet
                            for (int i = 0; i < qusList.size(); i++) {
                                userResPonseList.add(new userAnsModel(i, 0, "nulll", 0));
                            }
                            // setting the adapter
                            // bcs = 1 , bank  =

                            adapter = new qusAdapter(getApplicationContext() ,qusList , userResPonseList ,qus_name) ;
                            recyclerView.setAdapter(adapter);
                            loadingButton.onStopLoading();
                        }
                    } catch (Exception r) {

                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Error Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<qusizModel>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something Went Worng !!! ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void TriggerDialouge() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ModelQustionDetails.this);
        dialogBuilder.setTitle("Submit Answers");
        dialogBuilder.setMessage("Are You Sure You Want To Submit ?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // go to other acitivty
                countDownTimer.cancel();
                Intent p = new Intent(getApplicationContext() , ModelTestSummery.class) ;
                ModelQustion qustion  = new ModelQustion( userResPonseList , qusList  , model.getId() ,qus_name) ;
                p.putExtra("MODEL" , qustion) ;
                finish();
                startActivity(p);

            }
        });
        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogBuilder.create().show();
    }
    public static String getIntervalTime(long longInterval) {

        long intMillis = longInterval;
        long dd = TimeUnit.MILLISECONDS.toDays(intMillis);
        long daysMillis = TimeUnit.DAYS.toMillis(dd);
        intMillis -= daysMillis;
        long hh = TimeUnit.MILLISECONDS.toHours(intMillis);
        long hoursMillis = TimeUnit.HOURS.toMillis(hh);
      //  intMillis -= hoursMillis;
        long mm = TimeUnit.MILLISECONDS.toMinutes(intMillis);
        long minutesMillis = TimeUnit.MINUTES.toMillis(mm);
        intMillis -= minutesMillis;
        long ss = TimeUnit.MILLISECONDS.toSeconds(intMillis);
        long secondsMillis = TimeUnit.SECONDS.toMillis(ss);
        intMillis -= secondsMillis;

        Log.d("TAG", "getIntervalTime: " + hh);
        String stringInterval = "%02d:%02d";

        return String.format(stringInterval  ,mm, ss);
    }
}