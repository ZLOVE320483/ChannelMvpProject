package com.zlove.pulltorefreshrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public abstract class OnLoadMoreScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();

        boolean triggerCondition = visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE && canTriggerLoadMore(recyclerView);
        if (triggerCondition) {
            onLoadMore(recyclerView);
        }
    }

    public boolean canTriggerLoadMore(RecyclerView recyclerView) {
        View lastChild = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
        int position = recyclerView.getChildLayoutPosition(lastChild);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int totalItemCount = layoutManager.getItemCount();
        return totalItemCount - 1 == position;
    }

    public abstract void onLoadMore(RecyclerView recyclerView);
}
