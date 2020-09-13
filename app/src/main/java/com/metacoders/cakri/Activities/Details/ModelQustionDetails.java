package com.metacoders.cakri.Activities.Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.metacoders.cakri.Adapter.qusAdapter;
import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.ModelQustionListResponse;
import com.metacoders.cakri.Models.qusizModel;
import com.metacoders.cakri.Models.userAnsModel;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;
import com.metacoders.cakri.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_qustion_details);


        descp = findViewById(R.id.desc);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        floatingActionButton = findViewById(R.id.goToOtherPage) ;
        recyclerView = findViewById(R.id.list) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));



        // getting the model
        Intent i = getIntent();

        model = (ModelQustionListResponse.singleModelQus) i.getSerializableExtra("MODEL");
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

    private void setUpUI(ModelQustionListResponse.singleModelQus model) {
        date.setText(model.getDate());
        title.setText(model.getTitle());
        descp.setText(model.getDescription());

        requestForQiestion(model.getId());


    }

    private void requestForQiestion(Integer id) {
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
                        if (qusList.size() > 0) {
                            // qus is loaded
                            //now generate answer-sheet
                            /// need to generate this for answer sheet
                            for (int i = 0; i < qusList.size(); i++) {
                                userResPonseList.add(new userAnsModel(i, 0, "nulll", 0));
                            }
                            // setting the adapter
                            // bcs = 1 , bank  =
                            adapter = new qusAdapter(getApplicationContext() ,qusList , userResPonseList , Constants.QUS_TYPE_BANK) ;
                            recyclerView.setAdapter(adapter);
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
                ;
            }
        });
    }

    private void TriggerDialouge() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Submit Answers");
        dialogBuilder.setMessage("Are You Sure You Want To Submit ?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // go to other acitivty

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
}