package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;

/**
 * Created by HJianFei on 2016/11/25.
 */

public class BeaconAppreciate implements Serializable {

    /**
     * code : 200
     * BeaconAppreciate : {"minor":"1001","major":"2002","img_url":"http://www.gdmuseum.com/attachment/201602/22/2_1456104643guqv.jpg","title":"青铜器","content":"青铜器","video_url":"http://www.gdmuseum.com/attachment/201602/22/2_1456104643guqv.jpg","audio_url":"http://www.gdmuseum.com/attachment/201602/22/2_1456104643guqv.jpg","is_new":true}
     */

    private int code;
    private BeaconAppreciateBean BeaconAppreciate;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BeaconAppreciateBean getBeaconAppreciate() {
        return BeaconAppreciate;
    }

    public void setBeaconAppreciate(BeaconAppreciateBean BeaconAppreciate) {
        this.BeaconAppreciate = BeaconAppreciate;
    }

    public static class BeaconAppreciateBean implements Serializable{
        /**
         * minor : 1001
         * major : 2002
         * img_url : http://www.gdmuseum.com/attachment/201602/22/2_1456104643guqv.jpg
         * title : 青铜器
         * content : 青铜器
         * video_url : http://www.gdmuseum.com/attachment/201602/22/2_1456104643guqv.jpg
         * audio_url : http://www.gdmuseum.com/attachment/201602/22/2_1456104643guqv.jpg
         * is_new : true
         */

        private String minor;
        private String major;
        private String img_url;
        private String title;
        private String content;
        private String video_url;
        private String audio_url;
        private boolean is_new;

        public String getMinor() {
            return minor;
        }

        public void setMinor(String minor) {
            this.minor = minor;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getAudio_url() {
            return audio_url;
        }

        public void setAudio_url(String audio_url) {
            this.audio_url = audio_url;
        }

        public boolean isIs_new() {
            return is_new;
        }

        public void setIs_new(boolean is_new) {
            this.is_new = is_new;
        }
    }
}
