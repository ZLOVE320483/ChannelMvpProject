package com.zlove.channelmvp.fragment.friend;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.adapter.friend.FriendListAdapter;
import com.zlove.channelmvp.bean.friend.FriendListBean;
import com.zlove.channelmvp.contract.FriendListContract;
import com.zlove.channelmvp.fragment.base.BaseFragment;
import com.zlove.channelmvp.model.friend.FriendListModel;
import com.zlove.channelmvp.presenter.friend.FriendListPresenter;
import com.zlove.channelmvp.widget.LoadingTip;
import com.zlove.pulltorefreshrecyclerview.IRecyclerView;
import com.zlove.pulltorefreshrecyclerview.OnLoadMoreListener;
import com.zlove.pulltorefreshrecyclerview.OnRefreshListener;
import com.zlove.pulltorefreshrecyclerview.animation.ScaleInAnimation;
import com.zlove.pulltorefreshrecyclerview.divider.RecyclerViewDivider;
import com.zlove.pulltorefreshrecyclerview.widget.LoadMoreFooterView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class FriendListFragment extends BaseFragment<FriendListPresenter, FriendListModel> implements FriendListContract.View, OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.id_title)
    TextView tvTitle;
    @Bind(R.id.recycler_view)
    IRecyclerView recyclerView;
    @Bind(R.id.loading_tip)
    LoadingTip loadingTip;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private FriendListAdapter mAdapter;
    private boolean isRefresh = true;
    private int pageIndex = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_friend_list;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        tvTitle.setText("好友");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new FriendListAdapter(getContext());
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new RecyclerViewDivider(getContext(), LinearLayoutManager.VERTICAL, 1, R.color.common_divider));
        recyclerView.setOnRefreshListener(this);
        recyclerView.setOnLoadMoreListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(0);
            }
        });

        if (mAdapter.getSize() <= 0) {
            mPresenter.getFriendList("", String.valueOf(pageIndex), "10");
        }
    }

    @Override
    public void getFriendListSuccess(FriendListBean bean) {
        if (bean != null) {
            if (bean.getStatus() == 200) {
                pageIndex = bean.getData().getPage_info().getPage_index();
                List<FriendListBean.FriendListData.FriendListItem> list = bean.getData().getFriend_list();
                if (isRefresh) {
                    recyclerView.setRefreshing(false);
                    mAdapter.replaceAll(list);
                } else {
                    if (list.size() > 0) {
                        recyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                        mAdapter.addAll(list);
                    } else {
                        recyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                    }
                }
            } else {
                showShortToast(bean.getMessage());
            }
        }
    }

    @Override
    public void showLoading(String title) {
        if (isRefresh) {
            loadingTip.setLoadingTip(LoadingTip.LoadStatus.LOADING);
        }
    }

    @Override
    public void stopLoading() {
        loadingTip.setLoadingTip(LoadingTip.LoadStatus.FINISH);
    }

    @Override
    public void showErrorTip(String msg) {
        loadingTip.setLoadingTip(LoadingTip.LoadStatus.ERROR);
        loadingTip.setTips(msg);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        isRefresh = false;
        mPresenter.getFriendList("", String.valueOf(pageIndex), "10");
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        pageIndex = 0;
        mPresenter.getFriendList("", String.valueOf(pageIndex), "10");
    }
}
