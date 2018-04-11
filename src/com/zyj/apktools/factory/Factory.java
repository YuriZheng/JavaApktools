package com.zyj.apktools.factory;

import com.zyj.apktools.component.MainFrame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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

    public static class MenuItemFactory extends Factory {

        @Override public JMenuItem createComponent() {
            return new JMenuItem();
        }

        public static JMenuItem create() {
            return new MenuItemFactory().createComponent();
        }
    }

    public static class LabelFactory extends Factory {

        @Override public JLabel createComponent() {
            return new JLabel();
        }

        public static JLabel create() {
            return new LabelFactory().createComponent();
        }
    }

    public static class TextAreaFactory extends Factory {
        @Override public JTextArea createComponent() {
            return new JTextArea();
        }

        public static JTextArea create() {
            return new TextAreaFactory().createComponent();
        }
    }

    public static class TextFieldFactory extends Factory {

        @Override public JTextField createComponent() {
            return new JTextField();
        }

        public static JTextField create() {
            return new TextFieldFactory().createComponent();
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


