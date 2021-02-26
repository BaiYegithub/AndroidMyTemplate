package app.vp.cn.system;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;

public class HandlerActivity extends AppCompatActivity {


  /*  Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            int i = (int) message.obj;
            Log.i("xxx", "handleMessage: " + i);
            return false;
        }
    });*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

      /*  new Thread(new Runnable() {
            @Override
            public void run() {
                Message obtain = Message.obtain();
                obtain.obj = 2;
                handler.sendMessage(obtain);
            }
        }).start();*/
        findViewById(R.id.bt_showToast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a = null;
                try {
                    String appRootPath = getApplication().getApplicationContext().getExternalCacheDir().getParent() + "/";
                    Toast.makeText(HandlerActivity.this, appRootPath, Toast.LENGTH_LONG).show();
                    a.length();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("bai", "onClick: "+e);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        handler.removeCallbacksAndMessages(null);
    }
}
