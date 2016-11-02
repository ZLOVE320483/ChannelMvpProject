package com.zlove.channelmvp.fragment.project;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.adapter.project.ProjectListAdapter;
import com.zlove.channelmvp.bean.project.ProjectItemBean;
import com.zlove.channelmvp.contract.ProjectListContract;
import com.zlove.channelmvp.fragment.base.BaseFragment;
import com.zlove.channelmvp.model.project.ProjectListModel;
import com.zlove.channelmvp.presenter.project.ProjectListPresenter;
import com.zlove.channelmvp.widget.LoadingTip;
import com.zlove.pulltorefreshrecyclerview.IRecyclerView;
import com.zlove.pulltorefreshrecyclerview.OnLoadMoreListener;
import com.zlove.pulltorefreshrecyclerview.OnRefreshListener;
import com.zlove.pulltorefreshrecyclerview.animation.ScaleInAnimation;
import com.zlove.pulltorefreshrecyclerview.widget.LoadMoreFooterView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class ProjectListFragment extends BaseFragment<ProjectListPresenter, ProjectListModel> implements ProjectListContract.View, OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.id_title)
    TextView tvTitle;
    @Bind(R.id.recycler_view)
    IRecyclerView recyclerView;
    @Bind(R.id.loading_tip)
    LoadingTip loadingTip;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private ProjectListAdapter mAdapter;
    private boolean isRefresh = true;
    private int pageIndex = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_project_list;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        tvTitle.setText("楼盘");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ProjectListAdapter(getContext());
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnRefreshListener(this);
        recyclerView.setOnLoadMoreListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(0);
            }
        });

        if (mAdapter.getSize() <= 0) {
            mPresenter.getProjectList("", "1602", "", "", "", "", String.valueOf(pageIndex), "10", "");
        }
    }

    @Override
    public void getProjectListSuccess(ProjectItemBean bean) {
        if (bean != null) {
            if (bean.getStatus() == 200) {
                pageIndex = bean.getData().getPage_info().getPage_index();
                List<ProjectItemBean.ProjectItemData.ProjectItemHouseList> list = bean.getData().getHouse_list();
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
        mPresenter.getProjectList("", "1602", "", "", "", "", String.valueOf(pageIndex), "10", "");
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        pageIndex = 0;
        mPresenter.getProjectList("", "1602", "", "", "", "", String.valueOf(pageIndex), "10", "");
    }
}
