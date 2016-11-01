package com.zlove.channelmvp.contract;

import com.zlove.channelmvp.bean.CommonBean;
import com.zlove.channelmvp.bean.user.UserVerifyCodeBean;
import com.zlove.channelmvp.model.base.BaseModel;
import com.zlove.channelmvp.presenter.base.BasePresenter;
import com.zlove.channelmvp.view.base.BaseView;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public interface UserFindPwdContract {

    interface Model extends BaseModel {
        Observable<UserVerifyCodeBean> getFindPwdVerCode(String phone);
        Observable<CommonBean> checkPhoneCode(String phone, String code);
        Observable<CommonBean> updateNewPassword(String phone, String new_password);
    }

    interface View extends BaseView {
        void getFindPwdVerCodeSuccess(UserVerifyCodeBean bean);
        void checkPhoneCodeSuccess(CommonBean bean);
        void updateNewPasswordSuccess(CommonBean bean);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getFindPwdVerCode(String phone);
        public abstract void checkPhoneCode(String phone, String code);
        public abstract void updateNewPassword(String phone, String new_password);
    }
}
