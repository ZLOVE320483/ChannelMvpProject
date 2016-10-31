package com.zlove.channelmvp.contract;

import com.zlove.channelmvp.bean.user.UserLoginBean;
import com.zlove.channelmvp.model.base.BaseModel;
import com.zlove.channelmvp.presenter.base.BasePresenter;
import com.zlove.channelmvp.view.base.BaseView;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/10/28.
 */
public interface UserLoginContract {

    interface Model extends BaseModel {
        Observable<UserLoginBean> login(String userName, String password);
    }

    interface View extends BaseView {
        void loginSuccess(UserLoginBean loginBean);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void login(String userName, String password);
    }
}
