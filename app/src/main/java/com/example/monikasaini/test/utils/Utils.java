package com.example.monikasaini.test.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by Monika on 19/11/16.
 */

public class Utils {
    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {

            InputStream is = context.getAssets().open("data.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static String getReadableDateString(long time){
        SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("MMMM d, yyyy");
        shortenedDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        return shortenedDateFormat.format(time*1000);  //Beacuse it takes arguments in milliseconds
    }

    public static void runOnNewThread(final Runnable R) {

        Thread th = new Thread() {

            @Override
            public void run() {
                R.run();
            }
        };
        th.start();
    }
    public static void runOnMainThread(Runnable runnable) {

        if (Looper.myLooper() == Looper.getMainLooper()) {

            runnable.run();
        } else {

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(runnable);

        }
    }
}
