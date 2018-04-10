package com.zyj.apktools.component.menu;

import com.zyj.apktools.factory.Factory;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * CREATED ON: 2018/4/10 17:34
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: NON
 */
public final class MenuPanel {

    private final MenuPresenter presenter;

    public MenuPanel() {
        presenter = new MenuPresenter();
    }

    /**
     * 需要一个容器，需要一个大小就可以添加菜单了
     */
    public void buildMenu(JMenuBar bar) {
        JMenu fileMenu = Factory.MenuFactory.create();
        fileMenu.setText("文件");
        bar.add(fileMenu);
//        mFrame.add(fileMenu);
    }
}
