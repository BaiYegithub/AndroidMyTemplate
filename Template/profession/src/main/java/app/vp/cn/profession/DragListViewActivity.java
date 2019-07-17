package app.vp.cn.profession;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import app.vp.cn.profession.adapter.MyAdapter;
import app.vp.cn.profession.view2.DragListView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DragListViewActivity extends AppCompatActivity {

    @BindView(R.id.dragListView2)
    DragListView dragListView2;

    List<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_list_view);
        ButterKnife.bind(this);
        dragListView2.setDragViewId(R.id.iv_drag_list_item_1);
        arrayList = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            arrayList.add("我是第" + i + "条数据");
        }

        MyAdapter myAdapter = new MyAdapter(arrayList, this);
        dragListView2.setAdapter(myAdapter);


    }
}
