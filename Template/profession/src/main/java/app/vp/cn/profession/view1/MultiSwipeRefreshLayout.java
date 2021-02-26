package app.vp.cn.profession.view1;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * zlc.season.practicalrecyclerview
 * Created by pan on 2018/3/30 17:56
 * Email：277038705@qq.com
 */

public class MultiSwipeRefreshLayout extends SwipeRefreshLayout {
    private View[] mSwipeableChildren;

    public MultiSwipeRefreshLayout(Context context) {
        super(context);
    }

    public MultiSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置可以触发刷新事件的子view * Set the children which can trigger a refresh by swiping down when they are visible. These * views need to be a descendant of this view.
     */
    public void setSwipeableChildren(final int... ids) {
        assert ids != null;
        mSwipeableChildren = new View[ids.length];
        for (int i = 0; i < ids.length; i++) {
            mSwipeableChildren[i] = findViewById(ids[i]);
        }
    }
    /**
     * 如果返回false，开始gesture * <p/> * This method controls when the swipe-to-refresh gesture is triggered. By returning false here * we are signifying that the view is in a state where a refresh gesture can start. * <p/> * <p>As {@link SwipeRefreshLayout} only supports one direct child by * default, we need to manually iterate through our swipeable children to see if any are in a * state to trigger the gesture. If so we return false to start the gesture.
     */
    @Override
    public boolean canChildScrollUp() {
        if (mSwipeableChildren != null && mSwipeableChildren.length > 0) {
            for (View view : mSwipeableChildren) {
                if (view != null && view.isShown()) {
                    if (view instanceof RecyclerView) {
                        return canViewScrollUp(view);
                    }
                }
            }
        }
        return true;
    }

    private static boolean canViewScrollUp(View view) { //负数，表示view上滑
         return ViewCompat.canScrollVertically(view, -1);
    }
}
