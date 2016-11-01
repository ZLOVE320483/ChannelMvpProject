package com.zlove.channelmvp.act;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.bean.CommonBean;
import com.zlove.channelmvp.bean.user.UserVerifyCodeBean;
import com.zlove.channelmvp.contract.UserFindPwdContract;
import com.zlove.channelmvp.model.user.UserFindPwdModel;
import com.zlove.channelmvp.presenter.user.UserFindPwdPresenter;
import com.zlove.channelmvp.util.IntentKey;
import com.zlove.channelmvp.util.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class FindPwdNextActivity extends BaseActivity<UserFindPwdPresenter, UserFindPwdModel> implements UserFindPwdContract.View, View.OnClickListener {

    @Bind(R.id.id_password)
    EditText etPassword;
    @Bind(R.id.id_et_confirm_pwd)
    EditText etConfirmPassword;
    @Bind(R.id.id_title)
    TextView tvTitle;

    private String account;
    private String password;
    private String confirmPwd;

    @Override
    protected int getLayoutId() {
        return R.layout.act_find_pwd_next;
    }

    @Override
    public void initPresent() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(IntentKey.INTENT_KEY_ACCOUNT)) {
            account = intent.getStringExtra(IntentKey.INTENT_KEY_ACCOUNT);
        }
        setStatusBarColor();
        tvTitle.setText("找回密码");
    }

    @Override
    public void getFindPwdVerCodeSuccess(UserVerifyCodeBean bean) {
    }

    @Override
    public void checkPhoneCodeSuccess(CommonBean bean) {
    }

    @Override
    public void updateNewPasswordSuccess(CommonBean bean) {
        if (bean.getStatus() == 200) {
            ToastUtil.showShort("新密码设置成功");
            finish();
        } else {
            ToastUtil.showShort(bean.getMessage());
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

    @OnClick({R.id.id_back, R.id.id_confirm})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back:
                finish();
                break;

            case R.id.id_confirm:
                doConfirm();
                break;

            default:
                break;
        }
    }

    private void doConfirm() {
        password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShort("请输入密码");
            return;
        }
        confirmPwd = etConfirmPassword.getText().toString().trim();
        if (!confirmPwd.equals(password)) {
            ToastUtil.showShort("两次密码不一致");
            return;
        }
        mPresenter.updateNewPassword(account, password);
    }
}
