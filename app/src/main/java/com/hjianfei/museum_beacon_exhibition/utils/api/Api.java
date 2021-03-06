package com.hjianfei.museum_beacon_exhibition.utils.api;


import com.hjianfei.museum_beacon_exhibition.bean.AppreciateDetail;
import com.hjianfei.museum_beacon_exhibition.bean.Appreciates;
import com.hjianfei.museum_beacon_exhibition.bean.BeaconAppreciate;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaDynasty;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryBigThing;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryBigThingDetail;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryCulture;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryCultureDetail;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHeyDay;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHistory;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryHistoryDetail;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryOldenWar;
import com.hjianfei.museum_beacon_exhibition.bean.ChinaHistoryPeople;
import com.hjianfei.museum_beacon_exhibition.bean.Collection;
import com.hjianfei.museum_beacon_exhibition.bean.ExhibitionDetail;
import com.hjianfei.museum_beacon_exhibition.bean.Exhibitions;
import com.hjianfei.museum_beacon_exhibition.bean.ForeignHistory;
import com.hjianfei.museum_beacon_exhibition.bean.ForeignHistoryDetail;
import com.hjianfei.museum_beacon_exhibition.bean.HistoryCheck;
import com.hjianfei.museum_beacon_exhibition.bean.HistoryCheckDetail;
import com.hjianfei.museum_beacon_exhibition.bean.HistoryHeyDayInFo;
import com.hjianfei.museum_beacon_exhibition.bean.HistoryPeopleDetail;
import com.hjianfei.museum_beacon_exhibition.bean.HistoryWarDetail;
import com.hjianfei.museum_beacon_exhibition.bean.LoginResult;
import com.hjianfei.museum_beacon_exhibition.bean.Museum;
import com.hjianfei.museum_beacon_exhibition.bean.MuseumDetail;
import com.hjianfei.museum_beacon_exhibition.bean.NotifyResult;
import com.hjianfei.museum_beacon_exhibition.bean.ResultCode;
import com.hjianfei.museum_beacon_exhibition.bean.StepView;
import com.hjianfei.museum_beacon_exhibition.bean.UpdateInfo;
import com.hjianfei.museum_beacon_exhibition.bean.UploadFile;
import com.hjianfei.museum_beacon_exhibition.bean.ViewPager;
import com.hjianfei.museum_beacon_exhibition.canstants.Urls;

import java.util.Map;


import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by HJianFei on 2016/8/29.
 */

public interface Api {
    //获取HomeFragment中ViewPager的数据
    @GET(Urls.API_VIEWPAGER)
    Observable<ViewPager> getViewPager();

    //随机获取文物鉴赏数据（四条）
    @GET(Urls.API_HOME_APPRECIATES_BY_RANDOM)
    Observable<Appreciates> getAllAppreciatesByRandom();

    //获取文物鉴赏数据
    @GET(Urls.API_HOME_APPRECIATES_BY_TYPE)
    Observable<Appreciates> getAllAppreciatesByType(@Query("museum_name") String museum_name, @Query("type") String type, @Query("page") String page, @Query("search_condition") String search_condition);

    //展览详情
    @GET(Urls.APPRECIATE_DETAIL)
    Observable<AppreciateDetail> getAppreciateDetails(@Query("id") String id);

    //获取全部展览信息(展览预告)
    @GET(Urls.ALL_EXHIBITION_BY_TYPE)
    Observable<Exhibitions> getAllExhibitionByType(@Query("type") String type);

    //展览详情
    @GET(Urls.EXHIBITION_DETAIL)
    Observable<ExhibitionDetail> getExhibitionDetails(@Query("detail_url") String detail_url);

    //获取全部博物馆信息
    @GET(Urls.GET_ALL_MUSEUM)
    Observable<Museum> getAllMuseums(@Query("type") String type, @Query("page") String page, @Query("search_condition") String search_condition);

    //获取博物馆详情信息
    @GET(Urls.GET_MUSEUM_DETAIL)
    Observable<MuseumDetail> getMuseumDetail(@Query("id") String id);

    //用户注册
    @POST(Urls.REGISTER_USER)
    @FormUrlEncoded
    Observable<ResultCode> registerUser(@FieldMap Map<String, Object> map);

    /**
     * 用户登录
     */
    @POST(Urls.LOGIN_USER)
    @FormUrlEncoded
    Observable<LoginResult> loginUser(@FieldMap Map<String, Object> map);

