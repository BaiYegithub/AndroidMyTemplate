package app.vp.cn.ui.view.nestscrollview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by baiye
 * Date: 2021/7/22
 * Time: 16:27
 * Description:
 */
public class TestNestdParentLayout extends FrameLayout implements NestedScrollingParent {

    private NestedScrollingParentHelper parentHelper;

    public TestNestdParentLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        parentHelper = new NestedScrollingParentHelper(this);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {
        parentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {
        parentHelper.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {

        if (dx > 0) {
            if (dx + target.getRight() > getWidth()) {
//                dx = dx + target.getRight() - getWidth();
                offsetLeftAndRight(dx);
                consumed[0] += dx;
            }
        } else {
            if (dx + target.getLeft() < 0) {
//                dx = dx + target.getLeft();
                offsetLeftAndRight(dx);
                consumed[0] += dx;
            }
        }

        if (dy > 0) {
            if (dy + target.getBottom() > getHeight()) {
                offsetTopAndBottom(dy);
                consumed[1] += dy;
            }
        } else {
            if (dy + target.getTop() < 0) {
                offsetTopAndBottom(dy);
                consumed[1] += dy;
            }
        }
    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return parentHelper.getNestedScrollAxes();
    }
}
