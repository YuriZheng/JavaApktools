package com.zyj.apktools.command;

import java.util.Optional;

/**
 * CREATED ON: 2018/4/11 17:17
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: NON
 */
public interface Receiver {

    /**
     * @param command
     */
    void doAction(String command);

    /**
     * 设置消息监听
     */
    void setMessageCallback(Optional<InvokerMessageCallback> messageCallback);

    /**
     * 设置状态监听
     */
    void setStateCallback(Optional<InvokerStateCallback> stateCallback);

    /**
     * 获取消息监听
     *
     * @return 不能返回null，只能返回{@link Optional}对象
     */
    Optional<InvokerMessageCallback> getMessageCallback();

    /**
     * 设置消息监听
     *
     * @return 不能返回null，只能返回{@link Optional}对象
     */
    Optional<InvokerStateCallback> getStateCallback();

}
