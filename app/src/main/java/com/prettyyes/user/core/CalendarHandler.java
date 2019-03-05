package com.prettyyes.user.core;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;

import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.model.web.CalendarModel;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core
 * Author: SmileChen
 * Created on: 2016/12/15
 * Description: Nothing
 */
public class CalendarHandler {
    //检查是否已经添加了日历账户，如果没有添加先添加一个日历账户再查询
    public static int checkAndAddCalendarAccount(Context context) {
        int oldId = checkCalendarAccount(context);
        if (oldId >= 0) {
            return oldId;
        } else {
            long addId = addCalendarAccount(context);
            if (addId >= 0) {
                return checkCalendarAccount(context);
            } else {
                return -1;
            }
        }
    }

    public static int checkCalendarAccount(Context context) {
        Cursor userCursor = null;
        try {
            userCursor = context.getContentResolver().query(Uri.parse(CALANDER_URL), null, null, null, null);

            if (userCursor == null)//查询返回空值
                return -1;
            int count = userCursor.getCount();
            if (count > 0) {//存在现有账户，取第一个账户的id返回
                userCursor.moveToFirst();
                return userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID));
            } else {
                return -1;
            }
        } catch (Exception ee) {
            AppUtil.showToastShort("检查你的日历访问权限是否开启");

        } finally {
            if (userCursor != null) {
                userCursor.close();
            }
        }

        return -1;

    }

    private static String CALENDARS_NAME = "How";
    private static String CALENDARS_ACCOUNT_NAME = "prettyyes@gmail.com";
    private static String CALENDARS_ACCOUNT_TYPE = "com.android.prettyyes";
    private static String CALENDARS_DISPLAY_NAME = "How账户";

    public static void addCalendar(Context context, CalendarModel calendarModel) {
        if (CalendarHandler.checkAndAddCalendarAccount(context) != -1) {
            CalendarHandler.addCalendarEvent(context, calendarModel.getTitle()
                    , calendarModel.getDesc()
                    , Long.parseLong(calendarModel.getStart_time())
                    , Long.parseLong(calendarModel.getEnd_time())
                    , calendarModel.getRemindtime());
        } else {
            AppUtil.showToastShort("检查访问日历权限是否开启");
        }
    }

    private static void addCalendarEvent(Context context, String title, String description, long beginTime, long endtime, long remindtime) {
        // 获取日历账户的id
        int calId = checkAndAddCalendarAccount(context);
        if (calId < 0) {
            // 获取账户id失败直接返回，添加日历事件失败

            return;
        }
        ContentValues event = new ContentValues();
        event.put("title", title);
        event.put("description", description);
        // 插入账户的id
        event.put("calendar_id", calId);

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(beginTime);//设置开始时间
        long start = mCalendar.getTime().getTime();
        mCalendar.setTimeInMillis(endtime);//设置终止时间
        long end = mCalendar.getTime().getTime();
        event.put(CalendarContract.Events.DTSTART, start);
        event.put(CalendarContract.Events.DTEND, end);
        event.put(CalendarContract.Events.HAS_ALARM, 1);//设置有闹钟提醒
        event.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Beijin");  //这个是时区，必须有，
        //添加事件
        Uri newEvent = context.getContentResolver().insert(Uri.parse(CALANDER_EVENT_URL), event);
        if (newEvent == null) {
            // 添加日历事件失败直接返回
            return;
        }
        //事件提醒的设定
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent));
        // 提前10分钟有提醒
        values.put(CalendarContract.Reminders.MINUTES, remindtime);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        Uri uri = context.getContentResolver().insert(Uri.parse(CALANDER_REMIDER_URL), values);
        if (uri == null) {
            // 添加闹钟提醒失败直接返回
            return;
        }
        AppUtil.showToastShort("添加事件提醒成功");
    }

    private static long addCalendarAccount(Context context) {
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();
        value.put(CalendarContract.Calendars.NAME, CALENDARS_NAME);

        value.put(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE);
        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CALENDARS_DISPLAY_NAME);
        value.put(CalendarContract.Calendars.VISIBLE, 1);
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE);
        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);

        Uri calendarUri = Uri.parse(CALANDER_URL);
        calendarUri = calendarUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE)
                .build();

        Uri result = context.getContentResolver().insert(calendarUri, value);
        long id = result == null ? -1 : ContentUris.parseId(result);


        return id;
    }

    private static String CALANDER_URL = "content://com.android.calendar/calendars";
    private static String CALANDER_EVENT_URL = "content://com.android.calendar/events";
    private static String CALANDER_REMIDER_URL = "content://com.android.calendar/reminders";
}
