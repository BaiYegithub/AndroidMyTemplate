package app.vp.cn.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ConstraintLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
        findViewById(R.id.bt_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("bai", "onClick: aaa");
            }
        });

        /*findViewById(R.id.bt_b).setOnClickListener((v)->{

        });*/

    }
}
