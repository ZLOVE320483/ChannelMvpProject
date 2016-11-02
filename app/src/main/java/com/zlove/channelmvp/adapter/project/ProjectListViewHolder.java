package com.zlove.channelmvp.adapter.project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.bean.project.ProjectItemBean;
import com.zlove.channelmvp.util.ImageLoaderUtils;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class ProjectListViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private ImageView ivProjectImg;
    private TextView tvProjectName;
    private TextView tvProjectPrice;
    private TextView tvCommission;

    public static ProjectListViewHolder create(Context context) {
        ProjectListViewHolder viewHolder = new ProjectListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_project, null), context);
        return viewHolder;
    }

    public ProjectListViewHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        initView(itemView);
    }

    private void initView(View parentView) {
        ivProjectImg = (ImageView) parentView.findViewById(R.id.id_project_img);
        tvProjectName = (TextView) parentView.findViewById(R.id.id_project_name);
        tvProjectPrice = (TextView) parentView.findViewById(R.id.id_project_price);
        tvCommission = (TextView) parentView.findViewById(R.id.commission);
    }

    public void setData(ProjectItemBean.ProjectItemData.ProjectItemHouseList item) {
        if (item != null) {
            ImageLoaderUtils.display(mContext, ivProjectImg, item.getThumb());
            tvProjectName.setText("[" + item.getArea_desc() + "]" + item.getName());
            tvProjectPrice.setText("价格:" + item.getPrice_desc());
            tvCommission.setText(item.getHouse_rule_desc());
        }
    }
}
