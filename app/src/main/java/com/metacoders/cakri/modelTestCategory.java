package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.metacoders.cakri.Activities.lists.AllModelQusList;
import com.metacoders.cakri.Utils.Constants;

public class modelTestCategory extends AppCompatActivity {

    TextView page_title_textView;
    CardView whole, bySubject;
    String title, cat_id;

    /* cat_id
       1 = BCS
       2 =BANK

    */
    Intent model_qus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_test_category);
        page_title_textView = findViewById(R.id.page_title_textView);
        whole = findViewById(R.id.wholeBtn);
        bySubject = findViewById(R.id.subjectBtn);
        page_title_textView.setText("");
        model_qus = new Intent(getApplicationContext(), AllModelQusList.class);


        Intent p = getIntent();
        title = p.getStringExtra("TITLE");
        cat_id = p.getStringExtra("CAT_ID");

        page_title_textView.setText(title);


        bySubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cat_id.equals(Constants.MODEL_QUSTION_BCS_CATGORY)) {
                    Intent p = new Intent(getApplicationContext(), BcsModelQusPrep.class);
                    p.putExtra("CAT_ID", "1");
                    startActivity(p);
                } else {
                    Intent p = new Intent(getApplicationContext(), BankModelQusPrep.class);
                    p.putExtra("CAT_ID", "2");
                    startActivity(p);
                }
            }
        });

        whole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cat_id.equals(Constants.MODEL_QUSTION_BCS_CATGORY)) {

                    model_qus.putExtra("qus_type" , "whole");
                    model_qus.putExtra("cat_id" , Constants.MODEL_QUSTION_BCS_CATGORY) ;
                    model_qus.putExtra("sub_cat_id" , "0") ;
                    model_qus.putExtra("qus_name" , 1) ;
                    startActivity(model_qus);
                } else {

                    model_qus.putExtra("qus_type" , "whole");
                    model_qus.putExtra("cat_id" , Constants.MODEL_QUSTION_BANK_CATGORY ) ;
                    model_qus.putExtra("sub_cat_id" , "0") ;
                    model_qus.putExtra("qus_name" , 0) ;
                    startActivity(model_qus);
                }

            }
        });


    }
}