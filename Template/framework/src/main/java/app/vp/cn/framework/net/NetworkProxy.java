package app.vp.cn.framework.net;

import java.util.Map;

/**
 * Created by baiye
 * Date: 2019/9/17
 * Time: 11:50
 * Description:
 */
public class NetworkProxy implements NetworkUtils {

    private NetworkUtils networkUtils;

    public NetworkProxy(NetworkUtils realNetworkUtils) {
        this.networkUtils = realNetworkUtils;
    }

    @Override
    public void get(String url, Map<Object, Object> params) {
        networkUtils.get(url, params);
    }

    @Override
    public void post(String url, Map<Object, Object> params) {
        networkUtils.get(url, params);
    }

    @Override
    public void cancleRequest() {
        networkUtils.cancleRequest();
    }
}
