package app.vp.cn.ui.view.testview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import app.vp.cn.ui.R;
import app.vp.cn.ui.utils.DeviceUtil;

/**
 * Created by baiye
 * Date: 2021/7/27
 * Time: 11:29
 * Description:
 */
public class CustomTitleView extends View {


    private int titleColor;
    private float textSize;
    private String titleText;
    private TextPaint textPaint;
    private Rect mTextBound;
    private Paint bgPaint;

    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView);
        titleColor = typedArray.getColor(R.styleable.CustomTitleView_titleColor, getResources().getColor(R.color.colorAccent));
        textSize = typedArray.getDimension(R.styleable.CustomTitleView_titleSize, DeviceUtil.dip2px(context, 20));
        titleText = typedArray.getString(R.styleable.CustomTitleView_titleText);
        typedArray.recycle();

        init();
    }

    private void init() {
        textPaint = new TextPaint();
        textPaint.setColor(titleColor);
        textPaint.setTextSize(textSize);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);

        //获取推荐的行距
        textPaint.getFontSpacing();
        //获取textPaint的一系列数值
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        mTextBound = new Rect();
        textPaint.getTextBounds(titleText, 0, titleText.length(), mTextBound);

        bgPaint = new Paint();
        bgPaint.setColor(Color.BLUE);
        bgPaint.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        int width, height;
        if (MeasureSpec.EXACTLY == widthMode) {
            width = measureWidth;
        } else {
            width = Math.min(getPaddingLeft() + mTextBound.width() + getPaddingRight(), measureWidth);
        }

        if (MeasureSpec.EXACTLY == heightMode) {
            height = measureHeight;
        } else {
            height = Math.min(getPaddingTop() + mTextBound.height() + getPaddingBottom(), measureHeight);
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), bgPaint);

//        StaticLayout staticLayout = new StaticLayout(titleText, textPaint, getMeasuredWidth(),
//                Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
//        staticLayout.draw(canvas);
        //        canvas.drawText(titleText, getWidth() / 2 - mTextBound.width() / 2, getHeight() / 2 + mTextBound.height() / 2, textPaint);
        //测量文字宽度
        float textWidth = textPaint.measureText(titleText);
        float[] measureWidth = new float[]{};
        int count = textPaint.breakText(titleText, 0, titleText.length(), true, 300, measureWidth);
        canvas.drawText(titleText,0,count,150,150,textPaint);

        int count1 = textPaint.breakText(titleText, 0, titleText.length(), true, 600, measureWidth);
        canvas.drawText(titleText,0,count1,150,150+textPaint.getFontSpacing(),textPaint);
    }
}
