package com.zyj.apktools.command.command;

import com.zyj.apktools.command.Command;
import com.zyj.apktools.command.Receiver;

import java.io.File;

/**
 * CREATED ON: 2018/4/17 14:56
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 发送命令，在发送命令之前可以做一些事情
 */
abstract class AbstractCommand implements Command {

    private final Receiver receiver;

    private final String apkJarName = "apktool_2.3.2.jar";
    private final String encoding = "-Duser.language=en -Dfile.encoding=UTF8";
    private final String decodJar = String.format("java -jar %s .%slibs%s%s ", encoding, File.separator, File.separator, apkJarName);

    public AbstractCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public String getDecodJar() {
        return decodJar;
    }

    public String getEncoding() {
        return encoding;
    }
}
