package com.zlove.channelmvp.bean;

import java.io.Serializable;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class CommonBean implements Serializable {

    private int status;
    private String message;
    private long server_time;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getServer_time() {
        return server_time;
    }
}
