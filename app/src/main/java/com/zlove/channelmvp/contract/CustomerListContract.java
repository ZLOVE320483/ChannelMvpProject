package com.zlove.channelmvp.contract;

import com.zlove.channelmvp.bean.customer.CustomerListBean;
import com.zlove.channelmvp.model.base.BaseModel;
import com.zlove.channelmvp.presenter.base.BasePresenter;
import com.zlove.channelmvp.view.base.BaseView;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public interface CustomerListContract {

    interface Model extends BaseModel {
        Observable<CustomerListBean> getCustomerList(String keyword, String category_id, String house_type,
                                                     String property_type, String page_index, String page_size,
                                                     String status, String is_disabled);
    }

    interface View extends BaseView {
        void getCustomerListSuccess(CustomerListBean bean);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getCustomerList(String keyword, String category_id, String house_type,
                                      String property_type, String page_index, String page_size,
                                      String status, String is_disabled);
    }

}
