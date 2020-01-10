package app.vp.cn.third;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jeremyliao.liveeventbus.LiveEventBus;

import app.vp.cn.common.base.BaseActivity;
import app.vp.cn.third.LiveDataBusDesign.LiveDataBusClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bt_main)
    Button btMain;
    @BindView(R.id.bt_jump)
    Button btJump;
    @BindView(R.id.bt_jumpFour)
    Button btJumpFour;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewAndData() {

    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {
        LiveEventBus
                .get("main", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Log.i("bai", "onChanged: 收到了web 进程跨进程发送过来的事件，内容是>>> " + s);
                        Constant.test = "456";
                    }
                });


    }

    @Override
    protected void destroyResources() {

    }

    @OnClick({R.id.bt_main, R.id.bt_jump, R.id.bt_jumpFour})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_main:
                Constant.test = "123";
                LiveEventBus.get("sticky")
                        .broadcast("cup");
                break;
            case R.id.bt_jump:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("text", "卡达鸭");
                startActivityForResult(intent, 5);
                break;
            case R.id.bt_jumpFour:
                openActivity(FourthActivity.class);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            String test = data.getStringExtra("text");
            btJump.setText(test);
        }
    }
}
