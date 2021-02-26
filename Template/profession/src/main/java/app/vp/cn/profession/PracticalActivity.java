package app.vp.cn.profession;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import app.vp.cn.profession.view1.PracticalRecyclerView;

public class PracticalActivity extends AppCompatActivity {

    private PracticalRecyclerView practicalRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical);
        practicalRecyclerView = findViewById(R.id.rcv_ac);

        Button btAcPractical = findViewById(R.id.bt_acPractical);
        btAcPractical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("bai", "onClick: 点击了，第一个点击事件");
            }
        });
        btAcPractical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("bai", "onClick: 点击了，第二个点击事件");
            }
        });

    }
}
