package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.metacoders.cakri.Models.StartUpResponse;
import com.metacoders.cakri.Service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//            }
//        },200) ;

        getHomepageData();

    }

    public  void getHomepageData(){
       Call<StartUpResponse> call =  RetrofitClient.getInstance().getApi()
                .getInitialData() ;

       call.enqueue(new Callback<StartUpResponse>() {
           @Override
           public void onResponse(Call<StartUpResponse> call, Response<StartUpResponse> response) {

               if(response.code()==200){
                   // we have the data
                   StartUpResponse startUpResponse = response.body() ;

    //               Log.d("TAG", "onResponse: " +  startUpResponse.getCircular().get(0).getDescription());
                   Intent p = new Intent(getApplicationContext() , home_page.class) ;
                   p.putExtra("DATA" ,startUpResponse ) ;
                   startActivity(p);
                   finish();

               }
               else {

                   getHomepageData();
               }


           }

           @Override
           public void onFailure(Call<StartUpResponse> call, Throwable t) {

               Toast.makeText(SplashScreen.this, "Something Went Wrong. Trying Again !!", Toast.LENGTH_SHORT).show();
           }
       });



    }
}