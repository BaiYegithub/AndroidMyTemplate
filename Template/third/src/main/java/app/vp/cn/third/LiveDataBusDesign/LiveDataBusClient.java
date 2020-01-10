package app.vp.cn.third.LiveDataBusDesign;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

/**
 * Created by baiye
 * Date: 2019/10/9
 * Time: 17:22
 * Description:
 */
public class LiveDataBusClient<T> implements ILiveDataBus<T>{
    private static LiveDataBusClient liveDataBusClient;
    private  LiveDataBusProxy liveDataBusProxy;

    public <T>LiveDataBusClient(T t) {
        MyLiveEventBus myLiveEventBus = new MyLiveEventBus();
        liveDataBusProxy = new LiveDataBusProxy(myLiveEventBus);
    }

    public static synchronized <T>LiveDataBusClient<T> getInstance(T t){
        if(liveDataBusClient==null){
            synchronized (LiveDataBusClient.class){
                liveDataBusClient = new LiveDataBusClient(t);
            }
        }
        return liveDataBusClient;
    }


    @Override
    public void observe(String key, Class<T> type, @NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        liveDataBusProxy.observe(key,type,owner,observer);
    }

    @Override
    public void post(String key, Object value) {
        liveDataBusProxy.post(key,value);
    }

    @Override
    public void broadcast(String key, Object value) {
        liveDataBusProxy.broadcast(key,value);
    }
}
