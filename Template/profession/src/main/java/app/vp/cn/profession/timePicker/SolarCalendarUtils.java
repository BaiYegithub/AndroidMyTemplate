package app.vp.cn.profession.timePicker;



import java.util.Calendar;

import app.vp.cn.common.app.BaseApp;
import app.vp.cn.profession.R;

/**
 * 阳历(公历)对应的方法
 * SolarCalendar--阳历
 * GregorianCalendar--公历
 */
public class SolarCalendarUtils {
    /**
     * 获取公历年的天数
     * 闰年(Leap Year)是为了弥补因人为历法规定造成的年度天数与地球实际公转周期的时间差而设立的。补上时间差的年份为闰年。闰年共有366天（1-12月分别为31天，29天，31天，30天，31天，30天，31天，31天，30天，31天，30天，31天）。
     *
     * @param year
     * @return
     */
    public int getDaysOfYear(int year) {
        if (isLeapYear(year)) {
            return 366;
        }
        return 365;
    }

    /**
     * 获取公历年year，month月的天数
     * 闰年共有366天（1-12月分别为31天，29天，31天，30天，31天，30天，31天，31天，30天，31天，30天，31天）。
     *
     * @param year
     * @param month 1-12
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            }
            return 28;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else {
            return 30;
        }
    }

    /**
     * 判断公历年是否是闰年
     * 闰年是公历中的名词。闰年分为普通闰年和世纪闰年。
     * 普通闰年:公历年份是4的倍数的，且不是100的倍数，为闰年。（如2004年就是闰年）；
     * 世纪闰年:公历年份是整百数的，必须是400的倍数才是闰年（如1900年不是世纪闰年，2000年是世纪闰年）；
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        if ((year % 4 != 0 || year % 100 == 0) && year % 400 != 0) {
            return false;
        }
        return true;
    }

    /**
     * 根据时间，判断星座
     * 白羊座：3月21日～4月19日  金牛座：4月20日～5月20日 双子座：5月21日～6月21日  巨蟹座：6月22日～7月22日
     * <p>
     * 狮子座：7月23日～8月22日  处女座：8月23日～9月22日 天秤座：9月23日～10月23日 天蝎座：10月24日～11月22日
     * <p>
     * 射手座：11月23日～12月21日 魔羯座：12月22日～1月19日 水瓶座：1月20日～2月18日 双鱼座：2月19日～3月20日
     *
     * @param month
     * @param day
     * @return
     */
    public static String getConstellation(int month, int day) {
        if (month > 0 && day > 0) {
            if ((month == 1 && day > 19) || (month == 2 && day < 19)) {//水瓶座：1月20日～2月18日
                return "水瓶座";
            } else if ((month == 2 && day > 18) || (month == 3 && day < 21)) {//双鱼座：2月19日～3月20日
                return "双鱼座";
            } else if ((month == 3 && day > 20) || (month == 4 && day < 20)) {//白羊座：3月21日～4月19日
                return "白羊座";
            } else if ((month == 4 && day > 19) || (month == 5 && day < 21)) {//金牛座：4月20日～5月20日
                return "金牛座";
            } else if ((month == 5 && day > 20) || (month == 6 && day < 22)) {//双子座：5月21日～6月21日
                return "双子座";
            } else if ((month == 6 && day > 21) || (month == 7 && day < 23)) {//巨蟹座：6月22日～7月22日
                return "巨蟹座";
            } else if ((month == 7 && day > 22) || (month == 8 && day < 23)) {//狮子座：7月23日～8月22日
                return "狮子座";
            } else if ((month == 8 && day > 22) || (month == 9 && day < 23)) {//处女座：8月23日～9月22日
                return "处女座";
            } else if ((month == 9 && day > 22) || (month == 10 && day < 24)) {//天秤座：9月23日～10月23日
                return "天秤座";
            } else if ((month == 10 && day > 23) || (month == 11 && day < 23)) {//天蝎座：10月24日～11月22日
                return "天蝎座";
            } else if ((month == 11 && day > 22) || (month == 12 && day < 22)) {//射手座：11月23日～12月21日
                return "射手座";
            } else if ((month == 12 && day > 21) || (month == 1 && day < 20)) {//魔羯座：12月22日～1月19日
                return "魔羯座";
            }
        }
        return "";
    }

    //周几
    public static String getDayOfWeek(int year, int month, int day) {
        Calendar instance = Calendar.getInstance();
        instance.set(year, month - 1, day);
        int i = instance.get(Calendar.DAY_OF_WEEK);
        String[] stringArray = BaseApp.getmContext().getResources().getStringArray(R.array.zhouX);
        if (i > 0 && i <= 7) {
            return stringArray[i - 1];
        } else {
            return "";
        }
    }

    //判断是否是周末
    public static boolean isWeekend(int year, int month, int day) {
        Calendar instance = Calendar.getInstance();
        instance.set(year, month - 1, day);
        int i4 = instance.get(Calendar.DAY_OF_WEEK);
        if (i4 == 1 || i4 == 7) {
            return true;
        }
        return false;
    }
}
