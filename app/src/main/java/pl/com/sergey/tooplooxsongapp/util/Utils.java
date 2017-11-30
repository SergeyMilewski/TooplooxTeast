package pl.com.sergey.tooplooxsongapp.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by smilevkiy on 30.11.17.
 */

public final class Utils {

    public static String getYear(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        return simpleDateFormat.format(date);
    }

    public static Date getDataFromLong(long time) {
        return new Date(time);
    }

    public static String getFormattedDate(long time) {
        return getYear(getDataFromLong(time));
    }

    public static long getLongFromString(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {

            return simpleDateFormat.parse(time).getTime();
        } catch (ParseException e) {
            Log.e("parse","error", e);
            return 0;
        }
    }
}
