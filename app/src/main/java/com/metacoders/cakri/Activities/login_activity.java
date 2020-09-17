package com.metacoders.cakri.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.metacoders.cakri.Models.Response_login;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;
import com.metacoders.cakri.Utils.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_activity extends AppCompatActivity {
    TextInputEditText mailIn , passIn   ;
    Button loginBtn  ;
    Utilities utilities  ;
    TextView registerBtn ;
    ProgressBar pbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        mailIn = findViewById(R.id.email) ;
        passIn = findViewById(R.id.pass) ;
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn) ;
        pbar = findViewById(R.id.progress_bar);

        pbar.setVisibility(View.GONE);
        utilities = new Utilities() ;


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() , SignupActivity.class) ;
                startActivity(i);
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail  = mailIn.getText().toString() ;
                String pass = passIn.getText().toString() ;






                if(mail.isEmpty() || pass.isEmpty())
                {
                  utilities.showMsg(getApplicationContext(), "Please Fill The Data" );
                }
                else
                {
                  sendLogInData(mail , pass)  ;

                }

            }
        });

    }


    private void sendLogInData(String mail, String pass) {
        pbar.setVisibility(View.VISIBLE);

        //creating the sending response



        Call<Response_login> response = RetrofitClient.getInstance()
                .getApi().login(mail, pass) ;


        response.enqueue(new Callback<Response_login>() {
            @Override
            public void onResponse(Call<Response_login> call, Response<Response_login> response) {

                if (response.isSuccessful())
                {
                    if(response.code() == 200 )
                    { Log.d("TAG", "sendLogInData: " + response.raw());

                        Response_login responsee =  response.body() ;


                        if(!responsee.getError())
                        {
                            // user is authnticated
//
//                       Toasty.success(getApplicationContext() , "User ID : " + responsee.getCustomer_id() +
//                              " Name :   " +  responsee.getCustomer_address() , Toasty.LENGTH_LONG  , false).show();


                            SaveTheUser(responsee.getDetails().get(0).getId()+"" ,responsee.getDetails().get(0).getFullName() ,pass ,mail , responsee.getDetails().get(0).getAdress(), responsee.getDetails().get(0).getEmail()) ;


                        }
                        else
                        {
                            pbar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext() , "Wrong Email or Password", Toast.LENGTH_LONG  )
                                    .show();
                        }


//                        Toasty.success(getApplicationContext() , "User ID : " + responsee.getCustomer_id() +
//                                "MSG  " +  responsee.getMsg() , Toasty.LENGTH_LONG  , false)
//                                .show();


                    }
                    else
                    {
                        pbar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext() , "Server Is Busy Please Try Again !!" , Toast.LENGTH_LONG  )
                                .show();
                    }

                }
                else
                {

                    pbar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext() , "Server Is Busy Please Try Again" , Toast.LENGTH_LONG )
                            .show();
                }


            }

            @Override
            public void onFailure(Call<Response_login> call, Throwable t) {
                pbar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Server Is Busy Please Try Again", Toast.LENGTH_LONG)
                        .show();
            }
        });



    }

    private void SaveTheUser(  String customer_id , String customer_name, String pass , String number ,String adress , String mail) {

        // here saving the user in shared prefs

        // Toasty.success(getApplicationContext() , "LOGGED IN", Toasty.LENGTH_LONG  , false).show();

        // Context context, String number, String pass, int user_id, String name, String adress  , String mail
        utilities.createUser(getApplicationContext() , number,pass,Integer.parseInt(customer_id) , customer_name , adress , mail);

        Log.d("TAG", "SaveTheUser: "  + adress);
        pbar.setVisibility(View.GONE);
        finish();



    }
}