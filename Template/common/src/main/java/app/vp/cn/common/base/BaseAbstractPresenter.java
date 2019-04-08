package app.vp.cn.common.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import app.vp.cn.common.util.LogUtil;
import io.reactivex.disposables.CompositeDisposable;

/**
 * author : by
 * date: 2019/3/27 0027  上午 11:39.
 * describe
 */

public class BaseAbstractPresenter<T extends BaseView> implements BasePresenter, LifecycleObserver {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    public T mView;

    private LifecycleOwner lifecycleOwner;

    public BaseAbstractPresenter(T mView, LifecycleOwner lifecycleOwner) {
        this.mView = mView;
        this.lifecycleOwner = lifecycleOwner;
        addObserver();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Override
    public void detachView() {
        if (compositeDisposable != null) { //不判断compositeDisposable 是否disposed ,因为源码里面判断过了
            compositeDisposable.dispose();
            LogUtil.i("生命周期","清空资源，走了onDestroy 方法");
        }
        lifecycleOwner.getLifecycle().removeObserver(this);
    }

    private void addObserver() {
        lifecycleOwner.getLifecycle().addObserver(this);
    }
}
