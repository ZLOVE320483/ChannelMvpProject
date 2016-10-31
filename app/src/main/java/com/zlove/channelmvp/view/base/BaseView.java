package com.zlove.channelmvp.view.base;

/**
 * Created by ZLOVE on 2016/10/28.
 */
public interface BaseView {
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
