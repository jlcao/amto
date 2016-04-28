package com.system.amto.comm;

import android.util.Log;
import com.system.amto.comm.Cache;
import com.system.amto.comm.HttpUtil;
import org.json.JSONObject;
import java.util.Map;

/**
 * Created by cjl on 2015/9/2.
 */
public class ServerTools {


    public static String downLoadApk(String appName) throws Exception {
        String url = Cache.SERVER_URL+"work/download/"+appName;
        int i = HttpUtil.downloadFile(url,Cache.APPFILEPATH,appName);
        if(i>-1){
            String path = Cache.APPFILEPATH + appName;
            Log.i("cjl",path);
            return path;
        }
        return null;
    }

    public static String getWork(String imei) throws Exception {
        String url = Cache.SERVER_URL+"work/load/"+imei;
        String result =  HttpUtil.sendGetRequest(url, null, "utf-8");
        return result;
    }

    public static String workBegin(Map<String,String> data) throws Exception {
        String url = Cache.SERVER_URL+"work/receive/begin";
        String result = HttpUtil.sendPostRequest(url,data,"utf-8");
        return result;
    }
    public static String workEnd(Map<String,String> data) throws Exception {
        String url = Cache.SERVER_URL+"work/receive/end";
        String result = HttpUtil.sendPostRequest(url, data, "utf-8");
        return result;
    }

}
