package com.prettyyes.user.core.utils;

import com.prettyyes.user.core.TimeManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core.utils
 * Author: SmileChen
 * Created on: 2016/9/20
 * Description: Nothing
 */
public class FormatUtils {

    public static Date longtimeToDate(String num) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat();
        Long time = new Long(num + "000");
        String d = format.format(time);
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date longtimeToDateS(String num) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat();
        Long time = new Long(num);
        String d = format.format(time);
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static long StringToDate(String time) {
        if (time == null)
            return 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }

    public static long StringToDateMs(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static long StringToDateHanzi(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String StringToDateAmPm(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa");
        Date date = new Date(StringToDateMs(time));

        String res = "";
        String res_return = "";
        try {
            res = simpleDateFormat.format(date);
            res_return = res;
            if (res.contains("上午")) {
                res_return = res.replaceAll("上午", "AM");
            }
            if (res.contains("下午"))
                res_return = res.replaceAll("下午", "PM");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return res_return;
    }

    public static String getWeekOfDate(long time) {

        Date date = new Date(time);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    public static String getNowDate() {
        return FormatUtils.getDate(TimeManager.getManager().getServer_time() * 1000);
    }

    public static String getDate(long time) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date(time);

        String dateString = format.format(date);


        return dateString;

    }

    public static String getDate_yyyyMMdd(long time) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date(time);

        String dateString = format.format(date);


        return dateString;

    }

    public static String getDateMMddyy(long time) {

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        Date date = new Date(time);

        String dateString = format.format(date);


        return dateString;

    }

    public static String remoZero(String a) {
        if (a.length() >= 2 && a.substring(0, 1).equals("0")) {
            return a.substring(1, 2);
        }
        return a;

    }

    public static String getTopOrBottom(String time) {
        int a = Integer.parseInt(remoZero(time));
        if (a > 12)
            return "下午";
        else if (a == 12)
            return "中午";
        return "上午";
    }

    public static Integer getHour_12(int hour) {
        if (hour > 12)
            return hour - 12;
        return hour;
    }

    public static String getLeftTime(long date) {
        long day = date / (1000 * 60 * 60 * 24);
        long hour = (date / (1000 * 60 * 60) - day * 24);
        long min = ((date / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (date / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

        String days = day > 0 ? day + "天" : "";
        String hours = hour > 0 ? hour + "小时" : "";
        String mins = min > 0 ? min + "分钟" : "";
        String second = s > 0 ? s + "秒" : "";

        return days + hours + mins + second;
    }

    //传的是秒
    public static String getTimeBefore(long start, long now) {
        long date = now - start;
        long day = date / (60 * 60 * 24);
        long hour = (date / (60 * 60) - day * 24);
        long min = ((date / (60)) - day * 24 * 60 - hour * 60);
        long s = (date - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

        String days = day > 0 ? day + "天" : "";
        if (day > 0)
            return FormatUtils.getDate_yyyyMMdd(start * 1000);

        String hours = hour > 0 ? hour + "小时" : "";
        if (hour > 0)
            return hours + "前";
        String mins = min > 0 ? min + "分钟" : "";
        if (min > 0)
            return mins + "前";
        String second = s > 0 ? s + "秒" : "";
        if (s > 0)
            return "刚刚";
        return "";
    }


    public static String getLeftTimeNum(long date) {
        long hour = (date / (60 * 60));
        long min = (date / 60 - hour * 60);
        long s = date - min * 60 - hour * 60 * 60;

        String hours = AddZero(hour) + ":";
        String mins = AddZero(min) + ":";
        String second = AddZero(s) + "";

        return hours + mins + second;
    }

    public static String AddZero(long input) {
        String res = input + "";
        if (input < 10) {
            res = "0" + input;
        }
        return res;
    }

    public static String monthToChinese(String a) {
        String m = remoZero(a);
        String result = "";
        switch (m) {
            case "1":
                result = "一月";
                break;
            case "2":
                result = "二月";
                break;
            case "3":
                result = "三月";
                break;
            case "4":
                result = "四月";
                break;
            case "5":
                result = "五月";
                break;
            case "6":
                result = "六月";
                break;
            case "7":
                result = "七月";
                break;
            case "8":
                result = "八月";
                break;
            case "9":
                result = "九月";
                break;
            case "10":
                result = "十月";
                break;
            case "11":
                result = "十一月";
                break;
            case "12":
                result = "十二月";
                break;
        }
        return result;
    }


}
