/**
 * 
 */
package com.lenovo.ittools.ctd.util;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Kylin Zhang 2011-11-11
 * @description 
 * 
 */
public class DataTimeString {
	public static void main(String[] args) {
		String savePath = "Z)0(P{%UR2`%313_7HXKNOQ.png";
		String[] strs=savePath.split("\\.");
		String surfix=strs[strs.length-1];
		String filename=strs[strs.length-2];
		System.out.println(filename);
	}
	
	  public static String mapOsStringexcute(String mapOs){
		  String osString = "";
		   
		  osString = mapOs.replace(", ", "");
		  
		  return osString;
	  }
	public static String getInstKey(){
		String nowTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		
		Random random = new Random();
		String result = String.valueOf(random.nextInt(99)+1);
		return nowTime + result;
	}
	public static String getIssueNO(){
		String nowTime = new SimpleDateFormat("yyMMddHHmmss-SSS").format(new Date());
		
		Random random = new Random();
		String result = String.valueOf(random.nextInt(99)+1);
		return nowTime + result;
	}
	
	public static String getYMDHMS(){
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return nowTime;
	}
	
	public static Timestamp getTCurrentDate()
	{
		return new Timestamp(new Date().getTime()); 
	}
}
