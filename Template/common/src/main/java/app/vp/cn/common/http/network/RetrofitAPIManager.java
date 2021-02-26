package app.vp.cn.common.http.network;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import app.vp.cn.common.http.network.url.HttpUrlUtils;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @describe 描述 retrofit 网络请求类
 */
public class RetrofitAPIManager {
    private static RetrofitAPIManager retrofitAPIManager;

    private ApiService apiService;

    public static RetrofitAPIManager getInstance() {
        if (retrofitAPIManager == null) {
            synchronized (RetrofitAPIManager.class) {
                retrofitAPIManager = new RetrofitAPIManager();
            }
        }
        return retrofitAPIManager;
    }

    public ApiService getApiService() {
        return apiService;
    }


    private RetrofitAPIManager() {//创建retrofit+okhttp
        apiService = setURLRetrofit(HttpUrlUtils.BASE_URL).create(ApiService.class);
    }

    public static Retrofit setURLRetrofit(String url) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(genericClient())
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static OkHttpClient genericClient() {

        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                // .addHeader("System_Type", "0")//获得新版本 Android 传0 iOS 传1
                                .addHeader("devicetype", "1")  //1 安卓  2 苹果
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

       
    }

}
