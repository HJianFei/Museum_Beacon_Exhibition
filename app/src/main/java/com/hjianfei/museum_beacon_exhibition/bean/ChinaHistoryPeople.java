package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/12/7.
 */

public class ChinaHistoryPeople implements Serializable {


    /**
     * china_History_Peoples : [{"id":23,"type":"上古","name":"蚩尤","img_url":"http://www.todayonhistory.com/uploadfile/2015/0610/20150610050329589.jpg","views":795},{"id":24,"type":"上古","name":"黄帝","img_url":"http://www.todayonhistory.com/uploadfile/2015/0610/20150610052439915.jpg","views":795},{"id":25,"type":"上古","name":"炎帝","img_url":"http://www.todayonhistory.com/uploadfile/2015/0611/20150611092923996.jpg","views":795},{"id":26,"type":"上古","name":"伏羲","img_url":"http://www.todayonhistory.com/uploadfile/2015/0611/20150611094735617.jpg","views":795},{"id":27,"type":"上古","name":"颛顼","img_url":"http://www.todayonhistory.com/uploadfile/2015/0611/20150611101516372.jpg","views":795},{"id":861,"type":"上古","name":"仓颉","img_url":"http://www.todayonhistory.com/uploadfile/2015/1015/20151015033036576.jpg","views":795},{"id":863,"type":"上古","name":"大禹","img_url":"http://www.todayonhistory.com/uploadfile/2015/1015/20151015035705524.jpg","views":795},{"id":887,"type":"上古","name":"鲧","img_url":"http://www.todayonhistory.com/uploadfile/2015/1020/20151020025824855.jpg","views":795},{"id":888,"type":"上古","name":"后羿","img_url":"http://www.todayonhistory.com/uploadfile/2015/1020/20151020030910612.jpg","views":795},{"id":889,"type":"上古","name":"皋陶","img_url":"http://www.todayonhistory.com/uploadfile/2015/1020/20151020032026709.jpg","views":795}]
     * code : 200
     */

    private int code;
    private List<ChinaHistoryPeoplesBean> china_History_Peoples;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ChinaHistoryPeoplesBean> getChina_History_Peoples() {
        return china_History_Peoples;
    }

    public void setChina_History_Peoples(List<ChinaHistoryPeoplesBean> china_History_Peoples) {
        this.china_History_Peoples = china_History_Peoples;
    }

    public static class ChinaHistoryPeoplesBean implements Serializable {
        /**
         * id : 23
         * type : 上古
         * name : 蚩尤
         * img_url : http://www.todayonhistory.com/uploadfile/2015/0610/20150610050329589.jpg
         * views : 795
         */

        private int id;
        private String type;
        private String name;
        private String img_url;
        private int views;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        @Override
        public String toString() {
            return "ChinaHistoryPeoplesBean{" +
                    "id=" + id +
                    ", type='" + type + '\'' +
                    ", name='" + name + '\'' +
                    ", img_url='" + img_url + '\'' +
                    ", views=" + views +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ChinaHistoryPeople{" +
                "code=" + code +
                ", china_History_Peoples=" + china_History_Peoples +
                '}';
    }
}
