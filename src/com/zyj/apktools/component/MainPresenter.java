package com.zyj.apktools.component;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * CREATED ON: 2018/4/10 16:15
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 主界面的逻辑控制器，以组合的方式控制界面上组件业务逻辑
 */
public final class MainPresenter {

    private final MainFrame mainFrame;

    private AbstractAction actionOpenFile = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            System.out.println("1111111111");
        }
    };

    private AbstractAction actionDecodJar = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            System.out.println("222222");
        }
    };

    private AbstractAction actionRebuildJar = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            System.out.println("333333");
        }
    };

    private AbstractAction actionSign = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            System.out.println("4444");
        }
    };

    private AbstractAction actionTextSize = new AbstractAction() {
        @Override public void actionPerformed(ActionEvent e) {
            System.out.println("5555");
        }
    };


    public MainPresenter(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }


    /**
     * 打开文件
     */
    public Action getOpenFileAction() {
        return actionOpenFile;
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

}
