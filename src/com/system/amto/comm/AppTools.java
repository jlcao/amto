package com.system.amto.comm;

import java.io.IOException;

/**
 * Created by cjl on 2015/9/2.
 */
public class AppTools {
    public static Shellexec exec = new Shellexec();


    public static boolean isAvilible(String packageName) throws IOException {
        String i = exec.execCommand("pm list packages -f " + packageName);
        if(i!=null&&i.contains(packageName)) {
            return true;
        }
        return false;
    }

    public static boolean isRun(String packageName) throws IOException {
        String i = exec.execCommand("ps | grep "+packageName);
        if(i!=null&&i.contains(packageName)) {
            return true;
        }
        return false;
    }

}
