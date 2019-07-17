package app.vp.cn.ui.view;

import android.app.PendingIntent;
import android.widget.RemoteViews;

/**
 * Created by baiye
 * Date: 2019/7/2
 * Time: 18:01
 * Description:
 */
public class ImageRemoteViews extends RemoteViews {


    public ImageRemoteViews(String packageName, int layoutId) {
        super(packageName, layoutId);

    }

    @Override
    public void setOnClickPendingIntent(int viewId, PendingIntent pendingIntent) {
        super.setOnClickPendingIntent(viewId, pendingIntent);
    }

}
