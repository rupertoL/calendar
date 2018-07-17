package cn.lp.calendar;

/**
 * Created by jz on 2018/7/17.
 */

public class DateBean {

    /**
     * 日期选择方式
     */

    public int nowType = -1;


    /**
     * 当前选择的是开始时间还是结束时间
     */
    protected int nowTimeType = -1; //"" 表示没有选择日期或者是当前 "1"开始 “2”结束日期


    /**
     * 时间值
     */
    public String choosStartData = ""; //开始时间
    public String choosEndData = ""; //结束时间

    public long minTime; //最小时间

}
