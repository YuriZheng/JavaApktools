package com.zyj.apktools.component;

import com.zyj.apktools.factory.Factory;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * CREATED ON: 2018/4/10 17:34
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: NON
 */
public final class MenuPanel {

    private final MainPresenter presenter;

    public MenuPanel(MainPresenter p) {
        presenter = p;
    }

    /**
     * 需要一个容器
     */
    public void buildMenu(JMenuBar bar) {
        bar.add(buildFileMenu(Factory.MenuFactory.create()));
        bar.add(buildActionMenu(Factory.MenuFactory.create()));
        bar.add(buildSettingMenu(Factory.MenuFactory.create()));
    }

    private JMenu buildFileMenu(JMenu fileMenu) {
        fileMenu.setText("文件");
        JMenuItem openItem = Factory.MenuItemFactory.create();
        openItem.setAction(presenter.getOpenFileAction());
        openItem.setText("打开");

        fileMenu.add(openItem);
        return fileMenu;
    }

    private JMenu buildActionMenu(JMenu fileMenu) {
        fileMenu.setText("操作");

        JMenuItem decodItem = Factory.MenuItemFactory.create();
        decodItem.setAction(presenter.getDecodJarAction());
        decodItem.setText("反编译");

        JMenuItem reBuildItem = Factory.MenuItemFactory.create();
        reBuildItem.setAction(presenter.getRebuildJarAction());
        reBuildItem.setText("重编译");

        JMenuItem signItem = Factory.MenuItemFactory.create();
        signItem.setAction(presenter.getSignAction());
        signItem.setText("重签名");

        fileMenu.add(decodItem);
        fileMenu.add(reBuildItem);
        fileMenu.add(signItem);
        return fileMenu;
    }

    private JMenu buildSettingMenu(JMenu fileMenu) {
        fileMenu.setText("设置");

        JMenuItem textSizeItem = Factory.MenuItemFactory.create();
        textSizeItem.setAction(presenter.getTextSizeAction());
        textSizeItem.setText("字体大小");

        fileMenu.add(textSizeItem);
        return fileMenu;
    }
}
