package com.zyj.apktools;

import java.io.IOException;
import java.util.Properties;
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
public class SomeUtils {

    /**
     * java.version: Java 运行时环境版本
     * <p>
     * java.vendor: Java 运行时环境供应商
     * <p>
     * java.vendor.url: Java 供应商的 URL
     * <p>
     * java.home: Java 安装目录
     * <p>
     * java.vm.specification.version: Java 虚拟机规范版本
     * <p>
     * java.vm.specification.vendor: Java 虚拟机规范供应商
     * <p>
     * java.vm.specification.name: Java 虚拟机规范名称
     * <p>
     * java.vm.version: Java 虚拟机实现版本
     * <p>
     * java.vm.vendor: Java 虚拟机实现供应商
     * <p>
     * java.vm.name: Java 虚拟机实现名称
     * <p>
     * java.specification.version: Java 运行时环境规范版本
     * <p>
     * java.specification.vendor: Java 运行时环境规范供应商
     * <p>
     * java.specification.name: Java 运行时环境规范名称
     * <p>
     * java.class.version: Java 类格式版本号
     * <p>
     * java.class.path: Java 类路径
     * <p>
     * java.library.path: 加载库时搜索的路径列表
     * <p>
     * java.io.tmpdir: 默认的临时文件路径
     * <p>
     * java.compiler: 要使用的 JIT 编译器的名称
     * <p>
     * java.ext.dirs: 一个或多个扩展目录的路径
     * <p>
     * os.name: 操作系统的名称
     * <p>
     * os.arch: 操作系统的架构
     * <p>
     * os.version: 操作系统的版本
     * <p>
     * file.separator: 文件分隔符（在 UNIX 系统中是“/”）
     * <p>
     * path.separator: 路径分隔符（在 UNIX 系统中是“:”）
     * <p>
     * line.separator: 行分隔符（在 UNIX 系统中是“/n”）
     * <p>
     * user.name: 用户的账户名称
     * <p>
     * user.home: 用户的主目录
     * <p>
     * user.dir: 用户的当前工作目录
     */
    static final Properties PROPERTIES = new Properties(System.getProperties());

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
            logTmp.log(new LogRecord(level, message));
        }
    }

    public static String getFileSeparator() {
        return PROPERTIES.getProperty("file.separator");
    }

    public static String getPathSeparator() {
        return PROPERTIES.getProperty("path.separator");
    }

}
