package app.vp.cn.profession;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import app.vp.cn.common.base.BaseActivity;
import app.vp.cn.common.util.LiveDataBus;
import app.vp.cn.profession.bean.Person;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends BaseActivity {

    @BindView(R.id.bt_toSecond)
    Button btToSecond;

    @Override
    protected int initContentView() {
        return R.layout.activity_first;
    }

    @Override
    protected void initViewAndData() {

    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {

        //liveDataBus 是生命周期感知的，能够自动取消注册，同样的，如果在其他地方发送的消息，注册的界面不会马上接收并作出更改，
        //会回到这个界面再做更改     这个版本暂时不支持sticky   如要支持 请参考  https://github.com/JeremyLiao/LiveEventBus
        LiveDataBus.get().with("click",String.class).observe( this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.i("bai", "onChanged  click: 收到的click"+s);
            }
        });

        LiveDataBus.get().with("click1",String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.i("bai", "onChanged click1: 收到的click1"+s);
            }
        });

        LiveDataBus.get().with("click2", Person.class).observe(this, new Observer<Person>() {
            @Override
            public void onChanged(@Nullable Person person) {
                Log.i("bai","拿到的person"+person.name+"  "+person.age+"岁了");
            }
        });

    }

    @Override
    protected void destroyResources() {

    }

    @OnClick(R.id.bt_toSecond)
    public void onViewClicked() {
        openActivity(SecondActivity.class);
    }
}
