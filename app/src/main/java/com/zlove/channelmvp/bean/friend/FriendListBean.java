package com.zlove.channelmvp.bean.friend;

import com.zlove.channelmvp.bean.BaseBean;

import java.util.List;

/**
 * Created by ZLOVE on 2016/11/3.
 */
public class FriendListBean {

    private int status;
    private String message;
    private FriendListData data;
    private long server_time;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public FriendListData getData() {
        return data;
    }

    public long getServer_time() {
        return server_time;
    }

    public static class FriendListData extends BaseBean {
        private List<FriendListItem> friend_list;

        public List<FriendListItem> getFriend_list() {
            return friend_list;
        }

        public static class FriendListItem {

            private String friend_name;
            private String friend_phone;
            private String friend_id;
            private String rec_num;
            private String visit_num;
            private String house_names;

            public String getFriend_name() {
                return friend_name;
            }

            public String getFriend_id() {
                return friend_id;
            }

            public String getRec_num() {
                return rec_num;
            }

            public String getVisit_num() {
                return visit_num;
            }

            public String getHouse_names() {
                return house_names;
            }

            public String getFriend_phone() {
                return friend_phone;
            }
        }
    }
}
