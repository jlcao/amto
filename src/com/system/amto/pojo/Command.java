package com.system.amto.pojo;

/**
 * Created by cjl on 2015/9/1.
 */
public class Command {
    public final static String CLICK = "click";
    public final static String MOVEUP = "moveup";
    public final static String MOVEDOWN = "movedown";
    public final static String SLEEP = "sleep";
    public final static String KEY = "key";
    public final static String TEXT = "text";
    public final static String INSTALL = "install";
    public final static String UNINSTALL = "uninstall";
    public final static String OPEN = "open";
    public final static String CLOSE = "close";
    public String type;
    public String command;
    public String vars;



}
