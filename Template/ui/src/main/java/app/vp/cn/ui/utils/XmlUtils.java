package app.vp.cn.ui.utils;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import app.vp.cn.ui.bean.EWidgetSize;
import app.vp.cn.ui.bean.SkinInfo;

/**
 * Created by baiye
 * Date: 2019/7/1
 * Time: 19:00
 * Description:
 */
public class XmlUtils {

    public static SkinInfo parse(InputStream inputStream) {
        SkinInfo skinInfo = null;
        try {
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = parserFactory.newPullParser();
            xpp.setInput(inputStream, "UTF-8");
            int eventType = xpp.getEventType();
            SkinInfo.DrawPicture mDrawPicture = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {

                    case XmlPullParser.START_DOCUMENT://文档开始事件
                        Log.i("baiye", "onCreate: 文档开始了");
                        skinInfo = new SkinInfo();
                        break;
                    case XmlPullParser.START_TAG://标签元素开始事件
                        Log.i("baiye", "onCreate: 标签元素开始了");
                        if (xpp.getName().equals("canvasHeight")) {
                            eventType = xpp.next();
                            skinInfo.canvasHeight = Integer.parseInt(xpp.getText());
                        } else if (xpp.getName().equals("canvasWidth")) {
                            eventType = xpp.next();
                            skinInfo.canvasWidth = Integer.parseInt(xpp.getText());
                        } else if (xpp.getName().equals("eWidgetSize")) {
                            eventType = xpp.next();
                            skinInfo.eWidgetSize = Enum.valueOf(EWidgetSize.class, xpp.getText());
                        } else if (xpp.getName().equals("mCustomPictureArr")) {
                            mDrawPicture = new SkinInfo.DrawPicture();
                        } else if(xpp.getName().equals("mDrawType")){
                            eventType = xpp.next();
                            mDrawPicture.mDrawType = Enum.valueOf(SkinInfo.DrawPicture.DrawPictureType.class,xpp.getText());
                        } else if(xpp.getName().equals("mHeight")){
                            eventType = xpp.next();
                            mDrawPicture.mHeight = Integer.parseInt(xpp.getText());
                        } else if(xpp.getName().equals("mPictureName")){
                            eventType = xpp.next();
                            mDrawPicture.mPictureName = xpp.getText();
                        } else if(xpp.getName().equals("mWidth")){
                            eventType = xpp.next();
                            mDrawPicture.mWidth = Integer.parseInt(xpp.getText());
                        } else if(xpp.getName().equals("mX")){
                            eventType = xpp.next();
                            mDrawPicture.mX = Integer.parseInt(xpp.getText());
                        } else if(xpp.getName().equals("mY")){
                            eventType = xpp.next();
                            mDrawPicture.mY = Integer.parseInt(xpp.getText());
                        }
                        break;
                    // 判断当前事件是否为标签元素结束事件
                    case XmlPullParser.END_TAG:

                        break;
                }
                // 进入下一个元素并触发相应事件
                eventType = xpp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skinInfo;
    }
}
