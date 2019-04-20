package app.vp.cn.ui;

import android.view.SurfaceHolder;
import android.view.SurfaceView;

import app.vp.cn.common.base.BaseActivity;
import butterknife.BindView;

public class SurfaceActivity extends BaseActivity {


    @BindView(R.id.surface_acSurface)
    SurfaceView surfaceAcSurface;

    @Override
    protected int initContentView() {
        return R.layout.activity_surface;
    }

    @Override
    protected void initViewAndData() {
        SurfaceHolder holder = surfaceAcSurface.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            //当界面显现出来的时候的回调
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

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
