
package com.demo.util;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Kylin Zhang 2012-1-4
 * @description
 */
public class StringFormat {
    public static String formatStrForHtml(String str) {

        str = str.replace("&nbsp;", " ");
        // str = str.replace("<br>", "\r\n");
        str = str.replace("&amp;", "");
        str = str.replace("&quot;", "\"");
        str = str.replace("\\r", "");
        StringBuffer sb = new StringBuffer();
        //????????
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            switch (c) {
                case '\n':
                    sb.append("<br>");
                    break;
                case '\r':
                    break;
                default:
                    sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * Sperate String to 2 parts based on the interval. For example, sperate 'W4.ABC' to 'W4' and 'ABC'
     *
     * @param source
     * @param interval
     * @return
     */
    public static String[] separateStringTo2Parts(String source, String interval) {
        String[] result = new String[2];
        int index = source.indexOf(interval);
        if (-1 != index) {
            result[0] = source.substring(0, index);
            result[1] = source.substring(index + interval.length(), source.length());
        }
        return result;
    }

    public static String stringFormat(String str) {
        if (str == null)
            return null;

        StringBuffer sb = new StringBuffer();
        //????????
        int len = str.length();

        //???????
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            switch (c) {
                case ' ':
                    sb.append("&nbsp;");
                    break;
                case '\n':
                    sb.append("<br>");
                    break;
                case '\r':
                    break;
                case '\'':
                    sb.append("&#39;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '"':
                    sb.append("&#34");
                    break;
                case '\\':
                    sb.append("&#92");
                    break;
                default:
                    sb.append(c);
            }//end switch
        }//end for

        return sb.toString();
    }

    /**
     * separate a String to a String array by using the interval
     *
     * @param source   the String need to be separated
     * @param interval
     * @param times    specify separate times, if times==-1 then no time limitation
     * @return result array
     */
    public static String[] separateString2Array(String source, String interval, int times) {
        if (null == source)
            return new String[0];

        List<String> tempList = new LinkedList<String>();

        String[] pieces = source.split(interval);
        int i = 0;
        for (; i < pieces.length && (-1 == times || i < times); i++) {
            if ("\n".equals(interval) && pieces[i].endsWith("\r")) {
                pieces[i] = pieces[i].substring(0, pieces[i].length() - 1);
            }
            tempList.add(pieces[i]);
        }
        StringBuilder sb = i < pieces.length ? new StringBuilder() : null;
        for (; i < pieces.length; i++) {
            sb.append(pieces[i]);
        }
        if (null != sb)
            tempList.add(sb.toString());

        return tempList.toArray(new String[0]);
    }
}
