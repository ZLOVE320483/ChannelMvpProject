package com.zlove.channelmvp.contract;

import com.zlove.channelmvp.bean.CommonBean;
import com.zlove.channelmvp.bean.user.UserVerifyCodeBean;
import com.zlove.channelmvp.model.base.BaseModel;
import com.zlove.channelmvp.presenter.base.BasePresenter;
import com.zlove.channelmvp.view.base.BaseView;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/10/31.
 */
public interface UserRegisterContract {

    interface Model extends BaseModel {
        Observable<UserVerifyCodeBean> getVerifyCode(String phone);
        Observable<CommonBean> userRegister(String phone, String password, String code, String realName);
    }

    interface View extends BaseView {
        void getVerifyCodeSuccess(UserVerifyCodeBean bean);
        void registerSuccess(CommonBean bean);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getVerifyCode(String phone);
        public abstract void userRegister(String phone, String password, String code, String realName);
    }
}
