package com.zyj.apktools.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * CREATED ON: 2018/4/11 17:34
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: NON
 */
class JarCommand implements Command {

    private final Receiver receiver;

    public JarCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override public void execute(ExecResult result) {
        receiver.doAction(Optional.ofNullable(result));
    }

    @Override public void execute() {
        execute(null);
    }

    /**
     * 封装收集命令执行结果动作
     */
    public abstract class CollectReceiver implements Receiver {

    }

    public static class TestReceiver implements Receiver {

        @Override public void doAction(Optional<ExecResult> result) {
            Runtime rt = Runtime.getRuntime();
            String cmd = "javac";
            try {
                Process process = rt.exec(cmd);
                BufferedReader log = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
                BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream(), "utf-8"));
                List<String> logResult = new ArrayList<>();
                List<String> errorResult = new ArrayList<>();
                String line = null;
                while ((line = error.readLine()) != null) {
                    errorResult.add(line);
                }
                error.close();

                while ((line = log.readLine()) != null) {
                    logResult.add(line);
                }
                log.close();
                result.orElse(null).result(logResult, errorResult);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
