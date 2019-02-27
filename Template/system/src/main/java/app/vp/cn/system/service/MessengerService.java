package app.vp.cn.system.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * author : by
 * date: 2019/2/26 0026  下午 5:43.
 * describe  使用MessengerService 通信
 */

public class MessengerService extends Service {


    private static final int CODE = 1;

    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {

            Message toClient = Message.obtain();
            switch (msg.what) {
                case CODE:
                    //接收来自客户端的消息，并作处理
                    Bundle data = msg.getData();
                    Log.i("aaa", "handleMessage: 收到服务器的信息是" + data.getString("msg"));
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "收到你的消息了，等会儿回复你");
                    toClient.setData(bundle);
                    try {
                        msg.replyTo.send(toClient);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            super.handleMessage(msg);
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
