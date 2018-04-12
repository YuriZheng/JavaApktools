package com.zyj.apktools.command;

/**
 * CREATED ON: 2018/4/11 17:34
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 命令发出者
 */
class JarCommand implements Command {

    public final Receiver receiver;

    public JarCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override public void execute(String command, InvokerCallback callback) {
        receiver.doAction(command, callback);
    }

    @Override public void destory() {
        receiver.destory();
    }
}
