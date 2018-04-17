package com.zyj.apktools.command;

/**
 * CREATED ON: 2018/4/12 11:01
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: NON
 */
public interface InvokerMessageCallback {

    /**
     * 命令执行回调
     *
     * @param message 回调消息
     */
    void callback(String message);

}
