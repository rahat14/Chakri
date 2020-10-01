package com.metacoders.cakri.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utilities {

    public static SharedPreferences getPrefs(Context context){
        return  context.getSharedPreferences("USER", Context.MODE_PRIVATE);
    }

    public static void  showMsg(Context context , String msg ) {

        Toast.makeText(context , msg , Toast.LENGTH_LONG ).show();

    }
    public static String getDateFormated(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-d");
        Date newDate = new Date();
        try {
            newDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat("d-mm-yyyy");
        String FormatedDate = format.format(newDate);
        return FormatedDate;
    }

    public static String getTodayDate() {
        Date d = new Date();
        CharSequence s = android.text.format.DateFormat.format("yyyy-MM-dd", d.getTime());
        //Toast.makeText(this,s.toString(),Toast.LENGTH_SHORT).show();

        String date = s.toString();


        return date;
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public  void deleteExistingUSER(Context context){

        SharedPreferences.Editor editor=getPrefs(context).edit();
        editor.putString("number","null");
        editor.putString("pass","null");
        editor.putString("name", "null");
        editor.putString("address","");
        editor.putInt("user_id", 0);
        editor.apply();

       // Log.d("TAG", "ReadExistingResname:  USER DELTED !!"  );
    }

    public  void createUser(Context context, String number, String pass, int user_id, String name, String adress  , String mail  ){

        SharedPreferences.Editor editor=getPrefs(context).edit();
        editor.putString("number",number);
        editor.putString("name",name);
        editor.putString("pass",pass);
        editor.putString("address",adress);
        editor.putString("mail",mail);
        editor.putInt("user_id", user_id);

        editor.apply();
//        Log.d("TAG", "ReadExistingResname: CREATED  USER DATA " );
//        Log.d("TAG", "ReadExistingResname: CREATED  USER adress "  + adress);
    }

    public  int  isUserSignedIn(Context context) {
        int def = 0 ;
        String number = getPrefs(context).getString("number","null");
        String pass = getPrefs(context).getString("pass", "null")  ;
        int UserID  = getPrefs(context).getInt("user_id" , 0) ;

        if(!number.equals("null") && !pass.equals("null") && UserID!= 0) {
          //  Log.d("TAG", "ReadExistingResname:  USER Found "+ UserID );
            return UserID ;

        }
        else {
            //Log.d("TAG", "ReadExistingResname:  USER NOT FOUND  " );
            return  def  ;

        }
    }

    public  String getSavedName(Context context){

        String Adress = getPrefs(context).getString("name","null");


        if(!Adress.equals("null") ){
         //   Log.d("TAG", "ReadExistingResname:  Address Found "+ Adress );
            return Adress ;

        }
        else {
          //  Log.d("TAG", "ReadExistingResname:  Address NOT FOUND  " );
            return  "null"  ;

        }
    }

    public  String getSavedContacts(Context context){

        String number = getPrefs(context).getString("number","null");


        if(!number.equals("null") ){
         //   Log.d("TAG", "ReadExistingResname:  Address Found "+ number );
            return number ;

        }
        else {
            //Log.d("TAG", "ReadExistingResname:  Address NOT FOUND  " );
            return  "null"  ;

        }
    }
    public  String getSavedMail(Context context){

        String mail = getPrefs(context).getString("mail","null");


        if(!mail.equals("null") ){
            //Log.d("TAG", "ReadExistingResname:  Address Found "+ number );
            return mail ;

        }
        else {
           // Log.d("TAG", "ReadExistingResname:  Address NOT FOUND  " );
            return  "null"  ;

        }
    }

    public  int  getuserID(Context context){

        int  number = getPrefs(context).getInt("user_id",0);


        if(number !=0 ){
          //  Log.d("TAG", "ReadExistingResname:  Address Found "+ number );
            return number ;

        }
        else {
          //  Log.d("TAG", "ReadExistingResname:  Address NOT FOUND  " );
            return  0  ;

        }
    }

    public  String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        return  formattedDate ;

    }
}
