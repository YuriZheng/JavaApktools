package com.zyj.apktools.command;

import com.zyj.apktools.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CREATED ON: 2018/4/12 12:22
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 消息的具体执行者
 */
class JarReceiver implements Receiver {

    private final ExecutorService thread = Executors.newFixedThreadPool(2);

    @Override public void doAction(String command, InvokerCallback result) {
        Runtime rt = Runtime.getRuntime();
        try {
            final Process process = rt.exec(command);
            thread.execute(() -> {
                try {
                    readMessage(Command.RESULT_NORMAL, new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8")), result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.execute(() -> {
                try {
                    readMessage(Command.RESULT_ERROR, new BufferedReader(new InputStreamReader(process.getErrorStream(), "utf-8")), result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Log.l(command + " 执行结果: " + process.waitFor());
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.shutdown();
    }

    private void readMessage(int status, BufferedReader reader, InvokerCallback result) throws IOException {
        if (result != null) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (result != null) {
                    synchronized (result) {
                        result.callback(status, line);
                    }
                }
            }
        }
        reader.close();
    }

    @Override public void destory() {
        thread.shutdownNow();
    }
}
