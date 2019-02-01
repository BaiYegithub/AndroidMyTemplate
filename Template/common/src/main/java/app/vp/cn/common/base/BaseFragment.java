package app.vp.cn.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.vp.cn.common.util.SystemBarTintManager;
import app.vp.cn.common.util.UIUtils;
import app.vp.cn.common.view.LoadingDialog;
import butterknife.ButterKnife;
import butterknife.Unbinder;



/**
 * Created by cyx on 2018/11/8.
 */

public abstract class BaseFragment extends Fragment implements BaseView {
    protected Activity mActivity;
    private Unbinder bind;
    private LoadingDialog loadingDialog;

    /**
     * 获得全局的，防止使用getActivity()为空
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        initTheme();
        View view = LayoutInflater.from(mActivity)
                .inflate(getLayoutId(), container, false);
        bind = ButterKnife.bind(this, view);
        loadingDialog = new LoadingDialog(mActivity);
        initViewAndData(view, savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListener();
        initHttp();
    }

    protected void initTheme() {

    }

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 该抽象方法就是 初始化view
     *
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initViewAndData(View view, Bundle savedInstanceState);

    protected abstract void initHttp();

    protected abstract void initListener();

    /**
     * 销毁网络资源
     */
    protected abstract void destroyResources();

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyResources();
        if (bind != null) {
            bind.unbind();
        }
    }


    /********************** activity跳转 **********************************/
    public void openActivity(Class<?> targetActivityClass) {
        openActivity(targetActivityClass, null);
    }

    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(getActivity(), targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void openActivityAndCloseThis(Class<?> targetActivityClass) {
        openActivity(targetActivityClass);
        getActivity().finish();
    }

    public void openActivityAndCloseThis(Class<?> targetActivityClass, Bundle bundle) {
        openActivity(targetActivityClass, bundle);
        getActivity().finish();
    }

    @Override
    public void showLoading() {
        loadingDialog.showDialog();
    }

    @Override
    public void showLoading(String text) {
        loadingDialog.showDialog(text);
    }

    @Override
    public void hideLoading() {
        loadingDialog.dismiss();
    }

    @Override
    public void showToast(String text) {
        UIUtils.showToast(text);
    }

    @Override
    public void showNoNetWork() {

    }

    @Override
    public void hideNoNetWork() {

    }

    /**
     * 解决Fragment fitsSystemWindows属性失效问题
     */
    public void setSystemBarPadding(Activity activity, View view) {
        int top = new SystemBarTintManager(activity).getConfig().getStatusBarHeight();
        view.setPadding(view.getPaddingLeft(), top, view.getPaddingRight(), view.getPaddingBottom());
    }

    /***************************************************************/
}
