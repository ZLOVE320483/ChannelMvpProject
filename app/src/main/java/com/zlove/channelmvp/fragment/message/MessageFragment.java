package com.zlove.channelmvp.fragment.message;

import android.view.View;
import android.widget.TextView;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.bean.message.MessageHomeBean;
import com.zlove.channelmvp.contract.MessageHomeContract;
import com.zlove.channelmvp.fragment.base.BaseFragment;
import com.zlove.channelmvp.model.message.MessageHomeModel;
import com.zlove.channelmvp.presenter.message.MessageHomePresenter;
import com.zlove.channelmvp.util.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class MessageFragment extends BaseFragment<MessageHomePresenter, MessageHomeModel> implements MessageHomeContract.View, View.OnClickListener {

    @Bind(R.id.id_title)
    TextView tvTitle;
    @Bind(R.id.id_message_contact_customer_count)
    TextView tvContactCustomerUnReadCount;
    @Bind(R.id.id_message_customer_progress_count)
    TextView tvCustomerProgressUnReadCount;
    @Bind(R.id.id_message_customer_trace_count)
    TextView tvCustomerTraceUnReadCount;
    @Bind(R.id.id_message_project_dynamic_count)
    TextView tvProjectDynamicUnReadCount;
    @Bind(R.id.id_message_cooperate_rule_count)
    TextView tvCooperateRuleUnReadCount;

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_message;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        tvTitle.setText("消息");
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getMessageHomeUnReadCount();
    }

    @Override
    public void getMessageHomeUnReadCountSuccess(MessageHomeBean bean) {
        if (bean != null) {
            if (bean.getStatus() == 200) {
                MessageHomeBean.MessageHomeData data = bean.getData();
                if (data != null) {
                    int contactCustomerCount = data.getContact_client_num();
                    int customerProgressCount = data.getProcess_client_num();
                    int customerTraceCount = data.getTrack_client_num();
                    int projectDynamicCount = data.getHouse_news_num();
                    int cooperateRuleCount = data.getCooperate_num();

                    if (contactCustomerCount > 0) {
                        tvContactCustomerUnReadCount.setVisibility(View.VISIBLE);
                        tvContactCustomerUnReadCount.setText(String.valueOf(contactCustomerCount));
                    } else {
                        tvContactCustomerUnReadCount.setVisibility(View.GONE);
                    }

                    if (customerProgressCount > 0) {
                        tvCustomerProgressUnReadCount.setVisibility(View.VISIBLE);
                        tvCustomerProgressUnReadCount.setText(String.valueOf(customerProgressCount));
                    } else {
                        tvCustomerProgressUnReadCount.setVisibility(View.GONE);
                    }

                    if (customerTraceCount > 0) {
                        tvCustomerTraceUnReadCount.setVisibility(View.VISIBLE);
                        tvCustomerTraceUnReadCount.setText(String.valueOf(customerTraceCount));
                    } else {
                        tvCustomerTraceUnReadCount.setVisibility(View.GONE);
                    }

                    if (projectDynamicCount > 0) {
                        tvProjectDynamicUnReadCount.setVisibility(View.VISIBLE);
                        tvProjectDynamicUnReadCount.setText(String.valueOf(projectDynamicCount));
                    } else {
                        tvProjectDynamicUnReadCount.setVisibility(View.GONE);
                    }

                    if (cooperateRuleCount > 0) {
                        tvCooperateRuleUnReadCount.setVisibility(View.VISIBLE);
                        tvCooperateRuleUnReadCount.setText(String.valueOf(cooperateRuleCount));
                    } else {
                        tvCooperateRuleUnReadCount.setVisibility(View.GONE);
                    }
                }
            } else {
                showShortToast(bean.getMessage());
            }
        }
    }

    @Override
    public void showLoading(String title) {
    }

    @Override
    public void stopLoading() {
    }

    @Override
    public void showErrorTip(String msg) {
        ToastUtil.showToastWithImg(msg, R.drawable.ic_wrong);
    }

    @OnClick({R.id.id_message_contact_customer, R.id.id_message_customer_progress,
            R.id.id_message_customer_trace, R.id.id_message_project_dynamic, R.id.id_message_cooperate_rule})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_message_contact_customer:
                break;

            case R.id.id_message_customer_progress:
                break;

            case R.id.id_message_customer_trace:
                break;

            case R.id.id_message_project_dynamic:
                break;

            case R.id.id_message_cooperate_rule:
                break;

            default:
                break;
        }
    }
}
