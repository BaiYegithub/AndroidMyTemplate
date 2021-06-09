package app.vp.cn.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ConstraintLayoutActivity extends AppCompatActivity {

    private TextView tvText;

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

       /* Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aaa);
        Log.i("bai", "initView: ARGB8888 width是"+bitmap.getWidth()+"height:"+bitmap.getHeight()
                +"内存:"+bitmap.getByteCount());*/

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.aaa, options);
        Log.i("bai", "initView: RGB565 width是" + bitmap1.getWidth() + "height:" + bitmap1.getHeight()
                + "内存:" + bitmap1.getByteCount());

        tvText = findViewById(R.id.tv_text_line);
        tvText.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = tvText.getLineCount();
                Log.i("baiLine", "run: lineCount是" + lineCount);
            }
        });
        tvText.setText("没有什么能够阻挡，你对自由的向往，天马行空的生涯，我的心了无牵挂");

    }
}
