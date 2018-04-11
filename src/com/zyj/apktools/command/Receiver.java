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

    void doAction(Optional<ExecResult> result);

}
