package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建时间： 2016/9/17.
 * 作者：HJianFei
 * 功能描述：
 */

public class Appreciates implements Serializable {

    /**
     * appreciates : [{"content":"明代景德镇窑青花花鸟纹瓣口折沿碗","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=11290&subgid=1&gid=160&title=�໨��֮Լ","id":235,"img_url":"http://www.gdmuseum.com/attachment/201607/12/2_1468309962hLlA.jpg","minor":"10001","type":"青花瓷之约"},{"content":"明代景德镇窑青花开光山石花卉纹军持","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=11291&subgid=2&gid=160&title=�໨��֮Լ","id":236,"img_url":"http://www.gdmuseum.com/attachment/201607/12/2_1468309962dDED.jpg","type":"青花瓷之约"}]
     * status : 1
     */

    private String status;
    /**
     * content : 明代景德镇窑青花花鸟纹瓣口折沿碗
     * detail_url : http://www.gdmuseum.com/curio_detail.php?picid=11290&subgid=1&gid=160&title=�໨��֮Լ
     * id : 235
     * img_url : http://www.gdmuseum.com/attachment/201607/12/2_1468309962hLlA.jpg
     * minor : 10001
     * type : 青花瓷之约
     */

    private List<AppreciatesBean> appreciates;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AppreciatesBean> getAppreciates() {
        return appreciates;
    }

    public void setAppreciates(List<AppreciatesBean> appreciates) {
        this.appreciates = appreciates;
    }

    public static class AppreciatesBean {
        private String content;
        private String detail_url;
        private int id;
        private String img_url;
        private String minor;
        private String type;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getMinor() {
            return minor;
        }

        public void setMinor(String minor) {
            this.minor = minor;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "AppreciatesBean{" +
                    "content='" + content + '\'' +
                    ", detail_url='" + detail_url + '\'' +
                    ", id=" + id +
                    ", img_url='" + img_url + '\'' +
                    ", minor='" + minor + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Appreciates{" +
                "status='" + status + '\'' +
                ", appreciates=" + appreciates +
                '}';
    }
}
