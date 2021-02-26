package app.vp.cn.profession.timePicker;


/**
 * Created by pan on 2017/6/26.
 */

public interface ScheduleImp {
    /**
     * 选择日期联动
     * 1.获取总滑动的界面数据 count
     * 2.获取当前点年与月 及 相对与ViewPager中的位置
     * 3.年-起始年   月-起始月  A.当年 0  a.当月0 b.之前月 <0 c.之后月 >0
     *                  B.之前年 <0  选择年-当前年   + 选择月-当前月
     *                  C.之后年 >0  选择年-当前年   + 选择月-当前月
     */
    /**
     * @param year
     * @param month
     * @param day
     */
    void changeScheduleView(int year, int month, int day);

    /** 标记
     * @param year
     * @param month
     * @param day
     * @param taskState
     */
    void updateTaskHint(int year, int month, int day, TaskState taskState);
}
