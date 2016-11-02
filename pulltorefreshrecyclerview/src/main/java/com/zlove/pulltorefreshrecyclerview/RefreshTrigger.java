package com.zlove.pulltorefreshrecyclerview;

/**
 * Created by ZLOVE on 2016/10/13.
 */
public interface RefreshTrigger {

    void onStart(boolean automatic, int headerHeight, int finalHeight);
    void onMove(boolean finished, boolean automatic, int moved);
    void onRefresh();
    void onRelease();
    void onComplete();
    void onReset();
}
