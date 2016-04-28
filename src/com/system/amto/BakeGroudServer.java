package com.system.amto;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.system.amto.pojo.Work;
import com.system.amto.process.CmdExec;
import com.system.amto.process.Worker;

/**
 * Created by cjl on 2015/9/1.
 */
public class BakeGroudServer extends Service {
    Worker worker;
    CmdExec exec;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        worker.start();
        exec.start();
        return super.onStartCommand(intent, flags, startId);

    }
}
