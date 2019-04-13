package app.vp.cn.unit.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * author : by
 * date: 2019/4/11 0011  下午 5:18.
 * describe
 */

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /* service如何杀不死？

        1.onStartCommand方法，返回START_STICKY（粘性）当service因内存不足被kill，当内存又有的时候，service又被重新创建
        2.设置优先级，在服务里的ondestory里发送广播 在广播里再次开启这个服务,双进程守护*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //返回粘性广播  ，
        return START_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
