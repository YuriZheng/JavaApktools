package com.zyj.apktools.command;

import java.util.HashMap;
import java.util.Map;

/**
 * CREATED ON: 2018/4/11 17:18
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 开始调用命令
 */
public final class Invoker {

    private static Invoker instance;

    private static Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put(COMMAND_KEY.COMMAND_TEST, new JarCommand(new JarCommand.TestReceiver()));
    }

    private Invoker() {
    }

    public static Invoker getInstence() {
        if (instance == null) {
            synchronized (Invoker.class) {
                if (instance == null) {
                    instance = new Invoker();
                }
            }
        }
        return instance;
    }

    /**
     * 开启测试方法
     */
    public void commandTest() {
        commandMap.get(COMMAND_KEY.COMMAND_TEST).execute();
    }

    static class COMMAND_KEY {
        final static String COMMAND_TEST = "_test";
    }

}
