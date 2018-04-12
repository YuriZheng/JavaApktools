package com.zyj.apktools;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * CREATED NO: 2018/4/10 22:02
 * <p>
 * Author: Yuri.Zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 日志打印
 */
public class Log {

    private static boolean isDebug = true;

    private static Logger log = null;

    private static Logger getFilelogger() {
        if (log == null) {
            final String logName = "log.txt";
            log = Logger.getLogger(logName);
            log.setLevel(Level.ALL);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            log.addHandler(consoleHandler);
            try {
                FileHandler fileHandler = new FileHandler(logName);
                fileHandler.setEncoding("utf-8");
                fileHandler.setFormatter(new SimpleFormatter());
                fileHandler.setLevel(Level.ALL);
                log.addHandler(fileHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return log;
    }

    public static void l(String message) {
        l(Level.ALL, message);
    }

    public static void l(Level level, String message) {
        Logger logTmp = getFilelogger();
        if (isDebug) {
//            for (Handler handler : logTmp.getHandlers()) {
//                handler.setLevel(level);
//            }
//            logTmp.log(level, message);
            logTmp.log(new LogRecord(level, message));
        }
    }

}
