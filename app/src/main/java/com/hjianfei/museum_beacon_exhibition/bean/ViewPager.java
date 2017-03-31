package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/8/26.
 */

public class ViewPager implements Serializable {

    /**
     * code : 200
     * viewPagers : [{"id":71,"content":"荟雅南州\u2014\u2014明代广东文人的艺术与生活 展出时间：2016-9-9至2017-2-28 展出地点：三楼展厅三","detail_url":"http://www.gdmuseum.com/exhibit3_detail.php?picid=9297&LibID=41&gid=5&title=展览预告","img_url":"pictures/banner1.png"},{"id":72,"content":"桃花源里人家\u2014\u2014徽州传统民居文化与艺术展 展出时间：2016-9-27至2016-11-27 展出地点：三楼展厅二","detail_url":"http://www.gdmuseum.com/exhibit3_detail.php?picid=11720&LibID=41&gid=6&title=展览预告","img_url":"pictures/banner3.png"},{"id":73,"content":"君子之风\u2014\u2014传统梅兰竹菊绘画展 2014-12-5至 2015-4-19三楼书画厅","detail_url":"http://www.gdmuseum.com/exhibit3_detail.php?picid=7560&LibID=44&gid=117&title=展览预告","img_url":"pictures/banner2.png"}]
     */

    private int code;
    private List<ViewPagersBean> viewPagers;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ViewPagersBean> getViewPagers() {
        return viewPagers;
    }

    public void setViewPagers(List<ViewPagersBean> viewPagers) {
        this.viewPagers = viewPagers;
    }

    public static class ViewPagersBean {
        /**
         * id : 71
         * content : 荟雅南州——明代广东文人的艺术与生活 展出时间：2016-9-9至2017-2-28 展出地点：三楼展厅三
         * detail_url : http://www.gdmuseum.com/exhibit3_detail.php?picid=9297&LibID=41&gid=5&title=展览预告
         * img_url : pictures/banner1.png
         */

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
