package app.vp.cn.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import app.vp.cn.common.base.BaseActivity;
import app.vp.cn.ui.view.BlurBitmap;
import butterknife.BindView;

public class BlurActivity extends BaseActivity {

    @BindView(R.id.fram_acBlur)
    FrameLayout frameAcBlur;
    @BindView(R.id.iv_bg)
    ImageView ivBg;

    @BindView(R.id.fram_ac)
    FrameLayout frameAc;
    @BindView(R.id.iv_bg_2)
    ImageView ivBg2;

    @Override
    protected int initContentView() {
        return R.layout.activity_blur;
    }

    @Override
    protected void initViewAndData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.iv_bg_harf);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ivBg.setImageBitmap(BlurBitmap.blur(BlurActivity.this, bitmap));
        }

        ivBg2.setImageBitmap(bitmap);

    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void destroyResources() {

    }
}
