package app.vp.cn.system;

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

import app.vp.cn.system.bean.Person;
import app.vp.cn.system.service.MyAidlService;
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
        Intent intent = new Intent(getApplicationContext(), MyAidlService.class);
        /*intent.setAction("app.vp.cn.system.service.MyAidlService");
        intent.setPackage("app.vp.cn.system.service");*/
        startService(intent);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    @OnClick({R.id.bt_add, R.id.bt_get, R.id.to_sec,R.id.to_messenger,R.id.to_provider})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                try {
                    myAidl.addPerson(new Person("小明同学"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Log.i("xxx", "异常是" + e);
                }
                break;
            case R.id.bt_get:
                List<Person> personList = null;
                try {
                    personList = myAidl.getPersonList();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.i("xxx", "onViewClicked: " + personList.toString());
                break;
            case R.id.to_sec:
                ConstansUtils.a = "2";  //Intent 可以在不同的进程中去传值，  静态变量a 在一个新的进程去获得的时候会拿到原始数据
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("name", "不同进程的Activity呀呀呀");
                startActivity(intent);
                Log.i("aaa", "MainActivity 中的a的值" + ConstansUtils.a);
                break;
            case R.id.to_messenger:
                Intent intent1 = new Intent(MainActivity.this, MessengerActivity.class);
                startActivity(intent1);
                break;
            case R.id.to_provider:
                Intent intent2 = new Intent(MainActivity.this, ProviderActivity.class);
                startActivity(intent2);
                break;
        }
    }


  /*  @OnClick({R.id.bt_add, R.id.bt_get})
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
    }*/
}
