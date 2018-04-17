package com.zyj.apktools.command;

/**
 * CREATED ON: 2018/4/12 11:01
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: NON
 */
public interface InvokerStateCallback {

    /**
     * 准备运行
     */
    int STATE_READY = 0;

    /**
     * 正在运行
     */
    int STATE_RUNING = 1;

    /**
     * 已经运行完毕
     */
    int STATE_RUNED = 2;

    /**
     * 命令执行回调
     *
     * @param state 当前命令的状态信息
     *              <li> {@link #STATE_READY}
     *              <li> {@link #STATE_RUNING}
     *              <li> {@link #STATE_RUNED}
     */
    void callback(int state);

}
