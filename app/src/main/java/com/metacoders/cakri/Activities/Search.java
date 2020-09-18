package com.metacoders.cakri.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.metacoders.cakri.Adapter.SearchAdaper;
import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.SearchResponse;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends AppCompatActivity implements SearchAdaper.ItemClickLisnter {

    SearchView searchView;
    RecyclerView recyclerView ;
    List<JobCircularReponseModel.Job_Circular_Model> list = new ArrayList<>() ;
    SearchAdaper adaper ;
    LottieAnimationView animation ;
    String s ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.list) ;
        searchView = findViewById(R.id.search_bar) ;
        animation =findViewById(R.id.lav_actionBar) ;
        animation.setVisibility(View.VISIBLE);
        s =getIntent().getStringExtra("search") ;

        searchView.setQuery(s , false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaper = new SearchAdaper(getApplication() , list, this ) ;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adaper.getFilter().filter(newText);
                return false;
            }
        });
        loadSearch() ;
    }

    private void loadSearch() {

        Call<SearchResponse> searchResponseCall = RetrofitClient.getInstance()
                .getApi()
                .getSearch() ;


        searchResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if(response.code()==200){
                    list.addAll(response.body().getCircular()) ;
                    list.addAll(response.body().getPrep()) ;
                    //
                    adaper.addItems(list);
                    recyclerView.setAdapter(adaper);
                    animation.setVisibility(View.GONE);
                    adaper.getFilter().filter(s);
                }
                else {
                    Toast.makeText(getApplicationContext() , "Something Went Well"   + response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext() , "Something Went Well "+ t.getMessage() , Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onItemClick(JobCircularReponseModel.Job_Circular_Model model) {

    }
}