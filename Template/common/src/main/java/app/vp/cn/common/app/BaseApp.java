package app.vp.cn.common.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.LinkedList;
import java.util.List;


/**
 * 公用的BaseApp， 如果在具体的module中使用，可以继承这个类，也可以直接使用这个BaseApp
 */

public class BaseApp extends Application {
    private static BaseApp instance;
    private static Context mContext;
    private List<Activity> activities = new LinkedList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
    }


    public static BaseApp getInstance() {
        return instance;

    }

    public static Context getmContext() {
        return mContext;
    }

    /**
     * @param finishActivity 需要销毁的Activity
     *                       销毁传入的activity
     */
    public void finishSomeOneActivity(Class finishActivity) {
        String activityName = finishActivity.getName();
        String getActivityName = null;
        if (activities != null && activities.size() > 0) {
            for (Activity activity : activities) {
                getActivityName = activity.getClass().getName();
//                getActivityName = getActivityName.substring(getActivityName.lastIndexOf(".") + 1);
                if (activityName.equals(getActivityName)) {
                    activity.finish();
                }
            }
        }
    }

    /**
     * @param saveActivity 保留的Activity其他全部关闭
     *                     销毁除了传入参数以外的视图
     */
    public void finishAllActivityExceptThat(Activity saveActivity) {
        String activityName = saveActivity.getClass().getName();
        String getActivityName = null;
        if (activities != null && activities.size() > 0) {
            for (Activity activity : activities) {
                getActivityName = activity.getClass().getName();
//                getActivityName = getActivityName.substring(getActivityName.lastIndexOf(".") + 1);
                if (!activityName.equals(getActivityName)) {
                    activity.finish();
                }
            }
        }
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (activities != null && activities.size() > 0) {
            if (!activities.contains(activity)) {
                activities.add(activity);
            }
        } else {
            activities.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public void removeAllActivity() {
        try {
            for (Activity ac : activities) {
                if (ac != null) {
                    ac.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
