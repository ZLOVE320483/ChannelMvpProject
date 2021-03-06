package com.zlove.channelmvp.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.model.base.BaseModel;
import com.zlove.channelmvp.presenter.base.BasePresenter;
import com.zlove.channelmvp.rx.RxManager;
import com.zlove.channelmvp.util.StatusBarCompat;
import com.zlove.channelmvp.util.TUtil;
import com.zlove.channelmvp.util.ToastUtil;

import butterknife.ButterKnife;

/**
 * Created by ZLOVE on 2016/10/28.
 */
public abstract class BaseFragment<P extends BasePresenter, M extends BaseModel> extends Fragment {

    protected View mRootView;
    protected P mPresenter;
    protected M mModel;
    protected RxManager mRxManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutResource(), container, false);
        }
        mRxManager = new RxManager();
        ButterKnife.bind(this, mRootView);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = getActivity();
        }
        initPresenter();
        initView();
        return mRootView;
    }

    protected abstract int getLayoutResource();
    protected abstract void initPresenter();
    protected abstract void initView();

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void setStatusBarColor() {
        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.common_bg_top_bar));
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void setTranslateBar() {
        StatusBarCompat.translucentStatusBar(getActivity());
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUtil.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUtil.showShort(resId);
    }

}
