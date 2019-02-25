package app.vp.cn.system.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.vp.cn.system.IMyAidl;
import app.vp.cn.system.bean.Person;

/**
 * author : by
 * date: 2019/2/25 0025  上午 10:49.
 * describe
 */

public class MyAidlService extends Service {

    private final String TAG = this.getClass().getSimpleName();

    private ArrayList<Person> mPersons;

    /**
     * 创建生成的本地 Binder 对象，实现 AIDL 制定的方法
     */

    private IBinder mIBinder = new IMyAidl.Stub(){

        @Override
        public void addPerson(Person person) throws RemoteException {
            mPersons.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return mPersons;
        }
    };



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mPersons = new ArrayList<>();
        Log.i(TAG,"MyAidlService onBind");
        return mIBinder;
    }
}
