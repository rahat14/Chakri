package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.metacoders.cakri.Adapter.qusAdapter;
import com.metacoders.cakri.Models.qusizModel;
import com.metacoders.cakri.Models.userAnsModel;
import com.metacoders.cakri.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class Quiz_Activity extends AppCompatActivity {

    RecyclerView recyclerView ;
    List<qusizModel> qusList = new ArrayList<>();
    List<userAnsModel> userResPonseList = new ArrayList<>();
    List<userAnsModel> andsResPonseList = new ArrayList<>();
    FloatingActionButton floatingActionButton ;
    qusAdapter adapter ;
    double totalmarks = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_);

        recyclerView = findViewById(R.id.list) ;
        floatingActionButton = findViewById(R.id.test) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize() ;

        // create demo qus

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalmarks = 0 ;

                andsResPonseList= adapter.getUserResPonseList() ;

                Log.d("TAG" ,"SIZE"  + andsResPonseList.size() + "") ;
                for (userAnsModel item : andsResPonseList){

                    totalmarks = totalmarks+ item.getMark();

                    if(item.getChoosePos() != 0 ){
                        Log.d("TAG" , "qus numbered " + item.getPos() + "ANSERED  : "  + item.getChoosePos()+ "" + "value : " +
                                "" + item.getSelectedAns()) ;
                    }

                }


            }
        });


        /// need to generate this for answer sheet
        for(int i = 0 ; i<qusList.size() ; i++){

            userResPonseList.add(new userAnsModel(i , 0  , "nulll" , 0)) ;
        }
        Log.d("TAG", "onCreate: qus size =" + qusList.size() + "  response =" + userResPonseList.size());
        // bcs = 1 , bank  =
        adapter = new qusAdapter(this ,qusList , userResPonseList , Constants.QUS_TYPE_BANK) ;
        recyclerView.setAdapter(adapter);





    }
}