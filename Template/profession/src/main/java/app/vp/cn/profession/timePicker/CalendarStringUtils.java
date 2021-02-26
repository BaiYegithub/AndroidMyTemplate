package app.vp.cn.profession.timePicker;

/**
 * 日历文本集
 */
public class CalendarStringUtils {
    /**
     * Direction方向/方位
     */
    public static String[] Direction = {"东", "北", "西", "南"};
    /**
     * Chinese zodiac 十二生肖
     */
    public static final String[] ZODIAC = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    /**
     * Heavenly Stems 天干
     */
    public static final String[] HEAVENLY_STEMS = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    /**
     * Earthly Branches 地支
     */
    public static final String[] EARTHLY_BRANCHES = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    /**
     * 白话文模块
     */
    public static String[] ALMANAC_MODERN_CHINESE = {"查看白话文", "宜忌", "冲煞", "值神", "五行", "吉神宜驱", "凶神宜忌", "今日胎神", "彭祖百忌", "建除十二神", "二十八星宿"};
    /**
     * Pengzu hundred dread Heavenly Stems 彭祖百忌-天干
     */
    public static final String[] PENG_ZU_HEAVENLY = {
            "甲不开仓财物耗亡", "乙不栽植千株不长", "丙不修灶必见灾殃", "丁不剃头头主生疮", "戊不受田田主不祥",
            "己不破券二比并亡", "庚不经络织机虚张", "辛不合酱主人不尝", "壬不决水更难提防", "癸不词讼理弱敌强",
    };
    /**
     * Pengzu hundred dread Earthly Branches 彭祖百忌-地支
     */
    public static final String[] PENG_ZU_EARTHLY = {
            "子不问卜自惹祸殃", "丑不冠带主不还乡", "寅不祭祀神鬼不尝", "卯不穿井水泉不香",
            "辰不哭泣必主重丧", "巳不远行财物伏藏", "午不苫盖屋主更张", "未不服药毒气入肠",
            "申不安床鬼祟入房", "酉不宴客醉坐颠狂", "戌不吃犬作怪上床", "亥不嫁娶不利新郎"
    };

    //    /* five elements 五行*/
//    private static final String[] FIVE_ELEMENTS = {
//            "海中", "炉中火", "大林木", "路旁土", "剑锋金", "山头火", "涧下水", "城头土", "白蜡金", "杨柳木",
//            "泉中水", "屋上土", "霹雳火", "松柏木", "长流水", "砂石金", "山下火", "平地木", "壁上土", "金箔金",
//            "灯头火", "天河水", "大驿土", "钗钏金", "桑柘木", "大溪水", "沙中土", "天上火", "石榴木", "大海水"
//    };
    /**
     * five elements 五行
     */
    public static final String[] FIVE_ELEMENTS = {
            "海中", "炉中", "大林", "路旁", "剑锋", "山头", "涧下", "城头", "白蜡", "杨柳",
            "井泉", "屋上", "霹雳", "松柏", "长流", "沙石", "山下", "平地", "壁上", "金箔",
            "覆灯", "天河", "大驿", "钗钏", "桑拓", "大溪", "沙中", "天上", "石榴", "大海"
    };
    /**
     * 12神职
     * twelve duty
     */
    public static final String[] TWELVE_DURY = {
            "开", "闭", "建", "除", "满", "平", "定", "执", "破", "危", "成", "收"
    };
    /**
     * 12神职
     * twelve duty
     */
    public static final String[] TWELVE_DURY_TWO = {
            "建", "除", "满", "平", "定", "执", "破", "危", "成", "收", "开", "闭"
    };
    /**
     * 值神
     */
    public static final String[] ZHI_SHEN = {"青龙", "明堂", "天刑", "朱雀", "金匮", "天德", "白虎", "玉堂", "天牢", "玄武", "司命", "勾陈"};

    /**
     * fetus god position in Heavenlu Stems 胎神位置-天干
     */
    public static final String[] FETUS_GOD_HEAVENLY = {"占门", "碓磨", "厨灶", "仓库", "房床"};

    /**
     * fetus god position in Earthly Braches 胎神位置-地支
     */
    public static final String[] FETUS_GOD_EARTHLY = {"碓", "厕", "炉", "门", "栖", "床"};

    /**
     * solar terms 24节气
     */
    public static final String[] SOLAR_TERM = {
            "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨",
            "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑",
            "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"
    };

    /**
     * 周
     */
    public String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    /* day of week in Chinese 中文星期*/
    private static final String[] DAY_OF_WEEK_IN_CHINESE = {
            "日", "一", "二", "三", "四", "五", "六",
    };
    //
//    /* all the solar holidays 所有的阳历节日*/
//    private final Holiday[] mSolarHolidays = new Holiday[]{
//            new Holiday(1, 1, "元旦节"),
//            new Holiday(2, 14, "情人节"),
//            new Holiday(3, 8, "妇女节"),
//            new Holiday(3, 12, "植树节"),
//            //new Holiday(3, 15, "消费者权益日"),
//            //new Holiday(3, 21, "世界森林日"),
//            new Holiday(4, 1, "愚人节"),
//            //new Holiday(4, 7, "世界卫生日"),
//            //new Holiday(4, 22, "世界地球日"),
//            new Holiday(5, 1, "劳动节"),
//            new Holiday(5, 4, "青年节"),
//            //new Holiday(5, 31, "世界无烟日"),
//            new Holiday(6, 1, "儿童节"),
//            new Holiday(6, 26, "禁毒日"),
//            new Holiday(7, 1, "建党节"),
//            new Holiday(8, 1, "建军节"),
//            new Holiday(8, 15, "抗战胜利"),
//            new Holiday(9, 10, "教师节"),
//            //new Holiday(9, 28, "孔子诞辰"),
//            new Holiday(10, 1, "国庆节"),
//            //new Holiday(12, 20, "澳门回归"),
//            new Holiday(12, 24, "平安夜"),
//            new Holiday(12, 25, "圣诞节"),
//    };
//    /* all the lunar holidays 所有的农历假日*/
//    private final Holiday[] mLunarHolidays = new Holiday[]{
//            new Holiday(1, 1, "春节"),
//            new Holiday(1, 15, "元宵节"),
//            new Holiday(5, 5, "端午节"),
//            new Holiday(7, 7, "七夕节"),
//            new Holiday(7, 15, "中元节"),
//            new Holiday(8, 15, "中秋节"),
//            new Holiday(9, 9, "重阳节"),
//            new Holiday(12, 8, "腊八节"),
//            new Holiday(12, 23, "北方小年"),
//            new Holiday(12, 24, "南方小年")
//    };

}

