package app.vp.cn.profession.timePicker;



import java.util.Calendar;

import app.vp.cn.common.app.BaseApp;

public class CalendarUtils {

    public final static String[] nStr1 = new String[]{"", "正", "二", "三", "四",
            "五", "六", "七", "八", "九", "十", "冬", "腊"};

    public final static String getChinaDate(int day) {
        String a = "";
        if (day == 10)
            return "初十";
        if (day == 20)
            return "二十";
        if (day == 30)
            return "三十";
        int two = (int) ((day) / 10);
        if (two == 0)
            a = "初";
        if (two == 1)
            a = "十";
        if (two == 2)
            a = "廿";
        if (two == 3)
            a = "三";
        int one = (int) (day % 10);
        switch (one) {
            case 1:
                a += "一";
                break;
            case 2:
                a += "二";
                break;
            case 3:
                a += "三";
                break;
            case 4:
                a += "四";
                break;
            case 5:
                a += "五";
                break;
            case 6:
                a += "六";
                break;
            case 7:
                a += "七";
                break;
            case 8:
                a += "八";
                break;
            case 9:
                a += "九";
                break;
        }
        return a;
    }

    // 判断是否为闰年
    public static boolean isLeapYear(int year) {
        if (year % 100 == 0 && year % 400 == 0) {
            return true;
        } else if (year % 100 != 0 && year % 4 == 0) {
            return true;
        }
        return false;
    }

