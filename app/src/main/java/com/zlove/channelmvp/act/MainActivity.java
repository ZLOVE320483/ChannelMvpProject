package com.zlove.channelmvp.act;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zlove.channelmvp.R;
import com.zlove.channelmvp.bean.TabEntity;
import com.zlove.channelmvp.config.ChannelConstant;
import com.zlove.channelmvp.fragment.customer.CustomerListFragment;
import com.zlove.channelmvp.fragment.friend.FriendListFragment;
import com.zlove.channelmvp.fragment.me.MeFragment;
import com.zlove.channelmvp.fragment.message.MessageFragment;
import com.zlove.channelmvp.fragment.project.ProjectListFragment;

import java.util.ArrayList;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;

    private String[] mTitles = {"楼盘", "消息", "客户", "好友", "我"};
    private int[] mIconUnSelectIds = {R.drawable.ic_tab_project_normal, R.drawable.ic_tab_msg_normal, R.drawable.ic_tab_customer_normal, R.drawable.ic_tab_friend_normal, R.drawable.ic_tab_me_normal};
    private int[] mIconSelectIds = {R.drawable.ic_tab_project_checked, R.drawable.ic_tab_msg_checked, R.drawable.ic_tab_customer_checked, R.drawable.ic_tab_friend_checked, R.drawable.ic_tab_me_checked};

    private ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();

    private static final String PROJECT_LIST_FRAGMENT_TAG = "projectListFragment";
    private static final String MESSAGE_FRAGMENT_TAG = "messageFragment";
    private static final String CUSTOMER_LIST_FRAGMENT_TAG = "customerListFragment";
    private static final String FRIEND_LIST_FRAGMENT_TAG = "friendListFragment";
    private static final String ME_FRAGMENT_TAG = "meFragment";

    private ProjectListFragment projectListFragment;
    private MessageFragment messageFragment;
    private CustomerListFragment customerListFragment;
    private FriendListFragment friendListFragment;
    private MeFragment meFragment;
    private static int tabLayoutHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
        tabLayout.measure(0, 0);
        tabLayoutHeight = tabLayout.getMeasuredHeight();
        //监听菜单显示或隐藏
        mRxManager.on(ChannelConstant.MENU_SHOW_HIDE, new Action1<Boolean>() {

            @Override
            public void call(Boolean hideOrShow) {
                startAnimation(hideOrShow);
            }
        });
    }

    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            projectListFragment = (ProjectListFragment) getSupportFragmentManager().findFragmentByTag(PROJECT_LIST_FRAGMENT_TAG);
            messageFragment = (MessageFragment) getSupportFragmentManager().findFragmentByTag(MESSAGE_FRAGMENT_TAG);
            customerListFragment = (CustomerListFragment) getSupportFragmentManager().findFragmentByTag(CUSTOMER_LIST_FRAGMENT_TAG);
            friendListFragment = (FriendListFragment) getSupportFragmentManager().findFragmentByTag(FRIEND_LIST_FRAGMENT_TAG);
            meFragment = (MeFragment) getSupportFragmentManager().findFragmentByTag(ME_FRAGMENT_TAG);
            currentTabPosition = savedInstanceState.getInt(ChannelConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            projectListFragment = new ProjectListFragment();
            messageFragment = new MessageFragment();
            customerListFragment = new CustomerListFragment();
            friendListFragment = new FriendListFragment();
            meFragment = new MeFragment();

            transaction.add(R.id.id_framework, projectListFragment, PROJECT_LIST_FRAGMENT_TAG);
            transaction.add(R.id.id_framework, messageFragment, MESSAGE_FRAGMENT_TAG);
            transaction.add(R.id.id_framework, customerListFragment, CUSTOMER_LIST_FRAGMENT_TAG);
            transaction.add(R.id.id_framework, friendListFragment, FRIEND_LIST_FRAGMENT_TAG);
            transaction.add(R.id.id_framework, meFragment, ME_FRAGMENT_TAG);
        }
        transaction.commit();
        switchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    private void switchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.hide(messageFragment);
                transaction.hide(customerListFragment);
                transaction.hide(friendListFragment);
                transaction.hide(meFragment);
                transaction.show(projectListFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.hide(projectListFragment);
                transaction.hide(customerListFragment);
                transaction.hide(friendListFragment);
                transaction.hide(meFragment);
                transaction.show(messageFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.hide(projectListFragment);
                transaction.hide(messageFragment);
                transaction.hide(friendListFragment);
                transaction.hide(meFragment);
                transaction.show(customerListFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 3:
                transaction.hide(projectListFragment);
                transaction.hide(messageFragment);
                transaction.hide(customerListFragment);
                transaction.hide(meFragment);
                transaction.show(friendListFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 4:
                transaction.hide(projectListFragment);
                transaction.hide(messageFragment);
                transaction.hide(customerListFragment);
                transaction.hide(friendListFragment);
                transaction.show(meFragment);
                break;
            default:
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    public void initPresent() {
    }

    @Override
    public void initView() {
        initTab();
    }

    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            customTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        tabLayout.setTabData(customTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    /**
     * 菜单显示隐藏动画
     * @param showOrHide
     */
    private void startAnimation(boolean showOrHide){
        final ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha;
        if (!showOrHide) {
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 1, 0);
        } else {
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 0, 1);
        }

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height = (int) valueAnimator.getAnimatedValue();
                tabLayout.setLayoutParams(layoutParams);
            }
        });

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.setDuration(500);
        animationSet.playTogether(valueAnimator, alpha);
        animationSet.start();
    }
}
