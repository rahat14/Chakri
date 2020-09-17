package com.metacoders.cakri.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.metacoders.cakri.R;
import com.metacoders.cakri.Utils.Utilities;

public class Profile_Activity extends AppCompatActivity {
    Button payButton ;
    Utilities utilities  = new Utilities()  ;
    TextView amountTv , nameTv  , phTv  , mailTv ,expireTv , pacakgeTv  , nametv ;
    String TODAY ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);


        nameTv = findViewById(R.id.nameTv) ;
        phTv = findViewById(R.id.phone_tv) ;
        nametv = findViewById(R.id.name_bal_tv) ;
        mailTv = findViewById(R.id.email_tv ) ;

        nameTv.setText(utilities.getSavedName(getApplicationContext()));
        nametv.setText(utilities.getSavedName(getApplicationContext()));
        phTv.setText(utilities.getSavedContacts(getApplicationContext()));
        mailTv.setText(utilities.getSavedMail(getApplicationContext()));

    }
}