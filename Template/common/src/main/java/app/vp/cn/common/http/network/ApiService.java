package app.vp.cn.common.http.network;

/**
 * author : by
 * date: 2018/11/8 0008  下午 4:59.
 * describe  所有网络请求的发出者 json格式提交
 */

public interface ApiService {

    /******************************************登录模块********************************************/

   /* *//**
     * 账号密码登录
     *//*
    @POST("login/login")
    Flowable<Result<BaseBean<LoginBean>>> passLogin(@Body Map<String, Object> map);*/

   /* *//**
     * 登录
     *//*
    @POST("login/login")
    Flowable<Result<BaseBean<LoginBean>>> Login(@Body Map<String, Object> map);

    *//**
     * 注册
     *//*
    @POST("login/register")
    Flowable<Result<BaseBean<LoginBean>>> register(@Body Map<String, Object> map);


    *//**
     * 发送短信验证码
     *//*
    @POST("login/sendSms")
    Flowable<Result<BaseBean>> sendSms(@Body Map<String, Object> map);

    *//**
     * 找回密码第一步
     *//*
    @POST("login/retrievePasswordOne")
    Flowable<Result<BaseBean>> findPass1(@Body Map<String, Object> map);

    *//**
     * 找回密码第二步
     *//*
    @POST("login/retrievePasswordTwo")
    Flowable<Result<BaseBean<FindPassBean>>> findPass2(@Body Map<String, Object> map);

    *//*
    * 退出登录
    * *//*
    @POST("login/logout")
    Flowable<Result<BaseBean>> logout();

    *//************************************个人中心模块**************************************//*


    *//**
     * @return 查询个人信息
     *//*
    @POST("personal/queryPersonal")
    Flowable<Result<BaseBean<PersonalBean>>> queryPersonal();

    *//**
     * @return 修改个人信息
     *//*
    @POST("personal/updatePersonal")
    Flowable<Result<BaseBean>> updatePersonal(@Body Map<String, Object> map);

    //我的问答
    @POST("questionanswer/queanslist")
    Flowable<Result<BaseBean<QaListBean>>> QaList(@Body Map<String, Object> map);

    *//**
     * 在线提问
     *//*
    @POST("user/study/lesson/quiz")
    Flowable<Result<BaseBean<String>>> quiz(@Body Map<String, Object> map);


    *//**
     * @param map
     * @return 问答详情
     *//*
    @POST("questionanswer/queansdetail")
    Flowable<Result<BaseBean<QaDetailBean>>> qaDetail(@Body Map<String, Object> map);

    *//**
     * @param map
     * @return 修改密码
     *//*
    @POST("personal/updatePassword")
    Flowable<Result<BaseBean>> updatePassWord(@Body Map<String, Object> map);

    *//**
     * @param file 上传头像file
     * @return
     *//*
    @POST("file/upload")
    Flowable<Result<BaseBean<String>>> uploadHeadImg(@Body MultipartBody file);

    *//**
     * @param map
     * @return 收藏或者取消收藏的接口
     *//*
    @POST("courseDetails/collectionOrCancel")
    Flowable<Result<BaseBean<CollectBean>>> collectOrCancel(@Body Map<String, Object> map);

    *//**
     * @return 获取我的收藏列表
     *//*
    @POST("my/findCollectionList")
    Flowable<Result<BaseBean<CollectionBean>>> myCollectionList(@Body Map<String, Object> map);

    *//**
     * @param map
     * @return 获取课时详情界面的数据
     *//*
    @POST("courseDetails/details")
    Flowable<Result<BaseBean<CourseDetailBean>>> courseDetail(@Body Map<String, Object> map);

    *//**
     * @param map
     * @return 获取评价内容
     *//*
    @POST("course/queryCourseEvaluateList")
    Flowable<Result<BaseBean<EvaluateBean>>> getEvaluate(@Body Map<String, Object> map);

    *//**
     * @param map
     * @return 获取下载列表
     *//*
    @POST("download/getdownloadlist")
    Flowable<Result<BaseBean<List<DownLoadSelectBean>>>> getDownLoadList(@Body Map<String, Object> map);


    *//**
     * @return 获取阿里云凭证
     *//*
    @POST("download/getstscredentials")
    Flowable<Result<BaseBean<AliyunAccessBean>>> getAliyunAccess(@Body Map<String, Object> map);

    *//**
     * @return 获取教师咨询手机号
     *//*
    @GET("index/gettel")
    Flowable<Result<BaseBean<String>>> getTel();

    *//**
     * @return 获取继续学习的内容
     *//*
    @POST("study/isNextStudy")
    Flowable<Result<BaseBean<ContinueStudyBean>>> isNextStudy();*/

}
