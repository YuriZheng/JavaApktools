package com.zyj.apktools.command;

/**
 * CREATED ON: 2018/4/11 17:16
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 命令执行
 */
public interface Command {
    /**
     * 正常打印信息
     */
    int RESULT_NORMAL = 0;
    /**
     * 错误或警告
     */
    int RESULT_ERROR = 1;

    /**
     * 命令执行
     *
     * @param command  命令执行码
     * @param callback 执行回调
     */
    void execute(String command, InvokerCallback callback);

    /**
     * 销毁相关资源
     */
    void destory();
}
