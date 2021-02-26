package app.vp.cn.profession.timePicker;

import android.annotation.SuppressLint;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 类名称：DateUtil
 * 类描述：  日期工具类
 */
public class DateUtil {

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    private static final Object object = new Object();

    /**
     * @param @param  date
     * @param @return
     * @return String
     * @throws
     * @Title: DateToTimestamp
     * @Description: 字符串 2012-01-01 22:22:22转时间戳1325427742000
     */
    public static String DateToTimestamp(String date) {
        Date d = StringToDate(date, DateStyle.YYYY_MM_DD_HH_MM_SS);
        return String.valueOf(Math.round(d.getTime() / 1000));
    }

    /**
     * @param @param  date
     * @param @return
     * @return String
     * @throws
     * @Title: DateToTimestamp
     * @Description: Date 转时间戳1325427742000
     */
    public static String DateToTimestamp(Date date) {
        return String.valueOf(date.getTime());
    }

    /**
     * @param @param  date
     * @param @return
     * @return String
     * @throws
     * @Title: TimestampToYYYY_MM_DD_HH_MM_SS
     * @Description: 时间戳1325427742000字符串转 2012-01-01 22:22:22
     */
    public static String TimestampToY_M_D_H_M_S(String date) {
        if (date == null) {
            return "";
        }
        try {
            date = getDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()).
                    format(Long.parseLong(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param @param  date
     * @param @return
     * @return String YYYY_MM
     * @throws
     * @Title: TimestampToYYYY_MM
     * @Description: TODO
     */
    public static String TimestampToY_M(String date) {
        if (date == null) {
            return "";
        }
        try {
            date = getDateFormat(DateStyle.YYYY_MM.getValue()).
                    format(Long.parseLong(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param @param  date
     * @param @return
     * @return String YYYY_MM_DD_HH_MM_DOT
     * @throws
     * @Title: TimestampToYYYY_MM_DD_HH_MM_DOT
     * @Description: TODO
     */
    public static String timestampToYMDHM_DOT(String date) {
        if (date == null) {
            return "";
        }
        try {
            date = getDateFormat(DateStyle.YYYY_MM_DD_HH_MM_DOT.getValue()).
                    format(Long.parseLong(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * TimestampToYYYY_MM_DD_DOT
     *
     * @param date
     * @return
     */
    public static String timestampToYMD_DOT(String date) {
        if (date == null) {
            return "";
        }
        try {
            date = getDateFormat(DateStyle.YYYY_MM_DD_DOT.getValue()).
                    format(Long.parseLong(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * TimestampToHH_MM
     *
     * @param date
     * @return
     */
    public static String timestampToHH_MM(String date) {
        if (date == null) {
            return "";
        }
        try {
            date = getDateFormat(DateStyle.HH_MM.getValue()).
                    format(Long.parseLong(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param @param  date
     * @param @return
     * @return Timestamp
     * @throws
     * @Title: StringToTimestamp
     * @Description: 字符串 2012-01-01 22:22:22转时间戳1325427742000
     */
    public static Timestamp StringToTimestamp(String date) {
        Long time = Long.parseLong(date);
        String str = getDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()).format(time);
        return Timestamp.valueOf(str);
    }

    /**
     * 获取SimpleDateFormat
     *
     * @param pattern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
        SimpleDateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            synchronized (object) {
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(pattern);
                    dateFormat.setLenient(false);
                    threadLocal.set(dateFormat);
                }
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }

    /**
     * 获取日期中的某数值。如获取月份
     *
     * @param date     日期
     * @param dateType 日期格式
     * @return 数值
     */
    private static int getInteger(Date date, int dateType) {
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            num = calendar.get(dateType);
        }
        return num;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期字符串
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期字符串
     */
    private static String addInteger(String date, int dateType, int amount) {
        String dateString = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = StringToDate(date, dateStyle);
            myDate = addInteger(myDate, dateType, amount);
            dateString = DateToString(myDate, dateStyle);
        }
        return dateString;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期
     */
    private static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * 获取精确的日期
     *
     * @param timestamps 时间long集合
     * @return 日期
     */
    @SuppressLint("UseSparseArrays")
    private static Date getAccurateDate(List<Long> timestamps) {
        Date date = null;
        long timestamp = 0;
        Map<Long, long[]> map = new HashMap<Long, long[]>();
        List<Long> absoluteValues = new ArrayList<Long>();

        if (timestamps != null && timestamps.size() > 0) {
            if (timestamps.size() > 1) {
                for (int i = 0; i < timestamps.size(); i++) {
                    for (int j = i + 1; j < timestamps.size(); j++) {
                        long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = {timestamps.get(i), timestamps.get(j)};
                        map.put(absoluteValue, timestampTmp);
                    }
                }

                // 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的。此时minAbsoluteValue为0
                // 因此不能将minAbsoluteValue取默认值0
                long minAbsoluteValue = -1;
                if (!absoluteValues.isEmpty()) {
                    minAbsoluteValue = absoluteValues.get(0);
                    for (int i = 1; i < absoluteValues.size(); i++) {
                        if (minAbsoluteValue > absoluteValues.get(i)) {
                            minAbsoluteValue = absoluteValues.get(i);
                        }
                    }
                }

                if (minAbsoluteValue != -1) {
                    long[] timestampsLastTmp = map.get(minAbsoluteValue);

                    long dateOne = timestampsLastTmp[0];
                    long dateTwo = timestampsLastTmp[1];
                    if (absoluteValues.size() > 1) {
                        timestamp = Math.abs(dateOne) > Math.abs(dateTwo) ? dateOne : dateTwo;
                    }
                }
            } else {
                timestamp = timestamps.get(0);
            }
        }

        if (timestamp != 0) {
            date = new Date(timestamp);
        }
        return date;
    }

    /**
     * 判断字符串是否为日期字符串
     *
     * @param date 日期字符串
     * @return true or false
     */
    public static boolean isDate(String date) {
        boolean isDate = false;
        if (date != null) {
            if (getDateStyle(date) != null) {
                isDate = true;
            }
        }
        return isDate;
    }

    /**
     * 获取日期字符串的日期风格。失敗返回null。
     *
     * @param date 日期字符串
     * @return 日期风格
     */
    @SuppressLint("UseSparseArrays")
    public static DateStyle getDateStyle(String date) {
        DateStyle dateStyle = null;
        Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
        List<Long> timestamps = new ArrayList<Long>();
        for (DateStyle style : DateStyle.values()) {
            if (style.isShowOnly()) {
                continue;
            }
            Date dateTmp = null;
            if (date != null) {
                try {
                    ParsePosition pos = new ParsePosition(0);
                    dateTmp = getDateFormat(style.getValue()).parse(date, pos);
                    if (pos.getIndex() != date.length()) {
                        dateTmp = null;
                    }
                } catch (Exception e) {
                }
            }
            if (dateTmp != null) {
                timestamps.add(dateTmp.getTime());
                map.put(dateTmp.getTime(), style);
            }
        }
        Date accurateDate = getAccurateDate(timestamps);
        if (accurateDate != null) {
            dateStyle = map.get(accurateDate.getTime());
        }
        return dateStyle;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date StringToDate(String date) {
        DateStyle dateStyle = getDateStyle(date);
        return StringToDate(date, dateStyle);
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date    日期字符串
     * @param pattern 日期格式
     * @return 日期
     */
    public static Date StringToDate(String date, String pattern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(pattern).parse(date);
            } catch (Exception e) {
            }
        }
        return myDate;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date      日期字符串
     * @param dateStyle 日期风格
     * @return 日期
     */
    public static Date StringToDate(String date, DateStyle dateStyle) {
        Date myDate = null;
        if (dateStyle != null) {
            myDate = StringToDate(date, dateStyle.getValue());
        }
        return myDate;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String DateToString(Date date, String pattern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(pattern).format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dateString;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date      日期
     * @param dateStyle 日期风格
     * @return 日期字符串
     */
    public static String DateToString(Date date, DateStyle dateStyle) {
        String dateString = null;
        if (dateStyle != null) {
            dateString = DateToString(date, dateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date       旧日期字符串
     * @param newPattern 新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String newPattern) {
        DateStyle oldDateStyle = getDateStyle(date);
        return StringToString(date, oldDateStyle, newPattern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle newDateStyle) {
        DateStyle oldDateStyle = getDateStyle(date);
        return StringToString(date, oldDateStyle, newDateStyle);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date        旧日期字符串
     * @param olddPattern 旧日期格式
     * @param newPattern  新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String olddPattern, String newPattern) {
        return DateToString(StringToDate(date, olddPattern), newPattern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddDteStyle 旧日期风格
     * @param newParttern  新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle olddDteStyle, String newParttern) {
        String dateString = null;
        if (olddDteStyle != null) {
            dateString = StringToString(date, olddDteStyle.getValue(), newParttern);
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddPattern  旧日期格式
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, String olddPattern, DateStyle newDateStyle) {
        String dateString = null;
        if (newDateStyle != null) {
            dateString = StringToString(date, olddPattern, newDateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddDteStyle 旧日期风格
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle olddDteStyle, DateStyle newDateStyle) {
        String dateString = null;
        if (olddDteStyle != null && newDateStyle != null) {
            dateString = StringToString(date, olddDteStyle.getValue(), newDateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期字符串
     */
    public static String addYear(String date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date        日期
     * @param monthAmount 增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static String addMonth(String date, int monthAmount) {
        return addInteger(date, Calendar.MONTH, monthAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date        日期
     * @param monthAmount 增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int monthAmount) {
        return addInteger(date, Calendar.MONTH, monthAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期字符串
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期字符串
     */
    public static String addDay(String date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date       日期字符串
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期字符串
     */
    public static String addHour(String date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date       日期
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期
     */
    public static Date addHour(Date date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date         日期字符串
     * @param minuteAmount 增加数量。可为负数
     * @return 增加分钟后的日期字符串
     */
    public static String addMinute(String date, int minuteAmount) {
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date         日期
     * @param minuteAmount 增加数量。可为负数
     * @return 增加分钟后的日期
     */
    public static Date addMinute(Date date, int minuteAmount) {
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date         日期字符串
     * @param secondAmount 增加数量。可为负数
     * @return 增加秒钟后的日期字符串
     */
    public static String addSecond(String date, int secondAmount) {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date         日期
     * @param secondAmount 增加数量。可为负数
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int secondAmount) {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期字符串
     * @return 年份
     */
    public static int getYear(String date) {
        return getYear(StringToDate(date));
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        return getInteger(date, Calendar.YEAR);
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期字符串
     * @return 月份
     */
    public static int getMonth(String date) {
        return getMonth(StringToDate(date));
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        return getInteger(date, Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期字符串
     * @return 天
     */
    public static int getDay(String date) {
        return getDay(StringToDate(date));
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期
     * @return 天
     */
    public static int getDay(Date date) {
        return getInteger(date, Calendar.DATE);
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期字符串
     * @return 小时
     */
    public static int getHour(String date) {
        return getHour(StringToDate(date));
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期
     * @return 小时
     */
    public static int getHour(Date date) {
        return getInteger(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 分钟
     */
    public static int getMinute(String date) {
        return getMinute(StringToDate(date));
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        return getInteger(date, Calendar.MINUTE);
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 秒钟
     */
    public static int getSecond(String date) {
        return getSecond(StringToDate(date));
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期
     * @return 秒钟
     */
    public static int getSecond(Date date) {
        return getInteger(date, Calendar.SECOND);
    }

    /**
     * 获取日期 。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static String getDate(String date) {
        return StringToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取日期。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期
     * @return 日期
     */
    public static String getDate(Date date) {
        return DateToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * @param @param  date
     * @param @return
     * @return String
     * @throws
     * @Title: getDatetime
     * @Description: TODO
     */
    public static String getDatetime(Date date) {
        return DateToString(date, DateStyle.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 时间
     */
    public static String getTime(String date) {
        return StringToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期
     * @return 时间
     */
    public static String getTime(Date date) {
        return DateToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取两个日期相差的天数
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 相差天数。如果失败则返回-1
     */
    public static int getIntervalDays(String date, String otherDate) {
        return getIntervalDays(StringToDate(date), StringToDate(otherDate));
    }

    /**
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 相差天数。如果失败则返回-1
     */
    public static int getIntervalDays(Date date, Date otherDate) {
        int num = -1;
        Date dateTmp = DateUtil.StringToDate(DateUtil.getDate(date), DateStyle.YYYY_MM_DD);
        Date otherDateTmp = DateUtil.StringToDate(DateUtil.getDate(otherDate), DateStyle.YYYY_MM_DD);
        if (dateTmp != null && otherDateTmp != null) {
            long time = Math.abs(dateTmp.getTime() - otherDateTmp.getTime());
            num = (int) (time / (24 * 60 * 60 * 1000));
        }
        return num;
    }

    public static void main(String arg[]) {
        Date d = StringToDate("1325427742000", DateStyle.YYYY_MM_DD_HH_MM_SS);
        System.out.println(String.valueOf(d.getTime()));
    }
    /**
     * 获取当前年-月-日
     *
     * @param @return
     * @return String
     * @throws
     * @Title: getCurrentDate
     * @Description: TODO
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DateStyle.YYYY_MM_DD.getValue());
        String currTime = sdf.format(date);
        return currTime;
    }

    /**
     * 获取当前时间-样式自定义
     *
     * @param @return
     * @return String
     * @throws
     * @Title: getCurrentDate
     * @Description: TODO
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentTime(String style) {
        String currTime = "";
        Date date = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(style);
            currTime = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currTime;
    }


    public static Date getDate(int year, int moth, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, moth - 1, day, hour, minute);
        return calendar.getTime();
    }

    //获取周
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * @param year
     * @param moth 传入显示月份 0-11
     * @param day
     * @return
     */
    public static int getWeeksEn(int year, int moth, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, moth, day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 中国从周日开始
     *
     * @param @param  year
     * @param @param  moth 0-11
     * @param @param  day
     * @param @return
     * @return int
     * @throws
     * @Title: getWeeksIndex
     * @Description: TODO
     */
    public static int getWeeksChina(int year, int moth, int day) {
        return DateUtil.getWeeksEn(year, moth, day) - 1;
    }

    /**
     * 一整年中当天过去多少周
     *
     * @param @param  date
     * @param @return
     * @return int
     * @throws
     * @Title: getWeekInYearOfToday
     * @Description: TODO
     */
    public static int getWeekInYearOfDay(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();//这一句必须要设置，否则美国认为第一天是周日，而我国认为是周一，对计算当期日期是第几周会有错误
        switch (CalendarUtils.getFirstDayOfWeek()) {
            case Calendar.MONDAY:
                cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
                cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始
                break;
            case Calendar.SUNDAY:
                cal.setFirstDayOfWeek(Calendar.SUNDAY); // 设置每周的第一天为星期一
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);// 每周从周日开始
                break;
        }
        cal.setMinimalDaysInFirstWeek(1); // 一年里面的第一周至少包含有几天（1-7）
        cal.setTime(date);
        int weeks = cal.get(Calendar.WEEK_OF_YEAR);
        int month = cal.get(Calendar.MONTH);
        if (weeks == 1 && month != 0) {
            cal.add(Calendar.DAY_OF_YEAR, -6);
            weeks = cal.get(Calendar.WEEK_OF_YEAR) + 1;
        }
        return weeks;
    }

    /**
     * @param @param  year
     * @param @param  month
     * @param @param  day
     * @param @param  sign
     * @param @return
     * @return String
     * @throws
     * @Title: getDateString
     * @Description: TODO
     */
    public static String getDateString(int year, int month, int day, String sign) {
        StringBuilder build = new StringBuilder();
        build.append(year).append(sign).append(month < 10 ? "0" + month : month).
                append(sign).append(day < 10 ? "0" + day : day);
        return build.toString();
    }

    /**
     * getDateString
     *
     * @param year
     * @param month
     * @param sign
     * @return
     */
    public static String getDateString(int year, int month, String sign) {
        StringBuilder build = new StringBuilder();
        build.append(year).append(sign).append(month < 10 ? "0" + month : month);
        return build.toString();
    }

    public static String timeStampToHHMM(long time) {
        try {
            String date;
            date = getDateFormat(DateStyle.HH_MM.getValue()).
                    format(time);
            return date;
        } catch (Exception e) {
            return "";
        }
    }

    public static String timeStampToYYYYMMDD(long time) {
        try {
            String date;
            date = getDateFormat(DateStyle.YYYY_MM_DD.getValue()).
                    format(time);
            return date;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 按照输入的格式格式化日期
     * @param
     * @param pattern
     * @return
     */
    public static String formatDatePattern(Date date,String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String strDate = null;
        try {
            strDate = format.format(date);
        } catch (Exception e) {

        }
        return strDate == null ? "" :strDate;
    }
}

