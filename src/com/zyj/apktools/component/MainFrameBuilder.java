package com.zyj.apktools.component;

import com.zyj.apktools.factory.Factory;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * CREATED ON: 2018/4/10 15:50
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 窗体建造器
 */
public final class MainFrameBuilder {

    public JFrame builder() {
        return buildFrameInformation();
    }

    // 创建窗口
    private MainFrame buildFrameInformation() {
        MainFrame frame = Factory.FrameFactory.create();

        frame.setTitle(FrameInfo.FRAME_TITLE);
        frame.setMinimumSize(FrameInfo.FRAME_MIN_SIZE);
        frame.setResizable(FrameInfo.FRAME_RESIZEABLE);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addSubComponent();

        return frame;
    }

}
