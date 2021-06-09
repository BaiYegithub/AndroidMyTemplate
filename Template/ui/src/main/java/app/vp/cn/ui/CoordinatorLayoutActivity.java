package app.vp.cn.ui;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
    private ItemAdapter itemAdapter;
    private List<String> strings;

    @Override
    protected int initContentView() {
        return R.layout.activity_coordinator_layout;
    }

    @Override
    protected void initViewAndData() {
        strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("地" + i);
        }
        rcv.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(strings);
        rcv.setAdapter(itemAdapter);

        findViewById(R.id.bt_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < strings.size(); i++) {
                    strings.get(i).concat("天");
                    itemAdapter.notifyDataSetChanged();
                }
            }
        });
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
