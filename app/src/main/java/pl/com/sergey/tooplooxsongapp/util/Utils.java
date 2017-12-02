package pl.com.sergey.tooplooxsongapp.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by smilevkiy on 30.11.17.
 */

public final class Utils {

    public static String getLongFromString(String time) {
        try {

            return new SimpleDateFormat("yyyy", Locale.getDefault())
                    .format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(time));
        } catch (ParseException e) {
            Log.e("parse", "error", e);
            return "";
        }
    }
}
