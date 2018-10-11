package com.example.i20088.iconsultapp.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by dsriniv on 6/3/18.
 */

public class DateUtils {

    public final static String DATE_FORMAT_TIME_SSSZ = "hh:mm:ss.SSS'Z'";
    public final static String DATE_FORMAT_TIME_SSS = "hh:mm:ss.SSS";
    public final static String DATE_FORMAT_JAVA = "yyyy-MM-dd'T'hh:mm:ss.SSS";

    public static Date convertStringToDate(String dateString, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
//        formatter.setTimeZone(getTimeZoneConfig());
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertDateToString(Date date, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        formatter.setTimeZone(getTimeZoneConfig());
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getDay(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd");
        formatter.setTimeZone(getTimeZoneConfig());
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getMonth(Date date) {
        DateFormat formatter = new SimpleDateFormat("MM");
        formatter.setTimeZone(getTimeZoneConfig());
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getMonthName(Date date) {
        DateFormat formatter = new SimpleDateFormat("MMM");
        formatter.setTimeZone(getTimeZoneConfig());
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getYear(Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyy");
        formatter.setTimeZone(getTimeZoneConfig());
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getShortYear(Date date) {
        DateFormat formatter = new SimpleDateFormat("yy");
        formatter.setTimeZone(getTimeZoneConfig());
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getHourIn12HourFormat(Date date) {
        DateFormat formatter = new SimpleDateFormat("hh");
        formatter.setTimeZone(getTimeZoneConfig());
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getHourIn24HourFormat(Date date) {
        DateFormat formatter = new SimpleDateFormat("HH");
        formatter.setTimeZone(getTimeZoneConfig());
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getMinutes(Date date) {
        DateFormat formatter = new SimpleDateFormat("mm");
        formatter.setTimeZone(getTimeZoneConfig());
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getSeconds(Date date) {
        DateFormat formatter = new SimpleDateFormat("ss");
        formatter.setTimeZone(getTimeZoneConfig());
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getAmPm(Date date) {
        DateFormat formatter = new SimpleDateFormat("a");
        formatter.setTimeZone(getTimeZoneConfig());
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getDaySuffix(String dayString) {
        String suffix = null;
        int dayNumber = Integer.parseInt(dayString);

        switch (dayNumber % 10) {
            case 1:
                if (dayNumber != 11) {
                    suffix = "st";
                } else {
                    suffix = "th";
                }
                break;
            case 2:
                suffix = "nd";
                break;
            case 3:
                suffix = "rd";
                break;
            default:
                suffix = "th";
                break;
        }
        return suffix;
    }

    public static TimeZone getTimeZoneConfig() {
//        formatter.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getDisplayName()));
        return TimeZone.getTimeZone(TimeZone.getDefault().getDisplayName());
    }
}
