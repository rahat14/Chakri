package com.metacoders.cakri.Activities.lists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.metacoders.cakri.Adapter.FaqListAdapter;
import com.metacoders.cakri.Models.FaqModel;
import com.metacoders.cakri.Models.MsgModel;
import com.metacoders.cakri.R;
import com.metacoders.cakri.Service.RetrofitClient;
import com.metacoders.cakri.Utils.Utilities;

import java.time.temporal.ValueRange;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqList extends AppCompatActivity {

    FloatingActionButton floatingActionButton  ;
    RecyclerView recyclerView;
    Utilities utilities = new Utilities();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_list);
        floatingActionButton= findViewById(R.id.adQus)  ;
        recyclerView = findViewById(R.id.list) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        loadFaq();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                triggerAddDialouge() ;
            }
        });


    }

    private void triggerAddDialouge() {
        Dialog dialog = new Dialog(FaqList.this) ;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.faq_dialouge_ayout);
        Button ok ;
        EditText qusEdit ;
        Utilities utilities = new Utilities() ;
        ok = dialog.findViewById(R.id.ok_btn) ;
        qusEdit = dialog.findViewById(R.id.edit_text) ;


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String qus = qusEdit.getText().toString() ;
                if(TextUtils.isEmpty(qus) ){
                    Toast.makeText(getApplicationContext(), "Please Enter Your Question" , Toast.LENGTH_LONG)
                            .show();
                }
                else
                {
                    // upload the
                    int userid =utilities.isUserSignedIn(getApplicationContext()) ;
                    if(userid== 0 ){
                        Toast.makeText(getApplicationContext(), "Please Login !!!!" , Toast.LENGTH_LONG)
                                .show();
                    }
                    else {
                        // uplkoad

                        Call<MsgModel>call = RetrofitClient.getInstance()
                                .getApi()
                                .CreateFAQ(userid , qus , "")  ;

                        call.enqueue(new Callback<MsgModel>() {
                            @Override
                            public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {
                                if(response.code() == 200){
                                    dialog.dismiss();
                                    Toast.makeText( getApplicationContext(), "Qus Added !!!" , Toast.LENGTH_LONG).show(); ;
                                    loadFaq();
                                }
                                else   {
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Error "+ response.code() , Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<MsgModel> call, Throwable t) {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Error "+ t.getMessage() , Toast.LENGTH_LONG)
                                        .show();
                            }
                        });


                    }
                }


            }
        });


        dialog.show();
    }

    private void loadFaq() {

            Call<List<FaqModel>>list = RetrofitClient.getInstance().getApi()
                    .getFaqList(utilities.isUserSignedIn(getApplicationContext())) ;


            list.enqueue(new Callback<List<FaqModel>>() {
                @Override
                public void onResponse(Call<List<FaqModel>> call, Response<List<FaqModel>> response) {


                    if(response.code()== 200){
                        List<FaqModel> list1 = response.body() ;

                        recyclerView.setAdapter(new FaqListAdapter(getApplicationContext() ,list1 ));
                    }
                    else {
                        Toast.makeText(getApplicationContext() , "Something Went Wrong" + response.code() , Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<FaqModel>> call, Throwable t) {

                    Toast.makeText(getApplicationContext() , "Something Went Wrong" , Toast.LENGTH_LONG).show();
                }
            });


    }
}