package com.zlove.channelmvp.presenter.project;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.bean.project.ProjectItemBean;
import com.zlove.channelmvp.contract.ProjectListContract;
import com.zlove.channelmvp.rx.RxSubscriber;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class ProjectListPresenter extends ProjectListContract.Presenter {

    @Override
    public void getProjectList(String keyword, String city_id, String house_type, String property_type, String price_min, String price_max, String page_index, String page_size, String area_id) {
        mRxManager.add(mModel.getProjectList(keyword, city_id, house_type, property_type, price_min, price_max, page_index, page_size, area_id).subscribe(new RxSubscriber<ProjectItemBean>(mContext, false) {

            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }

            @Override
            protected void _onNext(ProjectItemBean bean) {
                mView.getProjectListSuccess(bean);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
