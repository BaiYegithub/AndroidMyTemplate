package app.vp.cn.common.util;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import app.vp.cn.common.app.BaseApp;

/**
 * author : by
 * date: 2019/1/28 0028  下午 4:12.
 * describe
 */

public class UIUtils {

    private static Toast toast;

    public static void showToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(BaseApp.getmContext(), content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static Context getContext() {
        return BaseApp.getmContext();
    }

    /**
     * 获取sd卡存储目录
     *
     * @param dirName 功能文件夹名
     */
    public static String getSDCardCachePATH(String dirName) {
        String path = "";
        if (!externalMemoryAvailable()) {
            path = Environment.getDataDirectory() + "/data/app.vp.cn.common/" + dirName;
        } else {
            path = Environment.getExternalStorageDirectory() + "/Android/data/app.vp.cn.common/" + dirName;
        }
        return path;
    }

    private static boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * @param is_removale true 外置
     *                    false 内置
     *                    获取外置/内置内存卡的地址       利用反射
     */
    public static String getStoragePath(Context mContext, boolean is_removale) {
        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (is_removale == removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
