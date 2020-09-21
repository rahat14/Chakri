package com.metacoders.cakri.Activities.lists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.github.ybq.android.spinkit.SpinKitView;
import com.metacoders.cakri.Activities.Details.SinglePostDownloadArea;
import com.metacoders.cakri.Adapter.BookMarkAdaper;
import com.metacoders.cakri.Models.BookmarkModel;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;
import com.metacoders.cakri.Utils.Constants;
import com.metacoders.cakri.Utils.Utilities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Book_Mark_List extends AppCompatActivity implements  BookMarkAdaper.ItemClickLisnter {
    RecyclerView recyclerView;

    String nextPage;
    BookMarkAdaper adaper;
    List<BookmarkModel> circularList = new ArrayList<>();
    List<BookmarkModel> mcircularList = new ArrayList<>();
    boolean isScrolling = false;
    LinearLayoutManager manager;
    int currentItems, totalItems, scrollOutItems;
    SpinKitView progress;
    RelativeLayout loadingPanel;
    LottieAnimationView animationView;
    String cat_id , sub_cat_id ;
    Utilities utilities = new Utilities();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__mark__list);

        recyclerView = findViewById(R.id.recyclerView);
        progress = (SpinKitView) findViewById(R.id.spin_kit);
        loadingPanel = findViewById(R.id.loadingPanel);
        animationView = findViewById(R.id.lav_actionBar);

        // getting  the data
        cat_id = getIntent().getStringExtra("cat_id") ;
        sub_cat_id = getIntent().getStringExtra("sub_cat_id") ;


        manager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);







    }

    private void loadList() {
            progress.setVisibility(View.GONE);

        Call<List<BookmarkModel>> call = RetrofitClient.getInstance()
                .getApi().getBookMark(utilities.getuserID(getApplicationContext()));

        //
        call.enqueue(new Callback<List<BookmarkModel>>() {
            @Override
            public void onResponse(Call<List<BookmarkModel>> call, Response<List<BookmarkModel>> response) {


                if (response.code() == 200) {
                    // now make a list

                    mcircularList = response.body();
                    progress.setVisibility(View.GONE);
                    animationView.setVisibility(View.GONE);
                    recyclerView.setAdapter(new BookMarkAdaper(getApplicationContext(), mcircularList  , Book_Mark_List.this));


                } else {
                    Log.d(Constants.TAG, response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<List<BookmarkModel>> call, Throwable t) {

                Log.d(Constants.TAG, t.getMessage() + "");

            }
        });


    }

    @Override
    public void onItemClick(BookmarkModel model) {
        Intent p = new Intent(getApplicationContext(), SinglePostDownloadArea.class);
        // getting the data
        p.putExtra("POST_TYPE" , model.getPostType()+"") ;
        p.putExtra("ID" , model.getPostId()+"") ;
        p.putExtra("TYPE" , 1 ) ;
        startActivity(p);


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }
}