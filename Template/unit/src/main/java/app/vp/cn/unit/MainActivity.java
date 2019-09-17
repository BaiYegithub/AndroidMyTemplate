package app.vp.cn.unit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import app.vp.cn.unit.service.MyIntentService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MyIntentService", "onCreate: 当前线程的id是"+Thread.currentThread().getId());
        Intent intent = new Intent(MainActivity.this, MyIntentService.class);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            startForegroundService(intent);
        }else {
            startService(intent);
        }


      /*  AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long triggerAtTime = SystemClock.elapsedRealtime()+10*1000;
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerAtTime,);*/
    }
}
