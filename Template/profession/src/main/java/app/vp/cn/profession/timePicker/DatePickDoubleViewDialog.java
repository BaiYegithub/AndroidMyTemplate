package app.vp.cn.profession.timePicker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import app.vp.cn.profession.R;


/**
 * Created by pan on 2017/5/11.
 */
public class DatePickDoubleViewDialog extends Dialog implements OnChangeLisener {

    private TextView titleTv;
    private FrameLayout wheelLayout;
    private TextView cancel;
    private TextView sure;
    private TextView tvTitleDesc1, tvTitleDesc2, tvTitleDesc3;
    private RadioGroup rgSelect;
    private RadioButton rbDate, rbTime;
    /**
     * 页面标识
     */
    public int touchUi = R.id.rbDate;

    private String title;
    private String btnLeftDescr, btnRightDescr, tabLeftDescr, tabRightDescr;
    private String format;
    private DateType typeOwn = DateType.TYPE_YMD;
    private DateType typeTwo = DateType.TYPE_HM;
    private DateType touchType;
    //开始时间
    //private Date startDate = null;
    private Date selDate = null;
    //年分限制，默认上下100年
    private int yearLimt = 0;
    private OnChangeLisener onChangeLisener;
    private OnSureLisener onSureLisener;
    private OnLeftBtnLisener onLeftBtnLisener;
    private DatePicker mDatePicker;
    //private int year,month,day;
    //设置标题
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 选项卡1
     *
     * @param tabLeftDescr
     */
    public void setTabLeftDescr(String tabLeftDescr) {
        this.tabLeftDescr = tabLeftDescr;
    }

    /**
     * 选项卡2
     *
     * @param tabRightDescr
     */
    public void setTabRightDescr(String tabRightDescr) {
        this.tabRightDescr = tabRightDescr;
    }

    /**
     * 左下边按钮
     *
     * @param btnLeftDescr
     */
    public void setBtnLeftDescr(String btnLeftDescr) {
        this.btnLeftDescr = btnLeftDescr;
    }

    /**
     * 右下边按钮
     *
     * @param btnRightDescr
     */
    public void setBtnRightDescr(String btnRightDescr) {
        this.btnRightDescr = btnRightDescr;
    }

    /**
     * 滚轮模式
     *
     * @param type
     */
    public void setType(DateType type) {
        this.typeOwn = type;
    }

    /**
     * 选项卡第二个界面滚轮模式
     *
     * @param typeTwo
     */
    public void setTypeTwoView(DateType typeTwo) {
        this.typeTwo = typeTwo;
    }

    /**
     * 标题部位—日期显示格式
     */
    public void setDateFormat(String format) {
        this.format = format;
    }

    public void setDateFormatTwoView(String format) {
        this.format = format;
    }

    /**
     * 设置开始时间
     */
    public void setStartDate(Date startDate) {
        this.selDate = startDate;
    }

    //设置年份限制，上下年份
    public void setYearLimt(int yearLimt) {
        this.yearLimt = yearLimt;
    }

    //设置选择回调
    public void setOnChangeLisener(OnChangeLisener onChangeLisener) {
        this.onChangeLisener = onChangeLisener;
    }

    //设置点击确定按钮，回调
    public void setOnSureLisener(OnSureLisener onSureLisener) {
        this.onSureLisener = onSureLisener;
    }

    public void setOnLeftBtnLisenerListener(OnLeftBtnLisener onLeftBtnLisener) {
        this.onLeftBtnLisener = onLeftBtnLisener;
    }

