package com.zlove.channelmvp.rx;

import android.app.Activity;
import android.content.Context;

import com.zlove.channelmvp.R;
import com.zlove.channelmvp.util.ApplicationUtil;
import com.zlove.channelmvp.util.NetWorkUtils;
import com.zlove.channelmvp.widget.LoadingDialog;

import rx.Subscriber;

/**
 * Created by ZLOVE on 2016/10/11.
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;

    public RxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    public RxSubscriber(Context context) {
        this(context, ApplicationUtil.getApplicationContext().getString(R.string.loading), true);
    }

    public RxSubscriber(Context context, boolean showDialog) {
        this(context, ApplicationUtil.getApplicationContext().getString(R.string.loading), showDialog);
    }

    @Override
    public void onCompleted() {
        if (showDialog) {
            LoadingDialog.cancelDialogForLoading();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                LoadingDialog.showDialogForLoading((Activity) mContext, msg, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        if (showDialog) {
            LoadingDialog.cancelDialogForLoading();
        }
        e.printStackTrace();
        if (!NetWorkUtils.isNetConnected(ApplicationUtil.getApplicationContext())) {
            _onError(ApplicationUtil.getApplicationContext().getString(R.string.no_net));
        } else if (e instanceof ServerException) {
            _onError(e.getMessage());
        } else {
            _onError(ApplicationUtil.getApplicationContext().getString(R.string.net_error));
        }
    }

    protected abstract void _onNext(T t);
    protected abstract void _onError(String message);
}
