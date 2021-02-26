package app.vp.cn.third.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import app.vp.cn.third.R;
import app.vp.cn.third.bean.GaoFenCity;
import app.vp.cn.third.bean.OldCity;
import app.vp.cn.third.utils.FileUtils;
import app.vp.cn.third.utils.IOUtils;

/**
 * Created by baiye
 * Date: 2020/8/23
 * Time: 18:23
 * Description:
 */
public class GaoFenDB {


    /**
     * 数据库名
     */
    private final static String CITY_DB_NAME = "gaofendb.db";
    /**
     * 数据库管理对象
     */
    private static SQLiteDatabase sqliteDatabase;
    /**
     * 初始化DB对象，第一次把数据库文件复制到database目录下
     *
     * @param context
     */
    /**
     * 数据库表city名
     */
    private final String CITY_TABLE_NAME = "gaofencity";

    private static GaoFenDB gaoFenDB;
    public static GaoFenDB getGaoFenDB(Context context) {
        synchronized (GaoFenDB.class) {
            try {
                if (sqliteDatabase == null || !sqliteDatabase.isOpen()) {
                    File file = context.getDatabasePath(CITY_DB_NAME);
                    try {
                        InputStream is = context.getResources().openRawResource(
                                R.raw.gaofendb);
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
            if (gaoFenDB == null) {
                gaoFenDB = new GaoFenDB();
            }
        }
        return gaoFenDB;
    }

    public List<GaoFenCity> getGaoFenCity(){
        String sql = "select areacodeId,district from "+ CITY_TABLE_NAME;
        List<GaoFenCity> gaoFenCities = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = sqliteDatabase.rawQuery(sql, null);
            while (cursor!=null&&cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("district"));
                String id = cursor.getString(cursor.getColumnIndex("areacodeId"));
                gaoFenCities.add(new GaoFenCity(id,name));
            }
        } catch (Exception ex) {
            Log.i("bai", "getGaoFenCity: ex是"+ex);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return gaoFenCities;
    }
}
