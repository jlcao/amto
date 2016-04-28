package com.system.amto.boss;

import com.system.amto.pojo.AppInfo;
import com.system.amto.pojo.Command;
import com.system.amto.pojo.Work;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 命令解析器
 * Created by cjl on 2015/9/1.
 */
public class CmdParser {

    public List<Work> parser(String str) throws JSONException {
        List<Work> list = new ArrayList<Work>();

        JSONObject worksJson = new JSONObject(str);
        JSONArray worksArray = worksJson.getJSONArray("works");

        for(int i = 0;i<worksArray.length();i++) {
            toWork(worksArray.getJSONObject(i), list);
        }
        return list;

    }

    private void toWork(JSONObject workJson, List<Work> list) throws JSONException {
        Work work = new Work();
        work.app = toApp(workJson.getJSONObject("app"));
        work.cmds = toCmds(workJson.getJSONArray("cmds"));
        work.workid = workJson.getString("workid");
        work.workType = workJson.getString("worktype");
        list.add(work);
    }

    private List<Command> toCmds(JSONArray strs) throws JSONException {
        List<Command> list = new ArrayList<Command>();
        for (int i = 0; i < strs.length(); i++) {
            Command command = new Command();
            String tmpCmd = strs.getString(i);
            String[] tmp = tmpCmd.split(" ");
            String cmd = tmp[0].trim();
            command.type = cmd.toLowerCase();
            command.command = tmpCmd;
            if (tmp.length == 2) {
                command.vars = tmp[1].replace(",", " ");
            }
            list.add(command);
        }
        return list;
    }

    private AppInfo toApp(JSONObject appJson) throws JSONException {
        AppInfo app = new AppInfo();
        app.appName = appJson.getString("appName");
        app.packageName = appJson.getString("packageName");
        app.firstActivity = appJson.getString("firstActivity");
        app.applicationName = appJson.getString("apkName");
        return app;
    }
}
