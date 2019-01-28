package app.vp.cn.common.base;

/**
 * author : by
 * date: 2018/11/9 0009  上午 11:05.
 * describe
 */

public interface BaseView<T> {
    //显示加载框
    void showLoading();
    //显示带自定义文字的加载框
    void showLoading(String text);
    //隐藏加载框
    void hideLoading();
    //显示吐司
    void showToast(String text);
    //显示没有网络的缺省页
    void showNoNetWork();
    //隐藏没有网络的缺省页
    void hideNoNetWork();
}
