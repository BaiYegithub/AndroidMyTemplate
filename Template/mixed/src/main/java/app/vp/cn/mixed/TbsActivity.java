package app.vp.cn.mixed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TbsActivity extends AppCompatActivity {


    @BindView(R.id.tbsWebView)
    WebView tbsWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbs);
        ButterKnife.bind(this);


        tbsWebView.loadUrl("https://so.toutiao.com/search?keyword=C罗打破贝利进球纪录&pd=synthesis&traffic_source=WA1113&original_source=1&source=client\n");

    }
}
