package com.zlove.channelmvp.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.zlove.channelmvp.config.ChannelConstant;
import com.zlove.channelmvp.rx.RxBus;
import com.zlove.channelmvp.rx.RxManager;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {

    RxManager rxManager;

    public ScrollAwareFABBehavior() {
        super();
        if (rxManager == null) {
            rxManager = new RxManager();
        }
    }

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (rxManager == null) {
            rxManager = new RxManager();
        }
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            child.hide();
            RxBus.getInstance().post(ChannelConstant.MENU_SHOW_HIDE,false);
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            RxBus.getInstance().post(ChannelConstant.MENU_SHOW_HIDE,true);
            child.show();
        }
    }
}
