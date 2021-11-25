package app.vp.cn.ui.view.testview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import app.vp.cn.ui.R;
import app.vp.cn.ui.utils.DeviceUtil;

/**
 * Created by baiye
 * Date: 2021/7/29
 * Time: 11:28
 * Description:
 */
public class CustomImageTitleView extends View {

    private int textColor;
    private float textSize;
    private String titleText;
    private int resourceId;
    private int mImageScale;
    private Bitmap mImgBitmap;

    private int mWidth, mHeight;
    private TextPaint mTextPaint;
    private Rect textBounds;
    private Paint bgPaint;
    private Paint imgPaint;
    private Rect imgRect;

    private static int FitXY = 0;

    public CustomImageTitleView(Context context) {
        this(context, null);
    }

    public CustomImageTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomImageTitleView);

        textColor = typedArray.getColor(R.styleable.CustomImageTitleView_titleColorC, Color.RED);
        textSize = typedArray.getDimension(R.styleable.CustomImageTitleView_titleSizeC, DeviceUtil.dip2px(context, 20));
        titleText = typedArray.getString(R.styleable.CustomImageTitleView_titleTextC);
        resourceId = typedArray.getResourceId(R.styleable.CustomImageTitleView_imageResC, R.drawable.iv_splash_logo);
        mImageScale = typedArray.getInt(R.styleable.CustomImageTitleView_imageScaleTypeC, 0);
        typedArray.recycle();
        init();
    }

    private void init() {
        mImgBitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(textColor);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setStrokeWidth(6);
        textBounds = new Rect();
        mTextPaint.getTextBounds(titleText, 0, titleText.length(), textBounds);

        bgPaint = new Paint();
        bgPaint.setColor(Color.YELLOW);
        bgPaint.setStrokeWidth(4);
        bgPaint.setStyle(Paint.Style.STROKE);

        imgPaint = new Paint();
        imgRect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置宽度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        if (MeasureSpec.EXACTLY == widthMode) {
            mWidth = widthSize;
        } else {
            mWidth = Math.max(mImgBitmap.getWidth(), textBounds.width()) + getPaddingLeft() + getPaddingRight();
            if (MeasureSpec.AT_MOST == widthMode) {
                mWidth = Math.min(mWidth, widthSize);
            }
        }
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (MeasureSpec.EXACTLY == heightMode) {
            mHeight = heightSize;
        } else {
            mHeight = getPaddingTop() + mImgBitmap.getHeight() + textBounds.height() + getPaddingBottom();
            if (MeasureSpec.AT_MOST == heightMode) {
                mHeight = Math.min(mHeight, heightSize);
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画边框
        canvas.drawRect(0, 0, mWidth, mHeight, bgPaint);
        //画图片
        if (mImageScale == FitXY) {
            imgRect.left = getPaddingLeft();
            imgRect.top = getPaddingTop();
            imgRect.right = mWidth - getPaddingRight();
            imgRect.bottom = mHeight - getPaddingBottom() - textBounds.height();
        } else {
            imgRect.left = mWidth / 2 - mImgBitmap.getWidth() / 2;
            imgRect.top = (mHeight - textBounds.height() - getPaddingTop() - getPaddingBottom()) / 2 - mImgBitmap.getWidth() / 2;
            imgRect.right = mWidth / 2 + mImgBitmap.getWidth() / 2;
            imgRect.bottom = (mHeight - textBounds.height() - getPaddingTop() - getPaddingBottom()) / 2 + mImgBitmap.getWidth() / 2;
        }

        canvas.drawBitmap(mImgBitmap, null, imgRect, imgPaint);
        //画底部文字
        if (textBounds.width() + getPaddingLeft() + getPaddingRight() > mWidth) {
            String msg = TextUtils.ellipsize(titleText, mTextPaint, mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom() - textBounds.height(), mTextPaint);
        }else {
            canvas.drawText(titleText,mWidth/2-textBounds.width()/2,mHeight - getPaddingBottom() ,mTextPaint);
        }
    }
}
