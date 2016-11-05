package com.hjianfei.museum_beacon_exhibition.canstants;

/**
 * Created by HJianFei on 2016/8/26.
 * <p>
 * Description: 接口 API 的URL
 */

public class Urls {

    //服务器地址
//    public static final String API_SERVER = "http://115.159.24.65:8080/BeaconSys/";
    public static final String API_SERVER = "http://192.168.191.1:8080/BeaconSys/";
    //首页ViewPager数据地址
    public static final String API_VIEWPAGER = "viewPager!getAllViewPager";
    //获取全部青花瓷之约、珍品鉴赏、专题鉴赏、自然标本的信息
    public static final String API_HOME_APPRECIATES_BY_TYPE = "appreciate!getAllAppreciate";
    //随机获取青花瓷之约、珍品鉴赏、专题鉴赏、自然标本的信息
    public static final String API_HOME_APPRECIATES_BY_RANDOM = "appreciate!getAppreciateByRandom";
    //获取文物鉴赏详情
    public static final String APPRECIATE_DETAIL = "appreciateDetail!getAppreciateDetails";
    //获取展览预告的信息
    public static final String IV_FORECAST_MORE = "exhibition!getFirstExhibition?type=0";
    //获取常设展览的信息
    public static final String IV_OFTEN_MORE = "exhibition!getFirstExhibition?type=1";
    //获取临时展览的信息
    public static final String IV_TEMP_MORE = "exhibition!getFirstExhibition?type=2";
    //获取展览回顾的信息
    public static final String IV_BACK_MORE = "exhibition!getFirstExhibition?type=3";
    //获取全部展览的信息type=0,1,2,3代表展览预告，常设展览，临时展厅，展览回顾
//    public static final String ALL_EXHIBITION_BY_TYPE = "exhibition!getAllExhibition";
    //展览预告(改)
    public static final String ALL_EXHIBITION_BY_TYPE = "exhibition!getAllExhibition";
    //获取全部教育数据
    //小知识
    public static final String ALL_EDUCATION_BY_TYPE = "education!getAllEducation";
    //教育详情信息
    public static final String EDUCATION_DETAIL = "educationDetail!getEducationDetails";
    //展览详情
    public static final String EXHIBITION_DETAIL = "exhibitionDetail!getExhibitionDetails";
    //导游
    public static final String NAVIGATION_ITEM = "navigationInfo!getNavigationInfos";

    //博物馆
    public static final String GET_ALL_MUSEUM = "museum!getAllMuseum";

}

