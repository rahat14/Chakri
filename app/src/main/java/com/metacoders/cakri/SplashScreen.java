package com.metacoders.cakri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.metacoders.cakri.Models.StartUpResponse;
import com.metacoders.cakri.Service.RetrofitClient;
import com.metacoders.cakri.Utils.Utilities;
import com.onesignal.OneSignal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {
    Utilities utilities;
    CardView cardView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        cardView =findViewById(R.id.noInternet) ;
        cardView.setVisibility(View.GONE);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//            }
//        },200) ;

        //throw new RuntimeException("Carsh") ;

        // check internet available
        utilities = new Utilities();



    }

    private void trigger(){
        if (utilities.isNetworkAvailable(getApplicationContext())) {
            cardView.setVisibility(View.GONE);

            getHomepageData();

        } else {
            cardView.setVisibility(View.GONE);
            // Trigger
            TriggerDialouge();


        }

    }//ic_stat_onesignal_default

    private void TriggerDialouge() {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this);
        builder.setTitle("Error");
        builder.setMessage("Please Connect To The Internet.");
        builder.setCancelable(false);
        // add a button
        builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                trigger();

            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public void getHomepageData() {
        Call<StartUpResponse> call = RetrofitClient.getInstance().getApi()
                .getInitialData();

        call.enqueue(new Callback<StartUpResponse>() {
            @Override
            public void onResponse(Call<StartUpResponse> call, Response<StartUpResponse> response) {

                if (response.code() == 200) {
                    // we have the data
                    StartUpResponse startUpResponse = response.body();

                    //               Log.d("TAG", "onResponse: " +  startUpResponse.getCircular().get(0).getDescription());
                    Intent p = new Intent(getApplicationContext(), home_page.class);
                    p.putExtra("DATA", startUpResponse);
                    startActivity(p);
                    finish();

                } else {

                    getHomepageData();
                }


            }

            @Override
            public void onFailure(Call<StartUpResponse> call, Throwable t) {

                Log.d("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(SplashScreen.this, "Something Went Wrong. Trying Again !!" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        trigger();
    }
}