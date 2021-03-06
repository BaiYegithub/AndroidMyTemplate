package app.vp.cn.profession;

import android.app.Dialog;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import app.vp.cn.common.base.BaseActivity;
import app.vp.cn.common.util.GlideUtil;
import app.vp.cn.common.util.LiveDataBus;
import app.vp.cn.profession.bean.Person;
import app.vp.cn.profession.view1.AppUpdateDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends BaseActivity {

    @BindView(R.id.bt_toSecond)
    Button btToSecond;
    @BindView(R.id.bt_saveStringSet)
    Button btSave;

    @BindView(R.id.iv_glide)
    ImageView ivGlide;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("isShow", "handleMessage: 接收到了  isFinishing 的值是"+isFinishing());
            if(!isFinishing()&&appUpdateDialog!=null){
                Log.i("isShow", "handleMessage: 显示了");
                appUpdateDialog.show();
            }
        }
    };
    private AppUpdateDialog appUpdateDialog;

    @Override
    protected int initContentView() {
        return R.layout.activity_first;
    }

    @Override
    protected void initViewAndData() {

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences spBai = getSharedPreferences("bai", MODE_PRIVATE);
                SharedPreferences.Editor edit = spBai.edit();
                Set<String> defaultSet = new HashSet<>();
                Set<String> la = spBai.getStringSet("la", defaultSet);
                if(!la.contains("a")){
                    Set<String> saveSet = new HashSet<>();
                    saveSet.add("a");
                    Iterator<String> iterator = la.iterator();
                    if(iterator!=null&&iterator.hasNext()){
                        saveSet.add(iterator.next());
                    }
                    edit.putStringSet("la",saveSet);
                    edit.commit();
                }
            }
        });

        String url = "http://imgservicepa.suning.cn/uimg1/b2c/image/-SlsxoOKsWa1aahASHQCLQ.jpg_720h_1280w_4e_0l?tdsourcetag=s_pctim_aiomsg";
        GlideUtil.setImageTiling(ivGlide,url,R.drawable.common_loading3_0);

    }

    @Override
    protected void initHttp() {
        appUpdateDialog = new AppUpdateDialog(this);

       // handler.sendEmptyMessageDelayed(0,5000);


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
