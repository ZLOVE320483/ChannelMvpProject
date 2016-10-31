package com.zlove.channelmvp.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zlove.channelmvp.R;

/**
 * Created by ZLOVE on 2016/10/10.
 */
public class ToastUtil {

    private static Toast toast;
    private static Toast toast2;

    private static Toast initToast(CharSequence message, int duration) {
        if (toast == null) {
            toast = Toast.makeText(ApplicationUtil.getApplicationContext(), message, duration);
        } else {
            toast.setText(message);
            toast.setDuration(duration);
        }
        return toast;
    }

    public static void showShort(CharSequence message) {
        initToast(message, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(int strResId) {
        initToast(ApplicationUtil.getApplicationContext().getResources().getText(strResId), Toast.LENGTH_SHORT).show();
    }

    public static void showLong(CharSequence message) {
        initToast(message, Toast.LENGTH_LONG).show();
    }

    public static void showLong(int strResId) {
        initToast(ApplicationUtil.getApplicationContext().getResources().getText(strResId), Toast.LENGTH_LONG).show();
    }

    public static void show(CharSequence message, int duration) {
        initToast(message, duration).show();
    }

    public static void show(Context context, int strResId, int duration) {
        initToast(context.getResources().getText(strResId), duration).show();
    }

    /**
     * 显示有image的toast
     *
     * @param tvStr
     * @param imageResource
     * @return
     */
    public static Toast showToastWithImg(final String tvStr, final int imageResource) {
        if (toast2 == null) {
            toast2 = new Toast(ApplicationUtil.getApplicationContext());
        }
        View view = LayoutInflater.from(ApplicationUtil.getApplicationContext()).inflate(R.layout.toast_custom, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
        tv.setText(TextUtils.isEmpty(tvStr) ? "" : tvStr);
        ImageView iv = (ImageView) view.findViewById(R.id.toast_custom_iv);
        if (imageResource > 0) {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(imageResource);
        } else {
            iv.setVisibility(View.GONE);
        }
        toast2.setView(view);
        toast2.setGravity(Gravity.CENTER, 0, 0);
        toast2.show();
        return toast2;

    }
}
