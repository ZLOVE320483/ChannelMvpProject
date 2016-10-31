package com.zlove.channelmvp.model.user;

import com.zlove.channelmvp.api.Api;
import com.zlove.channelmvp.bean.user.UserLoginBean;
import com.zlove.channelmvp.contract.UserLoginContract;
import com.zlove.channelmvp.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/10/28.
 */
public class UserLoginModel implements UserLoginContract.Model {

    @Override
    public Observable<UserLoginBean> login(String userName, String password) {
        return Api.getDefault().login(userName, password)
                .compose(RxSchedulers.<UserLoginBean>io_main());
    }
}
