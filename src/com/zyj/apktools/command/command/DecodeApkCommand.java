package com.zyj.apktools.command.command;

import com.zyj.apktools.command.InvokerStateCallback;
import com.zyj.apktools.command.Receiver;

/**
 * CREATED ON: 2018/4/11 17:34
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 命令发出者
 */
final class DecodeApkCommand extends AbstractCommand {

    /**
     * 反编译apk命令，两个参数：一个apk路径，一个生成的文件路径（该文件路径应该不存在）
     */
    private final String decodeCommandString = getDecodJar() + "apktool decode %s -o %s";

    private String filePath;

    public DecodeApkCommand(Receiver receiver) {
        super(receiver);
    }

    public DecodeApkCommand setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    @Override public void execute() {
        if (filePath == null || filePath.length() < 1) {
            getReceiver().getMessageCallback().ifPresent(invokerMessageCallback -> invokerMessageCallback.callback("反编译路径不能为空"));
            getReceiver().getStateCallback().ifPresent(callback -> callback.callback(InvokerStateCallback.STATE_RUNED));
        } else {
            getReceiver().doAction(String.format(decodeCommandString, filePath, filePath.substring(0, filePath.lastIndexOf("."))));
        }
    }
}
