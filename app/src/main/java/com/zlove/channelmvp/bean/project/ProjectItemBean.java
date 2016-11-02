package com.zlove.channelmvp.bean.project;

import com.zlove.channelmvp.bean.BaseBean;

import java.util.List;

/**
 * Created by ZLOVE on 2016/11/1.
 */
public class ProjectItemBean {

    private int status;
    private String message;
    private ProjectItemData data;
    private long server_time;

    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public ProjectItemData getData() {
        return data;
    }
    public long getServer_time() {
        return server_time;
    }

    public static class ProjectItemData extends BaseBean {

        private List<ProjectItemHouseList> house_list;
        public List<ProjectItemHouseList> getHouse_list() {
            return house_list;
        }

        public static class ProjectItemHouseList {
            private String house_id;
            private String city_id;
            private String name;
            private String code;
            private String thumb;
            private String price;
            private String price_desc;
            private String house_types;
            private String area_desc;
            private String house_rule_desc;

            public String getArea_desc() {
                return area_desc;
            }

            public String getHouse_id() {
                return house_id;
            }

            public String getCode() {
                return code;
            }

            public String getCity_id() {
                return city_id;
            }

            public String getThumb() {
                return thumb;
            }

            public String getName() {
                return name;
            }

            public String getPrice() {
                return price;
            }

            public String getHouse_types() {
                return house_types;
            }

            public String getPrice_desc() {
                return price_desc;
            }

            public String getHouse_rule_desc() {
                return house_rule_desc;
            }
        }
    }
}
