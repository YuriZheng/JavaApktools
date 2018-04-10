package com.zyj.apktools.component.frame;

import java.awt.HeadlessException;

import javax.swing.JFrame;

/**
 * CREATED ON: 2018/4/10 17:24
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: NON
 */
public final class MainFrame extends JFrame {

    private final MainPresenter presenter;

    public MainFrame() throws HeadlessException {

        presenter = new MainPresenter();

    }
}
