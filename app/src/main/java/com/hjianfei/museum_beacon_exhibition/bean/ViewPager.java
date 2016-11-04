package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/8/26.
 */

public class ViewPager implements Serializable {

    /**
     * viewPagers : [{"id":71,"content":"荟雅南州\u2014\u2014明代广东文人的艺术与生活 展出时间：2016-9-9至2017-2-28 展出地点：三楼展厅三","detail_url":"http://www.gdmuseum.com/exhibit3_detail.php?picid=9297&LibID=41&gid=5&title=展览预告","img_url":"http://www.gdmuseum.com/attachment/201608/23/2_1471930640o6zj.jpg"},{"id":72,"content":"桃花源里人家\u2014\u2014徽州传统民居文化与艺术展 展出时间：2016-9-27至2016-11-27 展出地点：三楼展厅二","detail_url":"http://www.gdmuseum.com/exhibit3_detail.php?picid=11720&LibID=41&gid=6&title=展览预告","img_url":"http://www.gdmuseum.com/attachment/201608/25/2_1472109135zmm2.jpg"},{"id":73,"content":"问鼎\u2014\u2014楚式青铜器特展 展出时间：2016-6-17至2016-9-16 展出地点：三楼展厅二","detail_url":"http://www.gdmuseum.com/exhibit3_detail.php?picid=11180&LibID=43&gid=4&title=临时展览","img_url":"http://www.gdmuseum.com/attachment/201607/4/2_146759397911wW.jpg"},{"id":74,"content":"文物动物园\u2014\u2014儿童专题展 展出时间：2016-6-1至2016-10-8 展出地点：三楼展厅一","detail_url":"http://www.gdmuseum.com/exhibit3_detail.php?picid=11158&LibID=41&gid=3&title=展览预告","img_url":"http://www.gdmuseum.com/attachment/201605/31/2_1464738703UtSt.jpg"}]
     * status : 1
     */

    private String status;
    /**
     * id : 71
     * content : 荟雅南州——明代广东文人的艺术与生活 展出时间：2016-9-9至2017-2-28 展出地点：三楼展厅三
     * detail_url : http://www.gdmuseum.com/exhibit3_detail.php?picid=9297&LibID=41&gid=5&title=展览预告
     * img_url : http://www.gdmuseum.com/attachment/201608/23/2_1471930640o6zj.jpg
     */

    private List<ViewPagersBean> viewPagers;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ViewPagersBean> getViewPagers() {
        return viewPagers;
    }

    public void setViewPagers(List<ViewPagersBean> viewPagers) {
        this.viewPagers = viewPagers;
    }

    public static class ViewPagersBean {
        private int id;
        private String content;
        private String detail_url;
        private String img_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
