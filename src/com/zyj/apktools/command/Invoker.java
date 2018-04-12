package com.zyj.apktools.command;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CREATED ON: 2018/4/11 17:18
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 开始调用命令
 */
public final class Invoker {

    private static Invoker instance;
    private Command command = new JarCommand(new JarReceiver());
    private final ExecutorService singleThread = Executors.newSingleThreadExecutor();

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
     * 测试方法
     */
    public void commandTest() {
        singleThread.submit(() -> command.execute("java -version", (statue, message) -> System.out.println(statue + ", " + message)));
    }

    public void destory() {
        command.destory();
        singleThread.shutdownNow();
        instance = null;
    }

}
