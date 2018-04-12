package com.zyj.apktools.component;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * CREATED NO: 2018/4/12 23:41
 * <p>
 * Author: Yuri.Zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 窗体及窗体内组件的配置信息
 */
public final class FrameInfo {
    private FrameInfo() {
    }


    /**
     * 窗口标题
     */
    public static String FRAME_TITLE = "Apktool工具";
    /**
     * 窗体大小
     */
    public static Dimension FRAME_MIN_SIZE = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width >> 1,
            Toolkit.getDefaultToolkit().getScreenSize().height >> 1);
    /**
     * 窗体是否可改变大小
     */
    public static boolean FRAME_RESIZEABLE = true;
}
