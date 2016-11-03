package com.zlove.channelmvp.presenter.customer;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.bean.customer.CustomerListBean;
import com.zlove.channelmvp.contract.CustomerListContract;
import com.zlove.channelmvp.rx.RxSubscriber;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class CustomerListPresenter extends CustomerListContract.Presenter {

    @Override
    public void getCustomerList(String keyword, String category_id, String house_type, String property_type, String page_index, String page_size, String status, String is_disabled) {
        mRxManager.add(mModel.getCustomerList(keyword, category_id, house_type, property_type, page_index, page_size, status, is_disabled).subscribe(new RxSubscriber<CustomerListBean>(mContext, false) {

            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }

            @Override
            protected void _onNext(CustomerListBean bean) {
                mView.getCustomerListSuccess(bean);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
