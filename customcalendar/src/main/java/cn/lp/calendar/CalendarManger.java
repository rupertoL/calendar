package cn.lp.calendar;

import android.content.Context;


/**
 * Created by jz on 2017/5/4.
 */

public class CalendarManger {

    private DialogChooseStartAndEndTime mChooseStartAndEndTime;


    private static class Holder {
        private static CalendarManger mAccountData = new CalendarManger();
    }


    private CalendarManger() {

    }

    public static CalendarManger newInstance() {
        return CalendarManger.Holder.mAccountData;
    }

    /**
     * 销毁弹窗
     */
    public void dismissDialog() {

        if (mChooseStartAndEndTime != null) {
            if (mChooseStartAndEndTime.isShowing()) {
                mChooseStartAndEndTime.dismiss();
            }
            mChooseStartAndEndTime = null;
        }
    }

    /**
     * 设置早于最低时间提示
     *
     * @param exceedHint
     */
    public void setExceedHint(String exceedHint) {

        KCalendarCofig.newInstance().exceedHint = exceedHint;
    }

    /**
     * 设置超过当前时间提示
     *
     * @param earlierThanHint
     */
    public void setEarlierThanHint(String earlierThanHint) {

        KCalendarCofig.newInstance().earlierThanHint = earlierThanHint;
    }


    /**
     * 开始日期背景
     *
     * @param drawableResId
     */
    public void setStartTimeBg(int drawableResId) {

        KCalendarCofig.newInstance().StartSingBg = drawableResId;
    }

    /**
     * 结束日期背景
     *
     * @param drawableResId
     */
    public void setEndTimeBg(int drawableResId) {

        KCalendarCofig.newInstance().EndSingBg = drawableResId;
    }

    /**
     * 结束开启同一天
     *
     * @param drawableResId
     */
    public void setStartAndEndIdentical(int drawableResId) {

        KCalendarCofig.newInstance().oincideSingBg = drawableResId;
    }

    /**
     * 选择单日期时间背景
     *
     * @param drawableResId
     */
    public void setOneDayBg(int drawableResId) {
        KCalendarCofig.newInstance().OnceSingBg = drawableResId;
    }

    /**
     * @param startDrawableResId                开始图片
     * @param endDrawableResId                  结束背景
     * @param startAndEndIdenticalDrawableResId 同一天背景
     */
    public void setectionModeBg(int startDrawableResId, int endDrawableResId, int startAndEndIdenticalDrawableResId) {

        setStartTimeBg(startDrawableResId);
        setEndTimeBg(endDrawableResId);
        setStartAndEndIdentical(startAndEndIdenticalDrawableResId);
    }


    public void getTimeQuantum(Context context, DateBean dateBean, CalendarTimeListenter calendarTimeListenter) {
        if (mChooseStartAndEndTime == null) {
            mChooseStartAndEndTime = new DialogChooseStartAndEndTime(context, calendarTimeListenter);
            mChooseStartAndEndTime.show();
        }
        mChooseStartAndEndTime.chooseSectionTime(dateBean);
    }


    /**
     * 区间选择时间端并且没有最小时间
     *
     * @param context
     * @param startDate
     * @param endDate
     * @param calendarTimeListenter
     */
    public void getTimeQuantum(Context context, String startDate, String endDate, CalendarTimeListenter calendarTimeListenter) {
        getTimeQuantum(context, -1, startDate, endDate, calendarTimeListenter);
    }

    /**
     * 区间选择时间端并且指定最小时间
     *
     * @param context
     * @param minTime
     * @param startDate
     * @param endDate
     * @param calendarTimeListenter
     */
    public void getTimeQuantum(Context context, long minTime, String startDate, String endDate, CalendarTimeListenter calendarTimeListenter) {
        if (mChooseStartAndEndTime == null) {
            mChooseStartAndEndTime = new DialogChooseStartAndEndTime(context, calendarTimeListenter);
            mChooseStartAndEndTime.show();
        }
        mChooseStartAndEndTime.chooseSectionTime(minTime, startDate, endDate);
    }


    /**
     * 选择一个日期
     *
     * @param context
     * @param minTime
     * @param startDate
     * @param calendarTimeListenter
     */
    public void getTimePoint(Context context, long minTime, String startDate, CalendarTimeListenter calendarTimeListenter) {
        if (mChooseStartAndEndTime == null) {
            mChooseStartAndEndTime = new DialogChooseStartAndEndTime(context, calendarTimeListenter);
            mChooseStartAndEndTime.show();
        }
        mChooseStartAndEndTime.chooseOnlyOneTime(startDate, minTime);
    }

    public void getTimePoint(Context context, DateBean dateBean, CalendarTimeListenter calendarTimeListenter) {
        if (mChooseStartAndEndTime == null) {
            mChooseStartAndEndTime = new DialogChooseStartAndEndTime(context, calendarTimeListenter);
            mChooseStartAndEndTime.show();
        }
        mChooseStartAndEndTime.chooseOnlyOneTime(dateBean);
    }


}
