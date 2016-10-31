package com.zlove.channelmvp.bean.user;

import java.io.Serializable;

public class UserLoginBean implements Serializable {

    private int status;
    private String message;
    private long server_time;
    private UserLoginData data;

    public int getStatus() {
        return status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public long getServer_time() {
        return server_time;
    }
    
    public UserLoginData getData() {
        return data;
    }

    public static class UserLoginData implements Serializable {

        private String user_id;
        private String session_id;
        private String gender;
        private String status;
        private String email;
        private String signature;
        private String bithday;
        private String avatar;
        private String realname;
        private String username;

        public String getUser_id() {
            return user_id;
        }

        public String getSession_id() {
            return session_id;
        }

        public String getGender() {
            return gender;
        }

        public String getStatus() {
            return status;
        }

        public String getEmail() {
            return email;
        }

        public String getSignature() {
            return signature;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getBithday() {
            return bithday;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "UserLoginData{" +
                    "user_id='" + user_id + '\'' +
                    ", session_id='" + session_id + '\'' +
                    ", gender='" + gender + '\'' +
                    ", status='" + status + '\'' +
                    ", email='" + email + '\'' +
                    ", signature='" + signature + '\'' +
                    ", bithday='" + bithday + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", realname='" + realname + '\'' +
                    ", username='" + username + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserLoginBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", server_time=" + server_time +
                ", data=" + data +
                '}';
    }
}
