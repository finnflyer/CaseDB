package com.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by finnf on 2018/8/23.
 */
public class DateTimeChange {
    public static String getDate(long time){
        String Date = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.format(new Date(time));
        return Date;
    }
    public static void main(String args[]){


    }
}
