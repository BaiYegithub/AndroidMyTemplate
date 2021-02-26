package app.vp.cn.profession.timePicker;

import android.support.v4.media.session.PlaybackStateCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 农历管理类
 */
public class NongLiManager {
    /**
     * 农历日
     */
    public static String[] lunarDate = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};
    /**
     * 农历月
     */
    public static String[] lunarMonth = {"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"};

    /**
     * 天干
     */
    private final String[] Gan = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    /**
     * 地支
     */
    private final String[] Zhi = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};


    /**
     * 农历年天数
     *
     * @param year
     * @return
     */
    private int lYearDays(int year) {
        int days = 348;
        for (int i = 32768; i > 8; i >>= 1) {
            if ((NongLiData.lunarInfo[year - 1900] & ((long) i)) != 0) {
                days++;
            }
        }
        return days + leapDays(year);
    }

    /**
     * 农历年闰月天数
     *
     * @param year
     * @return
     */
    public int leapDays(int year) {
        if (leapMonth(year) == 0) {
            return 0;
        }
        if ((NongLiData.lunarInfo[year - 1900] & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0) {
            return 30;
        }
        return 29;
    }

    /**
     * 农历年闰月
     *
     * @param year
     * @return
     */
    public int leapMonth(int year) {
        return (int) (NongLiData.lunarInfo[year - 1900] & 15);
    }

    /**
     * @param year
     * @param month
     * @return
     */
    public int monthDays(int year, int month) {
        if (year < 1900) {
            return 30;
        }
        try {
            if ((NongLiData.lunarInfo[year - 1900] & ((long) (65536 >> month))) == 0) {
                return 29;
            }
            return 30;
        } catch (Exception e) {
            e.printStackTrace();
            return 30;
        }
    }

    /**
     * 生肖
     * 1900
     *
     * @param year
     * @return
     */
    public String AnimalsYear(int year) {
        return CalendarStringUtils.ZODIAC[(year - 4) % 12];
    }

    /**
     * 天干地支
     * 天干10循环，地支12循环
     *
     * @param year 与甲子年份的年份差值
     * @return
     */
    public String cyclicalm(int year) {
        return this.Gan[year % 10] + this.Zhi[year % 12];
    }

    /**
     * 1。一般来说对应各个公元年份,则年份数除以60余4就是甲子年
     * 2。1900年庚子年 1864为甲子年
     *
     * @param year
     * @return
     */
    public String cyclical(int year) {
        return cyclicalm((year - 1900) + 36);
    }


    /**
     * @param year
     * @param i==(this.month - 1) * 2)
     * @return
     */
    public int getJieqi(int year, int i) {
        if (year < 1900 || year > 2101) {
            return 5;
        }
        try {
            int j = ((year - 1900) * 6) + (i / 4);
            switch (i % 4) {
                case 0:
                    return ((int) (NongLiData.jieqi[j] & -16777216)) >> 24;
                case 1:
                    return ((int) (NongLiData.jieqi[j] & 16711680)) >> 16;
                case 2:
                    return ((int) (NongLiData.jieqi[j] & 65280)) >> 8;
                case 3:
                    return ((int) (NongLiData.jieqi[j] & 255)) >> 0;
                default:
                    return 5;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 5;
        }
    }

    /**
     * 判断公历年是否是闰年
     * <p>
     * 闰年是公历中的名词。闰年分为普通闰年和世纪闰年。
     * 普通闰年:公历年份是4的倍数的，且不是100的倍数，为闰年。（如2004年就是闰年）；
     * 世纪闰年:公历年份是整百数的，必须是400的倍数才是闰年（如1900年不是世纪闰年，2000年是世纪闰年）；
     *
     * @param year
     * @return
     */
    public boolean isGongliYearIsLeapYear(int year) {
        if ((year % 4 != 0 || year % 100 == 0) && year % 400 != 0) {
            return false;
        }
        return true;
    }

    /**
     * 获取公历年的天数
     * 闰年(Leap Year)是为了弥补因人为历法规定造成的年度天数与地球实际公转周期的时间差而设立的。补上时间差的年份为闰年。闰年共有366天（1-12月分别为31天，29天，31天，30天，31天，30天，31天，31天，30天，31天，30天，31天）。
     * @param year
     * @return
     */
    public int getGongliOneYearDays(int year) {
        if (isGongliYearIsLeapYear(year)) {
            return 366;
        }
        return 365;
    }

    /**
     * 获取公历年year，month月的天数
     * <p>
     * 闰年共有366天（1-12月分别为31天，29天，31天，30天，31天，30天，31天，31天，30天，31天，30天，31天）。
     *
     * @param year
     * @param month
     * @return
     */
    public int getGongliOneMonthDays(int year, int month) {
        if (month == 2) {
            if (isGongliYearIsLeapYear(year)) {
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
     * 根据公历计算出农历
     * 根据年份，月份计算出一个月所对应的农历(包含节气)
     *
     * @param year
     * @param month
     * @return
     *
     * calGongliToNongli
     * 0--年
     * 1--月
     *
     * 6-- ？闰月
     */
    public ArrayList<long[]> calNongliByMonth(int year, int month) {
        int monthDays;
        ArrayList<long[]> arrayList = new ArrayList<>();
        if (year >= 1900 && year <= 2101) {
            try {
                int jieqi2 = getJieqi(year, (month - 1) * 2);
                long[] calGongliToNongli = calculateGongliToNongli(year, month, 1);
                if (calGongliToNongli[6] == 1) {
                    monthDays = leapDays((int) calGongliToNongli[0]);
                } else {
                    monthDays = monthDays((int) calGongliToNongli[0], (int) calGongliToNongli[1]);
                }
                arrayList.add(calGongliToNongli);
                Calendar instance = Calendar.getInstance();
                instance.set(year, month - 1, 2);
                long[] jArr = calGongliToNongli;
                while (instance.get(Calendar.MONTH) + 1 == month) {
                    long[] jArr2 = new long[7];
                    if (jArr[2] >= ((long) monthDays) || instance.get(Calendar.DAY_OF_MONTH) == jieqi2) {
                        jArr2 = calculateGongliToNongli(year, month, instance.get(Calendar.DAY_OF_MONTH));
                        if (jArr[6] == 1) {
                            monthDays = leapDays((int) jArr[0]);
                        } else {
                            monthDays = monthDays((int) jArr[0], (int) jArr[1]);
                        }
                    } else {
                        jArr2[0] = jArr[0];
                        jArr2[1] = jArr[1];
                        jArr2[2] = jArr[2] + 1;
                        jArr2[3] = jArr[3];
                        jArr2[4] = jArr[4];
                        jArr2[5] = jArr[5] + 1;
                        jArr2[6] = jArr[6];
                    }
                    arrayList.add(jArr2);
                    instance.add(Calendar.DAY_OF_MONTH, 1);
                    jArr = jArr2;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    /**
     * 根据公历计算出农历
     * 根据年份，月份，日计算出一天所对应的农历(包含节气)
     * 返回数组
     * 0----
     * 1----
     * 2----
     * 3----
     * 4----
     * @param year
     * @param month
     * @return
     */
    public long[] calculateGongliToNongli(int year, int month, int day) {
        long j = day;
        int i4 = year;
        long j2;
        long[] jArr = new long[7];
        if (year > 2000) {//2000年以后
            jArr[4] = 14;
            long gongliOneYearDays = 0 + ((long) (getGongliOneYearDays(2000) - 36));//
            jArr[4] = jArr[4] + 10;
            for (int i = 2001; i < year; i++) {
                gongliOneYearDays += (long) getGongliOneYearDays(i);
                jArr[4] = jArr[4] + 12;
            }
            for (int i = 1; i < month; i++) {
                gongliOneYearDays += (long) getGongliOneMonthDays(year, i);
                jArr[4] = jArr[4] + 1;
            }
            long j3 = ((long) day) + gongliOneYearDays;
            if (day >= getJieqi(year, (month - 1) * 2)) {
                jArr[4] = jArr[4] + 1;
            }
            jArr[5] = 29 + j3;
            int i7 = 2000;
            while (i4 < 2101) {
                int lYearDays = lYearDays(i4);
                if (j < ((long) lYearDays)) {
                    break;
                }
                j3 = j - ((long) lYearDays);
                i7 = i4 + 1;
            }
        } else {
            try {
                jArr[4] = 13;
                long gongliOneYearDays2 = 0 + ((long) (getGongliOneYearDays(NongLiData.minYear) - 31));
                jArr[4] = jArr[4] + 11;
                for (int i = 1901; i < year; i++) {
                    gongliOneYearDays2 += (long) getGongliOneYearDays(i);
                    jArr[4] = jArr[4] + 12;
                }
                for (int i = 1; i < month; i++) {
                    gongliOneYearDays2 += (long) getGongliOneMonthDays(year, i);
                    jArr[4] = jArr[4] + 1;
                }
                j = ((long) day) + gongliOneYearDays2;
                if (day >= getJieqi(year, (month - 1) * 2)) {
                    jArr[4] = jArr[4] + 1;
                }
                jArr[5] = 40 + j;
                i4 = NongLiData.minYear;
                while (i4 < 2101) {
                    int lYearDays2 = lYearDays(i4);
                    if (j < ((long) lYearDays2)) {
                        break;
                    }
                    j -= (long) lYearDays2;
                    i4++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int i10 = i4;
        jArr[3] = (long) ((year - 1864) - 1);
        boolean z = false;
        if (month > 2 || (month == 2 && day > 12)) {
            z = true;
        } else if (month == 2 && day >= getJieqi(year, (month - 1) * 2)) {
            z = true;
        }
        if (z) {
            jArr[3] = jArr[3] + 1;
        }
        jArr[0] = (long) i10;
        int leapMonth = leapMonth(i10);
        jArr[6] = 0;
        boolean z2 = false;
        int i11 = 1;
        while (true) {
            if (i11 >= 13) {
                break;
            }
            if (leapMonth > 0 && i11 == leapMonth + 1 && !z2) {
                int leapDays = leapDays((int) jArr[0]);
                if (j < ((long) leapDays)) {
                    jArr[6] = 1;
                    i11--;
                    break;
                }
                j2 = j - ((long) leapDays);
                i11--;
                z2 = true;
            } else {
                int monthDays = monthDays((int) jArr[0], i11);
                if (j < ((long) monthDays)) {
                    break;
                }
                j2 = j - ((long) monthDays);
            }
            i11++;
        }
        jArr[1] = (long) i11;
        jArr[2] = j + 1;
        return jArr;
    }


    ///////
    public long[] nongliToGongli(int year, int month, int day, boolean isSolar) {
        long[] jArr = new long[3];
        long j = 0;
        int i4 = NongLiData.minYear;
        while (i4 < year) {
            try {
                j += (long) lYearDays(i4);
                i4++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long j2 = j;
        int i5 = 1;
        while (i5 < month) {
            long monthDays = ((long) monthDays(year, i5)) + j2;
            i5++;
            j2 = monthDays;
        }
        if (isSolar && leapMonth(year) == month) {
            j2 += (long) monthDays(year, month);
        }
        if (leapMonth(year) > 0 && leapMonth(year) < month) {
            j2 += (long) leapDays(year);
        }
        Date date = new Date((j2 + ((long) (day - 1)) + (new Date(0, 0, 31).getTime() / 86400000)) * 86400000);
        jArr[0] = (long) (date.getYear() + NongLiData.minYear);
        jArr[1] = (long) (date.getMonth() + 1);
        jArr[2] = (long) date.getDate();
        return jArr;
    }

    /**
     * 计算农历时间间隔天数
     *
     * @param i
     * @param i2
     * @param i3
     * @param z
     * @param i4
     * @param i5
     * @param i6
     * @param z2
     * @return
     */
    public int calNongliJiangeDays(int i, int i2, int i3, boolean z, int i4, int i5, int i6, boolean z2) {
        try {
            long[] nongliToGongli = nongliToGongli(i, i2, i3, z);
            long[] nongliToGongli2 = nongliToGongli(i4, i5, i6, z2);
            return (int) ((new Date((int) (nongliToGongli2[0] - 1900), (int) (nongliToGongli2[1] - 1), (int) nongliToGongli2[2]).getTime() - new Date((int) (nongliToGongli[0] - 1900), (int) (nongliToGongli[1] - 1), (int) nongliToGongli[2]).getTime()) / 86400000);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 一天12个时辰对应的信息
     *
     * @param year
     * @param month
     * @param day
     * @return
     *
     * 0--
     * 1--吉凶
     *
     */
    public ArrayList<int[]> calDayTimeCyclical(int year, int month, int day) {
        //创建长度为12的list，并添加初始数据 {0, 0}
        ArrayList<int[]> arrayList = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            arrayList.add(new int[]{0, 0});
        }

        int i5 = ((int) calculateGongliToNongli(year, month, day)[5]) % 60;
        switch (i5 % 10) {
            case 0:
            case 5:
                for (int i = 0; i < 12; i++) {
                    ((int[]) arrayList.get(i))[0] = i;
                }
                break;
            case 1:
            case 6:
                for (int i7 = 0; i7 < 12; i7++) {
                    ((int[]) arrayList.get(i7))[0] = i7 + 12;
                }
                break;
            case 2:
            case 7:
                for (int i8 = 0; i8 < 12; i8++) {
                    ((int[]) arrayList.get(i8))[0] = i8 + 24;
                }
                break;
            case 3:
            case 8:
                for (int i9 = 0; i9 < 12; i9++) {
                    ((int[]) arrayList.get(i9))[0] = i9 + 36;
                }
                break;
            case 4:
            case 9:
                for (int i10 = 0; i10 < 12; i10++) {
                    ((int[]) arrayList.get(i10))[0] = i10 + 48;
                }
                break;
        }
        /**
         *
         */
        int[] iArr = NongLiData.shiChenJX[i5];
        for (int i = 0; i < 12; i++) {
            ((int[]) arrayList.get(i))[1] = iArr[i];
        }
        return arrayList;
    }

}
