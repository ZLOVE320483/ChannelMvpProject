package com.zlove.channelmvp.presenter.message;

import com.zlove.channelmvp.bean.message.MessageHomeBean;
import com.zlove.channelmvp.contract.MessageHomeContract;
import com.zlove.channelmvp.rx.RxSubscriber;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class MessageHomePresenter extends MessageHomeContract.Presenter {

    @Override
    public void getMessageHomeUnReadCount() {
        mRxManager.add(mModel.getMessageHomeUnReadCount().subscribe(new RxSubscriber<MessageHomeBean>(mContext, true) {

            @Override
            protected void _onNext(MessageHomeBean bean) {
                mView.getMessageHomeUnReadCountSuccess(bean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
