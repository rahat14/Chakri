package com.metacoders.cakri.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.metacoders.cakri.Adapter.solutionAdapter;
import com.metacoders.cakri.Models.ModelQustion;
import com.metacoders.cakri.Models.userAnsModel;
import com.metacoders.cakri.R;

import java.text.DecimalFormat;

public class ModelTestSummery extends AppCompatActivity {

    RecyclerView recyclerView ;
    LinearLayoutManager linearLayoutManager ;
    ModelQustion qustion ;
    solutionAdapter adapter  ;
    TextView totalView ;

    private static DecimalFormat df2 = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_test_summery);
        totalView = findViewById(R.id.total_ammount_tv_id) ;
        recyclerView = findViewById(R.id.list ) ;
        linearLayoutManager = new LinearLayoutManager(this) ;
        recyclerView.setLayoutManager(linearLayoutManager);
        // get list
        qustion =(ModelQustion) getIntent().getSerializableExtra("MODEL") ;
        if(qustion!= null){
        setupUI(qustion) ;
        }



    }

    private void setupUI(ModelQustion qustion) {
        // calculate the total
        int rightCount = 0  , wrongCount =  0    ;
        double total = 0 ;

        for(userAnsModel item : qustion.getUserAnsList()){
            if(item.getMark() < 0){
                wrongCount++ ;
            }
            else {
                rightCount++ ;

            }
            total= total+ item.getMark() ;

        }
        //loop ends
        totalView.setText(df2.format(total)+"");
        // setting the adapter
        recyclerView.setAdapter(new solutionAdapter(getApplicationContext() , qustion.getQusList() , qustion.getUserAnsList()  , 0));


    }


}