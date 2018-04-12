package com.zyj.apktools.command;

/**
 * CREATED ON: 2018/4/11 17:17
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: NON
 */
public interface Receiver {
    /**
     *
     * @param command
     * @param result
     */
    void doAction(String command, InvokerCallback result);

    /**
     * 销毁相关资源
     */
    void destory();

}
