package app.vp.cn.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarUtils.Lunar lunar = CalendarUtils.solarToLunar(new CalendarUtils.Solar(2020, 2, 24));
        String date = CalendarUtils.nStr1[lunar.lunarMonth] + "月" + CalendarUtils.getChinaDate(lunar.lunarDay);
        Log.i("bai", "onCreate: 农历日期是"+date);
    }
}
