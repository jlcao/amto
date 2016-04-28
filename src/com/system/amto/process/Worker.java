package com.system.amto.process;

import com.system.amto.boss.CmdParser;
import com.system.amto.comm.Cache;
import com.system.amto.comm.ServerTools;
import com.system.amto.pojo.Work;

import java.util.List;


/**
 * Created by cjl on 2015/9/1.
 */


public class Worker extends Thread {

    private CmdExec cmd;
    private CmdParser parser;
    private boolean runing = true;

    @Override
    public void run() {
        cmd = new CmdExec();
        parser = new CmdParser();
        //???????
        while (runing) {

            try {
                if(cmd.size()<2){
                    process(getWork());
                }
                Thread.sleep(1000*60);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void process(List<Work> works) throws Exception {
        cmd.put(works);
    }

    public List<Work> getWork() throws Exception {
        String jsonStr = ServerTools.getWork(Cache.IMEI);
        List<Work> works = parser.parser(jsonStr);
        return works;
    }
}
