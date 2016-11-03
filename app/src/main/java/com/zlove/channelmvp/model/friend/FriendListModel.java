package com.zlove.channelmvp.model.friend;

import com.zlove.channelmvp.api.Api;
import com.zlove.channelmvp.bean.friend.FriendListBean;
import com.zlove.channelmvp.contract.FriendListContract;
import com.zlove.channelmvp.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/11/3.
 */
public class FriendListModel implements FriendListContract.Model {
    @Override
    public Observable<FriendListBean> getFriendList(String keyword, String page_index, String page_size) {
        return Api.getDefault().getFriendList(keyword, page_index, page_size).compose(RxSchedulers.<FriendListBean>io_main());
    }
}
