package app.vp.cn.profession.timePicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

import app.vp.cn.profession.R;


/**
 * Created by pan on 2017/5/10.
 */
public class DatePicker extends BaseWheelPick {
    private WheelView yearView;
    private WheelView monthView;
    private WheelView dayView;
    //private WheelView weekView;
    private WheelView hourView;
    private WheelView minuteView;
    private TextView tvHour, tvMinute;
    private LinearLayout llyHour, llyMinute;
    private Integer[] yearArr, monthArr, dayArr, hourArr, minutArr;
    //    private String[]  monthStrArr,dayStrArr;
//    private String[]  weekArr;
    private DatePickerHelper datePickerHelp;
    public DateType type = DateType.TYPE_ALL;
    //开始时间
    private Date selDate = null;
    //年分限制
    private int yearLimt = 0;
    private OnChangeLisener onChangeLisener;
    private int selMonth, selectDay;
    private boolean isInitDate = false;

    //选择时间回调
    public void setOnChangeLisener(OnChangeLisener onChangeLisener) {
        this.onChangeLisener = onChangeLisener;
    }

    public DatePicker(Context context, DateType type) {
        super(context);
        if (this.type != null) {
            this.type = type;
        }
    }

    public void setStartDate(Date startDate) {
        this.selDate = startDate;
    }

    public void setYearLimt(int yearLimt) {
        this.yearLimt = yearLimt;
    }

