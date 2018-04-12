package com.zyj.apktools.command;

/**
 * CREATED ON: 2018/4/12 11:01
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: NON
 */
public interface InvokerCallback {

    /**
     * 命令执行回调
     *
     * @param statue  回调状态，{@link Command#RESULT_NORMAL}、{@link Command#RESULT_ERROR}
     * @param message 回调消息
     */
    void callback(int statue, String message);

}
