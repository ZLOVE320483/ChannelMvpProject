package com.zlove.channelmvp.adapter.friend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.bean.friend.FriendListBean;

/**
 * Created by ZLOVE on 2016/11/3.
 */
public class FriendListViewHolder extends RecyclerView.ViewHolder {

    private TextView tvFriendName;
    private TextView tvFriendPhone;
    private ImageView ivFriendMessage;
    private ImageView ivFriendCall;
    private TextView tvFriendAddress;
    private TextView tvFriendReport;
    private TextView tvFriendVisit;
    
    public static FriendListViewHolder create(Context context) {
        FriendListViewHolder viewHolder = new FriendListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_friend, null));
        return viewHolder;
    }

    public FriendListViewHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }
    
    private void initView(View parentView) {
        tvFriendName = (TextView) parentView.findViewById(R.id.id_friend_name);
        tvFriendPhone = (TextView) parentView.findViewById(R.id.id_friend_phone);
        ivFriendMessage = (ImageView) parentView.findViewById(R.id.id_friend_message);
        ivFriendCall = (ImageView) parentView.findViewById(R.id.id_friend_call);
        tvFriendAddress = (TextView) parentView.findViewById(R.id.id_friend_address);
        tvFriendReport = (TextView) parentView.findViewById(R.id.id_friend_report);
        tvFriendVisit = (TextView) parentView.findViewById(R.id.id_friend_visit);
    }
    
    public void setData(FriendListBean.FriendListData.FriendListItem item) {
        if (item != null) {
            if (item.getFriend_name().length() < 6) {
                tvFriendName.setText(item.getFriend_name());
            } else {
                tvFriendName.setText(item.getFriend_name().substring(0, 6) + "...");
            }
            tvFriendPhone.setText(item.getFriend_phone());
            tvFriendReport.setText(String.valueOf(item.getRec_num()));
            tvFriendVisit.setText(String.valueOf(item.getVisit_num()));
            tvFriendAddress.setText(item.getHouse_names());
        }
    }
}
