package com.zlove.channelmvp.presenter.user;

import com.zlove.channelmvp.bean.CommonBean;
import com.zlove.channelmvp.bean.user.UserVerifyCodeBean;
import com.zlove.channelmvp.contract.UserFindPwdContract;
import com.zlove.channelmvp.rx.RxSubscriber;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class UserFindPwdPresenter extends UserFindPwdContract.Presenter {

    @Override
    public void getFindPwdVerCode(String phone) {
        mRxManager.add(mModel.getFindPwdVerCode(phone).subscribe(new RxSubscriber<UserVerifyCodeBean>(mContext, true) {
            @Override
            protected void _onNext(UserVerifyCodeBean bean) {
                mView.getFindPwdVerCodeSuccess(bean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void checkPhoneCode(String phone, String code) {
        mRxManager.add(mModel.checkPhoneCode(phone, code).subscribe(new RxSubscriber<CommonBean>(mContext, true) {

            @Override
            protected void _onNext(CommonBean bean) {
                mView.checkPhoneCodeSuccess(bean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void updateNewPassword(String phone, String new_password) {
        mRxManager.add(mModel.updateNewPassword(phone, new_password).subscribe(new RxSubscriber<CommonBean>(mContext, true) {
            @Override
            protected void _onNext(CommonBean bean) {
                mView.updateNewPasswordSuccess(bean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
