package com.zlove.channelmvp.presenter.user;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.bean.user.UserLoginBean;
import com.zlove.channelmvp.contract.UserLoginContract;
import com.zlove.channelmvp.rx.RxSubscriber;

/**
 * Created by ZLOVE on 2016/10/28.
 */
public class UserLoginPresenter extends UserLoginContract.Presenter {

    @Override
    public void login(String userName, String password) {
//        Log.d("ZLOVE", "UserLoginPresenter---login---userName---" + userName + "---password---" + password);

//        mModel.login(userName, password).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<UserLoginBean>() {
//            @Override
//            public void onCompleted() {
//                Log.d("ZLOVE", "UserLoginPresenter---onCompleted---");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("ZLOVE", "UserLoginPresenter---onError---Throwable---" + e.getMessage());
//            }
//
//            @Override
//            public void onNext(UserLoginBean loginBean) {
//                Log.d("ZLOVE", "UserLoginPresenter---onNext---loginBean---" + loginBean);
//                mView.loginSuccess(loginBean);
//            }
//        });

        mRxManager.add(mModel.login(userName, password).subscribe(new RxSubscriber<UserLoginBean>(mContext, true) {

            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }

            @Override
            protected void _onNext(UserLoginBean loginBean) {
                mView.loginSuccess(loginBean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
