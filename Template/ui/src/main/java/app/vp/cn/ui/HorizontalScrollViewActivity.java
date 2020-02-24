package app.vp.cn.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.HorizontalScrollView;

import app.vp.cn.ui.view.CustomView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalScrollViewActivity extends AppCompatActivity {

   /* @BindView(R.id.tv_1)
    TextView tv1;*/
  /*  @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;*/

    @BindView(R.id.custom_acHorizontal)
    CustomView customView;
    @BindView(R.id.center_horizontal)
    HorizontalScrollView centerHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll_view);
        ButterKnife.bind(this);

        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.setClickData(centerHorizontal.getScrollX(),centerHorizontal.getScrollX()+200,0,centerHorizontal.getHeight(),0,0,getResources().getColor(R.color.colorPrimaryDark));
            }
        });

    }
}