    /**
     * 更新appreciate的浏览次数
     */
    @POST(Urls.UPDATE_APPRECIATE_VIEW_COUNT)
    @FormUrlEncoded
    Observable<ResultCode> updateAppreciateViewCount(@FieldMap Map<String, Object> map);

    /**
     * 更新museum的浏览次数
     */
    @POST(Urls.UPDATE_MUSEUM_VIEW_COUNT)
    @FormUrlEncoded
    Observable<ResultCode> updateMuseumViewCount(@FieldMap Map<String, Object> map);

    /**
     * 收藏（文物，博物馆，展厅）
     */
    @POST(Urls.COLLECTION_BY_TYPE)
    @FormUrlEncoded
    Observable<ResultCode> saveCollection(@FieldMap Map<String, Object> map);

    /**
     * 获取收藏列表（文物，博物馆，展厅）
     */
    @POST(Urls.LIST_COLLECTION_BY_TYPE)
    Observable<Collection> getCollectionList(@Query("user_phone") String phone, @Query("type") String type, @Query("page") String page);

    /**
     * 取消收藏（文物，博物馆，展厅）
     */
    @POST(Urls.DELETE_COLLECTION)
    @FormUrlEncoded
    Observable<ResultCode> deleteCollection(@FieldMap Map<String, Object> map);

    /**
     * app更新信息
     */
    @GET(Urls.GET_APP_UPDATE_INFO)
    Observable<UpdateInfo> getAppUpdateInfo();

    /**
     * 下载App安装包
     *
     * @param app_name
     * @return
     */
    @GET("app/{app_name}")
    Call<ResponseBody> loadFile(@Path("app_name") String app_name);

    /**
     * 意见反馈
     */
    @POST(Urls.FEEDBACK)
    @FormUrlEncoded
    Observable<ResultCode> saveFeedBack(@FieldMap Map<String, Object> map);

    /**
     * 修改密码
     */
    @POST(Urls.UPDATE_USER_PASSWORD)
    @FormUrlEncoded
    Observable<ResultCode> updateUserPassword(@FieldMap Map<String, Object> map);

    /**
     * 修改手机号码
     */
    @POST(Urls.CHANGE_USER_PHONE)
    @FormUrlEncoded
    Observable<ResultCode> changeUserPhone(@FieldMap Map<String, Object> map);

    /**
     * 修改用户名
     */
    @POST(Urls.CHANGE_USER_NAME)
    @FormUrlEncoded
    Observable<ResultCode> changeUserName(@FieldMap Map<String, Object> map);

    /**
     * 获取浏览步骤
     */
    @GET(Urls.GET_STEP_VIEW)
    Observable<StepView> getStepView(@Query("beacon_id") String beacon_id);

    /**
     * 获取Beacon蓝牙基站发出的信号
     *
     * @param minor
     * @return
     */
    @GET(Urls.GET_BEACON_APPRECIATE)
    Observable<BeaconAppreciate> getBeaconAppreciateByMinor(@Query("minor") String minor);

    /**
     * 獲取中國歷史朝代
     *
     * @param page
     * @return
     */
    @GET(Urls.GET_CHINA_DENASTY)
    Observable<ChinaDynasty> getChinaDynasty(@Query("page") String page);

    /**
     * 保存图片
     *
     * @param fileUrl
     * @return
     */
    @GET
    Observable<ResponseBody> downloadPicFromNet(@Url String fileUrl);

    @POST(Urls.CHANGE_USER_AVATAR)
    Observable<ResultCode> uploadFile(@Body MultipartBody file, @Query("user_phone") String user_phone);


    /**
     * 獲取中國歷史朝代大事件
     *
     * @param type
     * @param page
     * @return
     */
    @GET(Urls.GET_CHINA_HISTORY_BIG_THINGS)
    Observable<ChinaHistoryBigThing> getChinaHistoryBigThings(@Query("type") String type, @Query("page") String page);

    /**
     * 随机获取历史人物
     *
     * @param type
     * @return
     */
    @GET(Urls.GET_HISTORY_PEOPLE_BY_RANDOM)
    Observable<ChinaHistoryPeople> getChinaHistoryPeopleByRandom(@Query("type") String type);

    /**
     * 获取朝代历史
     *
     * @param type
     * @param page
     * @param search_condition
     * @return
     */
    @GET(Urls.GET_CHINA_HISTORY_HISTORY)
    Observable<ChinaHistoryHistory> getChinaHistoryHistory(@Query("type") String type, @Query("page") String page, @Query("search_condition") String search_condition);