    //初始化值
    public void init() {
        this.minuteView = (WheelView) findViewById(R.id.minute);
        this.hourView = (WheelView) findViewById(R.id.hour);
        //this.weekView = (WheelView) findViewById(R.id.week);
        this.dayView = (WheelView) findViewById(R.id.day);
        this.monthView = (WheelView) findViewById(R.id.month);
        this.yearView = (WheelView) findViewById(R.id.year);
        this.tvHour = (TextView) findViewById(R.id.tvHour);
        this.tvMinute = (TextView) findViewById(R.id.tvMinute);
        this.llyHour = (LinearLayout) findViewById(R.id.llyHour);
        this.llyMinute = (LinearLayout) findViewById(R.id.llyMinute);
        switch (type) {
            case TYPE_ALL:
                llyHour.setVisibility(View.VISIBLE);
                llyMinute.setVisibility(View.VISIBLE);
                //this.minuteView.setVisibility(VISIBLE);
                //this.hourView.setVisibility(VISIBLE);
                //this.weekView.setVisibility(VISIBLE);
                this.dayView.setVisibility(VISIBLE);
                this.monthView.setVisibility(VISIBLE);
                this.yearView.setVisibility(VISIBLE);
                break;
            case TYPE_YMDHM:
                llyHour.setVisibility(View.VISIBLE);
                llyMinute.setVisibility(View.VISIBLE);
                //this.minuteView.setVisibility(VISIBLE);
                //this.hourView.setVisibility(VISIBLE);
                //this.weekView.setVisibility(GONE);
                this.dayView.setVisibility(VISIBLE);
                this.monthView.setVisibility(VISIBLE);
                this.yearView.setVisibility(VISIBLE);
                break;
            case TYPE_YMDH:
                llyHour.setVisibility(View.VISIBLE);
                llyMinute.setVisibility(View.GONE);
                //this.minuteView.setVisibility(GONE);
                //this.hourView.setVisibility(VISIBLE);
                //this.weekView.setVisibility(GONE);
                this.dayView.setVisibility(VISIBLE);
                this.monthView.setVisibility(VISIBLE);
                this.yearView.setVisibility(VISIBLE);
                break;
            case TYPE_YMD_LUNAR:
            case TYPE_YMD:
                llyHour.setVisibility(View.GONE);
                llyMinute.setVisibility(View.GONE);
                //this.minuteView.setVisibility(GONE);
                //this.hourView.setVisibility(GONE);
                //this.weekView.setVisibility(VISIBLE);
                this.dayView.setVisibility(VISIBLE);
                this.monthView.setVisibility(VISIBLE);
                this.yearView.setVisibility(VISIBLE);
                resetYearView();
                break;
            case TYPE_YM:
                this.llyHour.setVisibility(GONE);
                this.llyMinute.setVisibility(GONE);
                this.dayView.setVisibility(GONE);
                this.monthView.setVisibility(VISIBLE);
                this.yearView.setVisibility(VISIBLE);
                break;
            case TYPE_MDHM:
                this.monthView.setVisibility(VISIBLE);
                this.dayView.setVisibility(VISIBLE);
                llyHour.setVisibility(View.VISIBLE);
                llyMinute.setVisibility(View.VISIBLE);
                tvHour.setVisibility(View.GONE);
                tvMinute.setVisibility(View.GONE);
                this.yearView.setVisibility(GONE);
                //findViewById(R.id.tvMargin).setVisibility(View.VISIBLE);
                findViewById(R.id.tvSign).setVisibility(View.VISIBLE);
                resetTimeView();
                break;
            case TYPE_HM:
                //this.minuteView.setVisibility(VISIBLE);
                //this.hourView.setVisibility(VISIBLE);
                llyHour.setVisibility(View.VISIBLE);
                llyMinute.setVisibility(View.VISIBLE);
                //this.weekView.setVisibility(GONE);
                this.dayView.setVisibility(GONE);
                this.monthView.setVisibility(GONE);
                this.yearView.setVisibility(GONE);
                break;
        }
        /*if(type==DateType.TYPE_HM){
            tvHour.setVisibility(View.VISIBLE);
        	tvMinute.setVisibility(View.VISIBLE);
        }else{
        	tvHour.setVisibility(View.GONE);
        	tvMinute.setVisibility(View.GONE);
        }*/

        datePickerHelp = new DatePickerHelper();
        datePickerHelp.setStartDate(selDate, yearLimt);
        yearArr = datePickerHelp.genYear();
        //weekArr =datePickerHelp.genWeek();
        hourArr = datePickerHelp.genHour();
        minutArr = datePickerHelp.genMinut();
        //weekView.setText(datePicker.getDisplayStartWeek());
//        monthArr =isShowLunar()?datePickerHelp.
//                genMonthLunArr(getLunar().getLunarYearNum()):datePickerHelp.genMonth();
        if (isShowLunar()) {
            monthArr = datePickerHelp.genMonthLunArr(getLunar().getLunarYearNum());
            dayArr = datePickerHelp.genDayLunArr(getLunar().getLunarYearNum(),
                    getLunar().getLunarMonthNum(), getLunar().isIsLeap());
        } else {
            monthArr = datePickerHelp.genMonth();
            dayArr = datePickerHelp.genDay();
        }

        setWheelListener(yearView, yearArr, true);
        setWheelListener(monthView, monthArr, true);
        setWheelListener(dayView, dayArr, true);
        //setWheelListener(weekView, weekArr, true);
        setWheelListener(hourView, hourArr, true);
        setWheelListener(minuteView, minutArr, true);


        int currItem;
        if (isShowLunar()) {
            currItem = datePickerHelp.findIndextByValue(getLunar().getLunarYearNum(), yearArr);
            yearView.setCurrentItem(currItem);
            currItem = datePickerHelp.findIndextByValue(
                    datePickerHelp.getToday(DatePickerHelper.Type.MONTH_LUN), monthArr);
            monthView.setCurrentItem(datePickerHelp.getLunar().isIsLeap() ? currItem + 1 : currItem);
            currItem = datePickerHelp.findIndextByValue(datePickerHelp.getToday(DatePickerHelper.Type.DAY_LUN), dayArr);
            dayView.setCurrentItem(currItem);
        } else {
            currItem = datePickerHelp.findIndextByValue(datePickerHelp.getToday(DatePickerHelper.Type.YEAR), yearArr);
            yearView.setCurrentItem(currItem);
            currItem = datePickerHelp.findIndextByValue(datePickerHelp.getToday(DatePickerHelper.Type.MOTH), monthArr);
            monthView.setCurrentItem(currItem);
            currItem = datePickerHelp.findIndextByValue(datePickerHelp.getToday(DatePickerHelper.Type.DAY), dayArr);
            dayView.setCurrentItem(currItem);
        }
        //weekView.setCurrentItem(datePickerHelp.getToady(DatePickerHelper.Type.WEEK));
        currItem = datePickerHelp.findIndextByValue(datePickerHelp.getToday(DatePickerHelper.Type.HOUR), hourArr);
        hourView.setCurrentItem(currItem);
        currItem = datePickerHelp.findIndextByValue(datePickerHelp.getToday(DatePickerHelper.Type.MINUTE), minutArr);
        minuteView.setCurrentItem(currItem);
        isInitDate = true;
    }

