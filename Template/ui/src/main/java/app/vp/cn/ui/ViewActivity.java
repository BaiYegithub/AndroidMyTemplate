package app.vp.cn.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import app.vp.cn.common.util.LogUtil;
import app.vp.cn.common.util.UIUtils;
import app.vp.cn.ui.view.ViewLinearlayout;
import app.vp.cn.ui.view.WaveView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewActivity extends AppCompatActivity {

    @BindView(R.id.wave_acView)
    WaveView waveAcView;
    @BindView(R.id.linear_acView)
    ViewLinearlayout linearAcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.i("xxx", "Activity 的 dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.i("xxx", "Activity 的 onTouchEvent");
        return super.onTouchEvent(event);
    }

    @OnClick({R.id.wave_acView, R.id.linear_acView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wave_acView:
                Toast.makeText(UIUtils.getContext(), "点击了view", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_acView:
                Toast.makeText(UIUtils.getContext(), "点击了linear", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
