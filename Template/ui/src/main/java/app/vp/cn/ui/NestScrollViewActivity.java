package app.vp.cn.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NestScrollViewActivity extends AppCompatActivity {

    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.ll)
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_scroll_view);
        ButterKnife.bind(this);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.i("bai", "onScrollChange: x轴 >>>" + scrollX + "Y轴 >>>" + scrollY);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                }
            });
        }

  /*     RxPermissions.getInstance(NestScrollViewActivity.this)
               .requestEach( Manifest.permission.READ_PHONE_STATE,
                       Manifest.permission.ACCESS_COARSE_LOCATION,
                       Manifest.permission.WRITE_EXTERNAL_STORAGE,
                       Manifest.permission.READ_EXTERNAL_STORAGE)*/

        /*String cameraPackageName = null;
        List<PackageInfo> packageInfos = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packageInfos.size(); i++) {
            String packageName = packageInfos.get(i).packageName;
            Log.i("bai", "onCreate: 系统安装的packageName 是"+packageName);
            if(packageName.contains("camera")){
                cameraPackageName = packageName;
            }
        }
        if(null!=cameraPackageName){
            Intent settintIntent = getPackageManager().
                    getLaunchIntentForPackage(cameraPackageName);
            startActivity(settintIntent);
        }*/

    }
}
