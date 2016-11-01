package com.zlove.channelmvp.presenter.user;

import com.zlove.channelmvp.bean.CommonBean;
import com.zlove.channelmvp.bean.user.UserVerifyCodeBean;
import com.zlove.channelmvp.contract.UserRegisterContract;
import com.zlove.channelmvp.rx.RxSubscriber;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class UserRegisterPresenter extends UserRegisterContract.Presenter {

    @Override
    public void getVerifyCode(String phone) {
        mRxManager.add(mModel.getVerifyCode(phone).subscribe(new RxSubscriber<UserVerifyCodeBean>(mContext, true) {

            @Override
            protected void _onNext(UserVerifyCodeBean bean) {
                mView.getVerifyCodeSuccess(bean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void userRegister(String phone, String password, String code, String realName) {
        mRxManager.add(mModel.userRegister(phone, password, code, realName).subscribe(new RxSubscriber<CommonBean>(mContext, true) {
            @Override
            protected void _onNext(CommonBean bean) {
                mView.registerSuccess(bean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
