package com.system.amto.boss;

import com.system.amto.comm.Shellexec;
import com.system.amto.pojo.Command;

import java.io.IOException;
import java.util.Random;

/**
 * ÃüÁîÖ´ÐÐÆ÷
 * Created by cjl on 2015/9/1.
 */
public class CmdProcessor {
    private Shellexec shell = new Shellexec();
    public int excl(Command command) {
        int i = -1;
        if (command.type.equals(Command.CLICK)) {
            i = click(command.vars);

        } else if (command.type.equals(Command.KEY)) {
            i = key(command.vars);

        } else if (command.type.equals(Command.MOVEDOWN)) {
            i = moveDown(command.vars);

        } else if (command.type.equals(Command.MOVEUP)) {
            i = moveUp(command.vars);

        } else if (command.type.equals(Command.SLEEP)) {
            i = sleep(command.vars);

        } else if (command.type.equals(Command.TEXT)) {
            i = text(command.vars);

        } else if (command.type.equals(Command.INSTALL)) {
            i = install(command.vars);

        } else if (command.type.equals(Command.UNINSTALL)) {
            i = uninstall(command.vars);

        } else if (command.type.equals(Command.OPEN)) {
            i = open(command.vars);

        } else if (command.type.equals(Command.CLOSE)) {
            i = close(command.vars);

        }

        return i;
    }

    public int close(String packageName) {
        try {
            shell.execCommand("am force-stop "+ packageName);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int open(String packageNameAndActivityName) {
        try {
            shell.execCommand("am start -n "+ packageNameAndActivityName);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int uninstall(String packageName) {
        try {
            shell.execCommand("pm uninstall "+ packageName);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int install(String path) {
        try {
            shell.execCommand("input install -f "+path);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int text(String vars) {
        try {
            shell.execCommand("input text '"+vars+"'");
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int sleep(String vars) {
        try {
            Thread.sleep(Integer.parseInt(vars));
            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int moveUp(String vars) {
        try {
            if(vars==null){
                vars = "300 300 350 600";
            }
            shell.execCommand("input swipe "+vars);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int moveDown(String vars) {
        try {
            if(vars ==null){
                vars = "300 700 350 300";
            }
            shell.execCommand("input swipe "+vars);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int key(String vars) {
        try {
            shell.execCommand("input keyevent "+vars);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int click(String vars) {
        try {
            if(vars == null){
                Random random = new Random();
                int x = random.nextInt(450);
                int y = random.nextInt(750);
                vars = x + " " + y;
            }
            shell.execCommand("input tap "+vars);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
