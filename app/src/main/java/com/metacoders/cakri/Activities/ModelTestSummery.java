package com.metacoders.cakri.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metacoders.cakri.Adapter.solutionAdapter;
import com.metacoders.cakri.Models.ModelQustion;
import com.metacoders.cakri.Models.MsgModel;
import com.metacoders.cakri.Models.userAnsModel;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;
import com.metacoders.cakri.Utils.Utilities;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelTestSummery extends AppCompatActivity {

    RecyclerView recyclerView ;
    LinearLayoutManager linearLayoutManager ;
    ModelQustion qustion ;
    solutionAdapter adapter  ;
    TextView totalView  , summery ;
    Button logINBtn , SubmitScoreBTn ;
    Utilities utilities = new Utilities() ;
    Boolean isUpload = false  ;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_test_summery);
        totalView = findViewById(R.id.total_ammount_tv_id) ;
        recyclerView = findViewById(R.id.list ) ;
        logINBtn = findViewById(R.id.LoginBtn) ;
        summery = findViewById(R.id.summery) ;

        SubmitScoreBTn = findViewById(R.id.uploadScore) ;

        linearLayoutManager = new LinearLayoutManager(this) ;
        recyclerView.setLayoutManager(linearLayoutManager);
        // get list
        qustion =(ModelQustion) getIntent().getSerializableExtra("MODEL") ;
        if(qustion!= null){
        setupUI(qustion) ;
        }


        SubmitScoreBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // upload score Here
                if(!isUpload){

                    uploadScore() ;

                }

            }
        });

        logINBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  p = new Intent(getApplicationContext() ,login_activity.class) ;
                startActivity(p);

            }
        });

    }

    private void uploadScore() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String userRespList = gson.toJson(qustion.getUserAnsList()); // all the order is in  This  String
        int id = utilities.isUserSignedIn(getApplicationContext()) ;
        if(id== 0){
            Toast.makeText(getApplicationContext() ,"Please Log in " , Toast.LENGTH_LONG).show();
        }
        else {
            Call<MsgModel> call = RetrofitClient.getInstance()
                    .getApi()
                    .CreateMarksheet(
                          id  ,
                          qustion.getId() ,
                          Double.parseDouble(totalView.getText().toString()),
                            userRespList,
                            utilities.getSavedName(getApplicationContext())



                    ) ;

            call.enqueue(new Callback<MsgModel>() {
                @Override
                public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {

                    if(response.code()==200){

                        Toast.makeText(getApplicationContext(), " Result Uploaded " , Toast.LENGTH_LONG)
                                .show();

                        Intent p = new Intent(getApplicationContext(), RankPage.class) ;
                        p.putExtra("id" , qustion.getId());
                        startActivity(p);

                    }
                    else {
                        isUpload = false ;
                        Toast.makeText(getApplicationContext(), "Error : " + response.code() , Toast.LENGTH_LONG)
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<MsgModel> call, Throwable t) {

                    isUpload = false ;
                    Toast.makeText(getApplicationContext(), "Error : " + t.getMessage() , Toast.LENGTH_LONG)
                            .show();
                }
            });
        }

    }

    private void setupUI(ModelQustion qustion) {
        // calculate the total
        int rightCount = 0  , wrongCount =  0    ;
        double total = 0 ;

        for(userAnsModel item : qustion.getUserAnsList()){
            if(item.getMark() <= 0){
                wrongCount++ ;
            }

            else {
                rightCount++ ;
            }
            total= total+ item.getMark() ;



        }
        //loop ends
        totalView.setText(df2.format(total)+"");
        summery.setText(rightCount + " Correct Answer In " + qustion.getQusList().size() + " Qustions");
        // setting the adapter


        recyclerView.setAdapter(new solutionAdapter(getApplicationContext() , qustion.getQusList() , qustion.getUserAnsList()  , qustion.getQusType()));


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpScoreContainer() ;
    }

    private void setUpScoreContainer() {
        int id = 0 ;
        id = utilities.isUserSignedIn(getApplicationContext()) ;

        if(id==0){
            // hide submit score
            logINBtn.setVisibility(View.VISIBLE);
            SubmitScoreBTn.setVisibility(View.GONE);
        }
        else {
            logINBtn.setVisibility(View.GONE);
            SubmitScoreBTn.setVisibility(View.VISIBLE);
        }
    }
}