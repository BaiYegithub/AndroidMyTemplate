package app.vp.cn.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.vp.cn.ui.view.WaveView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WaveActivity extends AppCompatActivity {

    @BindView(R.id.waveView)
    WaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        ButterKnife.bind(this);

        waveView.setProgress(20);
    }
}
