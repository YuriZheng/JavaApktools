package com.zyj.apktools.component.frame;

import com.zyj.apktools.Log;
import com.zyj.apktools.component.frame.MainFrame;
import com.zyj.apktools.component.menu.MenuPanel;
import com.zyj.apktools.factory.Factory;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
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
        MainFrame mainFrame = buildFrameInformation();

        JPanel mainPanel = Factory.PanelFactory.create();
        mainPanel.setLayout(new CardLayout());
        mainFrame.setContentPane(mainPanel);

        JMenuBar menubar = addMenu();
        mainFrame.setJMenuBar(menubar);

        addSplitPane(mainPanel);
        return mainFrame;
    }

    // 创建窗口
    private MainFrame buildFrameInformation() {
        MainFrame frame = Factory.FrameFactory.create();

        frame.setTitle(FrameInfo.FRAME_TITLE);
        frame.setMinimumSize(FrameInfo.FRAME_MIN_SIZE);
        frame.setResizable(FrameInfo.FRAME_RESIZEABLE);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        return frame;
    }

    // 创建菜单栏
    private JMenuBar addMenu() {
        JMenuBar menubar = Factory.MenuBarFactory.create();
        new MenuPanel().buildMenu(menubar);
        return menubar;
    }

    /**
     * 添加可拖动的分割窗体
     */
    private void addSplitPane(JPanel mainPanel) {
        JSplitPane vSplitPane = getJSplitPane();
        vSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

        vSplitPane.setBackground(Color.RED);

        vSplitPane.setLeftComponent(new JLabel("    1"));

        mainPanel.add(vSplitPane, BorderLayout.CENTER);

//        JSplitPane vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
//        vSplitPane.setBackground(Color.BLUE);
//        vSplitPane.setLeftComponent(new JLabel("    2"));
//        vSplitPane.setRightComponent(new JLabel("    3"));
//        vSplitPane.setDividerLocation(30);
//        vSplitPane.setDividerSize(8);
//        vSplitPane.setOneTouchExpandable(true);
//        vSplitPane.setContinuousLayout(true);
//        hSplitPane.setRightComponent(vSplitPane);

    }

    private JSplitPane getJSplitPane() {
        JSplitPane splitPane = Factory.SplitPanelFactory.create();
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(1);
        return splitPane;
    }



    private static class FrameInfo {
        /**
         * 窗口标题
         */
        static String FRAME_TITLE = "Apktool工具";

        /**
         * 窗体大小
         */
        static Dimension FRAME_MIN_SIZE = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width >> 1,
                Toolkit.getDefaultToolkit().getScreenSize().height >> 1);

        /**
         * 窗体是否可改变大小
         */
        static boolean FRAME_RESIZEABLE = true;
    }

}
