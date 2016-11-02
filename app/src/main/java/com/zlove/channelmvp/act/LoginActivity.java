package com.zlove.channelmvp.act;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;
import com.zlove.channelmvp.R;
import com.zlove.channelmvp.bean.user.UserLoginBean;
import com.zlove.channelmvp.config.ChannelCookie;
import com.zlove.channelmvp.contract.UserLoginContract;
import com.zlove.channelmvp.model.user.UserLoginModel;
import com.zlove.channelmvp.presenter.user.UserLoginPresenter;
import com.zlove.channelmvp.util.MD5Util;
import com.zlove.channelmvp.util.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class LoginActivity extends BaseActivity<UserLoginPresenter, UserLoginModel> implements UserLoginContract.View, View.OnClickListener {

    @Bind(R.id.id_account)
    MaterialEditText etAccount;
    @Bind(R.id.id_password)
    MaterialEditText etPassword;

    private String account;
    private String password;

    @Override
    protected int getLayoutId() {
        return R.layout.act_login;
    }

    @Override
    public void initPresent() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        setTranslateBar();

        etAccount.addValidator(new RegexpValidator("请输入正确的手机号", "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"));
        etPassword.addValidator(new RegexpValidator("请输入正确的密码", "^[a-zA-Z0-9]{6,14}$"));

        etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    etAccount.validate();
                }
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    etPassword.validate();
                }
            }
        });
    }

    @OnClick({R.id.id_login, R.id.id_register, R.id.id_find_pwd})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_login:
                doLogin();
                break;

            case R.id.id_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.id_find_pwd:
                Intent findIntent = new Intent(this, FindPwdActivity.class);
                startActivity(findIntent);
                break;

            default:
                break;
        }
    }

    private void doLogin() {
        if (!etAccount.validate()) {
            return;
        }
        if (!etPassword.validate()) {
            return;
        }
        account = etAccount.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        mPresenter.login(account, MD5Util.getMD5Code(password));
    }

    @Override
    public void loginSuccess(UserLoginBean loginBean) {
        Log.d("ZLOVE", "loginSuccess---" + loginBean.toString());
        ToastUtil.showToastWithImg("登录成功", R.drawable.ic_success);
        ChannelCookie.getInstance().setLoginPass(true);
        UserLoginBean.UserLoginData data = loginBean.getData();
        if (data != null) {
            String sessionId = data.getSession_id();
            ChannelCookie.getInstance().saveUserInfo(account, password, sessionId);
            String gender = data.getGender();
            if (gender.equals("1")) {
                ChannelCookie.getInstance().saveUserGender("男");
            } else if (gender.equals("2")) {
                ChannelCookie.getInstance().saveUserGender("女");
            } else {
                ChannelCookie.getInstance().saveUserGender("");
            }
            String userAvatar = data.getAvatar();
            ChannelCookie.getInstance().saveUserAvatar(userAvatar);
            String realName = data.getRealname();
            ChannelCookie.getInstance().saveUserName(realName);
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoading(String title) {
    }

    @Override
    public void stopLoading() {
    }

    @Override
    public void showErrorTip(String msg) {
        Log.d("ZLOVE", "showErrorTip---" + msg);
        ToastUtil.showToastWithImg(msg, R.drawable.ic_wrong);
    }
}
