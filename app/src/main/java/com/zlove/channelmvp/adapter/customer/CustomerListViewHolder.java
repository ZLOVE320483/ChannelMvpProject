package com.zlove.channelmvp.adapter.customer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.bean.customer.CustomerListBean;
import com.zlove.channelmvp.util.UIUtil;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class CustomerListViewHolder extends RecyclerView.ViewHolder {

    private TextView tvCustomerName;
    private ImageView ivCustomerType;
    private TextView tvCustomerPhone;
    private TextView tvCustomerState;
    private TextView tvProjectArea;
    private TextView tvProjectLayout;
    private TextView tvProjectPrice;
    private TextView tvProjectType;
    private TextView tvProjectName;
    private TextView tvCustomerSaleman;
    private TextView tvCustomerTime;
    private ImageView ivHasOverdue;

    public static CustomerListViewHolder create(Context mContext) {
        CustomerListViewHolder viewHolder = new CustomerListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_customer, null));
        return viewHolder;
    }

    public CustomerListViewHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View parentView) {
        tvCustomerName = (TextView) parentView.findViewById(R.id.id_customer_name);
        ivCustomerType = (ImageView) parentView.findViewById(R.id.id_customer_type);
        tvCustomerPhone = (TextView) parentView.findViewById(R.id.id_customer_phone);
        tvCustomerState = (TextView) parentView.findViewById(R.id.id_customer_state);
        tvProjectArea = (TextView) parentView.findViewById(R.id.id_project_area);
        tvProjectLayout = (TextView) parentView.findViewById(R.id.project_layout);
        tvProjectPrice = (TextView) parentView.findViewById(R.id.id_project_price);
        tvProjectType = (TextView) parentView.findViewById(R.id.project_type);
        tvProjectName = (TextView) parentView.findViewById(R.id.id_project_name);
        tvCustomerSaleman = (TextView) parentView.findViewById(R.id.id_customer_saleman);
        tvCustomerTime = (TextView) parentView.findViewById(R.id.id_customer_time);
        ivHasOverdue = (ImageView) parentView.findViewById(R.id.has_over_due);
    }
    
    public void setData(CustomerListBean.CustomerListData.CustomerListItem item) {
        String minPrice = "0";
        String maxPrice;
        if (item != null) {
            if (item.getIs_disabled() == 1) {
                ivHasOverdue.setVisibility(View.VISIBLE);
            } else {
                ivHasOverdue.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(item.getIntent_price_min())) {
                minPrice = item.getIntent_price_min();
            }
            if (TextUtils.isEmpty(item.getIntent_price_max()) || item.getIntent_price_max().equals("1000")) {
                maxPrice = "不限";
            } else {
                maxPrice = item.getIntent_price_max() + "万";
            }
            tvCustomerName.setText(item.getName());
            tvCustomerPhone.setText(item.getPhone());
            tvCustomerTime.setText(item.getCreate_time());
            tvProjectArea.setText(item.getIntent_location_ids());
            tvProjectLayout.setText(item.getHouse_types());
            tvProjectType.setText(item.getProperty_types());
            tvProjectPrice.setText(minPrice + "-" + maxPrice);
            if (TextUtils.isEmpty(item.getHouse_name())) {
                tvProjectName.setText("未报备楼盘");
                tvCustomerState.setVisibility(View.GONE);
            } else {
                tvProjectName.setText(item.getHouse_name());
                tvCustomerState.setVisibility(View.VISIBLE);
            }
            tvCustomerSaleman.setText(item.getSalesman());

            String categoryId = item.getCategory_id();
            if (categoryId.equals("1")) {
                ivCustomerType.setBackgroundResource(R.drawable.customer_type_a);
                tvCustomerState.setTextColor(UIUtil.getResColor(R.color.common_bg_top_bar));
                tvCustomerState.setBackgroundResource(R.drawable.common_round_rect_bg);
            } else if (categoryId.equals("2")) {
                ivCustomerType.setBackgroundResource(R.drawable.customer_type_b);
                tvCustomerState.setTextColor(UIUtil.getResColor(R.color.common_bg_top_bar));
                tvCustomerState.setBackgroundResource(R.drawable.common_round_rect_bg);
            } else if (categoryId.equals("3")) {
                ivCustomerType.setBackgroundResource(R.drawable.customer_type_c);
                tvCustomerState.setTextColor(UIUtil.getResColor(R.color.common_bg_top_bar));
                tvCustomerState.setBackgroundResource(R.drawable.common_round_rect_bg);
            } else if (categoryId.equals("4")) {
                ivCustomerType.setBackgroundResource(R.drawable.customer_type_d);
                tvCustomerState.setTextColor(UIUtil.getResColor(R.color.common_text_black_6));
                tvCustomerState.setBackgroundResource(R.drawable.common_round_rect_grey_bg);
            }

            String status = item.getStatus();
            if (status.equals("1")) {
                tvCustomerState.setText("待验证");
            } else if (status.equals("2")) {
                tvCustomerState.setText("无效");
            } else if (status.equals("3")) {
                tvCustomerState.setText("有效");
            } else if (status.equals("4")) {
                tvCustomerState.setText("待验证");
            } else if (status.equals("5")) {
                tvCustomerState.setText("到访");
            } else if (status.equals("6")) {
                tvCustomerState.setText("认筹");
            } else if (status.equals("7")) {
                tvCustomerState.setText("成交");
            } else if (status.equals("8")) {
                tvCustomerState.setText("结佣");
            } else {
                tvCustomerState.setVisibility(View.GONE);
            }
        }
    }
}
