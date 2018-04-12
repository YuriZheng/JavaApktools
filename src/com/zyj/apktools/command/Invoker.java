package com.zyj.apktools.command;

import com.zyj.apktools.SomeUtils;

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
    private final String apkJarName = "apktool_2.3.2.jar";
    private final String decodJar = String.format("java -jar .%slibs%s%s ", SomeUtils.getFileSeparator(), SomeUtils.getFileSeparator(), apkJarName);
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
        singleThread.submit(() -> command.execute(decodJar + "apktool d 1.apk", (statue, message) -> System.out.println(statue + ", " + message)));
    }

    /**
     * 开始反编译apk
     */
    public void comandDecod(String fullPath, InvokerCallback callback) {
        singleThread.submit(() -> command.execute(String.format(decodeCommandString, fullPath,
                fullPath.substring(0, fullPath.lastIndexOf("."))), callback));
    }

    public void comandBuild(String fullPath, InvokerCallback callback) {
        final String separator = SomeUtils.getFileSeparator();
        String tagPath = null;
        if (!fullPath.contains(separator)) {
            tagPath = fullPath + separator + fullPath;
        } else {
            tagPath = fullPath + separator + fullPath.substring(fullPath.lastIndexOf(SomeUtils.getFileSeparator()) + 1) + ".apk";
        }
        final String tmp = tagPath;
        singleThread.submit(() -> command.execute(String.format(buildCommandString, fullPath, tmp), callback));
    }

    public void destory() {
        command.destory();
        singleThread.shutdownNow();
        instance = null;
    }

    /**
     * 反编译apk命令，两个参数：一个apk路径，一个生成的文件路径（该文件路径应该不存在）
     */
    private final String decodeCommandString = decodJar + "apktool decode %s -o %s";

    /**
     * 重编译apk命令，两个参数：一个文件夹路径，一个生成的apk名称（该文件应该不存在）
     */
    private final String buildCommandString = decodJar + "apktool build %s -o %s";

}
