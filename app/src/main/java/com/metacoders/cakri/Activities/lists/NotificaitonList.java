package com.metacoders.cakri.Activities.lists;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.github.ybq.android.spinkit.SpinKitView;
import com.metacoders.cakri.Adapter.NotificaitonAdapter;
import com.metacoders.cakri.Models.NotificaitonResponse;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificaitonList extends AppCompatActivity {
    int currentPage = 1;
    int totalPage = 1;
    String nextPage;
    SpinKitView progress;
    RelativeLayout loadingPanel;
    LottieAnimationView animationView;
    String cat_id, sub_cat_id, qus_type;
    int qus_name ; // bank = 0  or bcs = 1
    boolean isScrolling = false;
    LinearLayoutManager manager;
    int currentItems, totalItems, scrollOutItems;
    RecyclerView list ;
    NotificaitonAdapter.ItemClickListenter  itemClickListenter ;

    List<NotificaitonResponse.NotificationModel> notificationList = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaiton_list);
        list = findViewById(R.id.list) ;
        progress = (SpinKitView) findViewById(R.id.spin_kit);
        loadingPanel = findViewById(R.id.loadingPanel);
        animationView = findViewById(R.id.lav_actionBar);
        manager = new LinearLayoutManager(this ) ;
        itemClickListenter = new NotificaitonAdapter.ItemClickListenter() {
            @Override
            public void onItemClick(View view, int pos) {
                Toast.makeText(getApplicationContext() , "this", Toast.LENGTH_SHORT).show();
            }
        } ;
        list.setLayoutManager(manager);
        loadList(currentPage ) ;
        initScrollListener();
    }

    private void loadList(int page ) {
        Call<NotificaitonResponse> listcal = RetrofitClient.getInstance().getApi()
                .getNotificaiton(page) ;

        listcal.enqueue(new Callback<NotificaitonResponse>() {
            @Override
            public void onResponse(Call<NotificaitonResponse> call, Response<NotificaitonResponse> response) {
                if(response.code()== 200){

                    try{
                        notificationList =  response.body().getData() ;
                        currentPage = response.body().getCurrentPage();
                        totalPage = response.body().getLastPage();
                        if(notificationList.size()>0){
                            list.setAdapter(new NotificaitonAdapter(getApplicationContext() , itemClickListenter ,notificationList  ));

                            progress.setVisibility(View.GONE);

                            Handler handler = new Handler();
                            handler.postDelayed(() -> {
                                if (currentPage == 1) {
                                    //shimmerFrameLayout.setVisibility(View.GONE);
                                    animationView.pauseAnimation();
                                    loadingPanel.setVisibility(View.GONE);

                                }
                            }, 500);

                        }


                    }catch (Exception e ){

                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<NotificaitonResponse> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext() , "Error : "+ t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initScrollListener() {
        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
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


}