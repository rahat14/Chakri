package com.metacoders.cakri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        int smonth = startMonth;
        int syear = startYear;

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