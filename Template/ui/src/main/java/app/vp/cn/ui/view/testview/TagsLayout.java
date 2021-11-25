package app.vp.cn.ui.view.testview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import app.vp.cn.ui.R;

/**
 * Created by baiye
 * Date: 2021/7/30
 * Time: 14:05
 * Description:
 */
public class TagsLayout extends ViewGroup {


    private int horizontalInterval;
    private int verticalInterval;

    public TagsLayout(Context context) {
        this(context, null);
    }

    public TagsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TagsLayout);
        if (typedArray != null) {
            horizontalInterval = (int) typedArray.getDimension(R.styleable.TagsLayout_horizontal_single_interval, 0);
            verticalInterval = (int) typedArray.getDimension(R.styleable.TagsLayout_vertical_single_interval, 0);
            typedArray.recycle();
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();

        int width = 0, height = 0;
        int lineWidth = 0;//每一行的宽度
        int lineHeight = 0;//每一行的高度
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() == View.GONE) {
                continue;
            }
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();
            int topMargin = marginLayoutParams.topMargin;
            int bottomMargin = marginLayoutParams.bottomMargin;
            int leftMargin = marginLayoutParams.leftMargin;
            int rightMargin = marginLayoutParams.rightMargin;

            if (paddingLeft + lineWidth + leftMargin + rightMargin + childWidth > widthSize) {
                width = Math.max(lineWidth, leftMargin + childWidth + rightMargin + horizontalInterval);
                height += lineHeight;
                //另起一行
                lineWidth = leftMargin + childWidth + rightMargin + horizontalInterval;
                lineHeight = childHeight + topMargin + bottomMargin + verticalInterval;
                childView.setTag(new MyLocation(paddingLeft + leftMargin, paddingTop + topMargin +
                        height+verticalInterval, paddingLeft + leftMargin + childWidth + horizontalInterval, paddingTop + topMargin +
                        height + childHeight + verticalInterval));
            } else {
                childView.setTag(new MyLocation(paddingLeft + lineWidth + leftMargin + horizontalInterval, paddingTop + topMargin + height + verticalInterval,
                        paddingLeft + lineWidth + leftMargin + childWidth + horizontalInterval, paddingTop + topMargin + height + childHeight + verticalInterval));
                lineHeight = Math.max(lineHeight, childHeight + topMargin + bottomMargin + verticalInterval);
                lineWidth += leftMargin + childWidth + rightMargin + horizontalInterval;
            }
        }
        height += lineHeight;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        }
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            MyLocation myLocation = (MyLocation) childView.getTag();
            childView.layout(myLocation.left, myLocation.top, myLocation.right, myLocation.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public static class MyLocation {
        public int left;
        public int top;
        public int right;
        public int bottom;

        public MyLocation(int left, int top, int right, int bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }
    }
}
