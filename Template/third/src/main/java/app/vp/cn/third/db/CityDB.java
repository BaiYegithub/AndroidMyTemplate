package app.vp.cn.third.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import app.vp.cn.third.R;
import app.vp.cn.third.bean.OldCity;
import app.vp.cn.third.utils.FileUtils;
import app.vp.cn.third.utils.IOUtils;
import butterknife.internal.Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 本地数据库管理类
 */
public class CityDB {
    /**
     * 数据库名
     */
    private final static String CITY_DB_NAME = "weather_cities.db";
    /**
     * 数据库表city名
     */
    private final String CITY_TABLE_NAME = "city";
    /**
     * 数据库热门城市中文表名
     */
    private final String HOT_CITY_TABLE_NAME = "hotcity";
    /**
     * 数据库热门城市英文表名
     */
    private final String HOT_CITY_TABLE_NAME_EN = "hotcity_en";
    /**
     * 数据库表景点名称
     */
    private final String SCENIC_TABLE_NAME = "scenic";
    /**
     * 数据库管理对象
     */
    private static SQLiteDatabase sqliteDatabase;
    /**
     * 单利模式用到的类对象
     */
    private static CityDB cityDB;

    private CityDB() {
    }

    /**
     * 初始化DB对象，第一次把数据库文件复制到database目录下
     *
     * @param context
     */
    public static CityDB getCityDB(Context context) {
        synchronized (CityDB.class) {
            try {
                if (sqliteDatabase == null || !sqliteDatabase.isOpen()) {
                    File file = context.getDatabasePath(CITY_DB_NAME);
                    try {
                        InputStream is = context.getResources().openRawResource(
                                R.raw.weather_cities);
                        FileUtils.writeFile(file, is);
                        IOUtils.closeInputStream(is);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sqliteDatabase = context.openOrCreateDatabase(CITY_DB_NAME, Context.MODE_PRIVATE, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (cityDB == null) {
                cityDB = new CityDB();
            }
        }
        return cityDB;
    }

    /**
     * 根据中文获取城市英文名
     *
     * @return
     */

    /**
     * 根据高德地图获取城市名称
     */

    public String getCityNameByGd(AMapLocation location) {
        String city_name = location.getCity();
        if (TextUtils.isEmpty(city_name)) {
            return null;
        }
        if (city_name.contains("市")) {
            int cityIndex = city_name.indexOf("市");
            city_name = city_name.substring(0, cityIndex);
        } else if ("香港特別行政區".equals(city_name)) {
            city_name = "香港";
        }
        return city_name;
    }

    /**
     * 根据ID获取城市中文名
     *
     * @return
     */
    public String getCNNameById(String id) {
        String sql = "select name_chinese from " + CITY_TABLE_NAME + " where id = '" + id + "'";

        String name = "";
        Cursor cursor = null;
        try {
            cursor = sqliteDatabase.rawQuery(sql, null);
            if (cursor != null && cursor.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex("name_chinese"));
            }
        } catch (Exception ex) {

        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return name;
    }

    public List<OldCity> getOldCity(){
        String sql = "select id,name_chinese from "+ CITY_TABLE_NAME;
        List<OldCity> oldCities = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = sqliteDatabase.rawQuery(sql, null);
            while (cursor!=null&&cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name_chinese"));
                String id = cursor.getString(cursor.getColumnIndex("id"));
                oldCities.add(new OldCity(id,name));
            }
        } catch (Exception ex) {
            Log.i("bai", "getGaoFenCity: ex是"+ex);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return oldCities;
    }

    /**
     * 根据ID获取城市英文名
     *
     * @return
     */
    private String getENNameById(String id) {
        String sql = "select name_english from " + CITY_TABLE_NAME + " where id = '" + id + "'";

        String name = "";
        Cursor cursor = null;
        try {
            cursor = sqliteDatabase.rawQuery(sql, null);
            if (cursor != null && cursor.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex("name_english"));
            }
        } catch (Exception ex) {

        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return name;
    }



}
