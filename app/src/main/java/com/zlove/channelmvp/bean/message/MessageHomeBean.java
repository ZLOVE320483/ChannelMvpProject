package com.zlove.channelmvp.bean.message;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class MessageHomeBean {

    private int status;
    private String message;
    private MessageHomeData data;
    private String server_time;

    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public MessageHomeData getData() {
        return data;
    }
    public String getServer_time() {
        return server_time;
    }

    public static class MessageHomeData {
        private int contact_client_num;
        private int process_client_num;
        private int track_client_num;
        private int house_news_num;
        private int cooperate_num;

        public int getContact_client_num() {
            return contact_client_num;
        }
        public int getProcess_client_num() {
            return process_client_num;
        }
        public int getTrack_client_num() {
            return track_client_num;
        }
        public int getHouse_news_num() {
            return house_news_num;
        }
        public int getCooperate_num() {
            return cooperate_num;
        }
    }
}
