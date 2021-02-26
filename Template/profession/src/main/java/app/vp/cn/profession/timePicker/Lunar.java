package app.vp.cn.profession.timePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 从1900年起200年的月历。
 */
public final class Lunar {
    /**
     * | 0 - 11(bit) | 12 - 15(bit) |
     * month      leap month
     * If last 4bit is 1111 or 0000 means no leap month.
     * If the last 4bit in next data is 1111, the days of leap month is 30 days,
     * otherwise, the days of leap month is 29days.
     */
    private static final int[] LUNR_INFO = {
            0x4bd8, 0x4ae0, 0xa570, 0x54d5, 0xd260, 0xd950, 0x5554, 0x56af,
            0x9ad0, 0x55d2, 0x4ae0, 0xa5b6, 0xa4d0, 0xd250, 0xd295, 0xb54f,
            0xd6a0, 0xada2, 0x95b0, 0x4977, 0x497f, 0xa4b0, 0xb4b5, 0x6a50,
            0x6d40, 0xab54, 0x2b6f, 0x9570, 0x52f2, 0x4970, 0x6566, 0xd4a0,
            0xea50, 0x6a95, 0x5adf, 0x2b60, 0x86e3, 0x92ef, 0xc8d7, 0xc95f,
            0xd4a0, 0xd8a6, 0xb55f, 0x56a0, 0xa5b4, 0x25df, 0x92d0, 0xd2b2,
            0xa950, 0xb557, 0x6ca0, 0xb550, 0x5355, 0x4daf, 0xa5b0, 0x4573,
            0x52bf, 0xa9a8, 0xe950, 0x6aa0, 0xaea6, 0xab50, 0x4b60, 0xaae4,
            0xa570, 0x5260, 0xf263, 0xd950, 0x5b57, 0x56a0, 0x96d0, 0x4dd5,
            0x4ad0, 0xa4d0, 0xd4d4, 0xd250, 0xd558, 0xb540, 0xb6a0, 0x95a6,
            0x95bf, 0x49b0, 0xa974, 0xa4b0, 0xb27a, 0x6a50, 0x6d40, 0xaf46,
            0xab60, 0x9570, 0x4af5, 0x4970, 0x64b0, 0x74a3, 0xea50, 0x6b58,
            0x5ac0, 0xab60, 0x96d5, 0x92e0, 0xc960, 0xd954, 0xd4a0, 0xda50,
            0x7552, 0x56a0, 0xabb7, 0x25d0, 0x92d0, 0xcab5, 0xa950, 0xb4a0,
            0xbaa4, 0xad50, 0x55d9, 0x4ba0, 0xa5b0, 0x5176, 0x52bf, 0xa930,
            0x7954, 0x6aa0, 0xad50, 0x5b52, 0x4b60, 0xa6e6, 0xa4e0, 0xd260,
            0xea65, 0xd530, 0x5aa0, 0x76a3, 0x96d0, 0x4afb, 0x4ad0, 0xa4d0,
            0xd0b6, 0xd25f, 0xd520, 0xdd45, 0xb5a0, 0x56d0, 0x55b2, 0x49b0,
            0xa577, 0xa4b0, 0xaa50, 0xb255, 0x6d2f, 0xada0, 0x4b63, 0x937f,
            0x49f8, 0x4970, 0x64b0, 0x68a6, 0xea5f, 0x6b20, 0xa6c4, 0xaaef,
            0x92e0, 0xd2e3, 0xc960, 0xd557, 0xd4a0, 0xda50, 0x5d55, 0x56a0,
            0xa6d0, 0x55d4, 0x52d0, 0xa9b8, 0xa950, 0xb4a0, 0xb6a6, 0xad50,
            0x55a0, 0xaba4, 0xa5b0, 0x52b0, 0xb273, 0x6930, 0x7337, 0x6aa0,
            0xad50, 0x4b55, 0x4b6f, 0xa570, 0x54e4, 0xd260, 0xe968, 0xd520,
            0xdaa0, 0x6aa6, 0x56df, 0x4ae0, 0xa9d4, 0xa4d0, 0xd150, 0xf252, 0xd520
    };

    /**
     * solar terms information
     * 节气信息
     */
    private static final int[] SOLAR_TERM_INFO = {
            0, 21208, 42467, 63836, 85337, 107014, 128867, 150921,
            173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033,
            353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758
    };


    /**
     * lunar string used in month and day
     * 阴历用到的字符
     */
    private static final String[] LUNAR_STRING = {
            "零", "一", "二", "三", "四", "五", "六", "七", "八", "九"
    };

