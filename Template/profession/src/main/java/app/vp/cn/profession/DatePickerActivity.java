package app.vp.cn.profession;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;

import app.vp.cn.profession.timePicker.DatePickDoubleViewDialog;
import app.vp.cn.profession.timePicker.DateType;
import app.vp.cn.profession.timePicker.OnClickRadioBtnListener;
import app.vp.cn.profession.timePicker.OnLeftBtnLisener;
import app.vp.cn.profession.timePicker.OnSureLisener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatePickerActivity extends AppCompatActivity {


    @BindView(R.id.bt_toSecond)
    Button btToSecond;
    @BindView(R.id.bt_showVoice)
    Button btShowVoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        ButterKnife.bind(this);
        showDatePickDialog(DateType.TYPE_YMD, DateType.TYPE_YMD_LUNAR);



        btShowVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                boolean wiredHeadsetOn = audioManager.isWiredHeadsetOn();

                int streamMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                int streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

                Log.i("bai", "是否插入耳机"+wiredHeadsetOn+"onCreate: 最大的音量是" + streamMaxVolume + "现在的音量是" + streamVolume);
            }
        });
    }

    private void showDatePickDialog(DateType type1, DateType type2) {
        DatePickDoubleViewDialog pickDialog = new DatePickDoubleViewDialog(DatePickerActivity.this);
        //设置上下年分限制 1901-2099
        //pickDialog.setYearLimt(CalendarUtils.SELECT_YEAR_LIMIT);
        //设置标题
        pickDialog.setTitle("选择日期");
        pickDialog.setTabLeftDescr("公历");
        pickDialog.setTabRightDescr("农历");
        //设置消息体的显示格式，日期格式 默认值阴历 YYYY年MM月初一
        //dialog.setMessageFormat(DateStyle.YYYY_MM_DDCN.getValue());
        //设置选择回调
        pickDialog.setOnChangeLisener(null);
        pickDialog.setBtnLeftDescr("今天");
        pickDialog.setOnLeftBtnLisenerListener(new OnLeftBtnLisener() {
            @Override
            public void onLeftBtnEvent(Date date) {
                resetToday();
            }
        });
        //设置点击确定按钮回调
        pickDialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {

            }
        });
        pickDialog.setOnClickRadioBtnListener(new OnClickRadioBtnListener() {
            @Override
            public void onLeftRadioBtnLisener(long millTime) {
            }

            @Override
            public void onRightRadioBtnLisener(long millTime) {
            }
        });
        //设置类型
        pickDialog.setType(type1);
        pickDialog.setTypeTwoView(type2);
        pickDialog.setStartDate(new Date(System.currentTimeMillis()));
        pickDialog.show();
    }

    //返回今天
    private void resetToday() {
        /*//时间重新赋值
        scheduleController.getSelDateEntity().resetTodayDate();
        //各数据刷新
        setUiTopDate();*/
        //周,月份界面刷新
    }

    @OnClick(R.id.bt_toSecond)
    public void onViewClicked() {
        Intent intent = new Intent(DatePickerActivity.this, PresenterActivity.class);
        startActivity(intent);
    }

}
