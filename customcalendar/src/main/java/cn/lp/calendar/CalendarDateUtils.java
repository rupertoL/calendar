package cn.lp.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jz on 2017/4/7.
 */

public class CalendarDateUtils {
    public static Date str2Date(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getNowTimeString() {
        String timeStr = "";
        long time = System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = new Date(time);
        timeStr = format.format(d1);
        return timeStr;
    }


    public static Date getNowTime() {
        long time = System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        Date d1 = new Date(time);
        return d1;
    }


    //格式化成道天的日期
    public static Date str2DateArriveDay(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 将Date型转换成指定格式的时间字符串
     */
    public static String date2Str(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(date);
    }

    /**
     * 如果d1在d2后则返回true，否则返回false
     */
    public static boolean greater(Date d1, Date d2) {
        //如果compareTo返回0，表示两个日期相等，返回小于0的值，表示d1在d2之前，大于0表示d1在d2之后
        return d1.compareTo(d2) > 0;
    }

    public static int getTimeMonth(Date date) {
        //如果compareTo返回0，表示两个日期相等，返回小于0的值，表示d1在d2之前，大于0表示d1在d2之后
        return date.getMonth();
    }

    public static int getTimeYear(Date date) {
        int year = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * date1>date2的月份返回ture 否则返回false
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareMonth(Date date1, Date date2) {
        int oneMonth = getTimeMonth(date1);
        int towMonth = getTimeMonth(date2);
        if (oneMonth > towMonth) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * date1的月份>Month的月份返回ture 否则返回false
     *
     * @param date1
     * @param Month
     * @return
     */
    public static boolean compareMonth(Date date1, int Month) {
        int oneMonth = getTimeMonth(date1);
        if (oneMonth >= Month) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 当前时间月份<Month的月份返回ture 否则返回false
     *
     * @param Month
     * @return
     */
    public static boolean compareMonth(int Month, int year) {
        Date date = getNowTime();
        int oneMonth = getTimeMonth(date) + 1;
        int oneYear = getTimeYear(date);

        if (year == oneYear) {
            if (oneMonth > Month) {
                return false;
            } else {
                return true;
            }
        } else if (oneYear > year) {
            return false;
        } else {
            return true;

        }


    }

    /**
     * 当前时间月份>Month的月份返回ture 否则返回false
     *
     * @param Month
     * @return
     */
    public static boolean compareMonth(String dateStr, int Month) {
        Date date = StrToDate(dateStr);
        int oneMonth = getTimeMonth(date);
        if (oneMonth >= Month) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 当前时间月份>Month的月份返回ture 否则返回false
     *
     * @param Month
     * @return
     */
    public static boolean compareMonth(long millisecond, int Month, int year) {
        Date date = getTimeFromeString((millisecond));
        int oneMonth = getTimeMonth(date);
        int oneYear = getTimeYear(date);
        if (year == oneYear) {
            if (Month > oneMonth) {
                return true;
            } else {
                return false;
            }
        } else if (oneYear < year) {
            return true;
        } else {
            return false;
        }


    }


    public static Date getTimeFromeString(long millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond);
        return calendar.getTime();
    }


    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
