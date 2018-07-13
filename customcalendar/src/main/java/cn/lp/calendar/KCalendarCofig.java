package cn.lp.calendar;

/**
 * Created by jz on 2017/5/4.
 */

public class KCalendarCofig {


    private static class Holder {
        private static KCalendarCofig mWebViewUtils = new KCalendarCofig();
    }


    private KCalendarCofig() {

    }

    public static KCalendarCofig newInstance() {
        return KCalendarCofig.Holder.mWebViewUtils;
    }


    //日历配置数据
    public String TimeSelectType = "1"; //1表示单选日期 2.表示双选日期

    public String StartTime = "StartTime";
    public String EndTime = "EndTime";

    public String nowTimeType = ""; //"" 表示没有选择日期或者是当前 "1"开始 “2”结束日期
    public final String nowTimeTypeStart = "1"; //"1"开始
    public final String nowTimeTypeEnd = "2"; // “2”结束日期

    public String choosStartData = ""; //开始时间
    public String choosEndData = ""; //结束时间

    public int StartSingBg = R.drawable.calendar_start_icon; //开始日期背景
    public int EndSingBg = R.drawable.calendar_end_icon; //结束背景
    public int oincideSingBg = R.drawable.calendar_oincide_icon; //相等背景

    public int OnceSingBg = R.drawable.calendar_oincide_icon; //单选背景

    public long minTime; //最小时间

    public String exceedHint = "查询范围不可早于注册时间";
    public String earlierThanHint = "查询范围不可超越当前时间";

}
