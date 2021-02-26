package app.vp.cn.profession.timePicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;
//import android.graphics.AvoidXfermode.Mode;

public class SharedPreferenceUtils {
	private static SharedPreferences sp;
    private static final String SP_NAME="config";
    public static final String FRIST_READ_NEWS = "first_read_news";
    public static final String LAT_ALT_CITY = "lat_alt_city";
    public static final String HAS_BEEN_ENTER_GUIDE_ACTIVITY = "HAS_BEEN_ENTER_GUIDE_ACTIVITY";
    //定位城市名
    public static String locationCityName=null;
    public static String mCurrtentCityName=null;
    /**
     * 保存boolean值信息
     */
    public static void saveBoolean(Context context,String key,boolean value){
          if (sp ==null) {
              sp = context.getSharedPreferences(SP_NAME , Context.MODE_PRIVATE );
         }
          sp.edit().putBoolean(key, value).commit();
          
          
    }
    /**
     * 获取boolean值的信息
     * @return
     */
    public static boolean getBoolean(Context context,String key,boolean defValue){
          if (sp ==null) {
              sp = context.getSharedPreferences(SP_NAME , Context.MODE_PRIVATE );
         }
          return sp .getBoolean(key, defValue);
    }
    
    /**
     * 保存String类型数据
     * @param context
     * @param key
     * @param value
     */
    public static void saveString (Context context,String key, String value) {
    	if (sp == null) {
    		sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    	}
    	
    	sp.edit().putString(key, value).commit();
    }
    
    /**
     * 获取String类型数据
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getString (Context context,String key, String defValue) {
    	
    	if (sp == null) {
    		sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    	}
    	return sp.getString(key, defValue);
    }
    
    /**
     * 保存String类型数据
     * @param context
     * @param key
     * @param value
     */
    public static void saveInt(Context context,String key, int value) {
    	if (sp == null) {
    		sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    	}
    	
    	sp.edit().putInt(key, value).commit();
    }
    
    /**
     * 获取String类型数据
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt (Context context,String key, int defValue) {
    	
    	if (sp == null) {
    		sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    	}
    	return sp.getInt(key, defValue);
    }
    
    /**
     * 保存Set<String>类型数据
     * @param context
     * @param key
     * @param value
     */
    @SuppressLint("NewApi") 
    public static void saveStringSet(Context context,String key, Set<String> value) {
    	if (sp == null) {
    		sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    	}
    	
    	sp.edit().putStringSet(key, value);
    }
    
    /**
     * 获取String类型数据
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    @SuppressLint("NewApi") 
    public static Set<String> getStringSet(Context context,String key, Set<String> defValue) {
    	
    	if (sp == null) {
    		sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    	}
    	return sp.getStringSet(key, defValue);

    }
    
    /**
     * 保存String类型数据
     * @param context
     * @param key
     * @param value
     */
    public static void saveLong(Context context,String key, long value) {
    	if (sp == null) {
    		sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    	}
    	
    	sp.edit().putLong(key, value).commit();
    }
    
    /**
     * 获取String类型数据
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(Context context,String key, long defValue) {
    	
    	if (sp == null) {
    		sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    	}
    	return sp.getLong(key, defValue);
    }


    /**
     * 移除某个值
     * @param context
     * @param key
     */
    public static void remove(Context context,String key){
        if(context==null)return;
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,  Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 保存float类型数据
     * @param context
     * @param key
     */
    public static void saveFloat(Context context,String key, float value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }

        sp.edit().putFloat(key, value).commit();
    }

    /**
     * 获取float类型数据
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static float getFloat (Context context,String key, float defValue) {

        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getFloat(key, defValue);
    }
    
}
