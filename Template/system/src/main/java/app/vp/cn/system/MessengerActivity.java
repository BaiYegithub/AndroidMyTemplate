package app.vp.cn.system;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import app.vp.cn.system.service.MessengerService;

public class MessengerActivity extends AppCompatActivity {

    private boolean mBound;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            mBound = true;

            Messenger serverMessenger = new Messenger(iBinder);
            Log.i("aaa", "onServiceConnected: 连接成功");
            Message clientMessage = Message.obtain();

            clientMessage.what = 1;
            Bundle bundle = new Bundle();
            bundle.putString("msg", "hello , this is client.");
            clientMessage.setData(bundle);
            clientMessage.replyTo = mMessenger; //设置replyTo 的 Messenger 为自定义的mMessenger
            try {
                serverMessenger.send(clientMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mMessenger = null;
            mBound = false;
        }
    };

    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("aaa", "handleMessage:收到了服务器的回复 " + msg.getData().getString("reply"));
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBound){
            unbindService(mConnection);
        }
    }
}
