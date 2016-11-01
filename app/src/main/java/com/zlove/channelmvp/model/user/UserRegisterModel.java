package com.zlove.channelmvp.model.user;

import com.zlove.channelmvp.api.Api;
import com.zlove.channelmvp.bean.CommonBean;
import com.zlove.channelmvp.bean.user.UserVerifyCodeBean;
import com.zlove.channelmvp.contract.UserRegisterContract;
import com.zlove.channelmvp.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class UserRegisterModel implements UserRegisterContract.Model {

    @Override
    public Observable<UserVerifyCodeBean> getVerifyCode(String phone) {
        return Api.getDefault().getVerifyCode(phone).compose(RxSchedulers.<UserVerifyCodeBean>io_main());
    }

    @Override
    public Observable<CommonBean> userRegister(String phone, String password, String code, String realName) {
        return Api.getDefault().userRegister(phone, password, code, realName).compose(RxSchedulers.<CommonBean>io_main());
    }
}
