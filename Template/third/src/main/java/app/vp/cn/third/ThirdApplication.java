package app.vp.cn.third;

import android.app.Application;
import android.content.Intent;

import com.jeremyliao.liveeventbus.LiveEventBus;

import javax.xml.transform.sax.TemplatesHandler;

import app.vp.cn.common.app.BaseApp;

/**
 * Created by baiye
 * Date: 2019/10/9
 * Time: 11:39
 * Description:
 */
public class ThirdApplication extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        LiveEventBus
                .config()
                .supportBroadcast(this)
                .lifecycleObserverAlwaysActive(true);

        startService(new Intent(ThirdApplication.getmContext(),WebService.class));
    }
}
