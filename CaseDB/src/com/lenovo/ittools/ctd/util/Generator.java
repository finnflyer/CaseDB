package com.lenovo.ittools.ctd.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Generator {
        public static String generatorID(){
        	Date d = new Date();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    		String list = sdf.format(d);
    		Calendar c = Calendar.getInstance();
    		int  millSecond = c.get(Calendar.MILLISECOND);
    		list +="0"+ String.valueOf(millSecond)+generateWord();
    		return list;
        }
        public static  String generateWord() {  
            String[] beforeShuffle = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",  
                    "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
                    "W", "X", "Y", "Z" };  
            List list = Arrays.asList(beforeShuffle);  
            Collections.shuffle(list);  
            StringBuilder sb = new StringBuilder();  
            for (int i = 0; i < list.size(); i++) {  
                sb.append(list.get(i));  
            }  
            String afterShuffle = sb.toString();  
            String result = afterShuffle.substring(5, 7);  
            return result;  
        }  


}
