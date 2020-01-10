package app.vp.cn.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by baiye
 * Date: 2020/1/9
 * Time: 10:47
 * Description:
 */
public class CustomView extends View {

    private Paint clickRoundPaint;//点击事件画笔
    private float clickLeft, clickRight, clickTop, clickBottom, clickRx, clickRy;
    private int clickColor;
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    private void initViews() {
        clickRoundPaint = new Paint();
        clickRoundPaint.setAntiAlias(true);
        clickRoundPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawClickRound(canvas,clickLeft, clickRight, clickTop, clickBottom, clickRx, clickRy, clickColor);
    }
    //画点击事件的背景
    private void onDrawClickRound(Canvas canvas,float left, float right, float top, float bottom, float rx, float ry, int color) {
        RectF rectF = new RectF();
        rectF.left = left;
        rectF.top = top;
        rectF.right = right;
        rectF.bottom = bottom;
        clickRoundPaint.setColor(color);
        canvas.drawRoundRect(rectF, rx, ry, clickRoundPaint);
        canvas.save();
    }

    public void setClickData(float left, float right, float top, float bottom, float rx, float ry, int color) {
        this.clickLeft = left;
        this.clickRight = right;
        this.clickTop = top;
        this.clickBottom = bottom;
        this.clickRx = rx;
        this.clickRy = ry;
        this.clickColor = color;

        postInvalidate((int) left,(int) top,(int) right,(int) bottom);
    }
}
