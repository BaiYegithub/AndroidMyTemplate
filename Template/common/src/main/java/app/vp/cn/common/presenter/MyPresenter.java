package app.vp.cn.common.presenter;

import android.arch.lifecycle.LifecycleOwner;

import app.vp.cn.common.base.BaseAbstractPresenter;
import app.vp.cn.common.base.BaseView;

/**
 * author : by
 * date: 2019/3/27 0027  上午 11:48.
 * describe
 */

public class MyPresenter extends BaseAbstractPresenter {

    public MyPresenter(BaseView mView, LifecycleOwner lifecycleOwner) {
        super(mView, lifecycleOwner);
    }

    
}
