package com.zzq.zzq_collapsing_demo_master.api;


import com.zzq.zzq_collapsing_demo_master.entity.WelfareEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 */
public interface HttpApi {


    /**
     *
     */
    @GET("api/4/news/latest")
    rx.Observable<WelfareEntity> getRetureResult();

//    http://gank.io/api/data/福利/

    /**
     * 获取首页信息   Retrofit
     */

    @GET("data/福利/{pageSize}/{page}")
    rx.Observable<WelfareEntity> getHomeResult(@Path("pageSize") int pageSize, @Path("page") int page);


//    rx.Observable<ApiResponse<HomeEntity>> getHomeInfo(@Query("loginUserId") Long loginUserId);

    /**
     * 首页展示
     */
    /*
     * @param loginUserId  用户ID（当用户登录后必须传入）
     * @return
     */
    /*
    @GET("app/user/home.api")
    rx.Observable<ApiResponse<HomeEntity>> getHomeInfo(@Query("loginUserId") Long loginUserId);

    *//**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return
     *//*
    @GET("app/user/login.api")
    rx.Observable<ApiResponse<LoginRegisterEntity>> loginApi(@Query("account") String account, @Query("password") String password);


    *//**
     * 获取短信验证码
     *
     * @param deviceKey 设备唯一ID
     * @param phone     用户电话
     * @return
     *//*
    @GET("app/user/sendMsgCode.api")
    rx.Observable<ApiResponse<Object>> sendMsgCode(@Query("deviceKey") String deviceKey, @Query("phone") String phone);


    *//**
     * 注册
     *
     * @param account   账号
     * @param password  用户密码
     * @param tag       用户标签：1:厂长；2:兽医；3:技术员；0:其他（没选择标签默认是0）
     * @param deviceKey 设备唯一ID
     * @param code      验证码
     * @return
     *//*
    @GET("app/user/register.api")
    rx.Observable<ApiResponse<LoginRegisterEntity>> register(@Query("account") String account, @Query("password") String password, @Query("tag") int tag, @Query("deviceKey") String deviceKey, @Query("code") String code);


    *//**
     * 验证电话号跟验证码
     *
     * @param phone
     * @param deviceKey 设备唯一ID
     * @param code      验证码
     * @return
     *//*
    @GET("app/user/verityPhone.api")
    rx.Observable<ApiResponse<Object>> verityPhone(@Query("deviceKey") String deviceKey, @Query("phone") String phone, @Query("code") String code);


    *//**
     * 修改密码
     *
     * @param phone
     * @param newPassword     新密码
     * @param confirmPassword 确认密码
     * @return
     *//*
    @GET("app/user/changePassword.api")
    rx.Observable<ApiResponse<Object>> changePassword(@Query("phone") String phone, @Query("newPassword") String newPassword, @Query("confirmPassword") String confirmPassword);


    *//**
     * 打赏6S人员
     * 需要登录
     *
     * @param loginUserId 登陆人员id
     * @param user6SId    6S人员id
     * @param coins       猪币数量
     * @return
     *//*
    @GET("app/6s/reward.api")
    rx.Observable<ApiResponse<Object>> reward(@Query("loginUserId") int loginUserId, @Query("user6SId") int user6SId, @Query("coins") int coins);


    *//**
     * 6S人员赞数量+1
     * 需要登录
     *
     * @param loginUserId 登陆人员id
     * @param user6SId    6S人员id
     * @return
     *//*
    @GET("app/6s/plusPraise.api")
    rx.Observable<ApiResponse<Object>> plusPraise(@Query("loginUserId") int loginUserId, @Query("user6SId") int user6SId);


    *//**
     * 6S人员答题数量+1
     * 需要登录
     *
     * @param loginUserId 登陆人员id
     * @param user6SId    6S人员id
     * @return
     *//*
    @GET("app/6s/plusAnswer.api")
    rx.Observable<ApiResponse<Object>> plusAnswer(@Query("loginUserId") int loginUserId, @Query("user6SId") int user6SId);


    *//**
     * 6s人员列表
     *
     * @return
     *//*
    @GET("app/6s/list.api")
    rx.Observable<ApiResponse<List<SixSEntity>>> get6sList();


    *//**
     * 我的礼品
     * 需要登录
     *
     * @param loginUserId 安佑云账号ID
     * @return
     *//*
    @GET("app/user/myPrize.api")
    rx.Observable<ApiResponse<List<MyPrizeEntity>>> getMyPrizeList(@Query("loginUserId") int loginUserId);


    *//**
     * 活动信息页面
     *
     * @param loginUserId 用户账号（当用户登录后必须传入）
     * @param activityId   活动ID
     * @return
     *//*
    @GET("app/activity/detail.api")
    rx.Observable<ApiResponse<ActivitiesDetailEntity>> getActivityDetail(@Query("loginUserId") long loginUserId, @Query("activityId") long activityId);



    *//**
     * 具体活动页面的topic列表
     *
     * @param loginUserId 安佑云账号ID
     * @param activityId 活动ID
     * @param orderType 1为精选 2为最新
     * @param pageIndex 翻页索引号
     * @return
     *//*
    @GET("app/topic/list.api")
    rx.Observable<ApiResponse<TopicInfoEntity>> getTopicList(@Query("loginUserId") int loginUserId, @Query("activityId") int activityId, @Query("orderType") int orderType, @Query("pageIndex") int pageIndex);


    *//**
     * 会讯列表
     *
     * @param loginUserId 用户账号（当用户登录后必须传入）
     * @param pageIndex   默认0（第一页）,根据页码返回会讯列表
     * @param flag 默认0（所有），1-今年会讯，2-明年会讯
     * @return
     *//*
    @GET("app/meetingInfo.api")
    rx.Observable<ApiResponse<List<MeetingEntity>>> getMeetingList(@Query("loginUserId") int loginUserId, @Query("pageIndex") int pageIndex, @Query("flag") int flag);


    *//**
     * 对Topic点赞/踩
     * 需要登录
     *
     * @param loginUserId 用户账号（当用户登录后必须传入）
     * @param activityId  活动id
     * @param topicId     主题id
     * @param isGood      赞or踩 0:踩;1:赞
     * @return
     *//*
    @GET("app/topic/praise.api")
    rx.Observable<ApiResponse<Object>> topicPraise(@Query("activityId") int activityId, @Query("loginUserId") int loginUserId, @Query("topicId") int topicId, @Query("isGood") int isGood);


    *//**
     * 用户签到
     * 需要登录
     *
     * @param loginUserId 用户账号（当用户登录后必须传入）
     * @return
     *//*
    @POST("app/user/sign.api")
    rx.Observable<ApiResponse<Object>> sign(@Query("loginUserId") int loginUserId);


    *//**
     * 我的猪币
     * 需要登录
     *
     * @param loginUserId 用户账号（当用户登录后必须传入）
     * @return
     *//*
    @GET("app/user/myPigCoins.api")
    rx.Observable<ApiResponse<MyPigCoinEntity>> getMyPigCoins(@Query("loginUserId") int loginUserId);


    *//**
     * 猪币记录
     * 需要登录
     *
     * @param loginUserId 用户账号（当用户登录后必须传入）
     * @return
     *//*
    @GET("app/user/pigCoinsRecord.api")
    rx.Observable<ApiResponse<List<PigCoinsRecordEntity>>> getPigCoinsRecord(@Query("loginUserId") int loginUserId);



    *//**
     * 兑换礼品
     * 需要登录
     *
     * @param loginUserId 用户账号（当用户登录后必须传入）
     * @param prizeId 奖品ID
     * @return
     *//*
    @GET("app/user/exchangePrize.api")
    rx.Observable<ApiResponse<Object>> exchangePrize(@Query("loginUserId") int loginUserId, @Query("prizeId") int prizeId);



    *//**
     * 抽奖页面
     * @return
     *//*
    @GET("app/user/lottery.api")
    rx.Observable<ApiResponse<LotteryEntity>> lottery();


    *//**
     * 抽奖
     * 需要登录
     *
     * @param loginUserId 用户账号（当用户登录后必须传入）
     * @return
     *//*
    @GET("app/user/lucky.api")
    rx.Observable<ApiResponse<LotteryPrizesEntity>> lucky(@Query("loginUserId") int loginUserId);


    *//**
     * 药品列表
     *
     * @return
     *//*
    @GET("app/disease/drugList.api")
    rx.Observable<ApiResponse<List<DrugListEntity>>> drugList();



    *//**
     * 药品详细
     *
     * @param drugId 药品id
     * @return
     *//*
    @GET("app/disease/drugDetail.api")
    rx.Observable<ApiResponse<DrugDetailEntity>> drugDetail(@Query("drugId") int drugId);


    *//**
     * 消毒剂列表
     *
     * @return
     *//*
    @GET("app/disease/disinfectorList.api")
    rx.Observable<ApiResponse<List<DisInfectorListEntity>>> disinfectorList();


    *//**
     * 消毒剂详细
     *
     * @param disinfectorId 消毒剂id
     * @return
     *//*
    @GET("app/disease/disinfectorDetail.api")
    rx.Observable<ApiResponse<DisinfectorDetailEntity>> disinfectorDetail(@Query("disinfectorId") int disinfectorId);



    *//**
     * 疫苗列表
     *
     * @return
     *//*
    @GET("app/disease/vaccineList.api")
    rx.Observable<ApiResponse<List<VaccineListEntity>>> vaccineList();


    *//**
     * 疫苗详细
     *
     * @param vaccineId 疫苗id
     * @return
     *//*
    @GET("app/disease/vaccineDetail.api")
    rx.Observable<ApiResponse<VaccineDetailEntity>> vaccineDetail(@Query("vaccineId") int vaccineId);



    *//**
     * 疾病列表
     *
     * @return
     *//*
    @GET("app/disease/diseaseList.api")
    rx.Observable<ApiResponse<List<DiseaseListEntity>>> diseaseList();


    *//**
     * 疾病详细
     *
     * @param diseaseId 疾病id
     * @return
     *//*
    @GET("/app/disease/diseaseDetail.api")
    rx.Observable<ApiResponse<DiseaseDetailEntity>> diseaseDetail(@Query("diseaseId") int diseaseId);


    *//**
     * 获取绑定猪场接口
     * 需要登录
     *
     * @param companyType 公司类型（PORKFARM：科技农场，PIGFARM：猪事通）
     * @param loginUserId 安佑云账号ID
     * @return
     *//*
    @GET("app/user/bindingList.api")
    rx.Observable<ApiResponse<List<BindingPigEntity>>> getBindingList(@Query("companyType") String companyType, @Query("loginUserId") int loginUserId);



    *//**
     * 安佑文库
     * 需要登录
     *
     * @param pageIndex 从0开始，不传则默认为0
     * @param  orderType 1：最热2：最新;不传默认为1
     * @return
     *//*
    @GET("app/ansLibrary/list.api")
    rx.Observable<ApiResponse<List<AnsLibraryEntity>>> getAnsLibraryList(@Query("pageIndex") int pageIndex, @Query("orderType") int orderType);



    *//**
     * 专家列表
     *
     * @return
     *//*
    @GET("app/ansExpert/list.api")
    rx.Observable<ApiResponse<List<AnsExpertInfoEntity>>> getAnsExpertList();


    *//**
     * 预约专家详情
     * 需要登录
     * @param expertId 专家ID
     * @param loginUserId  登录用户ID

     * @return
     *//*
    @GET("app/ansExpert/appointmentDetail.api")
    rx.Observable<ApiResponse<BookAnsExpertDetailEntity>> bookAnsExpertDetail(@Query("expertId") int expertId, @Query("loginUserId") int loginUserId);


    *//**
     * 预约专家时间
     * 需要登录
     * @param expertId 专家ID
     * @param date 日期，例:2016-07-29
     * @return
     *//*
    @GET("app/ansExpert/appointmentTime.api")
    rx.Observable<ApiResponse<int[]>> getBookAnsExpertTime(@Query("expertId") int expertId, @Query("date") String date);


    *//**
     * 预约专家
     * 需要登录
     * @param payType 支付方式 1:猪币 2:预约卡
     * @param timeType 时间类型
     * @param date 日期，例:2016-07-29
     * @return
     *//*
    @GET("app/ansExpert/appoint.api")
    rx.Observable<ApiResponse<Object>> bookAnsExpert(@Query("loginUserId") int loginUserId, @Query("expertId") int expertId, @Query("phone") String phone, @Query("payType") int payType, @Query("date") String date, @Query("timeType") int timeType);


    *//**
     * 我的消息
     * 需要登录
     * @param pageIndex 默认0（第一页）,根据页码返回消息列表
     * @param loginUserId 安佑云账号ID
     * @return
     *//*
    @GET("app/myMessage/list.api")
    rx.Observable<ApiResponse<List<MyMessageEntity>>> getMyMessageList(@Query("pageIndex") int pageIndex, @Query("loginUserId") int loginUserId);


    *//**
     * 自助服务
     *
     * @return
     *//*
    @GET("app/ansLibrary/fulltextSearch.api")
    rx.Observable<ApiResponse<List<MaybeResultEntity>>> fulltextSearch(@Query("pageIndex") int pageIndex, @Query("search") String search, @Query("tag") int tag);


    *//**
     * 意见反馈
     *@param deviceType 系统。1-iOS，2-安卓
     * @return
     *//*
    @GET("app/suggestion/submit.api")
    rx.Observable<ApiResponse<Object>> feedBackSubmit(@Query("loginUserId") int loginUserId, @Query("content") String content, @Query("deviceType") int deviceType);


    *//**
     * 获取可修改选择的职位列表
     * 需存入数据库
     * @return
     *//*
    @GET("app/user/job.api")
    rx.Observable<ApiResponse<List<JobEntity>>> getJobList();*/

}
