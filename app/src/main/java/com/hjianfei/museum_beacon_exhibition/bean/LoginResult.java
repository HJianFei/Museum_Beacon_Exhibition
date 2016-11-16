package com.hjianfei.museum_beacon_exhibition.bean;

/**
 * Created by HJianFei on 2016/11/16.
 */

public class LoginResult {

    /**
     * msg :
     * user : {"user_phone":"15107658882","user_id":"2","user_password":"123456"}
     * status : 0
     */

    private String msg;
    private UserBean user;
    private String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class UserBean {
        /**
         * user_phone : 15107658882
         * user_id : 2
         * user_password : 123456
         */

        private String user_phone;
        private String user_id;
        private String user_password;

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }
    }
}
