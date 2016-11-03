package com.zlove.channelmvp.contract;

import com.zlove.channelmvp.bean.friend.FriendListBean;
import com.zlove.channelmvp.model.base.BaseModel;
import com.zlove.channelmvp.presenter.base.BasePresenter;
import com.zlove.channelmvp.view.base.BaseView;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/11/3.
 */
public interface FriendListContract {

    interface Model extends BaseModel {
        Observable<FriendListBean> getFriendList(String keyword, String page_index, String page_size);
    }

    interface View extends BaseView {
        void getFriendListSuccess(FriendListBean bean);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getFriendList(String keyword, String page_index, String page_size);
    }
}
