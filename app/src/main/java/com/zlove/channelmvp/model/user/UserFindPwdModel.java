package com.zlove.channelmvp.model.user;

import com.zlove.channelmvp.api.Api;
import com.zlove.channelmvp.bean.CommonBean;
import com.zlove.channelmvp.bean.user.UserVerifyCodeBean;
import com.zlove.channelmvp.contract.UserFindPwdContract;
import com.zlove.channelmvp.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class UserFindPwdModel implements UserFindPwdContract.Model {

    @Override
    public Observable<UserVerifyCodeBean> getFindPwdVerCode(String phone) {
        return Api.getDefault().getFindPwdVerCode(phone).compose(RxSchedulers.<UserVerifyCodeBean>io_main());
    }

    @Override
    public Observable<CommonBean> checkPhoneCode(String phone, String code) {
        return Api.getDefault().checkPhoneCode(phone, code).compose(RxSchedulers.<CommonBean>io_main());
    }

    @Override
    public Observable<CommonBean> updateNewPassword(String phone, String new_password) {
        return Api.getDefault().updateNewPassword(phone, new_password).compose(RxSchedulers.<CommonBean>io_main());
    }
}
