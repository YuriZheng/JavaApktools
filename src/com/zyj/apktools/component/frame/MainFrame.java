package com.zyj.apktools.component.frame;

import com.zyj.apktools.component.menu.MenuPanel;
import com.zyj.apktools.component.menu.MenuPresenter;
import com.zyj.apktools.factory.Factory;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * CREATED ON: 2018/4/10 17:24
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: NON
 */
public final class MainFrame extends JFrame {

    private JTextArea logPanel;
    private JTextField pathInput;

    private final MainPresenter presenter;

    public MainFrame() throws HeadlessException {
        presenter = new MainPresenter(this);
    }

    public void addSubComponent() {
        JPanel mainPanel = Factory.PanelFactory.create();
        mainPanel.setLayout(new CardLayout());
        setContentPane(mainPanel);

        addMenu(this);

        addSplitPane(mainPanel);

        addComponentListener(new ComponentAdapter() {
            @Override public void componentResized(ComponentEvent e) {
                setPathInputLength();
            }
        });
    }

    public void setMenuPresenter(MenuPresenter presenter) {
        this.presenter.setMenuPresenter(presenter);
    }


    // 创建菜单栏
    private void addMenu(MainFrame mainFrame) {
        JMenuBar menubar = Factory.MenuBarFactory.create();
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.buildMenu(menubar);
        mainFrame.setMenuPresenter(menuPanel.getMenuPresenter());

        mainFrame.setJMenuBar(menubar);
    }

    /**
     * 添加可拖动的分割窗体
     */
    private void addSplitPane(JPanel mainPanel) {
        JSplitPane vSplitPane = getJSplitPane();
        vSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        vSplitPane.setContinuousLayout(true);
        vSplitPane.setDividerSize(1);

        vSplitPane.setTopComponent(getTopPanel());
        vSplitPane.setBottomComponent(getBottomPanel());

        mainPanel.add(vSplitPane, BorderLayout.CENTER);
    }

    private JSplitPane getJSplitPane() {
        JSplitPane splitPane = Factory.SplitPanelFactory.create();

        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(10);

        splitPane.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals(JSplitPane.DIVIDER_LOCATION_PROPERTY)) {
                setPathInputHeight();
            }
        });

        return splitPane;
    }

    private void setPathInputLength() {
        JPanel topPanel = (JPanel) pathInput.getClientProperty("parent");
        pathInput.setColumns(getSize().width * 2 / 3 / pathInput.getFont().getSize());
        topPanel.revalidate();
    }

    private void setPathInputHeight() {
        JPanel topPanel = (JPanel) pathInput.getClientProperty("parent");
        ((FlowLayout) topPanel.getLayout()).setVgap(topPanel.getHeight() / 2 - 15);
    }

    private JPanel getTopPanel() {
        final int minHeight = 80;
        JPanel topPanel = Factory.PanelFactory.create();
        topPanel.setBackground(Color.decode("#000000"));
        topPanel.setMinimumSize(new Dimension(-1, minHeight));
        ((FlowLayout) topPanel.getLayout()).setHgap(30);
        ((FlowLayout) topPanel.getLayout()).setVgap(minHeight / 2 - 15);

        pathInput = Factory.TextFieldFactory.create();
        pathInput.putClientProperty("parent", topPanel);
        pathInput.setEditable(false);
        pathInput.setText("选择文件");
        setPathInputLength();

        JButton choose = Factory.ButtonFactory.create();
        choose.setText("选择");

        topPanel.add(pathInput);
        topPanel.add(choose);

        return topPanel;
    }

    private JTextArea getBottomPanel() {
        logPanel = Factory.TextAreaFactory.create();
        logPanel.setBorder(new EmptyBorder(10, 5, 10, 5));

        logPanel.setOpaque(true);
        logPanel.setBackground(Color.decode("#000000"));
        logPanel.setForeground(Color.decode("#FFFFFF"));

        logPanel.setEditable(false);

        logPanel.setMinimumSize(new Dimension(-1, getSize().height >> 2));

        logPanel.setText("111");

        return logPanel;
    }
}
