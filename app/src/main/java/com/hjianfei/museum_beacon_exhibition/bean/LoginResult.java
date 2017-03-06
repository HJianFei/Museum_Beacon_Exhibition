package com.hjianfei.museum_beacon_exhibition.bean;

/**
 * Created by HJianFei on 2016/11/16.
 */

public class LoginResult {


    /**
     * msg : 登陆成功
     * user : {"user_phone":"15527798187","user_id":"1","user_name":"哈哈","user_password":"123","user_email":"190766172@qq.com","user_sex":"男","user_qq":"190766172"}
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
         * user_phone : 15527798187
         * user_id : 1
         * user_name : 哈哈
         * user_password : 123
         * user_email : 190766172@qq.com
         * user_sex : 男
         * user_qq : 190766172
         */

        private String user_phone;
        private String user_id;
        private String user_name;
        private String user_password;
        private String user_email;
        private String user_sex;
        private String user_qq;

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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public String getUser_qq() {
            return user_qq;
        }

        public void setUser_qq(String user_qq) {
            this.user_qq = user_qq;
        }

        @Override
        public String toString() {
            return "UserBean{" +
                    "user_phone='" + user_phone + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", user_password='" + user_password + '\'' +
                    ", user_email='" + user_email + '\'' +
                    ", user_sex='" + user_sex + '\'' +
                    ", user_qq='" + user_qq + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "msg='" + msg + '\'' +
                ", user=" + user +
                ", status='" + status + '\'' +
                '}';
    }
}
