package app.vp.cn.system;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class CountDownTimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);

        CountDownTimer countDownTimer = new CountDownTimer(10 * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("bai", "onTick: 还剩" + millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                Log.i("bai", "onFinish: >>>>>>>>>>>>>倒计时结束");
            }
        };
        countDownTimer.start();
    }
}