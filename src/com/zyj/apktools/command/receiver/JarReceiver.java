package com.zyj.apktools.command.receiver;

import com.zyj.apktools.Utils;
import com.zyj.apktools.command.InvokerMessageCallback;
import com.zyj.apktools.command.InvokerStateCallback;
import com.zyj.apktools.command.Receiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.logging.Level;

/**
 * CREATED ON: 2018/4/12 12:22
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 消息的具体执行者
 */
public final class JarReceiver implements Receiver {

    private Optional<InvokerMessageCallback> messageCallback = Optional.empty();
    private Optional<InvokerStateCallback> stateCallback = Optional.empty();

    @Override public void doAction(String command) {
        Runtime rt = Runtime.getRuntime();
        try {
            stateCallback.ifPresent(callback -> callback.callback(InvokerStateCallback.STATE_RUNING));
            final Process process = rt.exec(command);
            readMessage(new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8")), messageCallback.orElse(null));
            readMessage(new BufferedReader(new InputStreamReader(process.getErrorStream(), "utf-8")), messageCallback.orElse(null));
            int waitValue = process.waitFor();
            Utils.l(command + " 执行结果: " + waitValue);
            messageCallback.ifPresent(invokerMessageCallback -> invokerMessageCallback.callback(waitValue == 0 ? "执行成功" : "执行失败"));
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
            Utils.l(Level.WARNING, command + " 执行结果: " + e.getLocalizedMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            Utils.l(Level.WARNING, command + " 执行结果: " + e.getLocalizedMessage());
        }
        stateCallback.ifPresent(callback -> callback.callback(InvokerStateCallback.STATE_RUNED));
    }

    @Override public void setMessageCallback(Optional<InvokerMessageCallback> messageCallback) {
        if (messageCallback != null) {
            this.messageCallback = messageCallback;
        }
    }

    @Override public void setStateCallback(Optional<InvokerStateCallback> stateCallback) {
        if (stateCallback != null) {
            this.stateCallback = stateCallback;
        }
    }

    @Override public Optional<InvokerMessageCallback> getMessageCallback() {
        return messageCallback;
    }

    @Override public Optional<InvokerStateCallback> getStateCallback() {
        return stateCallback;
    }

    private void readMessage(BufferedReader reader, InvokerMessageCallback result) throws IOException {
        if (result != null) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                result.callback(line);
            }
        }
        reader.close();
    }
}
