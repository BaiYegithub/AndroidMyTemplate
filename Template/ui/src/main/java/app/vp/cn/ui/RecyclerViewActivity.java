package app.vp.cn.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.vp.cn.ui.adapter.ItemAdapter;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView  rcv = findViewById(R.id.rcv_acRcv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Log.i("bai", "getSpanSize: position是"+position);
                return 1;
            }
        });
        rcv.setLayoutManager(gridLayoutManager);
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        ItemAdapter itemAdapter = new ItemAdapter(list);
        rcv.setAdapter(itemAdapter);

    }
}
