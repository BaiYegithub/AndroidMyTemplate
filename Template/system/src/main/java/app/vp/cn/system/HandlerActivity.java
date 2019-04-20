package app.vp.cn.system;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class HandlerActivity extends AppCompatActivity {


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            int i = (int) message.obj;
            Log.i("xxx", "handleMessage: " + i);
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message obtain = Message.obtain();
                obtain.obj = 2;
                handler.sendMessage(obtain);
            }
        }).start();

        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
