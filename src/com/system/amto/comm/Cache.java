package com.system.amto.comm;

import android.content.Context;
import android.provider.Settings;

import java.io.IOException;

/**
 * Created by cjl on 2015/9/2.
 */
public class Cache {
    public final static String SERVER_URL = "http://192.168.31.192:8080/amto_server/";
    public static String APPFILEPATH = "";
    public static String IMEI = "";

    public static void  init(Context context) throws IOException {
        IMEI = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        APPFILEPATH = context.getFilesDir().getAbsolutePath().toString();
    }

}
