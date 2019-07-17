package app.vp.cn.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import app.vp.cn.common.app.BaseApp;
import app.vp.cn.common.util.UIUtils;
import app.vp.cn.ui.utils.DeviceUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LinearActivity extends AppCompatActivity {

    @BindView(R.id.ll_left)
    LinearLayout llLeft;
    @BindView(R.id.iv_pic)
    ImageView ivPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        ButterKnife.bind(this);

      /*  LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) llLeft.getLayoutParams();
        layoutParams.width = 1439;
        llLeft.setLayoutParams(layoutParams);*/

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic_1);
        Bitmap bitmap1 = drawBackGround(bitmap, UIUtils.dip2px(BaseApp.getmContext(), 2), UIUtils.dip2px(BaseApp.getmContext(), 4));

        ivPic.setImageBitmap(bitmap1);
    }

    public static Bitmap drawBackGround(Bitmap bitmap,int roundPx,int framePx) {
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
