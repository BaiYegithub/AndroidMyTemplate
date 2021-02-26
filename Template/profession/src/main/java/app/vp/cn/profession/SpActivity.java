package app.vp.cn.profession;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import app.vp.cn.common.app.BaseApp;
import app.vp.cn.profession.timePicker.SharedPreferenceUtils;

public class SpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        SharedPreferences spLili = BaseApp.getmContext().getSharedPreferences("lili", MODE_PRIVATE);
        SharedPreferences.Editor edit = spLili.edit();
        Log.i("bai", "onCreate: commit之前是");
        for (int i = 0; i < 1000; i++) {
            edit.putString(i + "", i + "lala");
        }
        edit.apply();
        Log.i("bai", "onCreate: commit之后是");
        Log.i("bai", "onCreate: spContains之前是");
        for (int i = 0; i < 1000; i++) {
            spLili.contains(i + "");
        }
        Log.i("bai", "onCreate: spContains之后是");
    }
}
