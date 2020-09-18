package com.metacoders.cakri.Activities.Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.metacoders.cakri.Activities.ModelTestSummery;
import com.metacoders.cakri.Adapter.qusAdapter;
import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.ModelQustion;
import com.metacoders.cakri.Models.ModelQustionListResponse;
import com.metacoders.cakri.Models.qusizModel;
import com.metacoders.cakri.Models.userAnsModel;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;
import com.metacoders.cakri.Utils.Constants;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_qustion_details);


        descp = findViewById(R.id.desc);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        startDwld = findViewById(R.id.startDwld) ;

        floatingActionButton = findViewById(R.id.goToOtherPage) ;
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

    private void startTimer() {
        if(model.getSubCatId().equals("0")){
// full qustion
            if(model.getId().equals("2")){
                // bcs fulll
                EXAM_MAX_TIME = Constants.BCS_FULL_TIME ;

            }
            else EXAM_MAX_TIME =Constants.BANK_FULL_TIME ;

        }
        else {
            EXAM_MAX_TIME = Constants.BANK_HALF_TIME ;
        }
        countDownTimer  =   new CountDownTimer(EXAM_MAX_TIME, 1000) {

            public void onTick(long millisUntilFinished) {
                // timer.setText("seconds remaining: " + millisUntilFinished / 1000);

//                            long  min =  TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)  ;
//                            long ss =TimeUnit.MINUTES.toMillis(min);
//                            String sec = TimeUnit.MILLISECONDS.toSeconds(ss) + "" ;

                timer.setText(getIntervalTime(millisUntilFinished));


            }

            public void onFinish() {
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
        intMillis -= hoursMillis;
        long mm = TimeUnit.MILLISECONDS.toMinutes(intMillis);
        long minutesMillis = TimeUnit.MINUTES.toMillis(mm);
        intMillis -= minutesMillis;
        long ss = TimeUnit.MILLISECONDS.toSeconds(intMillis);
        long secondsMillis = TimeUnit.SECONDS.toMillis(ss);
        intMillis -= secondsMillis;

        String stringInterval = "%02d:%02d";
        return String.format(stringInterval ,mm, ss);
    }
}