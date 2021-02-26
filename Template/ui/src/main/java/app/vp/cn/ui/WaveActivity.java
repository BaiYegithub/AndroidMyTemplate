package app.vp.cn.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import app.vp.cn.common.util.LogUtil;
import app.vp.cn.ui.text.DataSetAdapter;
import app.vp.cn.ui.text.VerticalRollingTextView;
import app.vp.cn.ui.utils.ZipUtils;
import app.vp.cn.ui.view.WaveView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class WaveActivity extends AppCompatActivity {

    @BindView(R.id.waveView)
    WaveView waveView;

    @BindView(R.id.verticalRollingView)
    VerticalRollingTextView verticalRollingTextView;

    @BindView(R.id.tv_text1)
    TextView tv;

    @BindView(R.id.tv_acWave)
    TextView tvAcWave;

    private List<String> mDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        ButterKnife.bind(this);

        waveView.setProgress(20);


        mDataSet = new ArrayList<>();
        Observable.interval(0, 10, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i("bai", "accept: 接收aLong"+aLong);
                        if(aLong%2==0){
                            verticalRollingTextView.setText("天体那都需要你看得见飞快点快点快点开房间啊");
                        }else {
                            verticalRollingTextView.setText("没有什么能够阻挡");
                        }
                        mDataSet.add(verticalRollingTextView.getText().toString()+aLong);

                        verticalRollingTextView.run();
                    }
                });

        DataSetAdapter dataSetAdapter =  new DataSetAdapter<String>(mDataSet) {

            @Override
            protected CharSequence text(String s) {
                return s;
            }
        };
        verticalRollingTextView.setDataSetAdapter(dataSetAdapter);

        tvAcWave.setMovementMethod(ScrollingMovementMethod.getInstance());

        try {
            ZipUtils.UnZipFolder("/storage/emulated/0/Android/data/com.lu.ashionweatherbanner/files/Pictures/ashionskin.zip","/storage/emulated/0/Android/data/com.lu.ashionweatherbanner/files/Pictures");
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i("zipfail","解压失败,失败原因是"+e);
        }

    /*   String strXml ;

        try {
            File file = new File("/storage/emulated/0/Android/data/com.lu.newsbanner/files/Download/ashionskin",
                    "skinInfo.json");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String readline = "";
            StringBuffer sb = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                System.out.println("readline:" + readline);
                sb.append(readline);
            }
            br.close();
            strXml = sb.toString();
            System.out.println("读取成功：" + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

       /* try {
            File file = new File("/storage/emulated/0/Android/data/com.lu.newsbanner/files/Download/ashionskin",
                    "new2.xml");
            FileInputStream inputStream = new FileInputStream(file);



            SkinInfo skinInfo = XmlUtils.parse(inputStream);

            Log.i("baiye", "onCreate:skinInfo "+skinInfo.canvasHeight);


         *//*
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = parserFactory.newPullParser();
            xpp.setInput(inputStream,"UTF-8");
            int eventType = xpp.getEventType();
            while (eventType!= XmlPullParser.END_DOCUMENT){
                switch (eventType){

                    case XmlPullParser.START_DOCUMENT://文档开始事件
                        Log.i("baiye", "onCreate: 文档开始了");
                        break;
                    case XmlPullParser.START_TAG://标签元素开始事件
                        Log.i("baiye", "onCreate: 标签元素开始了");
                        break;
                    // 判断当前事件是否为标签元素结束事件
                    case XmlPullParser.END_TAG:

                        break;
                }
                // 进入下一个元素并触发相应事件
                eventType = xpp.next();
            }*//*



        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Toast.makeText(WaveActivity.this,"这是我弹出的toast",Toast.LENGTH_LONG).show();
    }
}
