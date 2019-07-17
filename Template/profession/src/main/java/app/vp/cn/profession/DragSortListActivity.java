package app.vp.cn.profession;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.vp.cn.common.base.BaseActivity;
import app.vp.cn.profession.adapter.MyAdapter;
import app.vp.cn.profession.dslv.DragSortListView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DragSortListActivity extends BaseActivity {

    @BindView(R.id.dragListView)
    DragSortListView dragListView;
    private List<String> list;

    @Override
    protected int initContentView() {
        return R.layout.activity_drag_sort_list;
    }

    @Override
    protected void initViewAndData() {
        list = new ArrayList<>();

        for (int i = 0; i < 60; i++) {
            list.add("我是第" + i + "条数据");
        }

        MyAdapter myAdapter = new MyAdapter(list, DragSortListActivity.this);

        dragListView.setAdapter(myAdapter);

        dragListView.addNoChangeItemPosition(0);
        dragListView.addNoChangeItemPosition(1);
       // dragListView.setDragEnabled(false);
       // dragListView.setOnDragListener((View.OnDragListener) myAdapter);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
