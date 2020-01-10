package app.vp.cn.third.LiveDataBusDesign;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import com.jeremyliao.liveeventbus.LiveEventBus;

/**
 * Created by baiye
 * Date: 2019/10/9
 * Time: 17:19
 * Description:
 */
public class MyLiveEventBus<T> implements ILiveDataBus<T> {


    @Override
    public void observe(String key, Class<T> type, @NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        LiveEventBus.get(key, type).observe(owner, observer);
    }

    @Override
    public void post(String key, Object value) {
        LiveEventBus.get(key).post(value);
    }

    @Override
    public void broadcast(String key, Object value) {
        LiveEventBus.get(key).broadcast(value);
    }
}
