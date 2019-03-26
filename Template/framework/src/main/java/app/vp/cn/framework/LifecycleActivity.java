package app.vp.cn.framework;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import app.vp.cn.framework.utils.LifecycleHandler;

public class LifecycleActivity extends AppCompatActivity {

    //这里添加了一个不用回调清空缓存信息的handler  ,防止在activity结束的时候 忘记清空hanlder 的缓存
    LifecycleHandler lifecycleHandler = new LifecycleHandler(this) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Log.i("123", "收到信息" + msg.obj);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        new Thread(new Runnable() {
            @Override
            public void run() {

                Message msg = Message.obtain();
                msg.obj = "123";
                msg.what = 1;
                lifecycleHandler.sendMessage(msg);
            }
        }).start();
    }
}
