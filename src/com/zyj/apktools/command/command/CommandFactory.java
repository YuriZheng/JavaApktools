package com.zyj.apktools.command.command;

import com.zyj.apktools.command.Command;
import com.zyj.apktools.command.InvokerMessageCallback;
import com.zyj.apktools.command.InvokerStateCallback;
import com.zyj.apktools.command.Receiver;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * CREATED ON: 2018/4/17 15:07
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: NON
 */
public class CommandFactory {

    private CommandFactory() {
    }

    /**
     * 测试命令
     */
    public static Command createTestCommand(Receiver receiver, CommandParamskBuild build) {
        setReceiverBuild(checkNull(receiver), checkNull(build));
        return new TestCommand(receiver);
    }

    /**
     * 获取反编译命令
     */
    public static Command createDecodeApkCommand(Receiver receiver, CommandParamskBuild build) {
        setReceiverBuild(checkNull(receiver), checkNull(build));
        return new DecodeApkCommand(receiver).setFilePath(build.params.get("fullPath"));
    }

    /**
     * 重新编译命令
     */
    public static Command createRebuildApkCommand(Receiver receiver, CommandParamskBuild build) {
        setReceiverBuild(checkNull(receiver), checkNull(build));
        return new RebuildApkCommand(receiver).setFilePath(build.params.get("fullPath"));
    }

    /**
     * 对apk签名
     */
    public static Command createSignerApkCommand(Receiver receiver, CommandParamskBuild build) {
        setReceiverBuild(checkNull(receiver), checkNull(build));
        return new SignerApkCommand(receiver)
                .setStorPath(build.params.get("storPath"))
                .setStorpass(build.params.get("storpass"))
                .setKeypass(build.params.get("keypass"))
                .setUnsignApk(build.params.get("unsignApk"))
                .setAlias(build.params.get("alias"));
    }

    private static <T> T checkNull(T t) {
        if (t == null) {
            throw new NullPointerException(t.toString());
        }
        return t;
    }

    private static void setReceiverBuild(Receiver receiver, CommandParamskBuild build) {
        receiver.setMessageCallback(build.messageCallback);
        receiver.setStateCallback(build.stateCallback);
    }

    public static class CommandParamskBuild {

        private Optional<InvokerMessageCallback> messageCallback = Optional.empty();
        private Optional<InvokerStateCallback> stateCallback = Optional.empty();

        private Map<String, String> params = new HashMap();

        public CommandParamskBuild setMessageCallback(Optional<InvokerMessageCallback> messageCallback) {
            this.messageCallback = messageCallback;
            return this;
        }

        public CommandParamskBuild setStateCallback(Optional<InvokerStateCallback> stateCallback) {
            this.stateCallback = stateCallback;
            return this;
        }

        public CommandParamskBuild putParamByKey(String key, String param) {
            params.put(key, param);
            return this;
        }
    }

}
