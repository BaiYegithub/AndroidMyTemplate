package app.vp.cn.ui;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import app.vp.cn.common.app.BaseApp;
import app.vp.cn.common.util.UIUtils;
import app.vp.cn.ui.bean.ParentSingleton;
import app.vp.cn.ui.utils.DeviceUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LinearActivity extends AppCompatActivity {

    @BindView(R.id.ll_left)
    LinearLayout llLeft;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.ll_out)
    LinearLayout llOut;
    private View inflate;

    @BindView(R.id.tv_acLi)
    TextView tv_acLi;

    @BindView(R.id.tv_clock)
    TextClock tv_clock;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        ButterKnife.bind(this);

        ParentSingleton.value = 100;
      /*  LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) llLeft.getLayoutParams();
        layoutParams.width = 1439;
        llLeft.setLayoutParams(layoutParams);*/

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic_1);
        Bitmap bitmap1 = drawBackGround(bitmap, UIUtils.dip2px(BaseApp.getmContext(), 2), UIUtils.dip2px(BaseApp.getmContext(), 4));

        ivPic.setImageBitmap(bitmap1);

      /*  inflate = LayoutInflater.from(this).inflate(R.layout.layout_tv, llOut, false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        llOut.addView(inflate, 0, layoutParams);*/

        tv_acLi.setText("朝阳区西大望路朝阳区西大望路朝阳区西大望路");
        tv_acLi.setMaxLines(Integer.MAX_VALUE);
        tv_acLi.setMaxEms(Integer.MAX_VALUE);
        tv_acLi.setEllipsize(TextUtils.TruncateAt.END);
        tv_clock.setFormat12Hour("EEEE");
        tv_clock.setFormat24Hour("EEEE");
        tv_acLi.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        int Screendp = UIUtils.px2dip(LinearActivity.this, UIUtils.getScreenWidth(this));
        Log.i("bai", "onCreate: 屏幕宽度是>>>"+Screendp);
        tv_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("wave");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
     /*   inflate.post(new Runnable() {
            @Override
            public void run() {
                Log.i("baiye", "onCreate:宽是 " + llOut.getChildAt(0).getMeasuredWidth());
            }
        });*/
    }

    public static Bitmap drawBackGround(Bitmap bitmap, int roundPx, int framePx) {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_4444);
        //创建画板
        Canvas canvas = new Canvas(newBitmap);
        //画板drawARGB必须与Bitmap的Config一致
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
        //左上右下位置
        Rect rect = new Rect(framePx / 2, framePx / 2, bitmap.getWidth() - framePx / 2, bitmap.getHeight() - framePx / 2);
        RectF rectF = new RectF(rect);
        //RectF对象,X矩形圆角大小,Y矩形圆角大小,画笔
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        //设置两张图片相交时的模式为Mode.SRC_IN
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //创建画边框的画笔
        Paint paintFrame = new Paint();
        paintFrame.setAntiAlias(true);
        //设置空心画笔
        paintFrame.setStyle(Paint.Style.STROKE);
        //设置边框颜色
        paintFrame.setColor(Color.WHITE);
        //设置边框宽度
        paintFrame.setStrokeWidth(framePx);
        //画圆角矩形边框
        canvas.drawRoundRect(rectF, roundPx, roundPx, paintFrame);
        return newBitmap;
    }
}
