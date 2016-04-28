package com.system.amto.process;

import com.system.amto.boss.CmdProcessor;
import com.system.amto.comm.AppTools;
import com.system.amto.comm.Cache;
import com.system.amto.comm.ServerTools;
import com.system.amto.pojo.Command;
import com.system.amto.pojo.Work;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by cjl on 2015/9/1.
 */
public class CmdExec extends Thread {
    LinkedBlockingQueue<Work> queue = new LinkedBlockingQueue<Work>(1024);
    CmdProcessor processor = new CmdProcessor();

    @Override
    public void run() {
        while (true){
            try {
                Work work = queue.take();

                process(work);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void process(Work work) throws Exception {
        if(!checkApp(work)){//不存在应用,下载,安装
            String path = ServerTools.downLoadApk(work.app.appName);
            if(path!=null){
                processor.install(path);
                Thread.sleep(3000);
            }
        }
        open(work);
        for(Command cmd:work.cmds){
            processor.excl(cmd);
        }
        close(work);



    }

    private void close(Work work) throws Exception {
        Map<String,String> parm = new HashMap<String,String>();
        parm.put("runid", work.runid);
        parm.put("workid", work.workid);
        parm.put("worktype",work.workType);
        parm.put("imei", Cache.IMEI);
        ServerTools.workEnd(parm);
        processor.close(work.app.packageName);
    }

    private void open(Work work) throws Exception {
        Map<String,String> parm = new HashMap<String,String>();
        parm.put("runid", work.runid);
        parm.put("workid", work.workid);
        parm.put("worktype",work.workType);
        parm.put("imei", Cache.IMEI);

        ServerTools.workBegin(parm);
        processor.open(work.app.packageName+"/"+work.app.firstActivity);
    }

    private boolean checkApp(Work work) throws IOException {
        return AppTools.isAvilible(work.app.packageName);
    }

    public void put(List<Work> works) throws Exception {
        queue.addAll(works);
    }
    public int size(){
        return  queue.size();
    }
}
