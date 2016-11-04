package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建时间： 2016/9/17.
 * 作者：HJianFei
 * 功能描述：
 */

public class Exhibitions implements Serializable {

    /**
     * exhibitions : [{"content":"相看两不厌\u2014\u2014馆藏明清瓷画与绘画的对视 2016-10-25至 2017-2-26三楼展厅一","detail_url":"http://www.gdmuseum.com/exhibit3_detail.php?picid=9299&LibID=41&gid=7&title=展览预告","id":1089,"img_url":"http://www.gdmuseum.com/attachment/201602/19/2_1455847877pPJ1.jpg","type":"0"},{"content":"桃花源里人家\u2014\u2014徽州传统民居文化与艺术展 2016-9-27至 2016-11-27三楼展厅二","detail_url":"http://www.gdmuseum.com/exhibit3_detail.php?picid=11720&LibID=41&gid=6&title=展览预告","id":1090,"img_url":"http://www.gdmuseum.com/attachment/201608/25/2_1472109820zBJH.jpg","type":"0"},{"content":"荟雅南州\u2014\u2014明代广东文人的艺术与生活 2016-9-9至 2017-2-28三楼展厅三","detail_url":"http://www.gdmuseum.com/exhibit3_detail.php?picid=9297&LibID=41&gid=5&title=展览预告","id":1091,"img_url":"http://www.gdmuseum.com/attachment/201602/19/2_1455847876pzpv.jpg","type":"0"}]
     * status : 1
     */

    private String status;
    /**
     * content : 相看两不厌——馆藏明清瓷画与绘画的对视 2016-10-25至 2017-2-26三楼展厅一
     * detail_url : http://www.gdmuseum.com/exhibit3_detail.php?picid=9299&LibID=41&gid=7&title=展览预告
     * id : 1089
     * img_url : http://www.gdmuseum.com/attachment/201602/19/2_1455847877pPJ1.jpg
     * type : 0
     */

    private List<ExhibitionsBean> exhibitions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ExhibitionsBean> getExhibitions() {
        return exhibitions;
    }

    public void setExhibitions(List<ExhibitionsBean> exhibitions) {
        this.exhibitions = exhibitions;
    }

    public static class ExhibitionsBean implements Serializable {
        private String content;
        private String detail_url;
        private int id;
        private String img_url;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "ExhibitionsBean{" +
                    "content='" + content + '\'' +
                    ", detail_url='" + detail_url + '\'' +
                    ", id=" + id +
                    ", img_url='" + img_url + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Exhibitions{" +
                "status='" + status + '\'' +
                ", exhibitions=" + exhibitions +
                '}';
    }
}
