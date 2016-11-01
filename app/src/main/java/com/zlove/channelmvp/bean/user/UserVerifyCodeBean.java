package com.zlove.channelmvp.bean.user;

import java.io.Serializable;

/**
 * Created by ZLOVE on 2016/10/31.
 */
public class UserVerifyCodeBean implements Serializable {
    private int status;
    private String message;
    private UserVerifyCodeData data;
    private long server_time;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public UserVerifyCodeData getData() {
        return data;
    }

    public long getServer_time() {
        return server_time;
    }

    public static class UserVerifyCodeData implements Serializable {

        private int code;

        public int getCode() {
            return code;
        }
    }
}
