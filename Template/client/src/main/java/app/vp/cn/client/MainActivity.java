package app.vp.cn.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import app.vp.cn.system.IMyAidl;
import app.vp.cn.system.bean.Person;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.bt_get)
    Button btGet;
    private IMyAidl myAidl;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接后拿到 Binder，转换成 AIDL，在不同进程会返回个代理
            myAidl = IMyAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bindMyService();
    }

    private void bindMyService() {
        Intent intent = new Intent();
        intent.setAction("app.vp.cn.system.service.MyAidlService");
        intent.setPackage("app.vp.cn.system.service");
        startService(intent);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }


    @OnClick({R.id.bt_add, R.id.bt_get})
    public void onViewClicked(View view) throws RemoteException {
        switch (view.getId()) {
            case R.id.bt_add:
                try {
                    myAidl.addPerson(new Person("小明同学"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_get:
                List<Person> personList = myAidl.getPersonList();
                Log.i("xxx", "onViewClicked: " + personList.toString());
                break;
        }
    }
}
