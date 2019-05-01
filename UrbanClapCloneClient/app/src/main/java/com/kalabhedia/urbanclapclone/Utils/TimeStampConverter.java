package com.kalabhedia.urbanclapclone.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeStampConverter {

    public static long getCurrentUnixTime() {
        return System.currentTimeMillis() / 1000L;
    }

    public static String getTimeForUnixTime(long unixTimeStamp) {

        long dv = unixTimeStamp * 1000L;
        Date df = new java.util.Date(dv);
        return (new SimpleDateFormat("hh:mm a", Locale.getDefault())).format(df);

    }

    public static String getDateForUnixTime(long unixTimeStamp) {

        long dv = unixTimeStamp * 1000L;
        Date df = new java.util.Date(dv);
        return (new SimpleDateFormat("dd, MM yyyy", Locale.getDefault())).format(df);

    }

}
