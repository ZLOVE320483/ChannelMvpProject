package com.zlove.channelmvp.model.message;

import com.zlove.channelmvp.api.Api;
import com.zlove.channelmvp.bean.message.MessageHomeBean;
import com.zlove.channelmvp.contract.MessageHomeContract;
import com.zlove.channelmvp.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class MessageHomeModel implements MessageHomeContract.Model {

    @Override
    public Observable<MessageHomeBean> getMessageHomeUnReadCount() {
        return Api.getDefault().getMessageHomeUnReadCount().compose(RxSchedulers.<MessageHomeBean>io_main());
    }
}
