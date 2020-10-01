package com.metacoders.cakri.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.metacoders.cakri.Adapter.LeaderBoardAdapter;
import com.metacoders.cakri.Models.LeaderBoardModel;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankPage extends AppCompatActivity {

    RecyclerView list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_page);
        list = findViewById(R.id.list) ;
        list.setLayoutManager(new LinearLayoutManager(this));


        Call<List<LeaderBoardModel>> tel = RetrofitClient.getInstance()
                .getApi()
                .getRank(getIntent().getStringExtra("id"));

        tel.enqueue(new Callback<List<LeaderBoardModel>>() {
            @Override
            public void onResponse(Call<List<LeaderBoardModel>> call, Response<List<LeaderBoardModel>> response) {

                if(response.code()==200){
                    List<LeaderBoardModel> listt = response.body() ;

                    list.setAdapter(new LeaderBoardAdapter(getApplicationContext() , listt));
                }
                else {
                    Toast.makeText(getApplicationContext() ,"Error : "+ response.code() , Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<LeaderBoardModel>> call, Throwable t) {

                Toast.makeText(getApplicationContext() ,"Error : "+ t.getMessage() , Toast.LENGTH_LONG).show();
            }
        });

    }
}