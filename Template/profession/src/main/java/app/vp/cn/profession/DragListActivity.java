package app.vp.cn.profession;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.vp.cn.common.base.BaseActivity;
import app.vp.cn.profession.adapter.DragListAdapter;
import app.vp.cn.profession.view1.DragListView1;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by baiye
 * Date: 2019/7/11
 * Time: 14:47
 * Description:
 */
public class DragListActivity extends BaseActivity {


    @BindView(R.id.drag_acDrag)
    DragListView1 dragAcDrag;

    @Override
    protected int initContentView() {
        return R.layout.activity_drag;
    }

    @Override
    protected void initViewAndData() {


        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add("我是第" + i + "条数据");
        }
        ArrayList<Integer> drawList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            drawList.add(R.mipmap.ic_launcher);
        }


        DragListAdapter dragListAdapter = new DragListAdapter(DragListActivity.this, list, drawList);

        dragAcDrag.setAdapter(dragListAdapter);
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
