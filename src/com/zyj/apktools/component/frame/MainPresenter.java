package com.zyj.apktools.component.frame;

import com.zyj.apktools.component.menu.MenuPresenter;

/**
 * CREATED ON: 2018/4/10 16:15
 * <p>
 * Author: Yuri.zheng<br>
 * Email: 497393102@qq.com<br>
 * Description: 主界面的逻辑控制器，以组合的方式控制界面上组件业务逻辑
 */
public final class MainPresenter {

    private final MainFrame mainFrame;

    private MenuPresenter menuPresenter;

    public MainPresenter(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setMenuPresenter(MenuPresenter presenter) {
        menuPresenter = presenter;
    }

}
