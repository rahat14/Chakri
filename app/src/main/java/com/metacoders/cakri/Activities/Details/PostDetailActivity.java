package com.metacoders.cakri.Activities.Details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.metacoders.cakri.Activities.login_activity;
import com.metacoders.cakri.Adapter.CommentListAdapter;
import com.metacoders.cakri.AgeCalculator;
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
    PhotoView image1, image2, image3;
    TextView title, date;
    CommentListAdapter adapter;
    RecyclerView comment_list;
    EditText commentEditText;
    Button send;
    Button imaged1, imaged2, imaged3, pdfd ,  loginBtn  ;
            ;

    List<CommentResponse.CommentModel> commentList = new ArrayList<>();
    String UNIVERSAL_LINK = "";
    LinearLayout commentBox, LoginBox;
    Utilities utilities = new Utilities();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);



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
        send = findViewById(R.id.btnSend);
        commentEditText = findViewById(R.id.editText);
        commentBox = findViewById(R.id.commentContainer);
        LoginBox = findViewById(R.id.LoginContainer);
        loginBtn = findViewById(R.id.loginBtn) ;
        comment_list.setLayoutManager(new LinearLayoutManager(this));


        // getting the model
        Intent i = getIntent();

        model = (JobCircularReponseModel.Job_Circular_Model) i.getSerializableExtra("MODEL");


        if (model != null) {

            if(model.getPost_type()!= 555){
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                        WindowManager.LayoutParams.FLAG_SECURE);
            }
            Log.d("TAG", "onCreate: " + model.getTitle());
            setUpView(model);

        }

        send.setOnClickListener(v -> {
            String comment = commentEditText.getText().toString();

            if (TextUtils.isEmpty(comment)) {
                Toast.makeText(getApplicationContext(), "Please Fill The Comment!!!", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Utilities ut = new Utilities();
                if (ut.isUserSignedIn(getApplicationContext()) == 0) {
                    Toast.makeText(getApplicationContext(), "Please Log IN!!!", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    sendComment(comment, ut.isUserSignedIn(getApplicationContext()));
                }


//                Toast.makeText(getApplicationContext(), "P"+ Utilities.getTodayDate(), Toast.LENGTH_SHORT)
//                        .show();
            }

        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p  = new Intent(getApplicationContext() , login_activity.class) ;
                startActivity(p);
            }
        });

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
                Dialog dialog = new Dialog(PostDetailActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dwld_dialouge_ayout);

                imaged1 = dialog.findViewById(R.id.image1_Button);
                imaged2 = dialog.findViewById(R.id.image2_Button);
                imaged3 = dialog.findViewById(R.id.image3_Button);
                pdfd = dialog.findViewById(R.id.pdf_Button);

                if (model.getImage().equals("NULL")) {


                    imaged1.setVisibility(View.GONE);
                }
                if (model.getImage2().equals("NULL")) {
                    imaged2.setVisibility(View.GONE);
                }
                if (model.getImage3().equals("NULL")) {
                    imaged3.setVisibility(View.GONE);
                }
                if (model.getPdf().equals("NULL")) {
                    pdfd.setVisibility(View.GONE);
                }

                imaged1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UNIVERSAL_LINK = Constants.IMAGE_URL + model.getImage();
                        dialog.dismiss();
                        Start_dowload(Constants.IMAGE_URL + model.getImage());
                    }
                });
                imaged2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UNIVERSAL_LINK = Constants.IMAGE_URL + model.getImage2();
                        dialog.dismiss();
                        Start_dowload(Constants.IMAGE_URL + model.getImage2());
                    }
                });
                imaged3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UNIVERSAL_LINK = Constants.IMAGE_URL + model.getImage3();
                        dialog.dismiss();
                        Start_dowload(Constants.IMAGE_URL + model.getImage3());
                    }
                });
                pdfd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UNIVERSAL_LINK = Constants.PDF_URL + model.getPdf();
                        dialog.dismiss();
                        Start_dowload(Constants.PDF_URL + model.getPdf());
                    }
                });


                dialog.show();


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


        loadCommentList(model.getId() + "");


        // setting  texts
        title.setText(model.getTitle());
        date.setText("Date : " + Utilities.getDateFormated(model.getDate() + ""));
        descp.setText(model.getDescription() + "");

        // sendComment("this is a commnet from the app ");
        //loadCommentList("3");
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

    private void Start_dowload(String link) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                // permission denied requet is
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_STORAGE_CODE);
            } else {
                starDownloading(link);
            }
        } else {
            starDownloading(link);
        }


    }

    private void starDownloading(String link  ) {
// 0 means pdf

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(link));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading File");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());


        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }

    public void commnetBoxFunc() {


        int id = utilities.isUserSignedIn(getApplicationContext());
        if (id == 0) {
            // user not logged in
            commentBox.setVisibility(View.GONE);
            LoginBox.setVisibility(View.VISIBLE);

        } else {

            commentBox.setVisibility(View.VISIBLE);
            LoginBox.setVisibility(View.GONE);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    starDownloading(UNIVERSAL_LINK);
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

    public void sendComment(String comment, int id) {
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
                        model.getId(),
                        3, //post type
                        comment, utilities.getSavedName(getApplicationContext())
                        ,
                        id,
                        Utilities.getTodayDate() + ""
                );

        CreateCall.enqueue(new Callback<MsgModel>() {
            @Override
            public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {
                if (response.code() == 200) {
                    if (response.body().getError()) {
                        // eror happend
                        Toast.makeText(getApplicationContext(), "Something Went Wrong Try Again", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(getApplicationContext(), "Comment Posted", Toast.LENGTH_SHORT).show();

                        commentEditText.setText("");
                        loadCommentList(model.getId() + "");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Something Went Wrong Try Again " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: " + response.raw());
                }
            }

            @Override
            public void onFailure(Call<MsgModel> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something Went Wrong Try Again " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        commnetBoxFunc();
    }
}