package app.vp.cn.profession;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import app.vp.cn.common.base.BaseActivity;
import app.vp.cn.common.util.LiveDataBus;
import app.vp.cn.profession.bean.Person;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity {

    @BindView(R.id.bt_click)
    Button btClick;
    @BindView(R.id.bt_click1)
    Button btClick1;
    @BindView(R.id.bt_click2)
    Button btClick2;

    @Override
    protected int initContentView() {
        return R.layout.activity_second;
    }

    @Override
    protected void initViewAndData() {

    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {
        LiveDataBus.get().with("sec",String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.i("bai", "onChanged:stickey 模式成功 ");
            }
        });
    }

    @Override
    protected void destroyResources() {

    }

    @OnClick({R.id.bt_click, R.id.bt_click1, R.id.bt_click2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_click:
                LiveDataBus.get().with("click").setValue("click");
                break;
            case R.id.bt_click1:
                LiveDataBus.get().with("click1").postValue("click1");
                break;
            case R.id.bt_click2:
                Person person = new Person();
                person.age = 16;
                person.name = "黎明";
                LiveDataBus.get().with("click2").postValue(person);
                break;
                default:
                    break;
        }
    }
}
