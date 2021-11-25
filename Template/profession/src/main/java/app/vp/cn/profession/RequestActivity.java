package app.vp.cn.profession;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("bai", "onClick: >>>>>点击了bt1");
            }
        });
        findViewById(R.id.bt_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("bai", "onClick: >>>>>>>>>点击了bt2");
            }
        });
       /* new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    JSONObject json = new JSONObject();
                    JSONObject jsonIn = new JSONObject();
                    try {
                        json.put("user_id", 915);
                        json.put("sign", "b0f250da286d2d2c7054122a23c657c99874333f");
                        json.put("nonce", 269089);
                        json.put("app_id", 5001231);
                        json.put("timestamp", 1600834342);
                        json.put("channel", 1);
                        json.put("sub_channel", 2140);

                        jsonIn.put("os",1);
                        jsonIn.put("android_id","de87cd697d1543df");
                        jsonIn.put("ip","221.209.132.219 ");
                        jsonIn.put("ua","okhttp/3.12.0");

                        json.put("device",jsonIn);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //1 . 拿到OkHttpClient对象
                    OkHttpClient client = new OkHttpClient();
                    //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
                    RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));
                    //3 . 构建Request,将FormBody作为Post方法的参数传入
                    Request request = new Request.Builder()
                            .url("https://is.snssdk.com/api/ad/union/activate_event/")
                            .post(requestBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();

                    Log.i("bai", "run: 结果是"+responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();*/

    }
}
