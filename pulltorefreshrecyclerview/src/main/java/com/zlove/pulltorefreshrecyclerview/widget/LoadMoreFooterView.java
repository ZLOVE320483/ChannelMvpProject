package com.zlove.pulltorefreshrecyclerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zlove.pulltorefreshrecyclerview.R;


/**
 * Created by ZLOVE on 2016/10/13.
 */
public class LoadMoreFooterView extends FrameLayout {

    private Status mStatus;
    private View mLoadingView;
    private View mErrorView;
    private View mTheExdView;
    private OnRetryListener mOnRetryListener;

    public LoadMoreFooterView(Context context) {
        this(context,null);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_irecyclerview_load_more_footer_view, this, true);

        mLoadingView = findViewById(R.id.loadingView);
        mErrorView = findViewById(R.id.errorView);
        mTheExdView = findViewById(R.id.theEndView);

        mErrorView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnRetryListener != null) {
                    mOnRetryListener.onRetry(LoadMoreFooterView.this);
                }
            }
        });

        setStatus(Status.GONE);
    }

    public void setOnRetryListener(OnRetryListener mOnRetryListener) {
        this.mOnRetryListener = mOnRetryListener;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status mStatus) {
        this.mStatus = mStatus;
        change();
    }

    public boolean canLoadMore() {
        return mStatus == Status.GONE || mStatus == Status.ERROR;
    }

    private void change() {
        switch (mStatus) {
            case GONE:
                mLoadingView.setVisibility(GONE);
                mErrorView.setVisibility(GONE);
                mTheExdView.setVisibility(GONE);
                break;
            case LOADING:
                mLoadingView.setVisibility(VISIBLE);
                mErrorView.setVisibility(GONE);
                mTheExdView.setVisibility(GONE);
                break;
            case ERROR:
                mLoadingView.setVisibility(GONE);
                mErrorView.setVisibility(VISIBLE);
                mTheExdView.setVisibility(GONE);
                break;
            case THE_END:
                mLoadingView.setVisibility(GONE);
                mErrorView.setVisibility(GONE);
                mTheExdView.setVisibility(VISIBLE);
                break;
        }
    }

    public enum Status {
        GONE, LOADING, ERROR, THE_END
    }

    public interface OnRetryListener {
        void onRetry(LoadMoreFooterView view);
    }
}
