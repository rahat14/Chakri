package com.metacoders.cakri.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class Utilities {

      public static   String getDateFormated(String date){
          SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-d");
          Date newDate = new Date();
          try {
               newDate = format.parse(date);
          } catch (ParseException e) {
              e.printStackTrace();
          }
          format = new SimpleDateFormat("d-mm-yyyy");
          String FormatedDate = format.format(newDate);
          return FormatedDate ;
      }
}
