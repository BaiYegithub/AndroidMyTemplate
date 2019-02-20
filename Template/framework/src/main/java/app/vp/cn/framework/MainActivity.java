package app.vp.cn.framework;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import app.vp.cn.framework.bean.User;
import app.vp.cn.framework.bean.UserModel;
import app.vp.cn.framework.fragment.BottomFragment;
import app.vp.cn.framework.fragment.TopFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ll_ac)
    LinearLayout llAc;
    @BindView(R.id.bt_acMain)
    Button btAcMain;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TopFragment topFragment = new TopFragment();
        BottomFragment bottomFragment = new BottomFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.ll_ac, topFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.ll_ac2, bottomFragment).commit();

        userModel = ViewModelProviders.of(this).get(UserModel.class);
       // userModel.getMutable().postValue(new User("我是MainActivity"));
    }

    @OnClick(R.id.bt_acMain)
    public void onViewClicked() {
        userModel.getMutable().postValue(new User("我是MainActivity"));
    }
}
