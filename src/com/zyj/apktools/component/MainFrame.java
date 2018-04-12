package com.zyj.apktools.component;

import com.zyj.apktools.factory.Factory;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.border.EmptyBorder;

/**
 * CREATED ON: 2018/4/10 17:24
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: NON
 */
public final class MainFrame extends JFrame {

    private DefaultListModel<String> listModel;
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
                setPathInputLength(pathInput);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                listModel.clear();
                presenter.destory();
            }
        });
    }

    public void setPathText(String path) {
        pathInput.setText(path);
    }

    public String getPathText() {
        return pathInput.getText();
    }

    public void insertLog(String log) {
        synchronized (listModel) {
            listModel.addElement(log);
        }
    }

    // 创建菜单栏
    private void addMenu(MainFrame mainFrame) {
        JMenuBar menubar = Factory.MenuBarFactory.create();
        MenuPanel menuPanel = new MenuPanel(presenter);
        menuPanel.buildMenu(menubar);

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

    private void setPathInputLength(JTextField field) {
        JPanel topPanel = (JPanel) field.getClientProperty("parent");
        field.setColumns(getSize().width * 2 / 3 / field.getFont().getSize());
        topPanel.revalidate();
    }

    private void setMyTransferHandler(JTextField field) {
        field.setTransferHandler(new TransferHandler() {

            @Override public boolean importData(JComponent comp, Transferable t) {
                try {
                    Object o = t.getTransferData(DataFlavor.javaFileListFlavor);
                    String filepath = o.toString();
                    if (filepath.startsWith("[")) {
                        filepath = filepath.substring(1);
                    }
                    if (filepath.endsWith("]")) {
                        filepath = filepath.substring(0, filepath.length() - 1);
                    }
                    field.setText(filepath);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
                for (int i = 0; i < transferFlavors.length; i++) {
                    if (DataFlavor.javaFileListFlavor.equals(transferFlavors[i])) {
                        return true;
                    }
                }
                return false;
            }
        });
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
        setMyTransferHandler(pathInput);
        setPathInputLength(pathInput);

        JButton choose = Factory.ButtonFactory.create();
        choose.setAction(presenter.getChooseFile());
        choose.setText("选择");

        topPanel.add(pathInput);
        topPanel.add(choose);

        return topPanel;
    }

    private Component getBottomPanel() {
        final Color bColor = Color.decode("#000000");
        final Color fColor = Color.decode("#FFFFFF");
        JScrollPane scrollPane = Factory.ScrollPaneFactory.create();
        JList<String> logPanel = Factory.ListFactory.create();
        listModel = new DefaultListModel();
        logPanel.setModel(listModel);
        scrollPane.setViewportView(logPanel);

        logPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
        logPanel.setOpaque(true);
        logPanel.setBackground(bColor);
        logPanel.setForeground(fColor);
        logPanel.setMinimumSize(new Dimension(-1, getSize().height >> 2));

        logPanel.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JTextField item = Factory.TextFieldFactory.create();
            item.setBorder(null);
            item.setText(value);
            item.setBackground(bColor);
            item.setForeground(fColor);
            return item;
        });
        return scrollPane;
    }
}
