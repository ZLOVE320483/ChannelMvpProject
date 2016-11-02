package com.zlove.channelmvp.bean;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class BaseBean {

    private CommonPageInfo page_info;
    public CommonPageInfo getPage_info() {
        return page_info;
    }

    public static class CommonPageInfo {
        private int page_index;
        private String page_size;
        public int getPage_index() {
            return page_index;
        }
        public String getPage_size() {
            return page_size;
        }
    }
}
