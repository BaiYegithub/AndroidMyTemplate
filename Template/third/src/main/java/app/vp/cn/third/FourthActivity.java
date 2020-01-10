package app.vp.cn.third;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.jeremyliao.liveeventbus.LiveEventBus;

import app.vp.cn.common.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FourthActivity extends BaseActivity {


    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int initContentView() {
        return R.layout.activity_fourth;
    }

    @Override
    protected void initViewAndData() {
        tv.setText("test的值是" + Constant.test);
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
                        Log.i("bai", "onChanged:收到了MainActivity发送的粘性事件 内容是>>>" + s);
                    }
                });
    }

    @Override
    protected void destroyResources() {

    }
}
