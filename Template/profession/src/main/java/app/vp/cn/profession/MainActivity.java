package app.vp.cn.profession;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import app.vp.cn.profession.adapter.RcvAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.bt_toUi)
    Button btToUi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setHasFixedSize(true);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("自动滚动到指定位置" + i);
        }
        RcvAdapter rcvAdapter = new RcvAdapter(list);
        rcv.swapAdapter(rcvAdapter, false);
        list.add("我是新插入的数据");
        rcvAdapter.notifyItemRangeInserted(0, list.size());
        rcv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int i = rcv.computeVerticalScrollOffset();
                Log.i("xxx", "onCreate: 滑动后的距离" + i); //这里滑动的距离是基于item 的平均高度算得
            }
        });

        rcv.setRecycledViewPool(new RecyclerView.RecycledViewPool());

        btToUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setAction("wave");
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        //rcv.scrollToPosition(5); //滚动到指定位置   不带动画
        rcv.smoothScrollToPosition(0);  // 滚动到指定位置 带滑动动画

        // rcv.scrollBy(0, 200); //滚动到指定距离
        // rcv.smoothScrollBy(0, 500);  //滚动到指定距离  带动画
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("key", 1);
        Log.i("123","走了onSaveInstanceState方法");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }
}
