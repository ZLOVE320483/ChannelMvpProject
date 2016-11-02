package com.zlove.channelmvp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zlove.channelmvp.R;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class LoadingTip extends LinearLayout {

    private ImageView ivNoDataTip;
    private ProgressBar progressBar;
    private TextView tvTips;
    private Button btnOperate;
    private OnReloadListener onReloadListener;

    public LoadingTip(Context context) {
        super(context);
        initView(context);
    }

    public LoadingTip(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadingTip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.dialog_loading_tip, this);
        ivNoDataTip = (ImageView) findViewById(R.id.img_tip_logo);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        tvTips = (TextView) findViewById(R.id.tv_tips);
        btnOperate = (Button) findViewById(R.id.bt_operate);
        //重新尝试
        btnOperate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onReloadListener!=null){
                    onReloadListener.reload();
                }
            }
        });
        setVisibility(View.GONE);
    }

    public void setTips(String tips) {
        if (tvTips != null) {
            tvTips.setText(tips);
        }
    }

    /**
     * 根据状态显示不同的提示
     * @param loadStatus
     */
    public void setLoadingTip(LoadStatus loadStatus) {
        switch (loadStatus) {
            case SERVER_ERROR:
                setVisibility(VISIBLE);
                ivNoDataTip.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                tvTips.setText(R.string.net_error);
                ivNoDataTip.setImageResource(R.drawable.ic_wrong);
                break;

            case ERROR:
                setVisibility(VISIBLE);
                ivNoDataTip.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                tvTips.setText(R.string.net_error);
                ivNoDataTip.setImageResource(R.drawable.ic_wifi_off);
                break;

            case EMPTY:
                setVisibility(VISIBLE);
                ivNoDataTip.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                tvTips.setText(R.string.empty);
                ivNoDataTip.setImageResource(R.drawable.no_content_tip);
                break;

            case LOADING:
                setVisibility(VISIBLE);
                ivNoDataTip.setVisibility(GONE);
                progressBar.setVisibility(VISIBLE);
                tvTips.setText(R.string.loading);
                break;

            case FINISH:
                setVisibility(GONE);
                break;
        }
    }

    public void setOnReloadListener(OnReloadListener onReloadListener) {
        this.onReloadListener = onReloadListener;
    }

    public enum LoadStatus {
        SERVER_ERROR, ERROR, EMPTY, LOADING, FINISH
    }

    public interface OnReloadListener {
        void reload();
    }
}
