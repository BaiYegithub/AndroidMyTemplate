package app.vp.cn.common.util;

import android.util.Log;

/**
 * Created by cyx on 2018/11/08.
 */

public class LogUtil {
    private static boolean LOG = true;
    // 直接使用Log
    public static void v(String tag, String mess) {
        if (LOG) {
            Log.v(tag, mess);
        }
    }

    public static void d(String tag, String mess) {
        if (LOG) {
            Log.d(tag, mess);
        }
    }

    public static void i(String tag, String mess) {
        if (LOG) {
            Log.i(tag, mess);
        }
    }

    public static void w(String tag, String mess) {
        if (LOG) {
            Log.w(tag, mess);
        }
    }

    public static void e(String tag, String mess) {
        if (LOG) {
            Log.e(tag, mess);
        }
    }

    //不需要再在类中定义TAG，打印类名,方法名,行号等.并定位行
    public static void v1(String mess) {
        if (LOG) {
            Log.v(getTag(), getMsgFormat(mess));
        }
    }

    public static void d1(String mess) {
        if (LOG) {
            Log.d(getTag(), getMsgFormat(mess));
        }
    }

    public static void i1(String mess) {
        if (LOG) {
            Log.i(getTag(), getMsgFormat(mess));
        }
    }

    public static void w1(String mess) {
        if (LOG) {
            Log.w(getTag(), getMsgFormat(mess));
        }
    }

    public static void e1(String mess) {
        if (LOG) {
            Log.e(getTag(), getMsgFormat(mess));
        }
    }


    /**
     * 获取到调用者的类名
     *
     * @return 调用者的类名
     */
    private static String getTag() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String callingClass = "";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass
                        .lastIndexOf('.') + 1);
                break;
            }
        }
        return callingClass;
    }

    /**
     * 获取相关数据:类名,方法名,行号等.用来定位行
     *
     * @return
     */
    private static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts != null) {
            for (StackTraceElement st : sts) {
                if (st.isNativeMethod()) {
                    continue;
                }
                if (st.getClassName().equals(Thread.class.getName())) {
                    continue;
                }
                if (st.getClassName().equals(LogUtil.class.getName())) {
                    continue;
                }
                return "[ Thread:" + Thread.currentThread().getName() + ", at " + st.getClassName() + "." + st.getMethodName()
                        + "(" + st.getFileName() + ":" + st.getLineNumber() + ")" + " ]";
            }
        }
        return null;
    }

    /**
     * 输出格式定义
     *
     * @param msg
     * @return
     */
    private static String getMsgFormat(String msg) {
        return msg + " ;" + getFunctionName();
    }


}
