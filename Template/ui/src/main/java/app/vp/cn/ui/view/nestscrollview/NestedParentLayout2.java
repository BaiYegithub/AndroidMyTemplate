package app.vp.cn.ui.view.nestscrollview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by baiye
 * Date: 2021/7/24
 * Time: 17:27
 * Description:
 */
public class NestedParentLayout2 extends FrameLayout  implements NestedScrollingParent2 {


    private final NestedScrollingParentHelper nestedScrollingParentHelper;

    public NestedParentLayout2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        nestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes, type);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        nestedScrollingParentHelper.onStopNestedScroll(target,type);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @Nullable int[] consumed, int type) {

        if (dx > 0) {
            if (target.getRight() + dx > getWidth()) {
                offsetLeftAndRight(dx);
                consumed[0] += dx;
            }
        } else {
            if (target.getLeft() + dx < 0) {
                offsetLeftAndRight(dx);
                consumed[0] += dx;
            }
        }
        if (dy > 0) {
            if (target.getBottom() + dy > getHeight()) {
                offsetTopAndBottom(dy);
                consumed[0] += dy;
            }
        } else {
            if (target.getTop() + dy < 0) {
                offsetTopAndBottom(dy);
                consumed[0] += dy;
            }
        }
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        return true;
    }


    @Override
    public void onStopNestedScroll(@NonNull View target) {
        nestedScrollingParentHelper.onStopNestedScroll(target);
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
        return nestedScrollingParentHelper.getNestedScrollAxes();
    }
}
