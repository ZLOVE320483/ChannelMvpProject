package com.zlove.channelmvp.fragment.user;

import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.bean.user.UserLoginBean;
import com.zlove.channelmvp.contract.UserLoginContract;
import com.zlove.channelmvp.fragment.base.BaseFragment;
import com.zlove.channelmvp.model.user.UserLoginModel;
import com.zlove.channelmvp.presenter.user.UserLoginPresenter;
import com.zlove.channelmvp.util.MD5Util;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ZLOVE on 2016/10/28.
 */
public class LoginFragment extends BaseFragment<UserLoginPresenter, UserLoginModel> implements UserLoginContract.View, View.OnClickListener {

    @Bind(R.id.id_account)
    AutoCompleteTextView etAccount;
    @Bind(R.id.id_password)
    EditText etPassword;

    private String account;
    private String password;

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_login;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.id_login)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_login:
                doLogin();
                break;

            default:
                break;
        }
    }

    private void doLogin() {
        account = etAccount.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        mPresenter.login(account, MD5Util.getMD5Code(password));
    }

    @Override
    public void loginSuccess(UserLoginBean loginBean) {
        Log.d("ZLOVE", "res---" + loginBean.toString());
    }

    @Override
    public void showLoading(String title) {
    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }
}
