package com.zlove.channelmvp.act;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.app.AppManager;
import com.zlove.channelmvp.model.base.BaseModel;
import com.zlove.channelmvp.presenter.base.BasePresenter;
import com.zlove.channelmvp.rx.RxManager;
import com.zlove.channelmvp.util.StatusBarCompat;
import com.zlove.channelmvp.util.TUtil;

import butterknife.ButterKnife;

/**
 * Created by ZLOVE on 2016/10/31.
 */
public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity {

    public P mPresenter;
    public M mModel;
    public Context mContext;
    public RxManager mRxManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager = new RxManager();
        doBeforeSetContentView();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        this.initPresent();
        this.initView();
    }

    private void doBeforeSetContentView() {
        AppManager.getAppManager().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setStatusBarColor();
    }

    protected abstract int getLayoutId();
    public abstract void initPresent();
    public abstract void initView();

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void setStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.common_bg_top_bar));
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void setTranslateBar() {
        StatusBarCompat.translucentStatusBar(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        mRxManager.clear();
        ButterKnife.unbind(this);
        AppManager.getAppManager().finishActivity(this);
    }
}
