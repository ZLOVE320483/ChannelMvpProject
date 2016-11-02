package com.zlove.channelmvp.model.project;

import com.zlove.channelmvp.api.Api;
import com.zlove.channelmvp.bean.project.ProjectItemBean;
import com.zlove.channelmvp.contract.ProjectListContract;
import com.zlove.channelmvp.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class ProjectListModel implements ProjectListContract.Model {
    @Override
    public Observable<ProjectItemBean> getProjectList(String keyword, String city_id, String house_type, String property_type, String price_min, String price_max, String page_index, String page_size, String area_id) {
        return Api.getDefault().getProjectList(keyword, city_id, house_type, property_type, price_min, price_max, page_index, page_size, area_id).compose(RxSchedulers.<ProjectItemBean>io_main());
    }
}
