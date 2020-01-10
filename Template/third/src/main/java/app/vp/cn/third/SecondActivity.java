package app.vp.cn.third;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jeremyliao.liveeventbus.LiveEventBus;

import app.vp.cn.common.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity {


    @BindView(R.id.bt_toMain)
    Button btToMain;
    @BindView(R.id.bt_show)
    Button btShow;

    @Override
    protected int initContentView() {
        return R.layout.activity_second;
    }

    @Override
    protected void initViewAndData() {
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        btToMain.setText(text);
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {
        LiveEventBus.get("sticky", String.class)
                .observeSticky(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Log.i("bai", "onChanged:收到了跨进程发送的粘性事件 内容是>>>" + s);
                        Constant.test = "123";
                    }
                });
    }

    @Override
    protected void destroyResources() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("text","第二个");
            setResult(100,intent);
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);

    }

    @OnClick({R.id.bt_toMain, R.id.bt_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_toMain:
                Constant.test = "456";
                LiveEventBus.get("main")
                        .broadcast("tree");
                break;
            case R.id.bt_show:
                openActivity(ThirdActivity.class);
                break;
        }
    }
}
