package app.vp.cn.profession.timePicker;

import android.annotation.SuppressLint;
import android.text.TextUtils;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;



/**
 * Created by pan on 2017/5/10.
 */
public class DatePickerHelper {
    //开始年
    private int YEAR_START;
    //开始月
    private int MONTH_START;
    //开始天
    private int DAY_START;
    //开始周
    private int WEEK_START;
    //开始小时
    private int HOUR_START;
    //开始分钟
    private int MINUTE_START;
    //开始时间
    //private Date startDate = new Date();
    //年份限制，上下5年
    private int yearLimt = CalendarUtils.SELECT_YEAR_LIMIT;

    private Lunar mLunar;
	private Calendar mCalendar;
    private ArrayList<Integer> tem = new ArrayList<Integer>();
    private ArrayList<String> dispalyTem = new ArrayList<String>();
    private String[] weeks= {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private String[] months = null;
    public enum Type {
        YEAR,
        MOTH,
        MONTH_LUN,
        DAY,
        DAY_LUN,
        WEEK,
        HOUR,
        MINUTE
    }

    public DatePickerHelper(){

        init();
    }

    private void init(){
        Date date=getCalendar().getTime();
		getLunar().setTimeInMillis(date.getTime());
        //获取年 月 日 时 分
        YEAR_START = DateUtil.getYear(date);
        MONTH_START = DateUtil.getMonth(date);
        DAY_START = DateUtil.getDay(date);
        //下标从0开始   0->周日
        WEEK_START = DateUtil.getWeek(date);
        HOUR_START = DateUtil.getHour(date);
        MINUTE_START = DateUtil.getMinute(date);
    }

    //设置初始化时间
    public void setStartDate(Date date, int yearLimt) {
        if(yearLimt>0) {
            this.yearLimt = yearLimt;
        }
        if(null!=date) {
            getCalendar().setTime(date);
        }
        init();
    }
    private Calendar getCalendar(){
        return mCalendar==null?mCalendar=Calendar.getInstance():mCalendar;
    }
    public Lunar getLunar(){
    	return mLunar==null?mLunar=Lunar.newInstance():mLunar;
    }
    public int getToday(Type type) {
        switch (type) {
            case YEAR:
                return YEAR_START;
            case MOTH:
                return MONTH_START;
            case MONTH_LUN:
                return getLunar().getLunarMonthNum();
            case DAY:
                return DAY_START;
            case DAY_LUN:
                return getLunar().getLunarDayNum();
            case WEEK:
                return WEEK_START;
            case HOUR:
                return HOUR_START;
            case MINUTE:
                return MINUTE_START;
        }
        return 0;
    }

    public String[] getDisplayValue(Integer[] arr, String per) {
        dispalyTem.clear();
        for (Integer i : arr) {
            String value = i < 10 ? ("0" + i) : "" + i;
            dispalyTem.add(value + per);
        }
        return dispalyTem.toArray(new String[0]);
    }

    /**
     * @return
     */
    public String[] getMonthsEn() {
        return months;
    }
    /**
     * 农历月份 12或者13个月  闰月
     * @param yearLunar
     * @return
     */
    public Integer[] genMonthLunArr(int yearLunar) {
        tem.clear();
        int leapMonth=getLunar().getLunarLeapMonth(yearLunar);
        for(Integer month:genMonth()){
            tem.add(month);
            if(leapMonth>0&&leapMonth==month){
                tem.add(month);
            }
        }
        return tem.toArray(new Integer[0]);
    }
    public Integer[] genDayLunArr(int yearLunar,int monthLunar,boolean isLeapMonth) {
        tem.clear();
        int days=1;
        if(isLeapMonth){
            days=Lunar.newInstance().getLunarLeapDays(yearLunar);
        }else{
            days=getLunar().getLunarMonthDays(yearLunar,monthLunar);
        }
        for(int i=1;i<=days;i++){
            tem.add(i);
        }
        return tem.toArray(new Integer[0]);
    }

    /**月份农历[阴历]
     * @return
     * @param monthLunArr
     */
    public String[]  genMonthLun(Integer[] monthLunArr, String unit){
        dispalyTem.clear();
        for(int i=0;i<monthLunArr.length;i++){
            if(i>0){
                if(monthLunArr[i]==monthLunArr[i-1]){
                    dispalyTem.add(getLunar().getLunarMonth(monthLunArr[i],true)+unit);
                    continue;//跳过之后的代码，到开始循环位置
                }
            }
            dispalyTem.add(Lunar.newInstance().getLunarMonth(monthLunArr[i],false)+unit);
        }
        return dispalyTem.toArray(new String[0]);
    }

    /**
     * @param dayLunArrs
     * @return
     */
    public String[] genDayLun(Integer[] dayLunArrs){
        dispalyTem.clear();
        for(int i=0;i<dayLunArrs.length;i++){
            dispalyTem.add(Lunar.newInstance().getLunarDay(dayLunArrs[i]));
        }
        return dispalyTem.toArray(new String[0]);
    }
    public String[] getDisplayValue(String[] arr, String per) {
        dispalyTem.clear();
        for (String i : arr) {
            //String value = i < 10 ? ("0" + i) : "" + i;
            dispalyTem.add(i + per);
        }
        return dispalyTem.toArray(new String[0]);
    }
    public Integer[] genHour() {
        return genArr(24, true);
    }

    public Integer[] genMinut() {
        return genArr(60, true);
    }

    public Integer[] genArr(int size, boolean isZero) {
        tem.clear();
        for (int i = isZero ? 0 : 1; i < (isZero ? size : size + 1); i++) {
            tem.add(i);
        }
        return tem.toArray(new Integer[0]);
    }
    /**
     * 1900-2100  从1901-2099 200年
     * 生成年
     * @return
     */
    public Integer[] genYear() {
        tem.clear();
        //只要年份等于100年 就认定为1901-2099
        if(yearLimt==CalendarUtils.SELECT_YEAR_LIMIT){
            for (int i = CalendarUtils.YEAR_START+1; i <=CalendarUtils.YEAR_END; i++) {
                tem.add(i);
            }
        }else{
            for (int i = YEAR_START - yearLimt; i < YEAR_START; i++) {
                tem.add(i);
            }
            tem.add(YEAR_START);

            for (int i = YEAR_START + 1; i < YEAR_START + yearLimt; i++) {
                tem.add(i);
            }
        }
        return tem.toArray(new Integer[0]);
    }

    /**月份公历[阳历]
     * @return
     */
    public Integer[] genMonth() {
        return new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    }
    public String[] genWeek(){
        return weeks;
    }

    @SuppressLint("SimpleDateFormat")
    public  Integer[] genDay(int year,int moth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,moth,1);
        calendar.add(Calendar.DATE, -1);
        int day = Integer.parseInt(new SimpleDateFormat("d").format(calendar.getTime()));
        return genArr(day, false);
    }

    public  Integer[] genDay() {
       return genDay(YEAR_START,MONTH_START);
    }
    public int findIndextByValue(int value, Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (value == arr[i]) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 周
     * @Title: findIndextByValue
     * @Description: TODO
     * @param @param value
     * @param @param arr
     * @param @return
     * @return int
     * @throws
     */
    public int findIndextByValue(String value, String[] arr) {
    	if(TextUtils.isEmpty(value)){
    		return -1;
    	}
        for (int i = 0; i < arr.length; i++) {
            if (value.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 中国从周日开始
     * @Title: getDisplayWeek
     * @Description: TODO
     * @param @param year
     * @param @param moth 传入显示1-12
     * @param @param day
     * @param @return
     * @return String
     * @throws
     */
    public String getDisplayWeek(int year, int month, int day) {
        return weeks[ DateUtil.getWeeksChina(year, month-1, day)];
    }
    public String getDisplayStartWeek(){
          return getDisplayWeek(YEAR_START,MONTH_START,DAY_START);
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DatePickerHelper().genDay(2016,2)));

    }


}
