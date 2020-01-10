package app.vp.cn.third;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by baiye
 * Date: 2019/10/9
 * Time: 12:19
 * Description:
 */
public class WebService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
