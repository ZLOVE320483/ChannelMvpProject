package com.zlove.channelmvp.adapter.customer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zlove.channelmvp.bean.customer.CustomerListBean;
import com.zlove.pulltorefreshrecyclerview.adapter.BaseRecyclerViewAdapter;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class CustomerListAdapter extends BaseRecyclerViewAdapter<CustomerListBean.CustomerListData.CustomerListItem> {

    public CustomerListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CustomerListViewHolder.create(mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof CustomerListViewHolder) {
            ((CustomerListViewHolder) holder).setData(get(position));
        }
    }
}
