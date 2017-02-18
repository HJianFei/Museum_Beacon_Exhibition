package com.hjianfei.museum_beacon_exhibition.canstants;

/**
 * Created by HJianFei on 2016/8/26.
 * <p>
 * Description: 接口 API 的URL
 */

public class Urls {

    //服务器地址
//    public static final String API_SERVER = "http://115.159.24.65:8080/BeaconSys/";
    public static final String API_SERVER = "http://192.168.16.100:8080/BeaconSys/";
    //    public static final String API_SERVER = "http://123.207.250.79:8080/BeaconSys/";
    //首页ViewPager数据地址
    public static final String API_VIEWPAGER = "viewPager!getAllViewPager";
    //获取全部青花瓷之约、珍品鉴赏、专题鉴赏、自然标本的信息
    public static final String API_HOME_APPRECIATES_BY_TYPE = "appreciate!getAllAppreciate";
    //随机获取青花瓷之约、珍品鉴赏、专题鉴赏、自然标本的信息
    public static final String API_HOME_APPRECIATES_BY_RANDOM = "appreciate!getAppreciateByRandom";
    //获取文物鉴赏详情
    public static final String APPRECIATE_DETAIL = "appreciateDetail!getAppreciateDetails";
    //展览预告(改)
    public static final String ALL_EXHIBITION_BY_TYPE = "exhibition!getAllExhibition";
    //展览详情
    public static final String EXHIBITION_DETAIL = "exhibitionDetail!getExhibitionDetails";

    //博物馆
    public static final String GET_ALL_MUSEUM = "museum!listAllMuseum";

    //博物馆详情
    public static final String GET_MUSEUM_DETAIL = "museumDetail!getMuseumDetails";

    /**
     * 用户注册
     */
    public static final String REGISTER_USER = "user!saveUser";
    /**
     * 用户登录
     */
    public static final String LOGIN_USER = "user!getUserByPhone";
    /**
     * 更新Appreciate的查看次数
     */
    public static final String UPDATE_APPRECIATE_VIEW_COUNT = "appreciate!updateViewCount";
    /**
     * 更新Museum的查看次数
     */
    public static final String UPDATE_MUSEUM_VIEW_COUNT = "museum!updateViewCount";
    /**
     * 收藏（文物，博物馆，展厅）
     */
    public static final String COLLECTION_BY_TYPE = "collection!saveCollection";
    /**
     * 获取收藏列表（文物，博物馆，展厅）
     */
    public static final String LIST_COLLECTION_BY_TYPE = "collection!getAllCollectionByType";

    /**
     * 取消收藏（文物，博物馆，展厅）
     */
    public static final String DELETE_COLLECTION = "collection!deleteCollection";

    /**
     * app更新
     */
    public static final String GET_APP_UPDATE_INFO = "update!getAppUpdateInfo";

    /**
     * 意见反馈
     */
    public static final String FEEDBACK = "feed_back!saveFeedBack";

    /**
     * 更改用户密码
     */
    public static final String UPDATE_USER_PASSWORD = "user!updatePassword";

    /**
     * 更改手机号
     */
    public static final String CHANGE_USER_PHONE = "user!changePhone";

    /**
     * 更改用户名称
     */
    public static final String CHANGE_USER_NAME = "user!changeName";

    /**
     * 更改用户头像
     */
    public static final String CHANGE_USER_AVATAR="user!changeAvatar";

    /**
     * 获取浏览步骤
     */
    public static final String GET_STEP_VIEW = "stepView!getStepViewById";

    /**
     * 获取Beacon蓝牙基站发出的展品信息
     */
    public static final String GET_BEACON_APPRECIATE = "beaconAppreciate!getBeaconAppreciateByMinor";

    /**
     * 获取中国朝代信息
     */
    public static final String GET_CHINA_DENASTY = "chinaDynasty!getAllChinaDynasty";

    /**
     * 獲取朝代大事件
     */
    public static final String GET_CHINA_HISTORY_BIG_THINGS = "chinaHistoryBigThing!getChinaHistoryBigThings";

    /**
     * 隨機獲取歷史人物
     */
    public static final String GET_HISTORY_PEOPLE_BY_RANDOM = "chinaHistoryPeople!getChinaHistoryPeopleByRandom";

    /**
     * 获取朝代历史
     */
    public static final String GET_CHINA_HISTORY_HISTORY = "chinaHistoryHistory!getAllChinaHistoryHistory";

    /**
     * 获取朝代文化
     */
    public static final String GET_CHINA_HISTORY_CULTURE = "chinaHistoryCulture!getAllChinaHistoryCulture";

    /**
     * 获取朝代历史大事件详情信息
     */
    public static final String GET_HISTORY_BIG_THING_DETAIL = "chinaHistoryBigThingDetail!getChinaHistoryBigThingDetail";

    /**
     * 获取历史人物
     */
    public static final String GET_HISTORY_PEOPLE = "chinaHistoryPeople!getAllHistoryPeople";

    /**
     * 获取历史人物详情
     */
    public static final String GET_HISTORY_PEOPLE_DETAIL = "chinaHistoryPeopleDetail!getChinaHistoryPeopleDetail";

    /**
     * 获取朝代历史文化详情信息
     */
    public static final String GET_HISTORY_CULTURE_DETAIL = "chinaHistoryCultureDetail!getChinaHistoryCultureDetail";
    /**
     * 获取朝代历史详情信息
     */
    public static final String GET_HISTORY_HISTORY_DETAIL = "chinaHistoryHistoryDetail!getChinaHistoryHistoryDetail";

    /**
     * 获取历史战争列表
     */
    public static final String GET_HISTORY_OLDEN_WARS = "chinaHistoryOldenWar!getAllChinaHistoryOldenWars";

    /**
     * 获取历史战争详情信息
     */
    public static final String GET_HISTORY_OLDEN_WAR_DETAIL = "chinaHistoryOldenWarDetail!getChinaHistoryOldenWarDetails";

    /**
     * 获取历史盛世列表
     */
    public static final String GET_HISTORY_HEY_DAY = "chinaHistoryHeyDay!getAllChinaHistoryHeyDays";

    /**
     * 获取历史盛世详情
     */
    public static final String GET_HISTORY_HEY_DAY_INFO = "chinaHistoryHeyDayInFo!getChinaHistoryHeyDayInFo";

    /**
     * 获取历史盘点列表
     */
    public static final String GET_HISTORY_CHECK = "chinaHistoryCheck!getAllHistoryCheck";

    /**
     * 获取历史盘点详情
     */
    public static final String GET_HISTORY_CHECK_DETAIL = "chinaHistoryCheckDetail!getHistoryCheckDetail";

    /**
     * 获取外国历史
     */
    public static final String GET_FOREIGN_HISTORY = "foreignHistory!getForeignHistory";

    /**
     * 获取外国历史详情信息
     */
    public static final String GET_FOREIGN_HISTORY_DETAIL = "foreignHistoryDetail!getForeignHistoryDetail";
}

