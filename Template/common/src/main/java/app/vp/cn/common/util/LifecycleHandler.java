package app.vp.cn.common.util;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Handler;
import android.os.Looper;

/**
 * author : by
 * date: 2019/3/26 0026  下午 6:03.
 * describe
 */

public class LifecycleHandler extends Handler implements LifecycleObserver {

    private LifecycleOwner lifecycleOwner;

    public LifecycleHandler(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        addObserver();
    }

    public LifecycleHandler(Callback callback, LifecycleOwner lifecycleOwner) {
        super(callback);
        this.lifecycleOwner = lifecycleOwner;
        addObserver();
    }

    public LifecycleHandler(Looper looper, LifecycleOwner lifecycleOwner) {
        super(looper);
        this.lifecycleOwner = lifecycleOwner;
        addObserver();
    }

    public LifecycleHandler(Looper looper, Callback callback, LifecycleOwner lifecycleOwner) {
        super(looper, callback);
        this.lifecycleOwner = lifecycleOwner;
        addObserver();
    }


    private void addObserver() {
        lifecycleOwner.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        removeCallbacksAndMessages(null);
        lifecycleOwner.getLifecycle().removeObserver(this);
    }
}
