package com.metacoders.cakri.Activities.lists;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.github.ybq.android.spinkit.SpinKitView;
import com.metacoders.cakri.Activities.Details.PostDetailActivity;
import com.metacoders.cakri.Adapter.JobCircularAdaper;
import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;
import com.metacoders.cakri.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public  class All_List_Page extends AppCompatActivity implements JobCircularAdaper.ItemClickLisnter {

    RecyclerView recyclerView;
    int currentPage = 1;
    int totalPage = 1;
    String nextPage;
    JobCircularAdaper adaper;
    List<JobCircularReponseModel.Job_Circular_Model> circularList = new ArrayList<>();
    List<JobCircularReponseModel.Job_Circular_Model> mcircularList = new ArrayList<>();
    boolean isScrolling = false;
    LinearLayoutManager manager;
    int currentItems, totalItems, scrollOutItems;
    SpinKitView progress;
    RelativeLayout loadingPanel;
    LottieAnimationView animationView;
    String SUB_CAT_ID = "1" ;
    String Type  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__circular__list);
        recyclerView = findViewById(R.id.recyclerView);
        progress = (SpinKitView) findViewById(R.id.spin_kit);
        loadingPanel = findViewById(R.id.loadingPanel);
        animationView = findViewById(R.id.lav_actionBar);



        // get data
        Type = getIntent().getStringExtra("TYPE");

        manager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);

        adaper = new JobCircularAdaper(getApplicationContext(), circularList, this);
        recyclerView.setAdapter(adaper);


        loadList(currentPage);

        initScrollListener();


    }

    private void loadList(int page) {
        if (page == 1) {
            progress.setVisibility(View.GONE);
        } else progress.setVisibility(View.VISIBLE);


        Call<JobCircularReponseModel> call = RetrofitClient.getInstance()
                .getApi().getAll(Type  ,page);


        call.enqueue(new Callback<JobCircularReponseModel>() {
            @Override
            public void onResponse(Call<JobCircularReponseModel> call, Response<JobCircularReponseModel> response) {
                Log.d("TAG", "loadList: " + response.raw() );
                if (response.code() == 200) {


                    currentPage = response.body().getCurrentPage();
                    totalPage = response.body().getLastPage();
                    // now make a list

                    mcircularList = response.body().getData();
                    // circularList = response.body().getData() ;

                    progress.setVisibility(View.GONE);
                    circularList.addAll(mcircularList);
                    adaper.notifyDataSetChanged();


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (currentPage == 1) {
                                //shimmerFrameLayout.setVisibility(View.GONE);
                                animationView.pauseAnimation();
                                loadingPanel.setVisibility(View.GONE);

                            }
                        }
                    }, 500);


                } else {
                    Log.d(Constants.TAG, response.code() + "");
                }


            }

            @Override
            public void onFailure(Call<JobCircularReponseModel> call, Throwable t) {

                Log.d(Constants.TAG, t.getMessage() + "");

            }
        });


    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    loadMore();
                }
            }
        });


    }

    private void loadMore() {


        if (currentPage != totalPage) {
            loadList(currentPage + 1);
            ;
            isScrolling = false;

        } else {
            isScrolling = false;
            Toast.makeText(getApplicationContext(), "You Are At The Last Page", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onItemClick(JobCircularReponseModel.Job_Circular_Model model) {

        Intent p = new Intent(getApplicationContext(), PostDetailActivity.class);
        p.putExtra("MODEL", model);
        startActivity(p);

    }
}