    /* special lunar string 阴历用到的特殊字符*/
    private static final String[] LUNAR_SPEC_STRING = {
            "初", "十", "廿", "卅", "正", "冬", "腊", "闰"
    };

    /* twenty eight stars with direction and fortune 二十八颗星，有方向和财富*/
    private final Star[][] mTwentyEightStars = new Star[][]{
            new Star[]{
                    new Star("房日兔", "吉", "东方"),
                    new Star("心月狐", "凶", "东方"),
                    new Star("尾火虎", "吉", "东方"),
                    new Star("箕水豹", "吉", "东方"),
                    new Star("角木蛟", "吉", "东方"),
                    new Star("亢金龙", "凶", "东方"),
                    new Star("氐土貉", "凶", "东方"),
            },

            new Star[]{
                    new Star("虚日鼠", "凶", "北方"),
                    new Star("危月燕", "凶", "北方"),
                    new Star("室火猪", "吉", "北方"),
                    new Star("壁水貐", "吉", "北方"),
                    new Star("斗木獬", "吉", "北方"),
                    new Star("牛金牛", "凶", "北方"),
                    new Star("女士蝠", "凶", "北方"),
            },

            new Star[]{
                    new Star("昴日鸡", "凶", "西方"),
                    new Star("毕月乌", "吉", "西方"),
                    new Star("觜火猴", "凶", "西方"),
                    new Star("参水猿", "凶", "西方"),
                    new Star("奎水狼", "凶", "西方"),
                    new Star("娄金狗", "吉", "西方"),
                    new Star("胃土雉", "吉", "西方"),
            },

            new Star[]{
                    new Star("星日马", "凶", "南方"),
                    new Star("张月鹿", "吉", "南方"),
                    new Star("翼火蛇", "凶", "南方"),
                    new Star("轸水蚓", "吉", "南方"),
                    new Star("井木犴", "吉", "南方"),
                    new Star("鬼金羊", "凶", "南方"),
                    new Star("柳土獐", "凶", "南方"),
            },
    };

    private Calendar mSolar;
    private GregorianCalendar mUTCCalendar;
    private int mLunarYear;
    private int mLunarMonth;
    private int mLunarDay;
    private int mDaysInLuarMonth;
    private boolean mIsLeap;
    private int mSolarYear;
    private int mSolarMonth;
    private int mSolarDay;
    private int mCyclicalYear = 0;
    private int mCyclicalMonth = 0;
    private int mCyclicalDay = 0;

    /**
     * Create a new instance of Lunar Calendar.
     *
     * @return new instance
     */
    public static synchronized Lunar newInstance() {
        return new Lunar();
    }

    public Lunar() {
    }

    /* Twenty-eight stars class 二十八星宿 */
    private class Star {
        private String star;
        private String direction;
        private String fortune;

        public Star(String star, String fortune, String direction) {
            this.star = star;
            this.fortune = fortune;
            this.direction = direction;
        }

        public String getDirection() {
            return direction;
        }

        public String getFortune() {
            return fortune;
        }

        public String getStar() {
            return star;
        }
    }

    /**
     * 根据时间戳获取农历日期
     *
     * @param millisec
     */
    public void init(long millisec) {
        mUTCCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        mSolar = Calendar.getInstance();
        mSolar.setTimeInMillis(millisec);
        mLunarYear = 1900;

        //阳历1900-1-31为农历1900-1-1；
        Calendar baseDate = new GregorianCalendar(1900, 0, 31);//更改为 月份+1
        //获取距离农历1900-1-1的天数
        long offset = (millisec - baseDate.getTimeInMillis()) / 86400000L;//86400000=24 * 60 * 60 * 1000
        //获取当前的农历年份
        //从1900年开始，依次递减，直到剩余天数小于当前年份天数为止。
        int daysInLunarYear = getLunarYearDays(mLunarYear);
        while (mLunarYear < 2100 && offset >= daysInLunarYear) {
            offset -= daysInLunarYear;
            daysInLunarYear = getLunarYearDays(++mLunarYear);
        }
        //获取当前的农历月份
        int lunarMonth = 1;
        int leapMonth = getLunarLeapMonth(mLunarYear);
        boolean leapDec = false;
        boolean isLeap = false;
        int daysInLunarMonth = 0;
        /* to get lunar year, month and day */
        while (lunarMonth < 13 && offset > 0) {
            if (isLeap && leapDec) {
                daysInLunarMonth = getLunarLeapDays(mLunarYear);
                leapDec = false;
            } else {
                daysInLunarMonth = getLunarMonthDays(mLunarYear, lunarMonth);
            }

            if (offset < daysInLunarMonth) {
                break;
            }
            offset -= daysInLunarMonth;

            if (leapMonth == lunarMonth && !isLeap) {
                leapDec = true;
                isLeap = true;
            } else {
                lunarMonth++;
            }
        }

        mDaysInLuarMonth = daysInLunarMonth;
        mLunarMonth = lunarMonth;
        mIsLeap = (lunarMonth == leapMonth && isLeap);
        mLunarDay = (int) offset + 1;
        getCyclicalData();
    }

