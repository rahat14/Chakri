package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.JobPrepModel;
import com.metacoders.cakri.Utils.Constants;
import com.metacoders.cakri.Utils.Utilities;
import com.squareup.picasso.Picasso;

public class PostDetailActivity extends AppCompatActivity {

    LinearLayout like  , textSize ;
    TextView descp ;
    AlertDialog alertDialog;
    JobCircularReponseModel.Job_Circular_Model model ;
    ImageView image1 , image2 , image3 ;
    TextView title , date  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        like = findViewById(R.id.likesLayout) ;
        textSize= findViewById(R.id.sizeLayout);
        descp = findViewById(R.id.desc);
        image1 = findViewById(R.id.imag1);
        image2 = findViewById(R.id.image2) ;
        image3 =findViewById(R.id.image3) ;
        title = findViewById(R.id.title) ;
        date = findViewById(R.id.date) ;
        like.setVisibility(View.GONE);
        textSize.setVisibility(View.VISIBLE);

        // getting the model
        Intent i = getIntent();

        model = (JobCircularReponseModel.Job_Circular_Model) i.getSerializableExtra("MODEL");



        if(model != null){

            Log.d("TAG", "onCreate: " + model.getTitle());
            setUpView(model) ;
        }




        // click listener
        textSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               resizeTheFont();
            }
        });

        // sharebtn
        findViewById(R.id.shareBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareText(model.getShareText());

            }
        });
    }

    private void shareText(String shareText) {

        String s = shareText ;
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain") ;
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"My App");
        shareIntent.putExtra(Intent.EXTRA_TEXT,s);
        startActivity(Intent.createChooser(shareIntent ,"Share Via"));
    }

    private void setUpView(JobCircularReponseModel.Job_Circular_Model model) {
        //checking the image
        if(model.getImage().equals("NULL") || model.getImage().isEmpty()){
            image1.setVisibility(View.GONE);
        }
        else {
            Picasso.get().load(Constants.IMAGE_URL + model.getImage())
                    .placeholder(R.drawable.placeholder)
                    .into(image1);
        }
        if(model.getImage2().equals("NULL") || model.getImage2().isEmpty() ){
            image2.setVisibility(View.GONE);
        }
        else {
            image2.setVisibility(View.VISIBLE);
            Picasso.get().load(Constants.IMAGE_URL + model.getImage2())
                    .placeholder(R.drawable.placeholder)
                    .into(image2); ;
        }
        if(model.getImage3().equals("NULL") || model.getImage3().isEmpty()){

            image3.setVisibility(View.GONE);
        }
        else {
            image3.setVisibility(View.VISIBLE);
            Picasso.get().load(Constants.IMAGE_URL + model.getImage3())
                    .placeholder(R.drawable.placeholder)
                    .into(image3);
        }

        // setting  texts
        title.setText(model.getTitle());
        date.setText( "Date : " + Utilities.getDateFormated(model.getDate()+""));
        descp.setText(model.getDescription()+"");

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