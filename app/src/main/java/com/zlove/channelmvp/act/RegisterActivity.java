package com.zlove.channelmvp.act;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.bean.CommonBean;
import com.zlove.channelmvp.bean.user.UserVerifyCodeBean;
import com.zlove.channelmvp.contract.UserRegisterContract;
import com.zlove.channelmvp.model.user.UserRegisterModel;
import com.zlove.channelmvp.presenter.user.UserRegisterPresenter;
import com.zlove.channelmvp.util.MD5Util;
import com.zlove.channelmvp.util.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class RegisterActivity extends BaseActivity<UserRegisterPresenter, UserRegisterModel> implements UserRegisterContract.View, View.OnClickListener {

    @Bind(R.id.id_title)
    TextView tvTitle;
    @Bind(R.id.id_user_name)
    EditText etUserName;
    @Bind(R.id.id_account)
    EditText etAccount;
    @Bind(R.id.id_verify_code)
    EditText etVerifyCode;
    @Bind(R.id.id_get_verify_code)
    Button btnGetVerifyCode;
    @Bind(R.id.id_password)
    EditText etPassword;
    @Bind(R.id.id_et_confirm_pwd)
    EditText etConfirmPassword;

    private String account;
    private GetVerifyCodeTimer timer;

    private String realName;
    private String code;
    private String password;
    private String confirmPwd;

    @Override
    protected int getLayoutId() {
        return R.layout.act_register;
    }

    @Override
    public void initPresent() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        setStatusBarColor();
        tvTitle.setText("注册");
    }

    @Override
    public void getVerifyCodeSuccess(UserVerifyCodeBean bean) {
        if (bean.getStatus() == 200) {
            ToastUtil.showShort("验证码已发送");
        } else {
            ToastUtil.showShort(bean.getMessage());
        }
    }

    @Override
    public void registerSuccess(CommonBean bean) {
        if (bean.getStatus() == 200) {
            ToastUtil.showShort("注册成功");
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

    @OnClick({R.id.id_get_verify_code, R.id.id_register, R.id.id_user_protocol, R.id.id_back})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_get_verify_code:
                getVerifyCode();
                break;

            case R.id.id_register:
                doRegister();
                break;

            case R.id.id_user_protocol:
                break;

            case R.id.id_back:
                finish();
                break;

            default:
                break;
        }
    }

    private void getVerifyCode() {
        account = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ToastUtil.showShort("请输入手机号");
            return;
        }
        mPresenter.getVerifyCode(account);
        if (timer == null) {
            timer = new GetVerifyCodeTimer(60 * 1000, 1000);
        }
        timer.start();
    }

    private void doRegister() {
        realName = etUserName.getText().toString().trim();
        if (TextUtils.isEmpty(realName)) {
            ToastUtil.showShort("请输入真实姓名");
            return;
        }
        account = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ToastUtil.showShort("请输入手机号");
            return;
        }
        code = etVerifyCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            ToastUtil.showShort("请输入验证码");
            return;
        }
        password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShort("请输入密码");
            return;
        }
        confirmPwd = etConfirmPassword.getText().toString().trim();
        if (!confirmPwd.equals(password)) {
            ToastUtil.showShort("两次密码输入不一致");
            return;
        }
        mPresenter.userRegister(account, MD5Util.getMD5Code(password), code, realName);
    }

    class GetVerifyCodeTimer extends CountDownTimer {

        public GetVerifyCodeTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            btnGetVerifyCode.setText("重新获取");
            btnGetVerifyCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            btnGetVerifyCode.setClickable(false);
            btnGetVerifyCode.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}
