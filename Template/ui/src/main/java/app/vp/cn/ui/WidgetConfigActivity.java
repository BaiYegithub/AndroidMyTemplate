package app.vp.cn.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WidgetConfigActivity extends AppCompatActivity {

    @BindView(R.id.bt_toMain)
    Button btToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_config);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_toMain)
    public void onViewClicked() {
        Intent intent = new Intent(WidgetConfigActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
