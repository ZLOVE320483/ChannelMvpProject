package com.zlove.channelmvp.adapter.friend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zlove.channelmvp.bean.friend.FriendListBean;
import com.zlove.pulltorefreshrecyclerview.adapter.BaseRecyclerViewAdapter;

/**
 * Created by ZLOVE on 2016/11/3.
 */
public class FriendListAdapter extends BaseRecyclerViewAdapter<FriendListBean.FriendListData.FriendListItem> {

    public FriendListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return FriendListViewHolder.create(mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FriendListViewHolder) {
            ((FriendListViewHolder) holder).setData(get(position));
        }
    }
}
