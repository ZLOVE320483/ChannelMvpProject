package com.zlove.channelmvp.contract;

import com.zlove.channelmvp.bean.project.ProjectItemBean;
import com.zlove.channelmvp.model.base.BaseModel;
import com.zlove.channelmvp.presenter.base.BasePresenter;
import com.zlove.channelmvp.view.base.BaseView;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public interface ProjectListContract {

    interface Model extends BaseModel {
        Observable<ProjectItemBean> getProjectList(String keyword, String city_id, String house_type,
                                                   String property_type, String price_min, String price_max,
                                                   String page_index, String page_size, String area_id);
    }

    interface View extends BaseView {
        void getProjectListSuccess(ProjectItemBean bean);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getProjectList(String keyword, String city_id, String house_type,
                                            String property_type, String price_min, String price_max,
                                            String page_index, String page_size, String area_id);
    }
}
