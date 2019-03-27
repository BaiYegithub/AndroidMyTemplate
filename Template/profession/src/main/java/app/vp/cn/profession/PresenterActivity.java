package app.vp.cn.profession;

import app.vp.cn.common.base.BaseActivity;
import app.vp.cn.common.presenter.MyPresenter;

public class PresenterActivity extends BaseActivity {


    @Override
    protected int initContentView() {
        return R.layout.activity_presenter;
    }

    @Override
    protected void initViewAndData() {
        MyPresenter myPresenter = new MyPresenter(this, this);
    }

    @Override
    protected void initHttp() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void destroyResources() {

    }
}