    public boolean isIsLeap() {
        return mIsLeap;
    }

    public void initLunar(int lunarYear, int lunarMonth, int lunarDay) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(lunarYear + "-" + lunarMonth + "-" + lunarDay);
            init(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取闰月月份
     *
     * @param lunarYear lunar year 农历年份
     * @return 指定农历年的月份，否则返回0
     */
    public int getLunarLeapMonth(int lunarYear) {
        int leapMonth = LUNR_INFO[lunarYear - 1900] & 0xf;
        return leapMonth == 0xf ? 0 : leapMonth;
    }

    /**
     * 获取闰月的天数
     *
     * @param lunarYear lunar year 农历年份
     * @return 闰月的总天数，如果没有闰月则返回0。
     */
    public int getLunarLeapDays(int lunarYear) {
        return getLunarLeapMonth(lunarYear) > 0 ?
                ((LUNR_INFO[lunarYear - 1899] & 0xf) == 0xf ? 30 : 29) : 0;
    }

    /**
     * 获取天数
     *
     * @param lunarYear lunar year农历年份
     * @return 农历年总天数
     */
    private int getLunarYearDays(int lunarYear) {
        /* lunar year has (12 * 29 =) 348 days at least */
        int totalDays = 348;
        for (int i = 0x8000; i > 0x8; i >>= 1) {
            totalDays += ((LUNR_INFO[lunarYear - 1900] & i) != 0) ? 1 : 0;
        }
        return totalDays + getLunarLeapDays(lunarYear);
    }

    /**
     * 在正常情况下，得到农历月的总天数。
     *
     * @param lunarYear  农历年份
     * @param lunarMonth 农历月份
     * @return 农历月的总天数。
     */
    public int getLunarMonthDays(int lunarYear, int lunarMonth) {
        return ((LUNR_INFO[lunarYear - 1900] & (0x10000 >> lunarMonth)) != 0) ? 30 : 29;
    }

    /**
     * Get Coordinated Universal Time for given params.
     *
     * @param year  the year to set
     * @param month the month to set
     * @param day   the day to set
     * @param hour  the hour to set
     * @param min   the minute to set
     * @param sec   the second to set
     * @return Coordinated Universal Time
     */
    private synchronized long getUTC(int year, int month, int day, int hour, int min, int sec) {
        synchronized (Lunar.class) {
            mUTCCalendar.clear();
            mUTCCalendar.set(year, month, day, hour, min, sec);
            return mUTCCalendar.getTimeInMillis();
        }
    }

    /**
     * 将当前日期转换为协调的世界时。
     *
     * @param date 当前日期
     * @return 在协调世界时获得一天
     */
    private synchronized int getUTCDay(Date date) {
        synchronized (Lunar.class) {
            mUTCCalendar.clear();
            mUTCCalendar.setTimeInMillis(date.getTime());
            return mUTCCalendar.get(Calendar.DAY_OF_MONTH);
        }
    }

    /**
     * 将当前日期转换为协调的世界时。
     *
     * @param date 当前日期
     * @return 在协调世界时获得一月
     */
    private synchronized int getUTCMonth(Date date) {
        synchronized (Lunar.class) {
            mUTCCalendar.clear();
            mUTCCalendar.setTimeInMillis(date.getTime());
            return mUTCCalendar.get(Calendar.MONTH);
        }
    }

    /**
     * 获取节气日。
     *
     * @param solarYear the specified solar year
     * @param index     the index of solar terms in
     * @return 节气日
     */
    private int getSolarTermDay(int solarYear, int index) {
        long millisec = (long) 31556925974.7 * (solarYear - 1900) +
                SOLAR_TERM_INFO[index] * 60000L;
        return getUTCDay(new Date(millisec + getUTC(1900, 0, 6, 2, 5, 0)));
    }

    /**
     * Get the month of solar terms.
     *
     * @param solarYear the specified solar year
     * @param index     the index of solar terms in
     * @return the month of solar terms
     */
    @SuppressWarnings("unused")
    private int getSolarTermMonth(int solarYear, int index) {
        long millisec = (long) 31556925974.7 * (solarYear - 1900) +
                SOLAR_TERM_INFO[index] * 60000L;
        return getUTCMonth(new Date(millisec + getUTC(1900, 0, 6, 2, 5, 0)));
    }

    /* 获取天干地支数据 */
    private void getCyclicalData() {
        mSolarYear = mSolar.get(Calendar.YEAR);
        mSolarMonth = mSolar.get(Calendar.MONTH);
        mSolarDay = mSolar.get(Calendar.DAY_OF_MONTH);

        int cyclicalYear;
        int cyclicalMonth;
        int cyclicalDay;

        /* 年--天干地支 */
//        int term = getSolarTermDay(mSolarYear, 2);
//        if (mSolarMonth < 1 || (mSolarMonth == 1 && mSolarDay < term)) {
//        if (mSolarMonth < 1) {
//            cyclicalYear = (mSolarYear - 1900 + 36 - 1) % 60;
//        } else {
//            cyclicalYear = (mSolarYear - 1900 + 36) % 60;
//        }
        cyclicalYear = (mLunarYear - 1900 + 36) % 60;
        /* 年--天干地支 */
        int firstNode = getSolarTermDay(mSolarYear, mSolarMonth * 2);
        if (mSolarDay < firstNode) {
            cyclicalMonth = ((mSolarYear - 1900) * 12 + mSolarMonth + 12) % 60;
        } else {
            cyclicalMonth = ((mSolarYear - 1900) * 12 + mSolarMonth + 13) % 60;
        }

        cyclicalDay = (int) (getUTC(mSolarYear, mSolarMonth,
                mSolarDay, 0, 0, 0) / 86400000 + 25567 + 10) % 60;

        mCyclicalYear = cyclicalYear;
        mCyclicalMonth = cyclicalMonth;
        mCyclicalDay = cyclicalDay;
    }

    /**
     * 得到具有指定循环数的天干。
     *
     * @param cyclicalNum 天干地支的周期数
     * @return 天干指数
     */
    private int getHeavenlyStems(int cyclicalNum) {
        return cyclicalNum % 10;
    }

    /**
     * 获取具有指定循环数的地支。
     *
     * @param cyclicalNum 天干地支的周期数
     * @return 地支指数
     */
    private int getEarthlyBranches(int cyclicalNum) {
        return cyclicalNum % 12;
    }

    /**
     * 得到天干和地支。
     *
     * @param cyclicalNum cyclical number in Heavenly Stems and Earthly Branches
     * @return Heavenly Stems and Earthly Branches string
     */
    private String getCyclical(int cyclicalNum) {
        return CalendarStringUtils.HEAVENLY_STEMS[getHeavenlyStems(cyclicalNum)] +
                CalendarStringUtils.EARTHLY_BRANCHES[getEarthlyBranches(cyclicalNum)];
    }

    /**
     * Set date to get lunar data.
     *
     * @param year  the year
     * @param month the month
     * @param day   the day
     */
    public void setDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0);
        init(year == 0 ? System.currentTimeMillis() : calendar.getTimeInMillis());
    }

    /**
     * Set time in millisecond.
     *
     * @param millisecond millisecond to set
     */
    public void setTimeInMillis(long millisecond) {
        init(millisecond);
    }

    /**
     * Get Chinese zodiac string.
     *
     * @return Chinese zodiac
     */
    public String getZodiac() {
        return CalendarStringUtils.ZODIAC[(mLunarYear - 4) % 12];
    }

    /**
     * Get year in Heavenly Stems and Earthly Branches.
     *
     * @return year in Heavenly Stems and Earthly Branches
     */
    public String getCyclicalYear() {
        return getCyclical(mCyclicalYear);
    }

    /**
     * Get month in Heavenly Stems and Earthly Branches.
     *
     * @return month in Heavenly Stems and Earthly Branches
     */
    public String getCyclicalMonth() {
        return getCyclical(mCyclicalMonth);
    }

    /**
     * Get day in Heavenly Stems and Earthly Branches.
     *
     * @return day in Heavenly Stems and Earthly Branches
     */
    public String getCyclicalDay() {
        return getCyclical(mCyclicalDay);
    }


    /**
     * Get lunar month in Chinese according to lunar month numeric.
     *
     * @param lunarMonth lunar month numeric
     * @param isLeap     is current month leap or not
     * @return lunar month in Chinese
     */
    public String getLunarMonth(int lunarMonth, boolean isLeap) {
        String lunarMonthStr = "";
        if (lunarMonth == 1) {//正月
            lunarMonthStr = LUNAR_SPEC_STRING[4];
        } else {
            switch (lunarMonth) {//十
                case 10:
                    lunarMonthStr = LUNAR_SPEC_STRING[1];
                    break;
                case 11://冬
                    lunarMonthStr = LUNAR_SPEC_STRING[5];
                    break;

                case 12://腊
                    lunarMonthStr = LUNAR_SPEC_STRING[6];
                    break;

                default://2-9
                    lunarMonthStr += LUNAR_STRING[lunarMonth % 10];
                    break;
            }
        }
        return (isLeap ? LEAPSTR : "") + lunarMonthStr;
    }

    public final String LEAPSTR = "闰";

    /**
     * Get month in Chinese of lunar calendar.
     *
     * @return month in lunar calendar
     */
    public String getLunarMonth() {
        return getLunarMonth(mLunarMonth, mIsLeap);
    }

    /**
     * Get lunar day in Chinese according to lunar day numeric.
     *
     * @param lunarDay lunar day numeric
     * @return lunar day in Chinese
     */
    public String getLunarDay(int lunarDay) {
        if (lunarDay < 1 || lunarDay > 30) {
            return "";
        }

        int decade = lunarDay / 10;//总除 10=1 20=2 30=3
        int unit = lunarDay % 10;//余数 1 2 3 4 5 6 7 8 9 0
        String decadeStr = LUNAR_SPEC_STRING[decade];// 十  二十  三十
        String unitStr = LUNAR_STRING[unit];//1-9
        if (lunarDay < 11) {//1-10 初
            decadeStr = LUNAR_SPEC_STRING[0];
        }
        if (unit == 0)//0 10及10以上
        {
            unitStr = LUNAR_SPEC_STRING[1];
            if (decade == 2) {
                decadeStr = LUNAR_STRING[2];
            } else if (decade == 3) {
                decadeStr = LUNAR_STRING[3];
            }
        }
		/*if(unit == 0){
			return decadeStr;
		}*/
        return decadeStr + unitStr;
    }
	/*public String getChinaDayString(int day) {
		String chineseTen[] = { "初", "十", "廿", "卅" };
		int n = day % 10 == 0 ? 9 : day % 10 - 1;
		if (day > 30)
			return "";
		if (day == 10)
			return "初十";
		else if(day == 20){
			return chineseTen[2];
		}else if(day == 30){
			return chineseTen[3];
		}else
			return chineseTen[day / 10] + chineseNumber[n];
	}
	final static String chineseNumber[] = { "一", "二", "三", "四", "五", "六", "七",
		"八", "九", "十", "冬", "腊" };*/

    /**
     * Get day in lunar calendar.
     *
     * @return day in lunar calendar
     */
    public String getLunarDay() {
        return getLunarDay(mLunarDay);
    }

    public int getLunarYearNum() {
        return mLunarYear;
    }

    /**
     * Get lunar month in numeric.
     *
     * @return the numeric of lunar month
     */
    public int getLunarMonthNum() {
        return mLunarMonth;
    }

    public int getLunarDayNum() {
        return mLunarDay;
    }

    /**
     * Get solar calendar.
     *
     * @return solar calendar
     */
    public Calendar getCalendar() {
        return mSolar;
    }

    /**
     * Get year in the Gregorian calendar.
     *
     * @return year in the Gregorian calendar
     */
    public int getSolarYear() {
        return mSolarYear;
    }


    /**
     * Get the day of week.
     *
     * @return day of week(1-Sunday, 7-Saturday).
     */
    public int getDayOfWeek() {
        return mSolar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Get the week of year.
     *
     * @return week of year
     */
    public int getWeekOfYear() {
        return mSolar.get(Calendar.WEEK_OF_YEAR);
    }


    /**
     * Get star description for current day.
     *
     * @return star description
     */
    public String getTwentyEightStar() {
        int weekOfYear = (getWeekOfYear() - 1) % 4;
        int dayOfWeek = getDayOfWeek() - 1;
        Star star = mTwentyEightStars[weekOfYear][dayOfWeek];

        return star.getDirection() + star.getStar() + "-" + star.getFortune();
    }

    /**
     * @param yearSign
     * @param monthSign
     * @param daySign
     * @return
     */
    public String getLunarDate(String yearSign, String monthSign, String daySign) {
        return getLunarYearNum() + yearSign + getLunarMonth() + monthSign + getLunarDay() + daySign;
    }

}
