package com.zyj.apktools.component;

import com.zyj.apktools.SomeUtils;
import com.zyj.apktools.command.Invoker;
import com.zyj.apktools.command.InvokerCallback;
import com.zyj.apktools.factory.Factory;

import java.awt.event.ActionEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 * CREATED ON: 2018/4/10 16:15
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 主界面的逻辑控制器，以组合的方式控制界面上组件业务逻辑
 */
public final class MainPresenter {

    private final MainFrame mainFrame;
    private final String apk = ".apk";

    private SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd-HH mm:ss");
    private StringBuilder cacheBuilder = new StringBuilder();
    private InvokerCallback callback = (statue, message) -> {
        synchronized (cacheBuilder) {
            cacheBuilder.delete(0, cacheBuilder.length());
            cacheBuilder.append(time.format(new Date()));
            cacheBuilder.append("   ");
            cacheBuilder.append(statue);
            cacheBuilder.append("   ");
            cacheBuilder.append(message);
            final String lineMessage = new String(cacheBuilder.toString());
            SomeUtils.l(lineMessage);
            insert(lineMessage);
        }
    };

    private AbstractAction actionChooseApk = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            getFileChooser(new FileFilter() {
                @Override public boolean accept(File f) {
                    return f.isFile() && f.getAbsolutePath().endsWith(apk);
                }

                @Override public String getDescription() {
                    return apk;
                }
            }, JFileChooser.FILES_ONLY);
        }
    };

    private AbstractAction actionBuildApk = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            getFileChooser(null, JFileChooser.DIRECTORIES_ONLY);
        }
    };

    private AbstractAction actionChoseFile = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            getFileChooser(null, JFileChooser.FILES_AND_DIRECTORIES);
        }
    };

    private AbstractAction actionDecodJar = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            final String fullPath = mainFrame.getPathText();
            if (!fullPath.endsWith(apk)) {
                showDialog("警告", "只能选择apk文件进行反编译，请先选择文件", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Invoker.getInstence().comandDecod(fullPath, callback);
        }
    };

    private AbstractAction actionRebuildJar = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            final String fullPath = mainFrame.getPathText();
            if (!new File(fullPath).isDirectory()) {
                showDialog("警告", "只能选择文件夹进行编译，请先选择文件夹", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Invoker.getInstence().comandBuild(fullPath, callback);
        }
    };

    private AbstractAction actionSign = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            final String fullPath = mainFrame.getPathText();
            if (!fullPath.endsWith(apk)) {
                showDialog("警告", "只能选择apk文件进行签名，请先选择文件", JOptionPane.WARNING_MESSAGE);
                return;
            }
            new SingerInputDialog(mainFrame).setListener(info -> {
                Invoker.getInstence().comandSigner(info.keyPath, info.storePassword, info.keyPassword, fullPath, info.alias, callback);
            }).setVisible(true);
        }
    };

    private AbstractAction actionTextSize = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            System.out.println("5555");
        }
    };

    private AbstractAction actionExit = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            mainFrame.dispose();
        }
    };

    private void insert(String message) {
        mainFrame.insertLog(message);
    }

    private JFileChooser getFileChooser(FileFilter fileFilter, int mode) {
        JFileChooser jfc = Factory.FileChooserFactory.create();
        jfc.setFileSelectionMode(mode);
        if (mainFrame.getPathText().length() > 0) {
            jfc.setCurrentDirectory(new File(mainFrame.getPathText()));
        }
        jfc.setDialogType(JFileChooser.OPEN_DIALOG);
        if (fileFilter != null) {
            jfc.setFileFilter(fileFilter);
        }
        jfc.showDialog(new JLabel(), "选择");

        File file = jfc.getSelectedFile();
        String toastMessage = null;
        if (mode == JFileChooser.DIRECTORIES_ONLY) {
            if (file != null && file.isFile()) {
                toastMessage = "只能选择文件夹，请先选择文件夹";
            }
        } else if (mode == JFileChooser.FILES_ONLY) {
            if (file != null && file.isDirectory()) {
                toastMessage = "只能选择apk文件，请先选择文件";
            }
        }
        if (file != null) {
            if (toastMessage == null) {
                mainFrame.setPathText(file.getAbsolutePath());
            } else {
                mainFrame.setPathText(file.getAbsolutePath());
            }
        }
        return jfc;
    }

    private void showDialog(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(mainFrame, message, title, messageType);
    }

    public MainPresenter(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * 打开apk文件准备进行反编译
     */
    public Action getChooseApkAction() {
        return actionChooseApk;
    }

    /**
     * 重编译一个文件夹为apk
     */
    public Action getBuildApkAction() {
        return actionBuildApk;
    }

    /**
     * 退出
     */
    public Action getExitAction() {
        return actionExit;
    }

    /**
     * 选择一个文件，不论是apk还是文件夹
     */
    public Action getChooseFile() {
        return actionChoseFile;
    }

    /**
     * 反编译
     */
    public Action getDecodJarAction() {
        return actionDecodJar;
    }

    /**
     * 重编译
     */
    public Action getRebuildJarAction() {
        return actionRebuildJar;
    }

    /**
     * 签名
     */
    public Action getSignAction() {
        return actionSign;
    }

    /**
     * 修改日志区域字体大小
     */
    public Action getTextSizeAction() {
        return actionTextSize;
    }

    public void destory() {
        Invoker.getInstence().destory();
        // 有可能不执行
    }

    public static class SignerInfo {
        String keyPath; // 密钥库位置
        String storePassword; // 用于密钥库完整性的口令
        String keyPassword; // 专用密钥的口令（如果不同）
        String alias; // 密钥别名
    }

}
