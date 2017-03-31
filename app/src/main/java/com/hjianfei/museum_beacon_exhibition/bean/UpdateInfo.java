package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;

/**
 * Created by HJianFei on 2016/11/20.
 */

public class UpdateInfo implements Serializable {

    /**
     * appUpdateInfo : {"id":1,"error_code":0,"error_msg":"0","update_time":"2016-11-22","curVersion":"2","appURL":"app/app-release_legu_signed.apk","description":"更新更新","minVersion":"Android 5.0","appName":"博物展"}
     */

    private AppUpdateInfoBean appUpdateInfo;

    public AppUpdateInfoBean getAppUpdateInfo() {
        return appUpdateInfo;
    }

    public void setAppUpdateInfo(AppUpdateInfoBean appUpdateInfo) {
        this.appUpdateInfo = appUpdateInfo;
    }

    public static class AppUpdateInfoBean {
        /**
         * id : 1
         * error_code : 0
         * error_msg : 0
         * update_time : 2016-11-22
         * curVersion : 2
         * appURL : app/app-release_legu_signed.apk
         * description : 更新更新
         * minVersion : Android 5.0
         * appName : 博物展
         */

        private int id;
        private int error_code;
        private String error_msg;
        private String update_time;
        private String curVersion;
        private String appURL;
        private String description;
        private String minVersion;
        private String appName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

        public String getError_msg() {
            return error_msg;
        }

        public void setError_msg(String error_msg) {
            this.error_msg = error_msg;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getCurVersion() {
            return curVersion;
        }

        public void setCurVersion(String curVersion) {
            this.curVersion = curVersion;
        }

        public String getAppURL() {
            return appURL;
        }

        public void setAppURL(String appURL) {
            this.appURL = appURL;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMinVersion() {
            return minVersion;
        }

        public void setMinVersion(String minVersion) {
            this.minVersion = minVersion;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        @Override
        public String toString() {
            return "AppUpdateInfoBean{" +
                    "id=" + id +
                    ", error_code=" + error_code +
                    ", error_msg='" + error_msg + '\'' +
                    ", update_time='" + update_time + '\'' +
                    ", curVersion='" + curVersion + '\'' +
                    ", appURL='" + appURL + '\'' +
                    ", description='" + description + '\'' +
                    ", minVersion='" + minVersion + '\'' +
                    ", appName='" + appName + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "appUpdateInfo=" + appUpdateInfo +
                '}';
    }
}
