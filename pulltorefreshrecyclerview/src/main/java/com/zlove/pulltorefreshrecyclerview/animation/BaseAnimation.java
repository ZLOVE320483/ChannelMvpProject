package com.zlove.pulltorefreshrecyclerview.animation;

import android.animation.Animator;
import android.view.View;

/**
 * Created by ZLOVE on 2016/10/17.
 */
public interface BaseAnimation {

    Animator[] getAnimators(View view);
}
