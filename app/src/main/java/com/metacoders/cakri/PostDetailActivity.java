package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PostDetailActivity extends AppCompatActivity {

    LinearLayout like  , textSize ;
    TextView descp ;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        like = findViewById(R.id.likesLayout) ;
        textSize= findViewById(R.id.sizeLayout);
        descp = findViewById(R.id.desc);
        like.setVisibility(View.GONE);
        textSize.setVisibility(View.VISIBLE);

        // click listener
        textSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               resizeTheFont();
            }
        });
    }

    private void resizeTheFont() {

        CharSequence[] textSize = {"Normal","Large","Extra Large"};
        // Toast.makeText(getApplicationContext() , "CLOCKED" , Toast.LENGTH_SHORT).show();


        AlertDialog.Builder builder = new AlertDialog.Builder(PostDetailActivity.this,R.style.DialogTheme);
        builder.setTitle("Select Text Size");
        builder.setSingleChoiceItems(textSize, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item){
                    case 0:
                        descp.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
                        break;
                    case 1:
                        //  dView.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                        descp.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                        break;
                    case 2:
                        descp.setTextSize(TypedValue.COMPLEX_UNIT_SP,23);
                        break;
                }
                alertDialog.dismiss();
            }
        });
        alertDialog=builder.create();
        alertDialog.show();

    }
}