package app.vp.cn.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * @author Mr.Bai
 * @version 创建时间：2016年12月21日
 */
public class DeviceUtil {
    //三星手机
    public static final String SAMSUNG = "samsung";
    //小米手机
    public static final String XIAOMI = "xiaomi";
    //华为手机
    public static final String HUAWEI = "huawei";
    //vivo手机
    public static final String VIVO = "vivo";
    //oppo手机
    public static final String OPPO = "oppo";
    //魅族
    public static final String MEIZU = "meizu";
    //一加 手机
    public static final String ONEPLUS = "oneplus";
    //康佳手机
    public static final String KONKA = "konka";

    /**
     * 手机型号
     */
    @Deprecated
    public static String getMobileName() {
        return Build.MODEL.toLowerCase(Locale.ENGLISH);
    }

    /**
     * 手机厂商
     */
    @Deprecated
    public static String getMobileFactureName() {
        return Build.MANUFACTURER.toLowerCase(Locale.ENGLISH);
    }

    /**
     * 手机版本
     */
    @Deprecated
    public static String getMobileVersion() {
        return Build.VERSION.RELEASE;
    }


    /**
     * dip 转 px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        Log.i("baiye", "dip2px: 原来的缩放比例是" + scale);
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * @param width   用来参照的width，我们这里写360
     * @param dpValue
     * @return
     */
    public static int widgetsDipToPx(Context context, float width, float dpValue) {
        float targetDensity = 0;
        try {
            Log.i("baiye", "屏幕的宽度是" + context.getResources().getDisplayMetrics());
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

            targetDensity = displayMetrics.widthPixels / width;

            // targetDensity = (displayMetrics.widthPixels/displayMetrics.xdpi)*160*displayMetrics.density/width;
            //targetDensity = (float) (targetDensity*(displayMetrics.widthPixels*160/displayMetrics.xdpi)/width);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("baiye", "dip2px: 现在的缩放比例是" + targetDensity);
        return (int) (dpValue * targetDensity + 0.5f);

    }

    /**
     * px 转 dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * px 转 sx
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * sp 转 px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 屏幕密度
     */
    public static int getDisplay(Context context) {
        return (int) (context.getResources().getDisplayMetrics().density);
    }

    /**
     * 屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    public static int getScreenHeight(Activity activity) {
        return activity.getWindowManager()
                .getDefaultDisplay().getHeight();
    }

    public static int getStatusBarHeight(Context mContext) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;

        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = mContext.getResources().getDimensionPixelSize(x);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    /**
     * 字体宽度
     */
    public static int getTextWidth(Context context, String text, int textSize) {
        TextPaint paint = new TextPaint();
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        paint.setTextSize(scaledDensity * textSize);
        return (int) paint.measureText(text);
    }

    /**
     * 字体宽度
     */
    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }

    /**
     * 判断当前应用的渠道
     *
     * @param context
     * @return
     */
    public static boolean isChannel(Context context, String channelName) {
        try {
            String umeng_channel = getChannelName(context);
            if (!TextUtils.isEmpty(umeng_channel))
                return umeng_channel.toLowerCase(Locale.ENGLISH).contains(channelName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取当前应用的渠道名称
     *
     * @param context
     * @return
     */
    public static String getChannelName(Context context) {
        return getMataData(context, "UMENG_CHANNEL");
    }

    /**
     * 获取 application 里的 mata 值
     */
    public static String getMataData(Context context, String Key) {
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            return appInfo.metaData.getString(Key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 监测是否有虚拟导航栏
     *
     * @return
     */
    public static boolean isNavigationBarAvailable() {
        try {
            boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            boolean hasHomeKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_HOME);
            return (!(hasBackKey && hasHomeKey));
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 设置屏幕常量状态
     *
     * @param activity
     */
    public static void keepScreenLongLight(Activity activity) {
        try {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } catch (Exception e) {

        }
    }

    /**
     * 取消屏幕常量状态
     *
     * @param activity
     */
    public static void cancelkeepScreenLongLight(Activity activity) {
        try {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } catch (Exception e) {

        }
    }

    /**
     * 判断小米手机是否隐藏底部虚拟按键
     *
     * @param context
     * @return
     */
    public static boolean isMIHideNavBar(Context context) {
        if (Build.VERSION.SDK_INT >= 17 && Settings.Global.getInt(context.getContentResolver(), "force_fsg_nav_bar", 0) != 0) {
            //开启手势，不显示虚拟键
            return true;
        }
        return false;
    }


    /**
     * 获取屏幕原始尺寸高度（在小米8 小米6X 和小米MIX上   该方法获取值要比 getScreenHeight和 getScreenHeight1值要大）
     * 三星和华为上 该方法和getScreenHeight和 getScreenHeight1 值一样
     *
     * @param context
     * @return
     */
    public static int getRealScreenHeight(Context context) {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }


    /**
     * 屏幕高度（在小米8 小米6X 和小米MIX上   该方法获取值要比 getScreenHeight1值要大）
     * 三星和华为上 该方法和getScreenHeight和 getScreenHeight1 值一样
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }


    /**
     * 获取屏幕尺寸，不包括虚拟功能高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight1(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getHeight();
    }

    /**
     * 计算小米手机上虚拟按键高度
     *
     * @param context
     * @return
     */
    public static int getMINavBarheight(Context context) {
        return getRealScreenHeight(context) - getScreenHeight1(context);
    }

    /**
     * 判断是否是yunos系统
     *
     * @return
     */
    public static boolean isYunOS() {
        String version = null;
        String vmName = null;

        try {
            Method m = Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class});
            version = (String) m.invoke((Object) null, new Object[]{"ro.yunos.version"});
            vmName = (String) m.invoke((Object) null, new Object[]{"java.vm.name"});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vmName != null && vmName.toLowerCase().contains("lemur") || version != null && version.trim().length() > 0;
    }

    /**
     * 判断手机厂商类型 ,华为手机 huawei
     *
     * @param phoneName
     * @return
     */
    public static boolean isPhone(String phoneName) {
        String mobileFacture = DeviceUtil.getMobileFactureName();
        if (!TextUtils.isEmpty(mobileFacture))
            return mobileFacture.contains(phoneName);
        return false;
    }


    /**
     * 判断是否是某个手机
     *
     * @param name
     * @return
     */
    public static boolean isMobileName(String name) {
        String mobileName = DeviceUtil.getMobileName();
        if (!TextUtils.isEmpty(mobileName)) {
            return mobileName.contains(name);
        }
        return false;
    }


}
