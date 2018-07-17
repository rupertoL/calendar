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

    final int singleType = 1;
    final int doubleType = 2;

    protected final int nowTimeTypeStart = 101; //"1"开始
    protected final int nowTimeTypeEnd = 102; // “2”结束日期

    public int StartSingBg = R.drawable.calendar_start_icon; //开始日期背景
    public int EndSingBg = R.drawable.calendar_end_icon; //结束背景
    public int oincideSingBg = R.drawable.calendar_oincide_icon; //相等背景

    public int OnceSingBg = R.drawable.calendar_oincide_icon; //单选背景


    public String exceedHint = "查询范围不可早于注册时间";
    public String earlierThanHint = "查询范围不可超越当前时间";

}
