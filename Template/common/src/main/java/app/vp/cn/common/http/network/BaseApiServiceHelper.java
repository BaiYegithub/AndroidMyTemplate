package app.vp.cn.common.http.network;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author : by
 * date: 2018/11/8 0008  下午 5:10.
 * describe  统一调取网络请求的工具类
 */

public class BaseApiServiceHelper {

    private static final ApiService apiService = RetrofitAPIManager.getInstance().getApiService();


    /**
     * @param flowable 请求flowable
     * @return Flowable 对象
     */

    private static <T> Flowable<T> getFlowable(Flowable<T> flowable) {
        return flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }




}
