package com.zyj.apktools.command;

import java.util.List;

/**
 * CREATED ON: 2018/4/11 17:49
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 输出命令执行结果
 */
public interface ExecResult {

    /**
     * 将命令执行结果进行回调
     *
     * @param infos 正常执行命令，成功或警告信息
     * @param erros 执行命令的错误信息
     */
    void result(List<String> infos, List<String> erros);

}
