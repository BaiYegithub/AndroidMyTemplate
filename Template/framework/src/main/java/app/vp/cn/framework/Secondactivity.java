package app.vp.cn.framework;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import app.vp.cn.framework.bean.User;
import app.vp.cn.framework.bean.UserModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Secondactivity extends AppCompatActivity {

    @BindView(R.id.sr_acTwo)
    SmartRefreshLayout srAcTwo;

    @BindView(R.id.tv_acSecond)
    TextView tvSecond;

    @BindView(R.id.bt_toMainAc)
    Button bt_toMainAc;

    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);
        ButterKnife.bind(this);

        srAcTwo.autoRefresh();

        userModel = ViewModelProviders.of(this).get(UserModel.class);
        userModel.getMutable().observeForever( new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                tvSecond.setText(user.getUserName());
            }
        });

        bt_toMainAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Secondactivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
