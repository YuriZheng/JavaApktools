package com.zyj.apktools.command;

import com.zyj.apktools.command.command.CommandFactory;
import com.zyj.apktools.command.receiver.JarReceiver;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * CREATED ON: 2018/4/11 17:18
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 开始调用命令
 */
public final class Invoker {

    private static Invoker instance;
    private Receiver receiver = new JarReceiver();
    private final ExecutorService singleThread = Executors.newSingleThreadExecutor();

    /**
     * 任务同步，同一时间只能运行一个命令
     */
    private AtomicBoolean isRunSynchronized = new AtomicBoolean(false);

    private InvokerStateCallback stateCallback = state -> {
        if (state == InvokerStateCallback.STATE_RUNED) {
            isRunSynchronized.set(false);
        } else {
            isRunSynchronized.compareAndSet(false, true);
        }
    };

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
        if (isRunSynchronized.get()) {
            return;
        }
        singleThread.submit(() -> CommandFactory.createTestCommand(receiver, new CommandFactory.CommandParamskBuild()
                .setMessageCallback(Optional.ofNullable(null))
                .setStateCallback(Optional.of(stateCallback))
                .putParamByKey("1111", "2222")).execute());
    }

    /**
     * 开始反编译apk
     */
    public void comandDecod(String fullPath, Optional<InvokerMessageCallback> messageCallback) {
        if (isRunSynchronized.get()) {
            return;
        }
        singleThread.submit(() -> CommandFactory.createDecodeApkCommand(receiver, new CommandFactory.CommandParamskBuild()
                .setMessageCallback(messageCallback)
                .setStateCallback(Optional.of(stateCallback))
                .putParamByKey("fullPath", fullPath)).execute());
    }

    /**
     * 开始重新编译
     */
    public void comandRebuild(String fullPath, Optional<InvokerMessageCallback> messageCallback) {
        if (isRunSynchronized.get()) {
            return;
        }
        singleThread.submit(() -> CommandFactory.createRebuildApkCommand(receiver, new CommandFactory.CommandParamskBuild()
                .setMessageCallback(messageCallback)
                .setStateCallback(Optional.of(stateCallback))
                .putParamByKey("fullPath", fullPath)).execute());
    }

    /**
     * 开始对apk进行签名
     */
    public void comandSigner(String storPath, String storpass, String keypass, String unsignApk, String alias,
                             Optional<InvokerMessageCallback> messageCallback) {
        if (isRunSynchronized.get()) {
            return;
        }
        singleThread.submit(() -> CommandFactory.createSignerApkCommand(receiver, new CommandFactory.CommandParamskBuild()
                .setMessageCallback(messageCallback)
                .setStateCallback(Optional.of(stateCallback))
                .putParamByKey("storPath", storPath)
                .putParamByKey("storpass", storpass)
                .putParamByKey("keypass", keypass)
                .putParamByKey("unsignApk", unsignApk)
                .putParamByKey("alias", alias)
        ).execute());
    }

    public void destory() {
        singleThread.shutdownNow();
        instance = null;
    }

}
