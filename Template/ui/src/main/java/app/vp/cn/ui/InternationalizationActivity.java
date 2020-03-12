package app.vp.cn.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import app.vp.cn.ui.utils.LanguageUtils;
import app.vp.cn.ui.utils.MultiLanguageUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InternationalizationActivity extends AppCompatActivity {

    @BindView(R.id.tv_text_ac)
    TextView tvTextAc;
    @BindView(R.id.tv_2_ac)
    TextView tv2Ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internationalization);
//        UiApplication.getInstance().setChineseMode(InternationalizationActivity.this);
        ButterKnife.bind(this);
        tv2Ac.setText(getResources().getString(R.string.show_text));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageUtils.isChinaContainsTWUser()?base:MultiLanguageUtil.attachBaseContext(base));
    }

}
