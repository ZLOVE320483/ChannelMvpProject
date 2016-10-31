package com.zlove.channelmvp.presenter.base;

import android.content.Context;

import com.zlove.channelmvp.rx.RxManager;

/**
 * Created by ZLOVE on 2016/10/28.
 */
public abstract class BasePresenter<V, M> {
    public Context mContext;
    public V mView;
    public M mModel;
    public RxManager mRxManager = new RxManager();

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onStart(){}

    public void onDestroy() {
        mRxManager.clear();
    }

}
