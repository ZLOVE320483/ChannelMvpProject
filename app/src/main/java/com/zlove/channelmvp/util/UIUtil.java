package com.zlove.channelmvp.util;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


public class UIUtil {
    
    public static void showKeyboard(Context context, EditText view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }
    
    public static void showKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
    
    public static void hideKeyboard(Activity act) {
        InputMethodManager imm = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(act.getWindow().getDecorView().getWindowToken(), 0);
        }
    }
    
    public static String replaceNullOrEmpty(String msg) {
		if (TextUtils.isEmpty(msg)) {
			return "暂无相关信息";
		}
    	return msg;
	}
    
    public static int getResColor(int color) {
		return ApplicationUtil.getApplicationContext().getResources().getColor(color);
	}
}
