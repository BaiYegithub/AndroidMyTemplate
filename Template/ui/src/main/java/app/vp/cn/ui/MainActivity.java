package app.vp.cn.ui;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.concurrent.TimeUnit;

import app.vp.cn.common.app.BaseApp;
import app.vp.cn.common.util.UIUtils;
import app.vp.cn.ui.view.EnglishCharFilter;
import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @butterknife.BindView(R.id.stub_import)
    ViewStub stubImport;
    @butterknife.BindView(R.id.bt_show)
    Button btShow;
    @butterknife.BindView(R.id.tv_hide)
    Button tvHide;

    @BindView(R.id.et_acMain)
    EditText et_main;

    @BindView(R.id.tab_acMain)
    TabLayout tabAcMain;

    @BindView(R.id.tv_acMain)
    TextView tvAcMain;

    @BindView(R.id.viewflipper)
    ViewFlipper viewFlipper;

    @BindView(R.id.rlv_ib_out)
    RelativeLayout relativeLayout;


    private ValueAnimator valueAnimator;
    private ImageView ivLogoIcon;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("bai", "onCreate: ");
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);
        //使用ViewStub 控制布局按需加载 显示的方法
        ViewStub viewStub = findViewById(R.id.stub_import);
        viewStub.setVisibility(View.VISIBLE);
        viewStub.setVisibility(View.GONE);

        //这里给输入框加入了一个限制，输入内容不能大于4个字节  （意思是汉字不能多于两个，英文字母不能多于4个）
        et_main.setFilters(new InputFilter[]{new EnglishCharFilter(4)});


        //获取键盘是否弹出以及弹出的高度
        et_main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            //当键盘弹出隐藏的时候 会调用此方法
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //获取当前界面可视部分
                MainActivity.this.getWindow().getDecorView()
                        .getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight = MainActivity.this.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = screenHeight - r.bottom;
                //检查是否有底部导航栏，有的话要减去底部导航栏的高度
                if (UIUtils.isNavigationBarExist(MainActivity.this)) {
                    heightDifference -= UIUtils.getVirtualBarHeigh();
                }

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, heightDifference);

//                 hs_acWrite.setLayoutParams(layoutParams);
            }
        });
        for (int i = 0; i < 10; i++) {
            tabAcMain.addTab(tabAcMain.newTab().setText("无名之辈" + i));
        }
        tabAcMain.getTabAt(9).select();

        int[] colorInt = new int[]{0xffffff00, 0xff0000ff};
        valueAnimator = ValueAnimator.ofInt(colorInt);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                tvAcMain.setBackgroundColor(curValue);
            }
        });


        viewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                valueAnimator.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        for (int i = 0; i < 2; i++) {
            TextView textView = new TextView(MainActivity.this);
            textView.setText("啦啦啦啦啦啦啦啦" + i);
            viewFlipper.addView(textView);
        }

        Observable.interval(5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        TextView textView = new TextView(MainActivity.this);
                        if (aLong % 2 == 0) {
                            textView.setText("每5秒发送一个" + aLong);
                        }else {
                            textView.setText("基基基基棒棒棒棒棒棒棒棒棒棒棒棒棒棒"+aLong);
                        }
                        viewFlipper.addView(textView);
                    }
                });

        String url = "http://pgdt.ugdtimg.com/gdt/0/EAA4ly2AEsAEsAAAIEOBfKk8yAwrfLwXP.png/0?ck=705878ab3180c32bf49ecb5485aba3d2";
//        GlideUtil.setImageUriTiling(ivLogoIcon, Uri.parse(url),0);

        ivLogoIcon = findViewById(R.id.iv_logo_icon);
        Log.i("bai", "onCreate: ivLogoIcon是"+ ivLogoIcon.hashCode());
        ImageView ivLogoIcon2 = findViewById(R.id.iv_logo_icon);
        Log.i("bai", "onCreate: ivLogoIcon2是"+ivLogoIcon2.hashCode());

        int height = ivLogoIcon.getHeight();
        Log.i("bai", "onCreate: 在onCreate中imageView的height是"+height);

        ivLogoIcon.post(new Runnable() {
            @Override
            public void run() {

            }
        });

        bitmap = BitmapFactory.decodeResource(BaseApp.getmContext().getResources(), R.drawable.iv_splash_logo);
        if(bitmap !=null){
            Log.i("bai", "onCreate: bitmap 的宽高是" + bitmap.getWidth() + ">>>>>>>>>" + bitmap.getHeight());
        }else {
            Log.i("bai", "onCreate: bitmap 是空的");
        }

    }

    @butterknife.OnClick({R.id.bt_show, R.id.tv_hide ,R.id.bt_toWaveAc,R.id.rlv_ib_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_show:
                if(bitmap !=null){
                    Log.i("bai", "onCreate: bitmap 的宽高是" + bitmap.getWidth() + ">>>>>>>>>" + bitmap.getHeight());
                }else {
                    Log.i("bai", "onCreate: bitmap 是空的");
                }
                ivLogoIcon.setImageBitmap(bitmap);
                stubImport.setVisibility(View.VISIBLE);
                int height = ivLogoIcon.getHeight();
                Log.i("bai", "onClick: 在onClick中imageView的height是"+height);
                break;
            case R.id.tv_hide:
                stubImport.setVisibility(View.GONE);
                break;
            case R.id.bt_toWaveAc:
                startActivity(new Intent(MainActivity.this,WaveActivity.class));
                break;
            case R.id.rlv_ib_out:
                Log.i("bai", "onViewClicked: 点击了rlvButton");
                String s = getWindow().getDecorView().getParent().toString();
                Log.i("bai", "onCreate: getWIndow>>>>>>>"+s);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("bai", "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("bai", "onResume: ");
        int height = ivLogoIcon.getHeight();
        Log.i("bai", "onResume: 在onResume中imageView的height是"+height);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("bai", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("bai", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("bai", "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("bai", "onRestart: ");
    }
}