    /**
     * 获取朝代文化
     *
     * @param type
     * @param page
     * @param search_condition
     * @return
     */
    @GET(Urls.GET_CHINA_HISTORY_CULTURE)
    Observable<ChinaHistoryCulture> getChinaHistoryCulture(@Query("type") String type, @Query("page") String page, @Query("search_condition") String search_condition);

    /**
     * 获取朝代大事件详情信息
     *
     * @param title
     * @return
     */
    @GET(Urls.GET_HISTORY_BIG_THING_DETAIL)
    Observable<ChinaHistoryBigThingDetail> getChinaHistoryBigThingDetail(@Query("id") String id);

    /**
     * 获取历史人物列表
     *
     * @param type
     * @param page
     * @param search_condition
     * @return
     */
    @GET(Urls.GET_HISTORY_PEOPLE)
    Observable<ChinaHistoryPeople> getChinaHistoryPeople(@Query("type") String type, @Query("page") String page, @Query("search_condition") String search_condition);

    /**
     * 获取历史人物详情
     *
     * @param detail_url
     * @return
     */
    @GET(Urls.GET_HISTORY_PEOPLE_DETAIL)
    Observable<HistoryPeopleDetail> getChinaHistoryPeopleDetail(@Query("id") String id);

    /**
     * 获取朝代历史文化详情
     *
     * @param title
     * @return
     */
    @GET(Urls.GET_HISTORY_CULTURE_DETAIL)
    Observable<ChinaHistoryCultureDetail> getChinaHistoryCultureDetail(@Query("id") String id);

    /**
     * 获取朝代历史详情
     *
     * @param title
     * @return
     */
    @GET(Urls.GET_HISTORY_HISTORY_DETAIL)
    Observable<ChinaHistoryHistoryDetail> getChinaHistoryHistoryDetail(@Query("id") String id);

    /**
     * 获取历史战争
     *
     * @param type
     * @param page
     * @param search_condition
     * @return
     */
    @GET(Urls.GET_HISTORY_OLDEN_WARS)
    Observable<ChinaHistoryOldenWar> getChinaHistoryOldenWars(@Query("type") String type, @Query("page") String page, @Query("search_condition") String search_condition);

    /**
     * 获取历史战争详情
     *
     * @param detail_url
     * @return
     */
    @GET(Urls.GET_HISTORY_OLDEN_WAR_DETAIL)
    Observable<HistoryWarDetail> getHistoryOldenWarDetail(@Query("id") String id);

    /**
     * 获取历史盛世列表
     *
     * @param page
     * @param search_condition
     * @return
     */
    @GET(Urls.GET_HISTORY_HEY_DAY)
    Observable<ChinaHistoryHeyDay> getChinaHistoryHeyDay(@Query("page") String page, @Query("search_condition") String search_condition);

    /**
     * 获取历史盛世详情
     *
     * @param title
     * @return
     */
    @GET(Urls.GET_HISTORY_HEY_DAY_INFO)
    Observable<HistoryHeyDayInFo> getHistoryHeyDayInFo(@Query("id") String id);

    /**
     * 获取历史盘点列表
     *
     * @param page
     * @param search_condition
     * @return
     */
    @GET(Urls.GET_HISTORY_CHECK)
    Observable<HistoryCheck> getHistoryCheck(@Query("page") String page, @Query("search_condition") String search_condition);

    /**
     * 获取历史盘点详情
     *
     * @param title
     * @return
     */
    @GET(Urls.GET_HISTORY_CHECK_DETAIL)
    Observable<HistoryCheckDetail> getHistoryCheckDetail(@Query("id") String id);

    /**
     * 获取外国历史列表
     *
     * @param country
     * @param type
     * @param page
     * @param search_condition
     * @return
     */
    @GET(Urls.GET_FOREIGN_HISTORY)
    Observable<ForeignHistory> getForeignHistory(@Query("country") String country, @Query("type") String type, @Query("page") String page, @Query("search_condition") String search_condition);

    /**
     * 获取外国历史详情
     *
     * @param title
     * @return
     */
    @GET(Urls.GET_FOREIGN_HISTORY_DETAIL)
    Observable<ForeignHistoryDetail> getForeignHistoryDetail(@Query("id") String id);

    /**
     * 获取公告
     *
     * @param notify_id
     * @return
     */
    @GET(Urls.GET_NOTIFY)
    Observable<NotifyResult> getNotify(@Query("notify_id") String notify_id);


}
