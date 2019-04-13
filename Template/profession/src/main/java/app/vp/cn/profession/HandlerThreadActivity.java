package app.vp.cn.profession;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;

import app.vp.cn.common.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class HandlerThreadActivity extends BaseActivity {


    @BindView(R.id.bt_send)
    Button btSend;
    private Handler handler;

    @Override
    protected int initContentView() {
        return R.layout.activity_handler_thread;
    }

    @Override
    protected void initViewAndData() {


    }

    @Override
    protected void initHttp() {
        //在子线程开启handler 的方式，分为三步   1.在子线程中Looper.prepare (准备)2.new Handler (创建) 3.Looper.loop (轮询1起来)
        //开辟一个子线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if (msg.what == 1) {
                            showToast("我接收到了来自主线程的消息");
                        }
                    }
                };
                Looper.loop();
            }

        });
        thread.start();

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void destroyResources() {

    }

    @OnClick(R.id.bt_send)
    public void onViewClicked() {
        handler.sendEmptyMessage(1);
    }
}
