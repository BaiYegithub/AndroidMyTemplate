package app.vp.cn.framework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Secondactivity extends AppCompatActivity {

    @BindView(R.id.sr_acTwo)
    SmartRefreshLayout srAcTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);
        ButterKnife.bind(this);

        srAcTwo.autoRefresh();
    }
}
