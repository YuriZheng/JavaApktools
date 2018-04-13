package com.zyj.apktools.component;

import com.zyj.apktools.factory.Factory;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.EventListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * CREATED ON: 2018/4/13 13:42
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: NON
 */
class SingerInputDialog extends JDialog {

    private JTextField keyPath; // 密钥库位置
    private JPasswordField storePassword; // 用于密钥库完整性的口令
    private JPasswordField keyPassword; // 专用密钥的口令（如果不同）
    private JTextField alias; // 密钥别名

    private SingerInputListener listener;

    public SingerInputDialog(Component parent) {
        super();
        setTitle("请输入签名参数");
        setModal(true);
        setSize(parent.getSize().width >> 1, parent.getSize().height >> 1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        add(addChildComponent(new JPanel(null)));
        add(addConfirmComponent());

        setMyTransferHandler(keyPath, storePassword, keyPassword, alias);
    }

    public SingerInputDialog setListener(SingerInputListener listener) {
        this.listener = listener;
        return this;
    }

    private JPanel addChildComponent(JPanel jp) {

        final int margePadding = 40;
        final int lPadding = 30;
        final int tPadding = 30;
        final int lable_width = getSize().width * 1 / 4;
        final int input_width = getSize().width * 3 / 4 - lPadding * 2;
        final int c_height = Factory.LabelFactory.create().getFont().getSize() + 10;

        jp.setBounds(0, 0, getSize().width, (c_height + tPadding) * 4 - tPadding);

        JLabel l1 = getLabel("密钥库位置：", lable_width, c_height);
        l1.setLocation(0, tPadding);
        jp.add(l1);
        keyPath = Factory.TextFieldFactory.create();
        keyPath.setBounds(l1.getLocation().x + l1.getWidth() + lPadding, tPadding, input_width, c_height);
        jp.add(keyPath);

        JLabel l2 = getLabel("密钥库密码：", lable_width, c_height);
        l2.setLocation(0, l1.getLocation().y + margePadding);
        jp.add(l2);
        storePassword = Factory.PasswordFieldFactory.create();
        storePassword.setBounds(keyPath.getLocation().x, l2.getLocation().y, input_width, c_height);
        jp.add(storePassword);

        JLabel l3 = getLabel("专用密码(可选)：", lable_width, c_height);
        l3.setLocation(0, l2.getLocation().y + margePadding);
        jp.add(l3);
        keyPassword = Factory.PasswordFieldFactory.create();
        keyPassword.setBounds(storePassword.getLocation().x, l3.getLocation().y, input_width, c_height);
        jp.add(keyPassword);

        JLabel l4 = getLabel("密钥别名：", lable_width, c_height);
        l4.setLocation(0, l3.getLocation().y + margePadding);
        jp.add(l4);
        alias = Factory.TextFieldFactory.create();
        alias.setBounds(keyPassword.getLocation().x, l4.getLocation().y, input_width, c_height);
        jp.add(alias);

        return jp;
    }

    private JLabel getLabel(String title, int w, int h) {
        JLabel label = Factory.LabelFactory.create();
        label.setText(title);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setSize(w, h);
        return label;
    }

    private JButton addConfirmComponent() {
        JButton confirm = Factory.ButtonFactory.create();
        final int w = 200;
        final int top = 190;
        confirm.setBounds(getWidth() / 2 - w / 2, top, w, 40);
        confirm.setAction(new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (!new File(keyPath.getText()).isFile()) {
                    showDialog("错误", "密钥必须是文件", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                final String storePass = String.valueOf(storePassword.getPassword());
                if (storePass == null || storePass.length() < 1) {
                    showDialog("错误", "必须输入密钥文件密码", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                final String aliasName = alias.getText();
                if (aliasName == null || aliasName.length() < 1) {
                    showDialog("错误", "密钥文件别名必须输入", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (listener != null) {
                    MainPresenter.SignerInfo info = new MainPresenter.SignerInfo();
                    info.alias = aliasName;
                    info.storePassword = storePass;
                    info.keyPath = keyPath.getText();
                    final String keyPass = String.valueOf(keyPassword.getPassword());
                    if (keyPass == null || keyPass.length() < 1) {
                        info.keyPassword = info.storePassword;
                    }
                    listener.handlerCallback(info);
                }

                setVisible(false);
                dispose();
            }
        });
        confirm.setText("确定");
        return confirm;
    }

    private void setMyTransferHandler(JTextField... fields) {
        for (JTextField field : fields) {
            field.setTransferHandler(new MainFrame.DragTransferHandler(field));
        }
    }

    private void showDialog(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    public interface SingerInputListener extends EventListener {

        void handlerCallback(MainPresenter.SignerInfo info);

    }

}
