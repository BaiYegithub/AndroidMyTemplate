package app.vp.cn.data.app;

import android.content.Context;

import com.facebook.stetho.Stetho;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * author : by
 * date: 2019/1/29 0029  下午 4:26.
 * describe
 */

public class DataApp extends LitePalApplication {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        // 初始化
        LitePal.initialize(this);

        Stetho.initializeWithDefaults(this);
    }

    public static Context getmContext() {
        return mContext;
    }
}
