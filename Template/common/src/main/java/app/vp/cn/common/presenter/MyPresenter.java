package app.vp.cn.common.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import app.vp.cn.common.base.BaseAbstractPresenter;
import app.vp.cn.common.base.BaseView;

/**
 * author : by
 * date: 2019/3/27 0027  上午 11:48.
 * describe
 */

public class MyPresenter extends BaseAbstractPresenter {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (mView != null) {
                    mView.showToast("啦啦啦啦");
                }else {
                    Log.i("bai", "handleMessage:mView是null了 ");
                }
            }
        }
    };


    public MyPresenter(BaseView mView, LifecycleOwner lifecycleOwner) {
        super(mView, lifecycleOwner);
    }

    public void getdelayText() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 5000);
    }


}
