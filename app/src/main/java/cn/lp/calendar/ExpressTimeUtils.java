package cn.lp.calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/20.
 */

public class ExpressTimeUtils {

    public final  static long noeDayMillisecond = 86400000;

    public static void  getData(long timeMillis){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1=new Date(timeMillis);
        String t1=format.format(d1);
    }
    public static String getNowTime(){
        String timeStr = "";
        long time= System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date d1=new Date(time);
         timeStr=format.format(d1);
        return timeStr;
    }

    public static String getNowTimeBefore(long dayMillisecond){
        String timeStr = "";
        long time = System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        time = time - (dayMillisecond);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date d1=new Date(time);
         timeStr=format.format(d1);
        return timeStr;
    }

    public static String getTiemToDay(long dayMillisecond){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date d1=new Date(dayMillisecond);
        String t1=format.format(d1);
        return t1;
    }
    public static String getDate(long dayMillisecond){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1=new Date(dayMillisecond);
        String t1=format.format(d1);
        return t1;
    }

}
