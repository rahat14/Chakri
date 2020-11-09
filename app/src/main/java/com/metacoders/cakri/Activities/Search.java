package com.metacoders.cakri.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;
import com.metacoders.cakri.Activities.Details.PostDetailActivity;
import com.metacoders.cakri.Activities.lists.All_Job_Prep;
import com.metacoders.cakri.Activities.lists.Book_Mark_List;
import com.metacoders.cakri.Activities.lists.FaqList;
import com.metacoders.cakri.Activities.lists.NotificaitonList;
import com.metacoders.cakri.Adapter.SearchAdaper;
import com.metacoders.cakri.AgeCalculator;
import com.metacoders.cakri.Bank_job_special;
import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.SearchResponse;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;
import com.metacoders.cakri.Utils.Constants;
import com.metacoders.cakri.Utils.Utilities;
import com.metacoders.cakri.allJobSolution;
import com.metacoders.cakri.bcsSpecialPage;
import com.metacoders.cakri.modelTestCategory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends AppCompatActivity implements SearchAdaper.ItemClickLisnter {

    SearchView searchView;
    RecyclerView recyclerView;
    List<JobCircularReponseModel.Job_Circular_Model> list = new ArrayList<>();
    List<JobCircularReponseModel.Job_Circular_Model> filteredList = new ArrayList<>();
    SearchAdaper adaper;
    LottieAnimationView animation;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.list);
        searchView = findViewById(R.id.search_bar);
        animation = findViewById(R.id.lav_actionBar);
      //  animation.setVisibility(View.VISIBLE);
        s = getIntent().getStringExtra("search");

        searchView.setQuery(s, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaper = new SearchAdaper(getApplicationContext(), list, this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchData(newText.toLowerCase());
                return false;
            }
        });
        downloadWholeList(s);
    }

    private void searchData(String newText) {
        filteredList.clear();
        if (list.size() > 0) {
            // list has data
            for (JobCircularReponseModel.Job_Circular_Model item : list) {
                if (item.getTitle().toLowerCase().contains(newText) || item.getKeywords().toLowerCase().contains(newText)) {
                    filteredList.add(item);
                }
            }
            // set The adapter
            if(filteredList.size()>0){
                adaper.addItems(filteredList);
                animation.setVisibility(View.GONE);
                recyclerView.setAdapter(adaper);
            }
            else {
                adaper.clear();
                animation.setVisibility(View.GONE);
                recyclerView.setAdapter(adaper);
                Toast.makeText(getApplicationContext() , "No Item Found !!" , Toast.LENGTH_SHORT).show();
            }


        } else {
            // list is empty download it
            downloadWholeList(newText);
        }
    }

    public void setUpSideBar() {
        NavigationView navigationView;
        DrawerLayout drawerLayout;
        TextView name, phone;

        LinearLayout bcs_model_test, bank_model_test, daily_news, bcs_preparation, bank_preparation, teacher_preparation, current_qus_sol, all_job_sol, viva_expi, interview_tip, application_cv, job_qus, inspratn, age_cal, prblms_update, notifi, love, share, bookmark, contact;

        ImageView hamburger_Btn;

        hamburger_Btn = findViewById(R.id.hamburgerBtn);
        drawerLayout = findViewById(R.id.drawer_layout);
        contact = findViewById(R.id.contact);
        daily_news = drawerLayout.findViewById(R.id.daily_news);
        bcs_preparation = drawerLayout.findViewById(R.id.bcs_preparation);
        bcs_model_test = drawerLayout.findViewById(R.id.bcs_model_test);
        bank_preparation = drawerLayout.findViewById(R.id.bank_preparation);
        bank_model_test = drawerLayout.findViewById(R.id.bank_model_test);
        teacher_preparation = drawerLayout.findViewById(R.id.teacher_preparation);
        current_qus_sol = drawerLayout.findViewById(R.id.current_qus_sol);
        all_job_sol = drawerLayout.findViewById(R.id.all_job_sol);
        viva_expi = drawerLayout.findViewById(R.id.viva_expi);
        interview_tip = drawerLayout.findViewById(R.id.interview_tip);
        application_cv = drawerLayout.findViewById(R.id.application_cv);
        job_qus = drawerLayout.findViewById(R.id.job_qus);
        inspratn = drawerLayout.findViewById(R.id.inspratn);
        age_cal = drawerLayout.findViewById(R.id.age_cal);
        prblms_update = drawerLayout.findViewById(R.id.prblms_update);
        notifi = drawerLayout.findViewById(R.id.notifi);
        navigationView = findViewById(R.id.nav_view);
        love = findViewById(R.id.heart);
        bookmark = findViewById(R.id.bookmark);
        share = findViewById(R.id.share);


        name = drawerLayout.findViewById(R.id.name_on_header);
        phone = drawerLayout.findViewById(R.id.ph_on_header);

        SearchView searchView = findViewById(R.id.search_ed);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent p = new Intent(getApplicationContext(), Search.class);
                p.putExtra("search", query);
                startActivity(p);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        Utilities utilities = new Utilities();

        int id = 0;

        id = utilities.isUserSignedIn(getApplicationContext());
        if (id == 0) {
            name.setText("Login");

            phone.setText("");
        } else {
            name.setText(utilities.getSavedName(getApplicationContext()));
            phone.setText(utilities.getSavedContacts(getApplicationContext()));
        }

        // app bar section
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), Book_Mark_List.class);
                startActivity(p);
            }
        });

        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));

                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));

                }
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = Constants.SHARE_TEXT + " https://play.google.com/store/apps/details?id=" + getPackageName();

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share This App");
                shareIntent.putExtra(Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
            }
        });


        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = utilities.isUserSignedIn(getApplicationContext());
                if (id == 0) {
                    Intent p = new Intent(getApplicationContext(), login_activity.class);
                    startActivity(p);

                } else {

                    Intent p = new Intent(getApplicationContext(), Profile_Activity.class);
                    startActivity(p);

                }

            }
        });


        hamburger_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });


        //set click listener

        daily_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "4");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), Contact_us.class);
                startActivity(p);
            }
        });
        bcs_preparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextPage = new Intent(getApplicationContext(), bcsSpecialPage.class);
                startActivity(nextPage);
            }
        });

        bcs_model_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), modelTestCategory.class);
                p.putExtra("TITLE", "বিসিএস মডেল টেস্ট");
                p.putExtra("CAT_ID", Constants.MODEL_QUSTION_BCS_CATGORY);
                startActivity(p);

            }
        });

        bank_preparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(getApplicationContext(), Bank_job_special.class);
                startActivity(nextPage);


            }
        });

        bank_model_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), modelTestCategory.class);
                p.putExtra("TITLE", "ব্যাংক মডেল টেস্ট");
                p.putExtra("CAT_ID", Constants.MODEL_QUSTION_BANK_CATGORY);
                startActivity(p);

            }
        });


        teacher_preparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "6");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        current_qus_sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "7");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);

            }
        });

        all_job_sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(getApplicationContext(), allJobSolution.class);
                startActivity(nextPage);
            }
        });

        viva_expi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "14");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        interview_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "15");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        application_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "22");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        job_qus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(getApplicationContext(), FaqList.class);
                startActivity(nextPage);
            }
        });

        inspratn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "13");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        age_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), AgeCalculator.class);
                startActivity(p);
            }
        });

        prblms_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://docs.google.com/document/d/19Fast0IlEUd2XC5hPpNDP038DQKBYjNkCZ4EpLbFdWQ/edit?usp=sharing";

                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext(), NotificaitonList.class);
                startActivity(p);
            }
        });


    }

    private void downloadWholeList(String searchKey) {
        animation.setVisibility(View.VISIBLE);
        Call<SearchResponse> searchResponseCall = RetrofitClient.getInstance()
                .getApi()
                .getSearch();


        searchResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.code() == 200) {
                    list.addAll(response.body().getCircular());
                    list.addAll(response.body().getPrep());
                    //
                    //  adaper.addItems(list);
                    //recyclerView.setAdapter(adaper);

                    searchData(searchKey);
                    // adaper.getFilter().filter(s);
                } else {
                    Toast.makeText(getApplicationContext(), "Something Went Well" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something Went Well " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpSideBar();
    }

    @Override
    public void onItemClick(JobCircularReponseModel.Job_Circular_Model model) {
        Intent p = new Intent(getApplicationContext(), PostDetailActivity.class);
        p.putExtra("MODEL", model);
        startActivity(p);
    }
}