package com.bdhs.hzinsurance.api;


import com.bdhs.hzinsurance.api.presenters.updateapp.CheckVersionEntity;
import com.bdhs.hzinsurance.entity.EvaluateBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface ApiService {

    String GET_ARTICLE_LIST = "api/news/feed/v62/?refer=1&count=20&loc_mode=4&device_id=34960436458&iid=13136511752";
    String GET_COMMENT_LIST = "article/v2/tab_comments/";
    //http://is.snssdk.com
    //http://is.snssdk.com/api/news/feed/v54/?refer=1&count=20&min_behot_time=1498722625&last_refresh_sub_entrance_interval=1498724693&loc_mode=4&tt_from=pull（tab_tip） 新闻列表
    //http://is.snssdk.com/article/v2/tab_comments/?group_id=6436886053704958466&item_id=6436886053704958466&offset=30&count=20 评论
    //http://is.snssdk.com/2/article/information/v21/ 详情


    @GET("//index/get_comment/")
    Observable<EvaluateBean> getEvaluate(@Query("mobile") String phoneNum);

    //检查更新
    @GET("index.php/app/Pag/new/")
    Observable<CheckVersionEntity> checkAPPVersion(@QueryMap Map<String, Object> params);

    //验证此手机号码是否是注册用户
//    @GET("/app/login/getMobileCode")
//    Observable<ResultResponse> getCheckPhoneResult(@Query("mobile") String phoneNum);
//
//    //验证手机号码以及验证码是否正确
//    @GET("/app/login/getToken")
//    Observable<ResultResponse<CheckMobileCodeResponse>> getCheckCodeResult(@Query("mobile") String mobile, @Query("mobile_code") String checkcode);
//
//    //主页
//    @GET("/app/staff/index")
//    Observable<ResultResponse<HomeResponse>> getHomeInfo(@Query("token") String token);
//
//
//    //人员信息
//    @GET("/app/staff/staff_center")
//    Observable<ResultResponse<PersonInfoResponse>> getPersonInfo(@Query("token") String token);
//
//    //获取档期
//    @GET("/app/onduty/index")
//    Observable<ResultResponse<ScheduleResponse>> getMySchedule(@Query("token") String token, @Query("date") String data);
//
//    //提交档期
//    @GET("/app/onduty/at_onduty")
//    Observable<ResultResponse> commitNewSchedule(@Query("token") String token, @Query("start_date") String startdate, @Query("end_date") String enddate);
//
//
//    //设置极光别名
//    @GET("/app/staff/setAlias")
//    Observable<ResultResponse> setJigAlias(@Query("token") String token, @Query("registration_id") String registration_id);
//
////    //检查是否有新的版本
////    @GET("/app/staff/setAlias")
////    Observable<UpdateResponse> checkVersion(@Query("token") String token, @Query("versioncode") int versioncode);
//
//    //------------------------------------面试--------------------------------//
//    //从本地服务器获取面试的相关视频信息
//    @GET("/app/staff/video_list")
//    Observable<ResultResponse<VideoIDsResponse>> getVideoIDs(@Query("token") String token);
//
//    //从本地服务器获取上传视频需要用到的credentials
////    @GET("/app/aliyun_sts/get_acs")
////    Observable<STSResponse> getCredentials(@Query("token") String token);
//
//    @FormUrlEncoded
//    @POST("/app/aliyun_sts/get_acs")
//    Observable<STSResponse> getCredentials(@Field("token") String token);
//
//    //把视频的相关的信息VideoId上传给本地服务器
//    @GET("/app/staff/certify_video")
//    Observable<ResultResponse<SaveVideoResponse>> saveVideoId(@QueryMap Map<String, Object> params);
//
//    //“提交面试”接口
//    @GET("/app/staff/save_certify")
//    Observable<ResultResponse<SaveVideoResponse>> commitInterview(@QueryMap Map<String, Object> params);
//
//
//    //“分享成功”接口
//    @GET("/app/staff_article/share_article")
//    Observable<ResultResponse> shareCommit(@Query("token") String token, @Query("article_id") String article_id);
    /**
     * 获取新闻列表
     *
     * @param category 频道
     * @return
     */
//    @GET(GET_ARTICLE_LIST)
//    Observable<HomeResponse> getNewsList(@Query("category") String category, @Query("min_behot_time") long lastTime, @Query("last_refresh_sub_entrance_interval") long currentTime);

    /**
     * 获取新闻详情
     */
//    @GET
//    Observable<ResultResponse<NewsDetail>> getNewsDetail(@Url String url);

    /**
     * 获取评论列表数据
     *
     * @param groupId
     * @param itemId
     * @param offset
     * @param count
     * @return
     */
//    @GET(GET_COMMENT_LIST)
//    Observable<CommentResponse> getComment(@Query("group_id") String groupId, @Query("item_id") String itemId, @Query("offset") String offset, @Query("count") String count);

    /**
     * 获取视频数据json
     *
     * @param url
     * @return
     */
//    @GET
//    Observable<ResultResponse<VideoModel>> getVideoData(@Url String url);

//    @Headers({
//            "Content-Type:application/x-www-form-urlencoded; charset=UTF-8",
//            "Cookie:PHPSESSIID=334267171504; _ga=GA1.2.646236375.1499951727; _gid=GA1.2.951962968.1507171739; Hm_lvt_e0a6a4397bcb500e807c5228d70253c8=1507174305;Hm_lpvt_e0a6a4397bcb500e807c5228d70253c8=1507174305; _gat=1",
//            "Origin:http://toutiao.iiilab.com"
//
//    })
//    @POST("http://service.iiilab.com/video/toutiao")
//    Observable<VideoPathResponse> getVideoPath(@Query("link") String link, @Query("r") String r, @Query("s") String s);
}

