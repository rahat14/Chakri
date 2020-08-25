package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class modelTestCategory extends AppCompatActivity {

    TextView page_title_textView ;
    CardView whole , bySubject ;
    String title , cat_id ;

     /* cat_id
        1 = BCS
        2 =BANK

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_test_category);
        page_title_textView = findViewById(R.id.page_title_textView);
        whole = findViewById(R.id.wholeBtn);
        bySubject = findViewById(R.id.subjectBtn);
        page_title_textView.setText("");

        Intent p = getIntent() ;
        title = p.getStringExtra("TITLE") ;
        cat_id= p.getStringExtra("CAT_ID") ;

        page_title_textView.setText(title);


        bySubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cat_id.equals("1")){
                    Intent p = new Intent(getApplicationContext() , BcsModelQusPrep.class) ;
                    p.putExtra("CAT_ID" , "1") ;
                    startActivity(p);
                }
                else {
                    Intent p = new Intent(getApplicationContext() , BankModelQusPrep.class) ;
                    p.putExtra("CAT_ID" , "2") ;
                    startActivity(p);
                }
            }
        });





    }
}