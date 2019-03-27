package app.vp.cn.common.base;

/**
 * author : by
 * date: 2019/3/27 0027  上午 11:38.
 * describe
 */

public interface BasePresenter<T extends BaseView> {
    void detachView();
}
