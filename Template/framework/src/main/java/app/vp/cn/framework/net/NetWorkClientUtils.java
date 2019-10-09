package app.vp.cn.framework.net;

import java.util.Map;

/**
 * Created by baiye
 * Date: 2019/9/17
 * Time: 11:52
 * Description:
 */
public class NetWorkClientUtils {

    private static NetWorkClientUtils netWorkClientUtils;
    private final NetworkProxy networkProxy;

    private NetWorkClientUtils() {
        RealNetworkUtils realNetworkUtils = new RealNetworkUtils();
        networkProxy = new NetworkProxy(realNetworkUtils);
    }

    public static synchronized NetWorkClientUtils getInstance() {
        if (netWorkClientUtils == null) {
            synchronized (NetWorkClientUtils.class) {
                netWorkClientUtils = new NetWorkClientUtils();
            }
        }
        return netWorkClientUtils;
    }

    public void httpGet(String url, Map<Object, Object> map) {
        networkProxy.get(url, map);
    }

    public void httpPost(String url, Map<Object, Object> map) {
        networkProxy.post(url, map);
    }


}
