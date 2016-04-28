package com.system.amto.comm;

import android.util.Log;

import java.io.*;

/**
 * Created by cjl on 2015/9/1.
 */
public class Shellexec {

    public String execCommand(String[] command,String dir) throws IOException {
        String result = "";
        ProcessBuilder builder = new ProcessBuilder(command);
        if(dir!= null)
            builder.directory(new File(dir));
        builder.redirectErrorStream(true);
        Process process = builder.start();
        InputStream is = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String tmp = "";
        while ((tmp = reader.readLine())!=null) {
            result += tmp;
        }
        is.close();
        process.destroy();
        return result;
    }
    public String execCommand(String command) throws IOException {

        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec(command);
        String result = "";
        InputStream is = null;
        try {
            if (proc.waitFor() != 0) {
                Log.e("cjl", "exit value = " + proc.exitValue());
            }
            is = proc.getInputStream();
            byte[] buffer = new byte[1024];
            while (is.read(buffer)!=-1){
                result = result+ new String (buffer);
            }

            Log.i("cjl","is ok!!");
        } catch (InterruptedException e) {
            Log.e("cjl",e.getMessage());
        }finally {
            if(is != null){
                is.close();
            }
            proc.destroy();
        }
        return result;
    }

}
