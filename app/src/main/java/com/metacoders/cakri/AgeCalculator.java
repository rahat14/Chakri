package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.metacoders.cakri.Activities.Contact_us;
import com.metacoders.cakri.Activities.Profile_Activity;
import com.metacoders.cakri.Activities.Search;
import com.metacoders.cakri.Activities.lists.All_Job_Prep;
import com.metacoders.cakri.Activities.lists.Book_Mark_List;
import com.metacoders.cakri.Activities.lists.FaqList;
import com.metacoders.cakri.Activities.lists.NotificaitonList;
import com.metacoders.cakri.Activities.login_activity;
import com.metacoders.cakri.Utils.Constants;
import com.metacoders.cakri.Utils.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;

public class AgeCalculator extends AppCompatActivity {

    LinearLayout InputLayout, resultLayout;
    TextView startTv, endTv, result;
    Dialog dialog;
    int startDay = 0, startMonth = 0, startYear = 0;
    int endDay = 0, endMonth = 0, endYear = 0;
    Boolean isResultDone = false;
    Button calculate ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculator);

        startTv = findViewById(R.id.startDateTv);
        endTv = findViewById(R.id.endDateTv);
        result = findViewById(R.id.result);
        InputLayout = findViewById(R.id.inputLayout);
        resultLayout = findViewById(R.id.resultLayout);
        calculate =findViewById(R.id.calculateBtn) ;
        resultLayout.setVisibility(View.GONE);
        InputLayout.setVisibility(View.VISIBLE);
         Calendar calendar;
         SimpleDateFormat dateFormat;
        calendar = Calendar.getInstance();

         dateFormat = new SimpleDateFormat("dd,MMMM,yyyy");
        String  date = dateFormat.format(calendar.getTime());

        startTv.setText(date);
        endTv.setText(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) ; // Note: zero based!
        int day = calendar.get(Calendar.DAY_OF_MONTH);



        // Setting intial date

         startDay = day;
         startMonth = month;
         startYear = year;
         endDay = day ;
         endMonth = month;
         endYear = year ;



        // click Listener
        findViewById(R.id.startdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerCalenderDialogue("start");

            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             //   Toast.makeText(getApplicationContext(), getAGE(startDay, startMonth, startYear, endDay, endMonth, endYear) + "", Toast.LENGTH_LONG).show();
                if (isResultDone) {
                    // retore  layout
                    isResultDone = false ;
                    resultLayout.setVisibility(View.GONE);
                    InputLayout.setVisibility(View.VISIBLE);
                    calculate.setText("Calculate");
                } else {

                    isResultDone = true;
                    resultLayout.setVisibility(View.VISIBLE);
                    InputLayout.setVisibility(View.GONE);
//                    Toast.makeText(getApplicationContext()  , startMonth + " " , Toast.LENGTH_LONG).show();
                    result.setText(getAGE(startDay, startMonth, startYear, endDay, endMonth, endYear) + "");
                    calculate.setText("Reset");

                }

            }
        });

        findViewById(R.id.endDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerCalenderDialogue("end");
            }
        });


    }

    public void setUpSideBar() {
        NavigationView navigationView ;
        DrawerLayout drawerLayout;
        TextView name , phone ;

        LinearLayout bcs_model_test, bank_model_test , daily_news,bcs_preparation,bank_preparation,teacher_preparation,current_qus_sol,all_job_sol
                ,viva_expi,interview_tip,application_cv,job_qus,inspratn,age_cal,prblms_update,notifi
                , love , share , bookmark , contact ;

        ImageView hamburger_Btn;

        hamburger_Btn = findViewById(R.id.hamburgerBtn);
        drawerLayout = findViewById(R.id.drawer_layout);
        contact =findViewById(R.id.contact) ;
        daily_news = drawerLayout.findViewById(R.id.daily_news);
        bcs_preparation = drawerLayout.findViewById(R.id.bcs_preparation);
        bcs_model_test = drawerLayout.findViewById(R.id.bcs_model_test);
        bank_preparation = drawerLayout.findViewById(R.id.bank_preparation);
        bank_model_test = drawerLayout.findViewById(R.id.bank_model_test);
        teacher_preparation = drawerLayout.findViewById(R.id.teacher_preparation);
        current_qus_sol = drawerLayout.findViewById(R.id.current_qus_sol);
        all_job_sol = drawerLayout.findViewById(R.id.all_job_sol);
        viva_expi = drawerLayout.findViewById(R.id.viva_expi);
        interview_tip = drawerLayout.findViewById(R.id.interview_tip);
        application_cv = drawerLayout.findViewById(R.id.application_cv);
        job_qus = drawerLayout.findViewById(R.id.job_qus);
        inspratn = drawerLayout.findViewById(R.id.inspratn);
        age_cal = drawerLayout.findViewById(R.id.age_cal);
        prblms_update = drawerLayout.findViewById(R.id.prblms_update);
        notifi = drawerLayout.findViewById(R.id.notifi);
        navigationView=findViewById(R.id.nav_view);
        love = findViewById(R.id.heart) ;
        bookmark = findViewById(R.id.bookmark) ;
        share = findViewById(R.id.share) ;




        name = drawerLayout.findViewById(R.id.name_on_header) ;
        phone = drawerLayout.findViewById(R.id.ph_on_header);

        SearchView searchView = findViewById(R.id.search_ed) ;


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent p = new Intent(getApplicationContext() , Search.class);
                p.putExtra("search" , query) ;
                startActivity(p);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        Utilities utilities = new Utilities() ;

        int id  =0 ;

        id =  utilities.isUserSignedIn(getApplicationContext()) ;
        if(id == 0 ){
            name.setText("Login");

            phone.setText("");
        }
        else {
            name.setText(utilities.getSavedName(getApplicationContext()));
            phone.setText(utilities.getSavedContacts(getApplicationContext()));
        }

        // app bar section
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext() , Book_Mark_List.class);
                startActivity(p);
            }
        });

        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));

                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));

                }
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s ="https://play.google.com/store/apps/details?id=" + getPackageName();
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share This App");
                shareIntent.putExtra(Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
            }
        });




        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id =  utilities.isUserSignedIn(getApplicationContext()) ;
                if(id==0){
                    Intent p = new Intent(getApplicationContext(), login_activity.class) ;
                    startActivity(p);

                }
                else {

                    Intent p = new Intent(getApplicationContext(), Profile_Activity.class) ;
                    startActivity(p);

                }

            }
        });



        hamburger_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });



        //set click listener

        daily_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "4");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  p = new Intent(getApplicationContext() , Contact_us.class);
                startActivity(p);
            }
        });
        bcs_preparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  nextPage = new Intent(getApplicationContext(), bcsSpecialPage.class);
                startActivity(nextPage);
            }
        });

        bcs_model_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), modelTestCategory.class);
                p.putExtra("TITLE", "বিসিএস মডেল টেস্ট");
                p.putExtra("CAT_ID", Constants.MODEL_QUSTION_BCS_CATGORY);
                startActivity(p);

            }
        });

        bank_preparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(getApplicationContext(), Bank_job_special.class);
                startActivity(nextPage);


            }
        });

        bank_model_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), modelTestCategory.class);
                p.putExtra("TITLE", "ব্যাংক মডেল টেস্ট");
                p.putExtra("CAT_ID", Constants.MODEL_QUSTION_BANK_CATGORY);
                startActivity(p);

            }
        });


        teacher_preparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "6");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        current_qus_sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "7");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);

            }
        });

        all_job_sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(getApplicationContext(), allJobSolution.class);
                startActivity(nextPage);
            }
        });

        viva_expi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "0");
                p.putExtra("sub_cat_id", "14");
                startActivity(p);
            }
        });

        interview_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "15");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        application_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "22");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        job_qus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(getApplicationContext(), FaqList.class);
                startActivity(nextPage);
            }
        });

        inspratn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), All_Job_Prep.class);
                p.putExtra("cat_id", "13");
                p.putExtra("sub_cat_id", "0");
                startActivity(p);
            }
        });

        age_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), AgeCalculator.class);
                startActivity(p);
            }
        });

        prblms_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://docs.google.com/document/d/19Fast0IlEUd2XC5hPpNDP038DQKBYjNkCZ4EpLbFdWQ/edit?usp=sharing" ;

                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent p = new Intent(getApplicationContext() , NotificaitonList.class) ;
                startActivity(p);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpSideBar();
    }

    private void triggerCalenderDialogue(String start) {

        // trigger calender dialogue
        dialog = new Dialog(AgeCalculator.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.calender_dialogue);

        DatePicker datePicker = dialog.findViewById(R.id.date_picker);
        Button ok = dialog.findViewById(R.id.ok_Button);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getApplicationContext(), "Date : " + datePicker.getDayOfMonth() + " Month : "
//                        + getMonthName(datePicker.getMonth()) + " Year : " + datePicker.getYear(), Toast.LENGTH_LONG).show();

                if (start.equals("start")) {
                    startTv.setText("" + datePicker.getDayOfMonth() + "," + getMonthName(datePicker.getMonth()) + "," + datePicker.getYear());
                    startDay = datePicker.getDayOfMonth();
                    startMonth = datePicker.getMonth();
                    startYear = datePicker.getYear();
                } else {
                    endTv.setText("" + datePicker.getDayOfMonth() + "," + getMonthName(datePicker.getMonth()) + "," + datePicker.getYear());
                    endDay = datePicker.getDayOfMonth();
                    endMonth = datePicker.getMonth();
                    endYear = datePicker.getYear();
                }

                // now will calculate the date

                dialog.dismiss();

            }
        });

        dialog.show();

    }

    public String getMonthName(int monthNumber) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        cal.set(Calendar.MONTH, monthNumber);
        String month_name = month_date.format(cal.getTime());
        Log.e("", "" + month_name);

        return month_name;

    }


    public String getAGE(int startDay, int startmonth, int startyear, int endDay, int endMonth, int endYear) {
        int resyear, resmonth, resday;
        int sday = startDay;
        int smonth = startmonth;
        int syear = startyear;

       // Log.d("TAG", "getAGE: oldmon" + startmonth + "new " + endMonth);
        int eday = endDay;
        int emonth = endMonth;
        int eyear = endYear;

        //calculating year
        resyear = eyear - syear;

        //calculating month
        if (emonth >= smonth) {
            resmonth = emonth - smonth;
        } else {
            resmonth = emonth - smonth;
            resmonth = 12 + resmonth;
            resyear--;
        }

        //calculating date
        if (eday >= sday) {
            resday = eday - sday;
        } else {
            resday = eday - sday;
            resday = 31 + resday;
            if (resmonth == 0) {
                resmonth = 11;
                resyear--;
            } else {
                resmonth--;
            }
        }

        //displaying error if calculated age is negative
        if (resday < 0 || resmonth < 0 || resyear < 0) {
            Toast.makeText(getApplicationContext(), "Current Date must be greater than Date of Birth", Toast.LENGTH_LONG).show();
            // t1.setText("Current Date must be greater than Date of Birth");
        } else {
            //  t1.setText("Age: " + resyear + " years /" + resmonth + " months/" + resday + " days");
        }

        return "" + resyear + " Years " + resmonth + " Months " + resday + " Days";
    }

}