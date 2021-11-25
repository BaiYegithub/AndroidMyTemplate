package app.vp.cn.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import app.vp.cn.ui.R;

/**
 * Created by baiye
 * Date: 2021/11/19
 * Time: 16:17
 * Description:
 */
public class PhotoView extends View {
    private Bitmap mBitmap;

    private Paint mPaint = new Paint();
    private int left;
    private int top;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhotoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PhotoView);
        Drawable drawable = typedArray.getDrawable(R.styleable.PhotoView_photoview_src);
        if (drawable == null) {
            mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.iv_bg);
        } else {
            mBitmap = toBitmap(drawable, 800, 800);
        }

        typedArray.recycle();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        left = (w - mBitmap.getWidth()) / 2;
        top = (h - mBitmap.getHeight()) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, left, top, mPaint);
    }

    private Bitmap toBitmap(Drawable drawable, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
