package com.metacoders.cakri;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

 public  class BankModelQusPrep extends AppCompatActivity {
    TextView page_title_textView;

    CardView adviceBtn;
    LinearLayout bottomContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_job_special);
        page_title_textView = findViewById(R.id.page_title_textView);
        adviceBtn = findViewById(R.id.adviceBtn);
        bottomContainer = findViewById(R.id.bottomContainer);
        page_title_textView.setText("\uD83C\uDF1Fবিষয়ভিত্তিক মডেল টেস্ট\uD83C\uDF1F");
        adviceBtn.setVisibility(View.GONE);
        bottomContainer.setVisibility(View.GONE);

    }
}