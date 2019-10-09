package app.vp.cn.framework.net;

import java.util.Map;

/**
 * Created by baiye
 * Date: 2019/9/17
 * Time: 11:45
 * Description:  接口定义好是关键，接口参数传递统一是关键
 */
public interface NetworkUtils {
    //get请求
    void get(String url, Map<Object, Object> params);

    //post请求
    void post(String url, Map<Object, Object> params);

    //取消请求
    void cancleRequest();
}
