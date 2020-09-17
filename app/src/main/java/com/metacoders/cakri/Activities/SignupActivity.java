package com.metacoders.cakri.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.metacoders.cakri.Models.MsgModel;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity implements  Validator.ValidationListener {
    @NotEmpty
    @Email
    private TextInputEditText mailIn;
    Validator validator;
    Button signUP;

    @NotEmpty
    @Password(min = 3, scheme = Password.Scheme.ANY)
    private TextInputEditText passIn;
    @NotEmpty
    private TextInputEditText nameIn;
    @NotEmpty
    @Length(min = 11, max = 11, message = "Please Use Proper Phone Number")
    private TextInputEditText mobileIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        validator = new Validator(this);
        validator.setValidationListener(SignupActivity.this);
        mailIn = findViewById(R.id.mail);
        passIn = findViewById(R.id.pass);
        nameIn = findViewById(R.id.name);
        mobileIn = findViewById(R.id.ph);
        signUP = findViewById(R.id.signUPBtn);

        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validator.validate();
            }
        });

    }

    @Override
    public void onValidationSucceeded() {

//        Toast.makeText(getApplicationContext() , mobileIn.getText(), Toast.LENGTH_SHORT)
//              .show();


        checkPhoneNumber();


    }

    private void checkPhoneNumber() {

        final ProgressDialog dialog = new ProgressDialog(SignupActivity.this);
        dialog.setMessage("Checking Phone Number !!!");
        dialog.show();
        dialog.setCancelable(false);

        Call<MsgModel>call = RetrofitClient.getInstance().getApi()
                .checkPhone( mobileIn.getText().toString()) ;


        call.enqueue(new Callback<MsgModel>() {
            @Override
            public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {


                dialog.dismiss();

                if (response.code() == 200) {
                    MsgModel model = response.body();

                    if (model.getError()) {
                        Toast.makeText(getApplicationContext(), "Error : Phone Number  All Ready Exists  !!!", Toast.LENGTH_LONG)
                                .show();

                    } else {
                        Intent i = new Intent(getApplicationContext(), OTPActivity.class);
                        i.putExtra("NAME", nameIn.getText().toString());
                        i.putExtra("PASS", passIn.getText().toString());
                        i.putExtra("MAIL", mailIn.getText().toString());
                        i.putExtra("NUM", mobileIn.getText().toString());
                        startActivity(i);

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Error : " + response.code(), Toast.LENGTH_LONG)
                            .show();
                }

            }

            @Override
            public void onFailure(Call<MsgModel> call, Throwable t) {

                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error : " + t.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        });


    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}