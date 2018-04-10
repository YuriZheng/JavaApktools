package com.zyj.apktools.factory;

import com.zyj.apktools.component.frame.MainFrame;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * CREATED ON: 2018/4/10 16:27
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 界面组件都在这里创建
 */
public abstract class Factory implements ComponentFactory {

    protected Factory() {
    }

    public static class FrameFactory extends Factory {

        @Override public MainFrame createComponent() {
            return new MainFrame();
        }

        public static MainFrame create() {
            return new FrameFactory().createComponent();
        }
    }

    public static class MenuFactory extends Factory {

        @Override public JMenu createComponent() {
            return new JMenu();
        }

        public static JMenu create() {
            return new MenuFactory().createComponent();
        }
    }

    public static class MenuBarFactory extends Factory {

        @Override public JMenuBar createComponent() {
            return new JMenuBar();
        }

        public static JMenuBar create() {
            return new MenuBarFactory().createComponent();
        }
    }

    public static class PanelFactory extends Factory {
        @Override public JPanel createComponent() {
            return new JPanel();
        }

        public static JPanel create() {
            return new PanelFactory().createComponent();
        }
    }

    public static class SplitPanelFactory extends Factory {
        @Override public JSplitPane createComponent() {
            return new JSplitPane();
        }

        public static JSplitPane create() {
            return new SplitPanelFactory().createComponent();
        }
    }

    public static class ButtonFactory extends Factory {
        @Override public JButton createComponent() {
            return new JButton();
        }

        public static JButton create() {
            return new ButtonFactory().createComponent();
        }
    }

}


