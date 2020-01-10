package app.vp.cn.third.LiveDataBusDesign;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

/**
 * Created by baiye
 * Date: 2019/10/9
 * Time: 16:51
 * Description:
 */
public interface ILiveDataBus<T> {

    void observe(String key, Class<T> type, @NonNull LifecycleOwner owner, @NonNull Observer<T> observer);

    void post(String key, Object value);

    //跨进程发送消息
    void broadcast(String key, Object value);

}
