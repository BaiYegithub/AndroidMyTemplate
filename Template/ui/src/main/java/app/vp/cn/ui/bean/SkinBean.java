package app.vp.cn.ui.bean;

import java.util.List;

/**
 * Created by baiye
 * Date: 2019/7/2
 * Time: 10:22
 * Description:
 */
public class SkinBean {

    /**
     * canvasHeight : 0
     * canvasWidth : 0
     * eWidgetSize : ST_5x2
     * mCustomPictureArr : [{"mDrawType":"WEATHER_ICON","mHeight":39,"mPictureName":"n0.png","mWidth":36,"mX":165,"mY":16}]
     * mDrawTextArr : [{"drawTextType":"TIME","mBold":false,"mColorHex":2131558796,"mItalic":false,"mSize":50,"mX":8,"mY":10},{"drawTextType":"AMORPM","mBold":false,"mColorHex":2131558796,"mItalic":false,"mSize":10,"mX":125,"mY":18},{"drawTextType":"TEXT_WEATHER","mBold":false,"mColorHex":2131558796,"mItalic":false,"mSize":14,"mX":8,"mY":75},{"drawTextType":"TEMPERATURE_RANGE","mBold":false,"mColorHex":2131558796,"mItalic":false,"mSize":14,"mX":57,"mY":75},{"drawTextType":"DATE_SOLAR","mBold":false,"mColorHex":2131558796,"mItalic":false,"mSize":14,"mX":8,"mY":103},{"drawTextType":"WEEK","mBold":false,"mColorHex":2131558796,"mItalic":false,"mSize":14,"mX":53,"mY":103},{"drawTextType":"CITY","mBold":false,"mColorHex":2131558515,"mItalic":false,"mSize":14,"mX":8,"mY":129}]
     * useSkinBackground : true
     * userSkinWeatherIcon : false
     */

    private int canvasHeight;
    private int canvasWidth;
    private String eWidgetSize;
    private boolean useSkinBackground;
    private boolean userSkinWeatherIcon;
    private List<MCustomPictureArrBean> mCustomPictureArr;
    private List<MDrawTextArrBean> mDrawTextArr;

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public String getEWidgetSize() {
        return eWidgetSize;
    }

    public void setEWidgetSize(String eWidgetSize) {
        this.eWidgetSize = eWidgetSize;
    }

    public boolean isUseSkinBackground() {
        return useSkinBackground;
    }

    public void setUseSkinBackground(boolean useSkinBackground) {
        this.useSkinBackground = useSkinBackground;
    }

    public boolean isUserSkinWeatherIcon() {
        return userSkinWeatherIcon;
    }

    public void setUserSkinWeatherIcon(boolean userSkinWeatherIcon) {
        this.userSkinWeatherIcon = userSkinWeatherIcon;
    }

    public List<MCustomPictureArrBean> getMCustomPictureArr() {
        return mCustomPictureArr;
    }

    public void setMCustomPictureArr(List<MCustomPictureArrBean> mCustomPictureArr) {
        this.mCustomPictureArr = mCustomPictureArr;
    }

    public List<MDrawTextArrBean> getMDrawTextArr() {
        return mDrawTextArr;
    }

    public void setMDrawTextArr(List<MDrawTextArrBean> mDrawTextArr) {
        this.mDrawTextArr = mDrawTextArr;
    }

    public static class MCustomPictureArrBean {
        /**
         * mDrawType : WEATHER_ICON
         * mHeight : 39
         * mPictureName : n0.png
         * mWidth : 36
         * mX : 165
         * mY : 16
         */

        private String mDrawType;
        private int mHeight;
        private String mPictureName;
        private int mWidth;
        private int mX;
        private int mY;

        public String getMDrawType() {
            return mDrawType;
        }

        public void setMDrawType(String mDrawType) {
            this.mDrawType = mDrawType;
        }

        public int getMHeight() {
            return mHeight;
        }

        public void setMHeight(int mHeight) {
            this.mHeight = mHeight;
        }

        public String getMPictureName() {
            return mPictureName;
        }

        public void setMPictureName(String mPictureName) {
            this.mPictureName = mPictureName;
        }

        public int getMWidth() {
            return mWidth;
        }

        public void setMWidth(int mWidth) {
            this.mWidth = mWidth;
        }

        public int getMX() {
            return mX;
        }

        public void setMX(int mX) {
            this.mX = mX;
        }

        public int getMY() {
            return mY;
        }

        public void setMY(int mY) {
            this.mY = mY;
        }
    }

    public static class MDrawTextArrBean {
        /**
         * drawTextType : TIME
         * mBold : false
         * mColorHex : 2131558796
         * mItalic : false
         * mSize : 50
         * mX : 8
         * mY : 10
         */

        private String drawTextType;
        private boolean mBold;
        private int mColorHex;
        private boolean mItalic;
        private int mSize;
        private int mX;
        private int mY;

        public String getDrawTextType() {
            return drawTextType;
        }

        public void setDrawTextType(String drawTextType) {
            this.drawTextType = drawTextType;
        }

        public boolean isMBold() {
            return mBold;
        }

        public void setMBold(boolean mBold) {
            this.mBold = mBold;
        }

        public int getMColorHex() {
            return mColorHex;
        }

        public void setMColorHex(int mColorHex) {
            this.mColorHex = mColorHex;
        }

        public boolean isMItalic() {
            return mItalic;
        }

        public void setMItalic(boolean mItalic) {
            this.mItalic = mItalic;
        }

        public int getMSize() {
            return mSize;
        }

        public void setMSize(int mSize) {
            this.mSize = mSize;
        }

        public int getMX() {
            return mX;
        }

        public void setMX(int mX) {
            this.mX = mX;
        }

        public int getMY() {
            return mY;
        }

        public void setMY(int mY) {
            this.mY = mY;
        }
    }
}
