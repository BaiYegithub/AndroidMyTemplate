package app.vp.cn.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;

import app.vp.cn.ui.text.DataSetAdapter;
import app.vp.cn.ui.text.VerticalRollingTextView;
import app.vp.cn.ui.view.WaveView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WaveActivity extends AppCompatActivity {

    @BindView(R.id.waveView)
    WaveView waveView;

    @BindView(R.id.verticalRollingView)
    VerticalRollingTextView verticalRollingTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        ButterKnife.bind(this);

        waveView.setProgress(20);

        List<String> mDataSet = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            mDataSet.add("大魔王是 123..... 购买3年VIP会员 15:12:13"+i);
        }

        verticalRollingTextView.setDataSetAdapter(new DataSetAdapter<String>(mDataSet) {

            @Override
            protected String text(String charSequence) {
                return charSequence;
            }
        });

        verticalRollingTextView.run();
    }
}
