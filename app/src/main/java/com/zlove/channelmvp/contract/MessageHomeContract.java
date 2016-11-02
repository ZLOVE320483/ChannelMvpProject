package com.zlove.channelmvp.contract;

import com.zlove.channelmvp.bean.message.MessageHomeBean;
import com.zlove.channelmvp.model.base.BaseModel;
import com.zlove.channelmvp.presenter.base.BasePresenter;
import com.zlove.channelmvp.view.base.BaseView;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public interface MessageHomeContract {

    interface Model extends BaseModel {
        Observable<MessageHomeBean> getMessageHomeUnReadCount();
    }

    interface View extends BaseView {
        void getMessageHomeUnReadCountSuccess(MessageHomeBean bean);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getMessageHomeUnReadCount();
    }
}
