package com.zyj.apktools.command;

import com.zyj.apktools.SomeUtils;

import java.io.File;
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

    /**
     * 开始重新编译
     */
    public void comandBuild(String fullPath, InvokerCallback callback) {
        final String separator = SomeUtils.getFileSeparator();
        String parentPath = new File(fullPath).getParent();
        String tagPath = null;
        if (!parentPath.contains(separator)) {
            tagPath = parentPath + ".apk";
        } else {
            tagPath = parentPath + separator + fullPath.substring(fullPath.lastIndexOf(SomeUtils.getFileSeparator()) + 1) + ".apk";
        }
        final String tmp = tagPath;
        singleThread.submit(() -> command.execute(String.format(buildCommandString, fullPath, tmp), callback));
    }

    /**
     * 开始对apk进行签名
     */
    public void comandSigner(String storPath, String storpass, String keypass, String unsignApk, String alias, InvokerCallback callback) {
        File apk = new File(unsignApk);
        String newName = apk.getName().substring(0, apk.getName().lastIndexOf(".")) + "_signed.apk";
        String outputApk = apk.getParent() + SomeUtils.getFileSeparator() + newName;
        singleThread.submit(() -> command.execute(String.format(signerCommandString, storPath, storpass, keypass, outputApk, unsignApk, alias), callback));
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

    /**
     * jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore itools.jks -storepass Zyj497393102 -keypass Zyj497393102 -signedjar new_signed.apk new.apk itools
     * 开始对apk进行签名
     * 第一个参数：密钥文件路径<br>
     * 第二个参数：密钥文件密码<br>
     * 第三个参数：专用密钥密码（一般都是密钥文件密码一致）<br>
     * 第四个参数：未签名apk路径<br>
     * 第五个参数：输出签名apk路径<br>
     * 第六个参数：密钥别名<br>
     */
    private final String signerCommandString = "jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore %s -storepass %s -keypass %s -signedjar %s %s %s";

}
