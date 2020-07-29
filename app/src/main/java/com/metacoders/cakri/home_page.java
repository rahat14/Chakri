package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.metacoders.cakri.Adapter.listAdapter;

public class home_page extends AppCompatActivity {


    RecyclerView latestUpdate , latestCircular , latestJobPrep ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        latestCircular = findViewById(R.id.latestCircular) ;
        latestUpdate= findViewById(R.id.latestData) ;
        latestJobPrep = findViewById(R.id.latestJobPrep) ;

        latestJobPrep.setLayoutManager(new LinearLayoutManager(this));
        latestUpdate.setLayoutManager(new LinearLayoutManager(this));
        latestCircular.setLayoutManager(new LinearLayoutManager(this));


        latestUpdate.setAdapter(new listAdapter(this));
        latestJobPrep.setAdapter(new listAdapter(this));
        latestCircular.setAdapter(new listAdapter(this));



    }
}