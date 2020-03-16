package app.vp.cn.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import app.vp.cn.common.app.BaseApp;

public class MainActivity extends AppCompatActivity {


    private Button btWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btWrite = findViewById(R.id.bt_writeToSys);

        CalendarUtils.Lunar lunar = CalendarUtils.solarToLunar(new CalendarUtils.Solar(2020, 2, 24));
        String date = CalendarUtils.nStr1[lunar.lunarMonth] + "月" + CalendarUtils.getChinaDate(lunar.lunarDay);
        Log.i("bai", "onCreate: 农历日期是"+date);


        btWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "/storage/emulated/0/Android/data/com.lu.ashionweatherbanner/files/Pictures/ST_4x2D_add.png";
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                PhotoUtils.saveBmp2Gallery(BaseApp.getmContext(),bitmap,"111");
            }
        });
    }
}
