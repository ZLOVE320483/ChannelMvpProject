package com.zlove.channelmvp.act;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
public class FindPwdActivity extends BaseActivity<UserFindPwdPresenter, UserFindPwdModel> implements UserFindPwdContract.View, View.OnClickListener {

    @Bind(R.id.id_title)
    TextView tvTitle;
    @Bind(R.id.id_account)
    EditText etAccount;
    @Bind(R.id.id_verify_code)
    EditText etVerifyCode;
    @Bind(R.id.id_get_verify_code)
    Button btnGetVerifyCode;

    private GetVerifyCodeTimer timer;
    private String account;
    private String code;

    @Override
    protected int getLayoutId() {
        return R.layout.act_find_pwd;
    }

    @Override
    public void initPresent() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        setStatusBarColor();
        tvTitle.setText("找回密码");
    }

    @Override
    public void getFindPwdVerCodeSuccess(UserVerifyCodeBean bean) {
        if (bean.getStatus() == 200) {
            ToastUtil.showShort("验证码已发送");
        } else {
            ToastUtil.showShort(bean.getMessage());
        }
    }

    @Override
    public void checkPhoneCodeSuccess(CommonBean bean) {
        if (bean.getStatus() == 200) {
            Intent intent = new Intent(this, FindPwdNextActivity.class);
            intent.putExtra(IntentKey.INTENT_KEY_ACCOUNT, account);
            startActivity(intent);
            finish();
        } else {
            ToastUtil.showShort(bean.getMessage());
        }
    }

    @Override
    public void updateNewPasswordSuccess(CommonBean bean) {
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

    @OnClick({R.id.id_back, R.id.id_get_verify_code, R.id.id_confirm})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back:
                finish();
                break;

            case R.id.id_get_verify_code:
                getVerifyCode();
                break;

            case R.id.id_confirm:
                doNextConfirm();
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
        mPresenter.getFindPwdVerCode(account);
        if (timer == null) {
            timer = new GetVerifyCodeTimer(60 * 1000, 1000);
        }
        timer.start();
    }

    private void doNextConfirm() {
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
        mPresenter.checkPhoneCode(account, code);
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
