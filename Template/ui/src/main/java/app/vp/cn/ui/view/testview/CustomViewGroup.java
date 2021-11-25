package app.vp.cn.ui.view.testview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by baiye
 * Date: 2021/7/29
 * Time: 16:58
 * Description:
 */
public class CustomViewGroup extends ViewGroup {

    public CustomViewGroup(Context context) {
        this(context, null);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int cCount = getChildCount();
        int width = 0, height = 0;
        int topWidth = 0, bottomWidth = 0, lHeight = 0, rHeight = 0;

        for (int i = 0; i < cCount; i++) {
            View viewChild = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) viewChild.getLayoutParams();
            if (i == 0 || i == 1) {
                topWidth += layoutParams.leftMargin + viewChild.getMeasuredWidth() + layoutParams.rightMargin;
            }
            if (i == 2 || i == 3) {
                bottomWidth += layoutParams.leftMargin + viewChild.getMeasuredWidth() + layoutParams.rightMargin;
            }
            if (i == 0 || i == 2) {
                lHeight += layoutParams.topMargin + viewChild.getMeasuredHeight() + layoutParams.bottomMargin;
            }
            if (i == 1 || i == 3) {
                rHeight += layoutParams.topMargin + viewChild.getMeasuredHeight() + layoutParams.bottomMargin;
            }

        }
        width = Math.max(topWidth, bottomWidth);
        height = Math.max(lHeight, rHeight);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

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
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();
            int childL = 0, childT = 0, childR = 0, childB = 0;
            switch (i) {
                case 0:
                    childL = marginLayoutParams.leftMargin;
                    childT = marginLayoutParams.topMargin;
                    break;
                case 1:
                    childL = getWidth() - marginLayoutParams.rightMargin - childView.getMeasuredWidth();
                    childT = marginLayoutParams.topMargin;
                    break;
                case 2:
                    childL = marginLayoutParams.leftMargin;
                    childT = getHeight() - marginLayoutParams.bottomMargin - childView.getMeasuredHeight();
                    break;
                case 3:
                    childL = getWidth() - marginLayoutParams.rightMargin - childView.getMeasuredWidth();
                    childT = getHeight() - marginLayoutParams.bottomMargin - childView.getMeasuredHeight();
                    break;
            }
            childR = childL + childView.getMeasuredWidth();
            childB = childT + childView.getMeasuredHeight();
            childView.layout(childL, childT, childR, childB);
        }
    }
}