    /**
     * 传回阳历 year年的总天数
     *
     * @param year 将要计算的年份
     * @return 返回传入年份的总天数
     */
    public static int daysInYear(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    /**
     * 返回当前月份1号位于周几
     *
     * @param year  年份
     * @param month 月份0-11，传入系统获取的，不需要正常的
     * @return
     */
    public static int getFirstDayWeek(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        return calendar.get(Calendar.DAY_OF_WEEK);//day of week(1-Sunday, 7-Saturday).
    }

    /**
     * 获得两个日期距离几周
     *
     * @param lastYear
     * @param lastMonth 0-11
     * @param lastDay
     * @param year
     * @param month     0-11
     * @param day
     * @return
     */
    public static int getWeeksAgo(int lastYear, int lastMonth, int lastDay, int year, int month, int day) {
        Calendar lastClickDay = Calendar.getInstance();
        lastClickDay.set(lastYear, lastMonth, lastDay);
        int week = lastClickDay.get(Calendar.DAY_OF_WEEK);
        switch (CalendarUtils.getFirstDayOfWeek()) {
            case Calendar.MONDAY:
                week = week - 1;
                if (week == 0) {
                    week = 7;
                }
                break;
        }
        week = week - 1;
        Calendar clickDay = Calendar.getInstance();
        clickDay.set(year, month, day);
        if (clickDay.getTimeInMillis() > lastClickDay.getTimeInMillis()) {
            return (int) ((clickDay.getTimeInMillis() - lastClickDay.getTimeInMillis() + week * 24 * 3600 * 1000) / (7 * 24 * 3600 * 1000));
        } else {
            return (int) ((clickDay.getTimeInMillis() - lastClickDay.getTimeInMillis() + (week - 6) * 24 * 3600 * 1000) / (7 * 24 * 3600 * 1000));
        }
    }

    /**
     * 获得两个日期距离几个月
     *
     * @param lastYear
     * @param lastMonth 0-11
     * @param year
     * @param month     0-11
     * @return
     */
    public static int getMonthsAgo(int lastYear, int lastMonth, int year, int month) {
        return (year - lastYear) * 12 + (month - lastMonth);
    }

    /**
     * @param year
     * @param month 0-11
     * @param day
     * @return
     */
    public static int getWeekRow(int year, int month, int day) {
        int week = getFirstDayWeek(year, month);//1-7
        switch (CalendarUtils.getFirstDayOfWeek()) {
            case Calendar.MONDAY:
                week = week - 1;
                if (week == 0) {
                    week = 7;
                }
                break;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        int lastWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (CalendarUtils.getFirstDayOfWeek()) {
            case Calendar.MONDAY:
                lastWeek = lastWeek - 1;
                if (lastWeek == 0) {
                    lastWeek = 7;
                }
                break;
        }
        if (lastWeek == 7) {
            day--;
        }
        week = (day + week - 1) / 7;
        return week;
    }

    /**
     * 当天此月的行数
     *
     * @param year
     * @param month 0-11
     * @param day
     * @return
     */
    public static int getInThisMonthRow(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获取此月行数
     *
     * @param year
     * @param month 0-11
     * @return
     */
    public static int getMonthRows(int year, int month) {
        int weekNumber = getFirstDayWeek(year, month);
        switch (CalendarUtils.getFirstDayOfWeek()) {
            case Calendar.MONDAY:
                weekNumber = weekNumber - 1;
                if (weekNumber == 0) {
                    weekNumber = 7;
                }
                break;
        }
        int size = weekNumber +  SolarCalendarUtils.getDaysOfMonth(year, month+1) - 1;
        return size % 7 == 0 ? size / 7 : (size / 7) + 1;
    }

    /**
     * 选择框,月，周等时间的统一管理
     */
    public static final int YEAR_START = 1900;//开始日期
    public static final int MONTH_START = 11;//开始月--必须为12 选择宽与对应日历表
    public static final int YEAR_END = 2099;//结束日期
    public static final int MONTH_END = 11;//月--必须为12 选择宽与对应日历表
    //默认上下一百年 1900-2000-2099
    public static final int SELECT_YEAR_LIMIT = 100;

    /**
     * 起始时间
     *
     * @return
     */
    public static long getStartTimeMills() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_START + 1, 0, 1);
        return calendar.getTimeInMillis();
    }

    /**
     * 结束时间
     *
     * @return
     */
    public static long getEndTimeMills() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_END, MONTH_END, SolarCalendarUtils.getDaysOfMonth(YEAR_END, MONTH_END + 1));
        return calendar.getTimeInMillis();
    }

    /**
     * 1900-2099 200年
     *
     * @return
     */
    public static int getMonthViewCount() {
        int monthViewNum = getMonthsAgo(YEAR_START, MONTH_START, YEAR_END, MONTH_END);
        return monthViewNum;
    }

    /**
     * 1900-2099 200年
     *
     * @return
     */
    public static int getWeekViewCount() {
        int weekViewNum = getWeeksAgo(YEAR_START, MONTH_START,
                SolarCalendarUtils.getDaysOfMonth(YEAR_START, MONTH_START+1), YEAR_END, MONTH_END,
                SolarCalendarUtils.getDaysOfMonth(YEAR_END, MONTH_END+1)) + 1;//多一周
        return weekViewNum;
    }

    /**
     * 距离1900.0.1
     *
     * @param year
     * @param month 0-11
     * @return
     */
    public static int getCurrMonthViewCount(int year, int month) {
        int monthViewNum = getMonthsAgo(YEAR_START, MONTH_START, year, month) - 1;//往前推一月
        return monthViewNum;
    }

    /**
     * 距离1900.0.1
     *
     * @param year
     * @param month
     * @return
     */
    public static int getCurrWeekViewCount(int year, int month, int day) {
        int weekViewNum = getWeeksAgo(YEAR_START, MONTH_START,
                SolarCalendarUtils.getDaysOfMonth(YEAR_START, MONTH_START+1), year, month, day);
        return weekViewNum;
    }

    /**
     * 今天毫秒-不包含时分
     *
     * @return
     */
    public static long getToadayMills() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0);
        return cal.getTime().getTime();
    }

    /**
     * 判断是否是每月的第一天
     *
     * @param dayString
     * @return
     */
    public static boolean isLunarFirstOfMonth(String dayString) {
        if ("初一".equals(dayString)) {
            return true;
        }
        return false;
    }

    public static String getLunarMonth(int lunarMonth) {
        return NongLiManager.lunarMonth[lunarMonth - 1];
    }

    public static int getFirstDayOfWeek() {
        String firstDayString = SharedPreferenceUtils.getString(BaseApp.getmContext(), "first_day_of_week", "MonDay");
        if ("MonDay".equals(firstDayString)) {
            return Calendar.MONDAY;
        } else if ("SunDay".equals(firstDayString)) {
            return Calendar.SUNDAY;
        }
        return Calendar.MONDAY;
    }
}

