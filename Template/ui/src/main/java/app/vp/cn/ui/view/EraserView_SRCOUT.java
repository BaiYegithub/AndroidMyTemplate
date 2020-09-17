package app.vp.cn.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import app.vp.cn.ui.R;

/**
 * Created by baiye
 * Date: 2020/9/16
 * Time: 18:01
 * Description:
 */
public class EraserView_SRCOUT extends View {

    private final Paint mPaint;
    private final Bitmap bmpSrc;
    private final Bitmap bmpDst;
    private final Path mPath;
    private float mPreX;
    private float mPreY;

    public EraserView_SRCOUT(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(45);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        bmpSrc = BitmapFactory.decodeResource(getResources(), R.drawable.lion);
        bmpDst = Bitmap.createBitmap(bmpSrc.getWidth(), bmpSrc.getHeight(), Bitmap.Config.ARGB_8888);

        mPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float endY = event.getY();
                mPath.quadTo(mPreX, mPreY, (mPreX + endX) / 2, (mPreY + endY) / 2);
                mPreX = endX;
                mPreY = endY;

                break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //使用离屏绘制
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        //先把手势轨迹画到目标图像上
        Canvas canvas1 = new Canvas(bmpDst);
        canvas1.drawPath(mPath,mPaint);

        //然后把目标图像画到画布上
        canvas.drawBitmap(bmpDst,0,0,mPaint);

        //计算原图像区域
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(bmpSrc,0,0,mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }
}
