package com.metacoders.cakri.Activities.Details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.metacoders.cakri.Adapter.CommentListAdapter;
import com.metacoders.cakri.Models.CommentResponse;
import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.MsgModel;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;
import com.metacoders.cakri.Utils.Constants;
import com.metacoders.cakri.Utils.Utilities;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class PostDetailActivity extends AppCompatActivity {

    private static final int PERMISSION_STORAGE_CODE = 1000;
    LinearLayout like, textSize;
    TextView descp;
    AlertDialog alertDialog;
    JobCircularReponseModel.Job_Circular_Model model;
    ImageView image1, image2, image3;
    TextView title, date;
    CommentListAdapter adapter;
    RecyclerView comment_list;

    List<CommentResponse.CommentModel> commentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        like = findViewById(R.id.likesLayout);
        textSize = findViewById(R.id.sizeLayout);
        descp = findViewById(R.id.desc);
        image1 = findViewById(R.id.imag1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        like.setVisibility(View.GONE);
        comment_list = findViewById(R.id.commentList);
        textSize.setVisibility(View.VISIBLE);
        comment_list.setLayoutManager(new LinearLayoutManager(this));


        // getting the model
        Intent i = getIntent();

        model = (JobCircularReponseModel.Job_Circular_Model) i.getSerializableExtra("MODEL");


        if (model != null) {

            Log.d("TAG", "onCreate: " + model.getTitle());
            setUpView(model);
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
        findViewById(R.id.downloadBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start_dowload();
            }
        });
    }

    private void shareText(String shareText) {

        String s = shareText;
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My App");
        shareIntent.putExtra(Intent.EXTRA_TEXT, s);
        startActivity(Intent.createChooser(shareIntent, "Share Via"));
    }

    private void setUpView(JobCircularReponseModel.Job_Circular_Model model) {
        //checking the image
        if (model.getImage().equals("NULL") || model.getImage().isEmpty()) {
            image1.setVisibility(View.GONE);
        } else {
            Picasso.get().load(Constants.IMAGE_URL + model.getImage())
                    .placeholder(R.drawable.placeholder)
                    .into(image1);
        }
        if (model.getImage2().equals("NULL") || model.getImage2().isEmpty()) {
            image2.setVisibility(View.GONE);
        } else {
            image2.setVisibility(View.VISIBLE);
            Picasso.get().load(Constants.IMAGE_URL + model.getImage2())
                    .placeholder(R.drawable.placeholder)
                    .into(image2);
            ;
        }
        if (model.getImage3().equals("NULL") || model.getImage3().isEmpty()) {

            image3.setVisibility(View.GONE);
        } else {
            image3.setVisibility(View.VISIBLE);
            Picasso.get().load(Constants.IMAGE_URL + model.getImage3())
                    .placeholder(R.drawable.placeholder)
                    .into(image3);
        }
        // load comment list
        loadCommentList("3");


        // setting  texts
        title.setText(model.getTitle());
        date.setText("Date : " + Utilities.getDateFormated(model.getDate() + ""));
        descp.setText(model.getDescription() + "");

        sendComment("this is a commnet from the app ");
        loadCommentList("3");
    }

    private void resizeTheFont() {

        CharSequence[] textSize = {"Normal", "Large", "Extra Large"};
        // Toast.makeText(getApplicationContext() , "CLOCKED" , Toast.LENGTH_SHORT).show();


        AlertDialog.Builder builder = new AlertDialog.Builder(PostDetailActivity.this, R.style.DialogTheme);
        builder.setTitle("Select Text Size");
        builder.setSingleChoiceItems(textSize, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        descp.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
                        break;
                    case 1:
                        //  dView.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                        descp.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                        break;
                    case 2:
                        descp.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23);
                        break;
                }
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();

    }

    private void Start_dowload() {

        if (model.getImage().equals("NULL") && model.getImage2().equals("NULL") && model.getImage3().equals("NULL") && model.getPdf().equals("NULL")) {

            Toast.makeText(getApplicationContext(), "There is NO Attachment For Download!!", Toast.LENGTH_LONG)
                    .show();

        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                    // permission denied requet is
                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                } else {
                    starDownloading();
                }
            } else {
                starDownloading();
            }

        }


    }

    private void starDownloading() {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(Constants.IMAGE_URL + model.getImage()));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading File");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());


        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    starDownloading();
                } else {
                    Toast.makeText(this, "Permission denied ....!", Toast.LENGTH_LONG).show();
                }
            }


        }
    }

    public void loadCommentList(String postID) {

        Call<CommentResponse> list = RetrofitClient.getInstance().getApi()
                .getCommentList(postID);
        list.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {

                // model
                if (response.code() == 200) {

                    commentList = response.body().getData();

                    try {
                        if (commentList.size() > 0) {
                            //set the adapter
                            comment_list.setAdapter(new CommentListAdapter(getApplicationContext(), commentList));


                        } else {

                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void sendComment(String comment) {
        // get user data  , user_ id , user _ name

        //create the comment
//        @Field("post_id") String post_id,
//        @Field("post_type") String post_type,
//        @Field("content") String content,
//        @Field("user_name") String user_name,
//        @Field("user_id") String user_id,
//        @Field("date") String date
        Call<MsgModel> CreateCall = RetrofitClient.getInstance().getApi()
                .PostAComment(
                        3,
                        3, //post type
                        comment,
                        "user_name",
                        1,
                        Utilities.getTodayDate() + ""
                );

        CreateCall.enqueue(new Callback<MsgModel>() {
            @Override
            public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {
                if (response.code() == 200) {
                        if(response.body().getError()){
                          // eror happend
                            Toast.makeText(getApplicationContext(), "Something Went Wrong Try Again", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Comment Posted", Toast.LENGTH_SHORT).show();

                        }
                } else {
                    Toast.makeText(getApplicationContext(), "Something Went Wrong Try Again "+ response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: " + response.raw());
                }
            }

            @Override
            public void onFailure(Call<MsgModel> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something Went Wrong Try Again " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}