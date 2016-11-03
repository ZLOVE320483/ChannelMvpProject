package com.zlove.channelmvp.presenter.friend;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.bean.friend.FriendListBean;
import com.zlove.channelmvp.contract.FriendListContract;
import com.zlove.channelmvp.rx.RxSubscriber;

/**
 * Created by ZLOVE on 2016/11/3.
 */
public class FriendListPresenter extends FriendListContract.Presenter {

    @Override
    public void getFriendList(String keyword, String page_index, String page_size) {
        mRxManager.add(mModel.getFriendList(keyword, page_index, page_size).subscribe(new RxSubscriber<FriendListBean>(mContext, false) {

            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }

            @Override
            protected void _onNext(FriendListBean bean) {
                mView.getFriendListSuccess(bean);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
