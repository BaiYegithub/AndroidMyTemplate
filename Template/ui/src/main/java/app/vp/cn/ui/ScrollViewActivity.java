package app.vp.cn.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class ScrollViewActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        findViewById(R.id.bt_acScroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("bai", "onClick: 看看能不能响应点击事件");
            }
        });

        /*scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        int height = scrollView.getHeight();
                        Log.i("bai", "run: scrollView的height是"+height);
                    }
                });
            }
        });*/
    }
}
