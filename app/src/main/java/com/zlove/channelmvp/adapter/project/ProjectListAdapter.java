package com.zlove.channelmvp.adapter.project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zlove.channelmvp.bean.project.ProjectItemBean;
import com.zlove.pulltorefreshrecyclerview.adapter.BaseRecyclerViewAdapter;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class ProjectListAdapter extends BaseRecyclerViewAdapter<ProjectItemBean.ProjectItemData.ProjectItemHouseList> {

    public ProjectListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ProjectListViewHolder.create(mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof ProjectListViewHolder) {
            ((ProjectListViewHolder) holder).setData(get(position));
        }
    }
}