    private void resetYearView() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) yearView.getLayoutParams();
        layoutParams.weight = 2.3f;//只有年月日的时候-追求平衡 但一定得比月日的比例高点点
        yearView.setLayoutParams(layoutParams);
    }

    private void resetTimeView() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1.8f;//只有年月日的时候-追求平衡 但一定得比月日的比例高点点
        llyHour.setLayoutParams(layoutParams);
        llyMinute.setLayoutParams(layoutParams);
    }

    @Override
    protected void setAttrs(Context context, AttributeSet attrs) {
        if (context != null && attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DatePicker);
            textColor = a.getColor(R.styleable.DatePicker_picker_text_color, 0xffbbbbbb);
            selectColor = a.getColor(R.styleable.DatePicker_picker_select_textColor, 0xff444444);
            split = a.getColor(R.styleable.DatePicker_picker_split, 0xffeeeeee);
            splitHeight = a.getDimension(R.styleable.DatePicker_picker_split_height, 0.5f);
            a.recycle();
        }
    }

    @Override
    protected String[] convertData(WheelView wheelView, Integer[] data) {
        if (wheelView == yearView) {
            return datePickerHelp.getDisplayValue(data,"年" );
        } else if (wheelView == monthView) {
            if (isShowLunar()) {//农历
                return datePickerHelp.genMonthLun(data, "月" );
            }  else {
                return datePickerHelp.getDisplayValue(data, "月" );
            }
        } else if (wheelView == dayView) {
            if (isShowLunar()) {//农历
                return datePickerHelp.genDayLun(data);
            } else {
                return datePickerHelp.getDisplayValue(data,  "日");
            }
        } else if (wheelView == hourView) {
            return datePickerHelp.getDisplayValue(data, "");
        } else if (wheelView == minuteView) {
            return datePickerHelp.getDisplayValue(data, "");
        }
        return new String[0];
    }

    /**
     * 是否为农历
     *
     * @return
     */
    public boolean isShowLunar() {
        return type == DateType.TYPE_YMD_LUNAR;
    }
    /*@Override
    protected String[] convertData(WheelView wheelView, String[] data) {
    	if(wheelView == weekView){
        	return datePickerHelp.getDisplayValue(data, "");
        }
    	return new String[0];
    }*/

    @Override
    protected int getLayout() {
        return R.layout.layout_wheel_view;
    }

    @Override
    protected int getItemHeight() {
        return dayView.getItemHeight();
    }

    @Override
    protected void setData(Object[] datas) {
    }

    /**
     * 阳历选择天数
     *
     * @param year
     * @param month
     */
    private void setChgDayOrLunDaySel(int year, int month, boolean isLeapMonth) {
        dayArr = isShowLunar() ? datePickerHelp.genDayLunArr(year, month, isLeapMonth) : datePickerHelp.genDay(year, month);
        WheelGeneralAdapter adapter = (WheelGeneralAdapter) dayView.getViewAdapter();
        adapter.setData(convertData(dayView, dayArr));
        dayView.invalidateWheel(true);
        int indxt = datePickerHelp.findIndextByValue(selectDay, dayArr);
        if (indxt == -1) {
            dayView.setCurrentItem(0);
        } else {
            dayView.setCurrentItem(indxt);
        }
    }

    /**
     * 显示的月份 (目前是这两种类型TYPE_YMD_LUNAR，TYPE_MDHM)
     */
    private void setMonthsShowStr(int year) {
        if (isShowLunar()) {
            monthArr = datePickerHelp.genMonthLunArr(year);
        }
        WheelGeneralAdapter adapter = (WheelGeneralAdapter) monthView.getViewAdapter();
        adapter.setData(convertData(monthView, monthArr));
        monthView.invalidateWheel(true);
        if (isShowLunar()) {
            int indxt = datePickerHelp.findIndextByValue(selMonth, monthArr);
            if (indxt == -1) {
                monthView.setCurrentItem(0);
            } else {
                monthView.setCurrentItem(indxt);
            }
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        getSelectDate(wheel, oldValue, newValue);
        if (onChangeLisener != null) {
            onChangeLisener.onChanged(selDate);
        }
    }

    //是否为闰月
    private boolean isLeapMonthLun(int currItem) {
        String str = datePickerHelp.genMonthLun(monthArr, "")[currItem];
        return !TextUtils.isEmpty(str) && str.contains(getLunar().LEAPSTR);
    }

    @Override
    public void onScrollingStarted(WheelView wheel) {
    }

    @Override
    public void onScrollingFinished(WheelView wheel) {
    }


    //获取选中日期
    private Date getSelectDate(WheelView wheel, int oldValue, int newValue) {
        int year = yearArr[yearView.getCurrentItem()];
        int month = monthArr[monthView.getCurrentItem()];
        int day = dayArr[dayView.getCurrentItem()];
        int hour = hourArr[hourView.getCurrentItem()];
        int minut = minutArr[minuteView.getCurrentItem()];
        if (isShowLunar()) {
            if (wheel == yearView) {
                setMonthsShowStr(year);
            } else if (wheel == monthView) {
                selMonth = month;
            }
        } else if (type == DateType.TYPE_MDHM) {
            year = getLunar().getSolarYear();
            if (isInitDate) {
                if (wheel == monthView) {
                    //往下拉减少
                    if (oldValue == 0 && newValue == 11) {
                        year = year - 1;
                        //往上拉增加
                    } else if (oldValue == 11 && newValue == 0) {
                        year = year + 1;
                    }
                }
            }
        }
        if (wheel == yearView || wheel == monthView) {
            setChgDayOrLunDaySel(year, month, isShowLunar() && isLeapMonthLun(monthView.getCurrentItem()));
        } else {
            selectDay = dayArr[dayView.getCurrentItem()];
        }
        //农历
        if (isShowLunar()) {
            //初始化不做时间更新
//            if (!((wheel == yearView || wheel == monthView || wheel == dayView) && oldValue == 0)) {
            //农历转阳历
            if (isInitDate) {
                int[] solar = LunarCalendarUtils.lunarToSolar(year, isLeapMonthLun(monthView.getCurrentItem()) ? -month : month, day);
                if (year < 2049) {
                    try {
                        String lunarDate = "" + year;
                        int mMonth = isLeapMonthLun(monthView.getCurrentItem()) ? -month : month;
                        if (mMonth < 10) {
                            lunarDate = lunarDate + "0" + mMonth;
                        } else {
                            lunarDate = lunarDate + "" + mMonth;
                        }
                        int mDay = day;
                        if (mDay < 10) {
                            lunarDate = lunarDate + "0" + mDay;
                        } else {
                            lunarDate = lunarDate + "" + mDay;
                        }
                        solar = CalendarT.lunarToSolar(lunarDate, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                selDate = DateUtil.getDate(solar[0], solar[1], solar[2], hour, minut);
            }
//                   }
        } else {
            selDate = DateUtil.getDate(year, month, day, hour, minut);
        }
        getLunar().setTimeInMillis(selDate.getTime());
        return selDate;
    }

    /**
     * @param @return
     * @return Lunar
     * @throws
     * @Title: getLunar
     * @Description: TODO
     */
    public Lunar getLunar() {
        return datePickerHelp.getLunar();
    }

    public String getDisplayWeek(int year, int month, int day) {
        return datePickerHelp.getDisplayWeek(year, month, day);
    }
}
