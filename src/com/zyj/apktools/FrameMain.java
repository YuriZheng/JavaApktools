package com.zyj.apktools;

/**
 * CREATED ON: 2018/4/10 15:29
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 主窗体程序
 */
public final class FrameMain {

    private FrameMain() {
    }

    public static void main(String[] args) {
        FrameMain.createFrame();
    }

    private static void createFrame() {
        new FrameBuilder().builder().setVisible(true);
    }

}
