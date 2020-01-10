package app.vp.cn.third.LiveDataBusDesign;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import app.vp.cn.common.util.LiveDataBus;

/**
 * Created by baiye
 * Date: 2019/10/9
 * Time: 17:17
 * Description:
 */
public class MyLiveDataBus<T> implements ILiveDataBus<T> {


    @Override
    public void observe(String key, Class<T> type, @NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        LiveDataBus.get().with(key).observe(owner, (Observer<Object>) observer);
    }

    @Override
    public void post(String key, Object value) {
        LiveDataBus.get().with(key).postValue(value);
    }

    //不支持跨进程
    @Override
    public void broadcast(String key, Object value) {

    }
}
