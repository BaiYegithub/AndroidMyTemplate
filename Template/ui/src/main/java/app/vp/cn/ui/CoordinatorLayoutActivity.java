package app.vp.cn.ui;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.vp.cn.common.base.BaseActivity;
import app.vp.cn.ui.adapter.ItemAdapter;
import butterknife.BindView;

public class CoordinatorLayoutActivity extends BaseActivity {

    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.coor)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.appBar)
    AppBarLayout appBarLayout;

    @Override
    protected int initContentView() {
        return R.layout.activity_coordinator_layout;
    }

    @Override
    protected void initViewAndData() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("地"+i);
        }
        rcv.setLayoutManager(new LinearLayoutManager(this));
        ItemAdapter itemAdapter = new ItemAdapter(strings);
        rcv.setAdapter(itemAdapter);
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
