package com.zzq.zzq_collapsing_demo_master.api;


/**
 *
 */
public interface HttpAppApi {




    /**
     * 首页接口
     *
     * @param companyId 猪场（公司）ID
     * @return
     *//*
    @GET("app/homePage/company/{companyId}/home")
    rx.Observable<ApiResponse<FarmSacleEntity>> getFarmSacle(

            @Path("companyId") int companyId

    );


    *//**
     * 获取绑定猪场详细信息接口
     *
     * @param companyIds     猪场ID
     * @param pigfarmUserIds 猪场用户ID
     * @return
     *//*
    @GET("app/tool/bindingInfo")
    rx.Observable<ApiResponse<List<BindingPigInfoEntity>>> getBindingPigInfo(

            @Query("companyIds[]") String companyIds

            , @Query("pigfarmUserIds[]") String pigfarmUserIds);

    *//**
     * 公共事件---入场接口
     *
     * @param supCompanyId 上级公司ID
     * @param companyId    猪场（公司）ID
     * @param employeeId   用户ID
     * @param body
     * @return
     *//*

    @POST("app/pigMoveIn/supCompany/{supCompanyId}/company/{companyId}/employee/{employeeId}/pigMoveIn")
    rx.Observable<ApiResponse> postAdmission(

            @Path("supCompanyId") int supCompanyId

            , @Path("companyId") int companyId

            , @Path("employeeId") int employeeId

            , @Body AdmissionBody body);


    *//**
     * 公共事件---转舍接口
     *
     * @param companyId  猪场（公司）ID
     * @param employeeId 用户ID
     * @param pigType    猪只类型
     * @param body
     * @return
     *//*
    @POST("app/billChangeHouse/company/{companyId}/employee/{employeeId}/pigType/{pigType}/billChangeHouseNew")
    rx.Observable<ApiResponse> postTurm(

            @Path("companyId") int companyId

            , @Path("employeeId") int employeeId

            , @Path("pigType") String pigType

            , @Body TurmBody body);

    *//**
     * 公共事件---转群接口
     *
     * @param supCompanyId 上级公司ID
     * @param companyId    猪场（公司）ID
     * @param employeeId   用户ID
     * @param body
     * @return
     *//*
    @POST("app/billChangeSwinery/supCompany/{supCompanyId}/company/{companyId}/employee/{employeeId}/billChangeSwinery")
    rx.Observable<ApiResponse> postTransferGroup(


            @Path("supCompanyId") int supCompanyId


            , @Path("companyId") int companyId

            , @Path("employeeId") int employeeId

            , @Body TransferGroupBody body);

    *//**
     * 公共接口—根据companyID查找产线、猪舍、猪群
     *
     * @param companyId 猪场（公司）ID  ture
     * @param query     模糊查询字符串 false
     * @return
     *//*
    @GET("app/productionLine/company/{companyId}/searchLineHouseSwinery")
    rx.Observable<ApiResponse<SearchLineHouseSwineryEntity>> getSearchLineHouseSwinery(

            @Path("companyId") int companyId

            , @Query("query") String query);


    *//**
     * 公共接口—根据companyID查找客户
     *
     * @param companyId 猪场（公司）ID
     * @param start     开始位置，用于分页，默认为0
     * @param limit     每页数量，默认为1-全部
     * @param query     模糊查询字符串
     * @return
     *//*
    @GET("app/customer/company/{companyId}/list")
    rx.Observable<ApiResponse<List<CustomerEntity>>> getCustomer(


            @Path("companyId") int companyId

            , @Query("start") Integer start

            , @Query("limit") Integer limit

            , @Query("query") String query);


    *//**
     * 公共接口—查找codeList
     * 根据typeID查找code,很多地方需要使用此接口，type见说明表格。
     *
     * @param companyId 猪场（公司）ID
     * @param typeId    Code类型
     * @param linkValue 用于区分猪只类别，1-公猪、2-母猪、3-肉猪
     * @param start     开始位置，用于分页，默认为0
     * @param limit     每页数量，默认为10000
     * @param query     模糊查询字符串
     * @return
     *//*
    @GET("app/codeList/company/{companyId}/type/{typeId}/searchByTypeByLinkValue")
    rx.Observable<ApiResponse<List<SearchByTypeByLinkValueEntity>>> getSearchByTypeByLinkValue(

            @Path("companyId") int companyId

            , @Path("typeId") int typeId

            , @Query("linkValue") int linkValue

            , @Query("start") Integer start

            , @Query("limit") Integer limit

            , @Query("query") String query);


    *//**
     * 公共接口—获取系统设置项
     * 根据companyID查找系统设置。
     *
     * @param companyId     猪场（公司）ID
     * @param setTypeString 系统设置项代码，详见表格
     * @return
     *//*
    @GET("app/toolbox /company/{companyId}/getSetType")
    rx.Observable<ApiResponse<SetTypeEntity>> getSetType(

            @Path("companyId") int companyId

            , @Query("setTypeString") String setTypeString);


    *//**
     * 公共接口—根据companyId、产线、猪只类别查找猪群
     *
     * @param companyId 猪场（公司）ID  true
     * @param lineId    产线ID  true
     * @param pigType   猪类别  true
     * @param query     模糊查询字符串 false
     * @return
     *//*
    @GET("app/productionLine/company/{companyId}/line/{lineId}/pigType/{pigType}/searchSwineryByType")
    rx.Observable<ApiResponse<List<SearchSwineryByTypeEntity>>> getsearchSwineryByType(

            @Path("companyId") int companyId

            , @Path("lineId") int lineId

            , @Path("pigType") int pigType

            , @Query("query") String query);


    *//**
     * 公共接口—根据猪场、猪舍查找该猪舍下的猪栏
     *
     * @param companyId  猪场（公司）ID true
     * @param pigHouseId 猪舍ID true
     * @return
     *//*
    @GET("app/pigpen/company/{companyId}/pigHouse/{pigHouseId}/searchByPigHouseId")
    rx.Observable<ApiResponse> getPigField(

            @Path("companyId") int companyId

            , @Path("pigHouseId") int pigHouseId);

    *//**
     * 公共接口—根据猪场、产线、猪类别查找猪群
     *
     * @param companyId 猪场（公司）ID
     * @param lineId    产线ID
     * @param pigType   用于区分猪只类别，1-公猪、2-母猪、3-肉猪
     * @param query     模糊查询字符串
     * @return
     *//*

    @GET("app/productionLine/company/{companyId}/line/{lineId}/pigType/{pigType}/searchSwineryByType")
    rx.Observable<ApiResponse> getHerd(

            @Path("companyId") int companyId

            , @Path("lineId") int lineId

            , @Path("pigType") int pigType

            , @Query("query") String query);


    *//**
     * 公共接口—根据猪场和产线查找猪舍
     *
     * @param companyId 猪场（公司）ID
     * @param lineId    产线ID
     * @param query     模糊查询字符串
     * @return
     *//*
    @GET("app/productionLine/company/{companyId}/line/{lineId}/searchByLine")
    rx.Observable<ApiResponse<SearchByLineEntity>> getSearchByLine(

            @Path("companyId") int companyId

            , @Path("lineId") int lineId

            , @Query("query") String query
    );


    *//**
     * 公共接口—根据猪类别查找猪只状态
     *
     * @param companyId 猪场（公司）ID
     * @param pigType   用于区分猪只类别，1-公猪、2-母猪、3-肉猪
     * @return
     *//*
    @GET("app/pigClass/company/{companyId}/pigType/{pigType}/searchStatusByPigType")
    rx.Observable<ApiResponse<SearchStatusByPigTypeEntity>> getPigStatus(

            @Path("companyId") int companyId

            , @Path("pigType") int pigType);

    *//**
     * 公共接口—根据猪类别查找物料
     *
     * @param companyId 猪场（公司）ID
     * @param pigType   用于区分猪只类别，1-公猪、2-母猪、3-肉猪
     * @return
     *//*
    @GET("app/materials/company/{companyId}/pigType/{pigType}/searchByType")
    rx.Observable<ApiResponse<SearchByType>> getSearchByType(@Path("companyId") int companyId, @Path("pigType") int pigType);

    *//**
     * 公共接口—根据猪类别和物料查询物料详细信息
     *
     * @param companyId  猪场（公司）ID
     * @param pigType    用于区分猪只类别，1-公猪、2-母猪、3-肉猪
     * @param materialId 物料ID
     * @return
     *//*
    @GET("app/materials/company/{companyId}/pigType/{pigType}/material/{materialId}/searchMaterialPig")
    rx.Observable<ApiResponse<SearchByTypeInfo>> getSearchByTypeInfo(
            @Path("companyId") int companyId,

            @Path("pigType") int pigType,

            @Path("materialId") int materialId
    );

    *//**
     * 公共接口—根据猪场和产线查找猪舍
     * 获取猪场和产线查询所有猪舍。
     *
     * @param companyId
     * @param lineId
     * @return
     *//*
    @GET("app/productionLine/company/{companyId}/line/{lineId}/searchByLine")
    rx.Observable<ApiResponse<PigHouse>> getPigHouse(
            @Path("companyId") int companyId,
            @Path("lineId") int lineId,
            @Query("query") String query
    );

    *//**
     * 报表接口—猪只存栏报表
     *
     * @param companyId  猪场（公司）ID true
     * @param searchDate 查找日期 true
     * @param breedIds   默认“” false
     * @param start      开始位置，用于分页，默认为1 false
     * @param limit      每页数量，默认为10000 false
     * @return
     *//*
    @GET("app/report/company/{companyId}/pigHeardByBreed")
    rx.Observable<ApiResponse<PigHeardByBreedEntity>> getPigHeardByBreed(

            @Path("companyId") int companyId

            , @Query("searchDate") String searchDate

            , @Query("breedIds") String breedIds

            , @Query("start") Integer start

            , @Query("limit") Integer limit);


    *//**
     * 报表接口—猪舍存栏接口
     *
     * @param companyId  猪场（公司）ID true
     * @param searchDate 查找日期 true
     * @param houseType  默认“” false
     * @param houseIds   默认“” false
     * @param start      开始位置，用于分页，默认为1 false
     * @param limit      每页数量，默认为10000 false
     * @return
     *//*
    @GET("app/report/company/{companyId}/getPigHouseHerdsByProduction")
    rx.Observable<ApiResponse<PigHouseHerdsByProductionEntity>> getPigHouseHerdsByProduction(

            @Path("companyId") int companyId

            , @Query("searchDate") String searchDate

            , @Query("houseType") String houseType

            , @Query("houseIds") String houseIds

            , @Query("start") Integer start

            , @Query("limit") Integer limit);

    *//**
     * 报表接口—综合指标接口
     *
     * @param companyId 猪场（公司）ID true
     * @return
     *//*
    @GET("app/homePage/company/{companyId}/getIndicatorBySingleFarm")
    rx.Observable<ApiResponse<IndicatorBySingleFarmEntity>> getIndicatorBySingleFarm(

            @Path("companyId") int companyId);


    *//**
     * 猪群接口—获取猪群
     *
     * @param supCompanyId 上级公司ID
     * @param companyId    猪场（公司）ID
     * @param isClosed     是否显示离场，0-不关闭、1-关闭 true
     * @param isDelete     是否显示删除，默认0（不显示） false
     * @param pigType      猪类别,默认为0，,1-公、2-母、3-肉 false
     * @param pigTypeFlag  猪类别flag，默认为1 false
     * @param start        开始位置，用于分页，默认为0 false
     * @param limit        每页数量 true
     * @param query        模糊查询字符串 false
     * @return
     *//*

    @GET("app/swinery/supCompany/{supCompanyId}/company/{companyId}/advSearchByPage")
    rx.Observable<ApiResponse<AdvSearchByPageEntity>> getAdvSearchByPage(

            @Path("supCompanyId") int supCompanyId

            , @Path("companyId") int companyId

            , @Query("isClosed") String isClosed

            , @Query("isDelete") String isDelete

            , @Query("pigType") String pigType

            , @Query("pigTypeFlag") String pigTypeFlag

            , @Query("start") Integer start

            , @Query("limit") Integer limit

            , @Query("query") String query);

    *//**
     * 猪群接口—获取猪群详细信息
     * 获取猪舍详细信息，即里面的猪只信息。见注释。
     *
     * @param companyId 猪场（公司）ID true
     * @param swineryId 猪群ID true
     * @param startDate 时间 yyyy-MM-dd true
     * @param start     开始位置，用于分页，默认为0 false
     * @param limit     每页数量 true
     * @return
     *//*
    @GET("app/swinery/company/{companyId}/getSwineryHerdsInfo")
    rx.Observable<ApiResponse<SwineryHerdsInfoEntity>> getSwineryHerdsInfo(

            @Path("companyId") int companyId

            , @Query("swineryId") int swineryId

            , @Query("startDate") String startDate

            , @Query("start") Integer start

            , @Query("limit") Integer limit);


    *//**
     * 猪只接口—获取猪只
     * 获取猪只信息。
     *
     * @param supCompanyId 上级公司ID
     * @param companyId    猪场（公司）ID
     * @param isLeave      是否显示离场，0-不显示、1-显示 true
     * @param isDelete     是否显示删除，默认0（不显示） false
     * @param pigType      猪类别,默认为0，,1-公、2-母、3-肉 true
     * @param pigTypeFlag  猪类别flag，默认为1 false
     * @param pigClass     猪只状态，默认为 “” false
     * @param swineryId    猪群ID，默认为 “” false
     * @param pigHouseId   猪舍ID，默认为 “” false
     * @param earBrand     耳牌号，默认为 “”false
     * @param start        开始位置，用于分页，默认为0 false
     * @param limit        每页数量 true
     * @param query        模糊查询字符串 false
     * @return
     *//*

    @GET("app/pigInfo/supCompany/{supCompanyId}/company/{companyId}/advSearchByPage")
    rx.Observable<ApiResponse<AdvSearchByPigEntity>> getAdvSearchByPig(

            @Path("supCompanyId") int supCompanyId

            , @Path("companyId") int companyId

            , @Query("isLeave") String isLeave

            , @Query("isDelete") String isDelete

            , @Query("pigType") String pigType

            , @Query("pigTypeFlag") String pigTypeFlag

            , @Query("pigClass") String pigClass

            , @Query("swineryId") String swineryId

            , @Query("pigHouseId") String pigHouseId

            , @Query("earBrand") String earBrand

            , @Query("start") Integer start

            , @Query("limit") Integer limit

            , @Query("query") String query);


    *//**
     * 猪只接口—根据猪只ID或耳牌号获取猪只详细信息
     * 猪只ID和耳牌号必须传一个，但二者可以不必都传。字段太多，按需取用。。。。
     *
     * @param companyId 猪场（公司）ID
     * @param pigId     猪只ID
     * @param earbrand  耳牌号
     * @return
     *//*
    @GET("app/widget/pig /company/{companyId}/info")
    rx.Observable<ApiResponse<AdvSearchByPageInfo>> getAdvSearchByPageInfo(

            @Path("companyId") int companyId

            , @Query("pigId") Integer pigId

            , @Query("earbrand") String earbrand

    );


    *//**
     * 猪只接口—根据猪只ID获取公猪生产性能
     * 获取公猪生产性能的详细信息。见注释。。。
     *
     * @param companyId 猪场（公司）ID
     * @param pigId     猪只ID
     * @return
     *//*
    @GET("app/widget/pig/boarPerformance")
    rx.Observable<ApiResponse<BoarPerformance>> getBoarPerformance(

            @Query("company") int companyId

            , @Query("pigId") Integer pigId


    );


    *//**
     * 猪只接口—根据耳牌号ID获取系谱
     * 获取猪只系谱
     *
     * @param supCompanyId 上级公司ID
     * @param companyId    猪场（公司）ID
     * @param pigId        耳牌号id
     * @return
     *//*
    @GET("app/widget/pig/supCompany/{supCompanyId}/company/{companyId}/family")
    rx.Observable<ApiResponse<Family>> getFamily(

            @Path("supCompanyId") int supCompanyId

            , @Path("companyId") int companyId

            , @Query("pigId") int pigId


    );

    *//**
     * 猪只接口—根据猪只ID获取历史记录
     * <p>
     * 获取猪只历史记录
     *
     * @param companyId 猪场（公司）ID
     * @param pigId     猪只ID
     * @return
     *//*
    @GET("app/widget/pig/getEventHis")
    rx.Observable<ApiResponse<EventHis>> getEventHis(

            @Query("company") int companyId

            , @Query("pigId") int pigId


    );

    *//**
     * 猪只接口—根据猪只ID获取母猪生产性能
     * <p>
     * 获取母猪生产性能的详细信息
     *
     * @param companyId 猪场（公司）ID
     * @param pigId     猪只ID
     * @return
     *//*
    @GET("app/widget/pig/company/{companyId}/sowPerformance")
    rx.Observable<ApiResponse<List<SowPerformance>>> getSowPerformance(

            @Path("companyId") int companyId

            , @Query("pigId") int pigId


    );

    *//**
     * 猪舍接口—获取猪舍
     * 获取猪场下的所有猪舍
     *
     * @param companyId 猪场（公司）ID true
     * @param start     开始位置，用于分页，默认为0 false
     * @param limit     每页数量 true
     * @param query     模糊查询字符串 false
     * @return
     *//*
    @GET("app/productionLine/company/{companyId}/searchHouseByPage")
    rx.Observable<ApiResponse<SearchHouseByPage>> getSearchHouseByPage(

            @Path("companyId") int companyId

            , @Query("start") Integer start

            , @Query("limit") Integer limit

            , @Query("query") String query);

    *//**
     * 猪舍接口—获取猪舍类型
     * 获取猪场下的所有猪舍类型
     *
     * @param companyId 猪场（公司）ID true
     * @param start     开始位置，用于分页，默认为0 false
     * @param limit     每页数量 true
     * @param query     模糊查询字符串 false
     * @return
     *//*
    @GET("app/productionLine/company/{companyId}/searchHouseTypeByPage")
    rx.Observable<ApiResponse<SearchHouseTypeByPage>> getSearchHouseTypeByPage(

            @Path("companyId") int companyId

            , @Query("start") Integer start

            , @Query("limit") Integer limit

            , @Query("query") String query);


    @GET("app/productionLine/company/{companyId}/getHouseHerdsInfo")
    rx.Observable<ApiResponse<HouseHerdsInfo>> getHouseHerdsInfo(

            @Path("companyId") int companyId

            , @Query("pigHouseId") int pigHouseId

            , @Query("startDate") String startDate

            , @Query("start") Integer start

            , @Query("limit") Integer limit

            , @Query("query") String query);


    *//**
     * 公共接口—查找品种2
     *
     * @param companyId
     * @return
     *//*
    @GET("app/report/company/{companyId}/searchBreedByCompany")
    rx.Observable<ApiResponse<SearchBreedByCompany>> getSearchBreedByCompany(@Path("companyId") int companyId);*/

}
