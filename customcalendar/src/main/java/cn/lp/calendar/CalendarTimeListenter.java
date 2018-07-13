package cn.lp.calendar;

import java.util.Map;

/**
 * Created by jz on 2018/7/6.
 */

public interface CalendarTimeListenter {
    /**
     * @param data 存放的是选择的时间
     *             时间段选择：
     *             mStartData = data.get(KCalendarCofig.newInstance().StartTime);  开始时间
     *             mEndDate = data.get(KCalendarCofig.newInstance().EndTime); //结束时间
     *             单独时间：
     *             mStartData = data.get(KCalendarCofig.newInstance().StartTime);  用户选择的时间
     */
    void chooseDate(Map<String, String> data);
}
