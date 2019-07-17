package app.vp.cn.ui.bean;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiye
 * Date: 2019/6/29
 * Time: 12:01
 * Description:  桌面小组件的皮肤信息
 */
public class SkinInfo {

    public static String mSkinID;
    public static String skinName;
    public String folderName;//文件夹路径
    public int canvasHeight; //组件高
    public int canvasWidth;//组件宽
    public boolean useSkinBackground; //是否使用皮肤的背景
    public boolean userSkinWeatherIcon;//是否使用皮肤的天气图案
    public EWidgetSize eWidgetSize;//什么尺寸 4x1 4x2 5x1 5x2
    public List<DrawPicture> mCustomPictureArr = new ArrayList<>();//我的图片集合
    public List<DrawText> mDrawTextArr = new ArrayList<>();//我的文字集合



    public static class DrawPicture {
        public DrawPictureType mDrawType;//图片类型
        public int mWidth;//图片宽度
        public int mHeight;//图片高度
        public int mX;//图片位置 x 轴
        public int mY;//图片位置 y 轴
        public String mPictureName;//图片名称


        public enum DrawPictureType{
            BACKGROUND,//背景图片
            WEATHER_ICON,//天气图片
            VOICE_ICON,//语音播报图片
            ADD_ICON_1,//新加的不确定的图片1
            ADD_ICON_2,//新加的不确定的图片2
            ADD_ICON_3,//新加的不确定的图片3

        }
    }

    public static class DrawText{
        public DrawTextType drawTextType;//文字类型
        public Paint.Align mAlign; //Paint 的 Align (绘制参考点的位置)
        public boolean mBold; //是否加粗
        public int mColorHex;//16位 的色值
        public String mDirection;//描述
        public String mFontName;//
        public boolean mItalic;//是否是斜体
        public String mRotate;//旋转角度
        public int mSize;//文字大小
        public int mX;//坐标  x
        public int mY;// 坐标 y

        public enum DrawTextType{
            TIME,//时间
            AMORPM, //上午还是下午
            TEXT_WEATHER, //天气
            CURRENT_TEMPERATURE, //当前温度
            TEMPERATURE_RANGE,//温度范围
            DATE_SOLAR,//阳历日期
            DATE_LUNAR,//阴历日期
            WEEK,//星期
            CITY,//地点
            AQI_DESC, //空气质量描述
            AQI_VALUE,//空气质量 值
            EARLY_WARNING // 分钟级预警
        }
    }


}
