/**
 * 
 */
package com.lenovo.ittools.ctd.common;

/**
 * @author Kylin Zhang 2011-12-19
 * @description 
 * 
 */
public class StringHandler {
	public static String getJsonString(String str){
		if("".equals(str) || null == str){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<str.length(); i++){
			char c = str.toCharArray()[i];
			switch(c){
			case '\"': sb.append("\\\""); break;
            case '\\': sb.append("\\\\"); break;
            case '/': sb.append("\\/"); break;
            case '\b': sb.append("\\b"); break;
            case '\f': sb.append("\\f"); break;
            case '\n': sb.append("\\n"); break;
            case '\r': sb.append("\\r"); break;
            case '\t': sb.append("\\t"); break;
            default: sb.append(c); break;
			}
		}
		return sb.toString();
	}
	
}
