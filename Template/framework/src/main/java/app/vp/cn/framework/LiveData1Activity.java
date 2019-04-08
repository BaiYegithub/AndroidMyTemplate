package app.vp.cn.framework;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LiveData1Activity extends AppCompatActivity {

    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.bt)
    Button bt;
    private MutableLiveData<String> stringMutableLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        ButterKnife.bind(this);

        stringMutableLiveData = new MutableLiveData<>();


        stringMutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvText.setText(s);
            }
        });


    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
        stringMutableLiveData.setValue("随机数字是" + (int) (Math.random() * 10 + 1));
    }
}
