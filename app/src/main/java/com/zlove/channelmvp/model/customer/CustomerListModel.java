package com.zlove.channelmvp.model.customer;

import com.zlove.channelmvp.api.Api;
import com.zlove.channelmvp.bean.customer.CustomerListBean;
import com.zlove.channelmvp.contract.CustomerListContract;
import com.zlove.channelmvp.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class CustomerListModel implements CustomerListContract.Model {

    @Override
    public Observable<CustomerListBean> getCustomerList(String keyword, String category_id, String house_type, String property_type, String page_index, String page_size, String status, String is_disabled) {
        return Api.getDefault().getCustomerList(keyword, category_id, house_type, property_type, page_index, page_size, status, is_disabled).compose(RxSchedulers.<CustomerListBean>io_main());
    }
}