    public DatePickDoubleViewDialog(Context context) {
        super(context, R.style.dialog_style);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pick_doubleview);
        initView();
        initParas();
    }

    private DatePicker getDatePicker() {
        mDatePicker = new DatePicker(getContext(), touchType);
        mDatePicker.setStartDate(selDate);
        mDatePicker.setYearLimt(yearLimt);
        mDatePicker.setOnChangeLisener(this);
        mDatePicker.init();
        return mDatePicker;
    }

    private void initView() {
        this.titleTv = (TextView) findViewById(R.id.title);
        rgSelect = (RadioGroup) findViewById(R.id.rgSelect);
        rbDate = (RadioButton) findViewById(R.id.rbDate);
        rbTime = (RadioButton) findViewById(R.id.rbTime);
        tvTitleDesc1 = (TextView) findViewById(R.id.tvTitleDesc1);
        tvTitleDesc2 = (TextView) findViewById(R.id.tvTitleDesc2);
        tvTitleDesc3 = (TextView) findViewById(R.id.tvTitleDesc3);

        this.wheelLayout = (FrameLayout) findViewById(R.id.wheelLayout);
        this.sure = (TextView) findViewById(R.id.sure);
        this.cancel = (TextView) findViewById(R.id.cancel);
        this.cancel.setTextColor(getContext().getResources().
                getColor(R.color.color_text_hint));
        initData();
        refreshWheelLayout();
        initListener();
    }

    private void initData() {
        touchType = typeOwn;
        if (!TextUtils.isEmpty(title)) {
            this.titleTv.setText(title);
        }
        if (!TextUtils.isEmpty(tabLeftDescr)) {
            this.rbDate.setText(tabLeftDescr);
        }
        if (!TextUtils.isEmpty(tabRightDescr)) {
            this.rbTime.setText(tabRightDescr);
        }
        if (!TextUtils.isEmpty(btnLeftDescr)) {
            this.cancel.setText(btnLeftDescr);
        }else{
            this.cancel.setText(R.string.label_cancel);
        }
        if (!TextUtils.isEmpty(btnRightDescr)) {
            this.sure.setText(btnRightDescr);
        }
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        //rgSelect.getChildAt(touchUi).performClick();//必须在监听设置之后,监听才能被调用
        rgSelect.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==0||checkedId == touchUi)//这里是为了防止viewpager滑动后，调用setCurrentItem
                {
                    return;
                }
                if (checkedId == R.id.rbDate) {//公历
                    touchType = typeOwn;
                    if(null!=onClickRadioBtnListener){
                        onClickRadioBtnListener.onLeftRadioBtnLisener(selDate.getTime());
                    }
                } else {//农历
                    touchType = typeTwo;
                    if(null!=onClickRadioBtnListener){
                        onClickRadioBtnListener.onRightRadioBtnLisener(selDate.getTime());
                    }
                }
                refreshWheelLayout();
                touchUi = checkedId;
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftBtnLisener != null) {
                    onLeftBtnLisener.onLeftBtnEvent(selDate);
                }
                dismiss();
            }
        });

        this.sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onSureLisener != null) {
                    onSureLisener.onSure(selDate);
                }
            }
        });
    }

    private void refreshWheelLayout() {
        int childCount = this.wheelLayout.getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                this.wheelLayout.removeViewAt(i);
            }
            //wheelLayout.removeAllViews();
        }
        mDatePicker = getDatePicker();
        this.wheelLayout.addView(mDatePicker);
    }

    private void initParas() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        //params.width = DateUtils.getScreenWidth(getContext());
        getWindow().setAttributes(params);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onChanged(Date date) {
        selDate=date;
        if ( touchType != DateType.TYPE_HM && touchType != DateType.TYPE_DHM) {
            //startDate = date;//开始时间变更
            int year = DateUtil.getYear(date);
            int month = DateUtil.getMonth(date);
            int day = DateUtil.getDay(date);
            String desc2 = String.format(getContext().getString(R.string.label_year_of_week),
                    String.valueOf(DateUtil.getWeekInYearOfDay(date)));
            String desc3 = "";
            if (mDatePicker != null) {
                desc3 = mDatePicker.getDisplayWeek(year, month, day);
            }
            String messge = "";
            if (touchType == DateType.TYPE_YMD_LUNAR) {
                messge = DateUtil.getDateString(year, month, day, ".");
                //messge=DateUtil.DateToString(date, DateStyle.YYYY_MM_DD_DOT);
                if (!TextUtils.isEmpty(format)) {
                    try {
                        messge = new SimpleDateFormat(format).format(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                messge = mDatePicker.getLunar().getLunarDate("年", "月", "");
            }
            setTitleDesc(messge, desc2, desc3);
        }
        if (onChangeLisener != null) {
            onChangeLisener.onChanged(date);
        }
    }

    private void setTitleDesc(String desc1, String desc2, String desc3) {
        tvTitleDesc1.setText(desc1);
        tvTitleDesc2.setText(" "+desc2);
        tvTitleDesc3.setText(" "+desc3);
    }
    private OnClickRadioBtnListener onClickRadioBtnListener;
    public void setOnClickRadioBtnListener(OnClickRadioBtnListener onClickListener){
        this.onClickRadioBtnListener=onClickListener;
    }
}
