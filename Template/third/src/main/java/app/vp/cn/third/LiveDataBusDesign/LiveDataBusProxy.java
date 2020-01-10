package app.vp.cn.third.LiveDataBusDesign;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

/**
 * Created by baiye
 * Date: 2019/10/9
 * Time: 17:14
 * Description:
 */
public class LiveDataBusProxy<T> implements ILiveDataBus<T> {

    private ILiveDataBus iLiveDataBus;

    public LiveDataBusProxy(ILiveDataBus iLiveDataBus) {
        this.iLiveDataBus = iLiveDataBus;
    }


    @Override
    public void observe(String key, Class<T> type, @NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        iLiveDataBus.observe(key,type,owner,observer);
    }

    @Override
    public void post(String key, Object value) {
        iLiveDataBus.post(key,value);
    }

    @Override
    public void broadcast(String key, Object value) {
        iLiveDataBus.broadcast(key,value);
    }
}
