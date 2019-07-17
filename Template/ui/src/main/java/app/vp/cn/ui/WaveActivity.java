package app.vp.cn.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import app.vp.cn.common.util.LogUtil;
import app.vp.cn.ui.bean.SkinInfo;
import app.vp.cn.ui.text.DataSetAdapter;
import app.vp.cn.ui.text.VerticalRollingTextView;
import app.vp.cn.ui.utils.XmlUtils;
import app.vp.cn.ui.utils.ZipUtils;
import app.vp.cn.ui.view.WaveView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WaveActivity extends AppCompatActivity {

    @BindView(R.id.waveView)
    WaveView waveView;

    @BindView(R.id.verticalRollingView)
    VerticalRollingTextView verticalRollingTextView;

    @BindView(R.id.tv_text1)
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        ButterKnife.bind(this);

        waveView.setProgress(20);

        List<String> mDataSet = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            mDataSet.add("大魔王是 123..... 购买3年VIP会员 15:12:13"+i);
        }

        verticalRollingTextView.setDataSetAdapter(new DataSetAdapter<String>(mDataSet) {

            @Override
            protected String text(String charSequence) {
                return charSequence;
            }
        });

        verticalRollingTextView.run();

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
    }
}
