package cn.lp.calendar;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by shequren on 2016/5/20.
 */
public class DialogChooseStartAndEndTime extends android.app.Dialog implements View.OnClickListener, KCalendar.OnCalendarClickListener, KCalendar.OnCalendarDateChangedListener {


    Context context;
    CalendarTimeListenter myInterface;
    private TextView mPopupwindowCalendarMonth;
    private LinearLayout mLlPopup;
    private Button mChoosNowDate;
    private TextView mTvLine;
    private KCalendar mCalendar;
    private Button mPopupwindowCalendarBtEnter;
    private RelativeLayout mPopupwindowCalendarLastMonth;
    private RelativeLayout mPopupwindowCalendarNextMonth;
    private Map<String, String> mTimeMap;
    private String mOnlyOneTimeNowDate;

    public DialogChooseStartAndEndTime(Context context, CalendarTimeListenter myInterface) {
        super(context, R.style.AlertDialogStyle);
        this.context = context;
        this.myInterface = myInterface;
    }


    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }


    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow_calendar);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.CalendarDialogAnimatyion);

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = getScreenWidth(getContext()) / 10 * 9;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);
        initView();
        setCanceledOnTouchOutside(false);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mLlPopup = findViewById(R.id.ll_popup);
        mLlPopup.startAnimation(AnimationUtils.loadAnimation(context,
                R.anim.push_bottom_in_1));
        mPopupwindowCalendarMonth = findViewById(R.id.popupwindow_calendar_month);
        mChoosNowDate = findViewById(R.id.choos_now_date);
        mTvLine = findViewById(R.id.tv_line);
        mCalendar = findViewById(R.id.popupwindow_calendar);
        mPopupwindowCalendarBtEnter = findViewById(R.id.popupwindow_calendar_bt_enter);

        //上月按钮监听
        mPopupwindowCalendarLastMonth = findViewById(R.id.popupwindow_calendar_last_month);
        //下月监听按钮
        mPopupwindowCalendarNextMonth = findViewById(R.id.popupwindow_calendar_next_month);
        mPopupwindowCalendarLastMonth.setOnClickListener(this);
        mPopupwindowCalendarNextMonth.setOnClickListener(this);

        mCalendar.setOnCalendarClickListener(this);
        mCalendar.setOnCalendarDateChangedListener(this);

        mChoosNowDate.setOnClickListener(this);
        mPopupwindowCalendarBtEnter.setOnClickListener(this);

        mTimeMap = new HashMap<>();
    }

    public void chooseSectionTime(long minTime, String startDate, String endDate) {
        KCalendarCofig.newInstance().TimeSelectType = "2";
        mTimeMap.clear();
        mPopupwindowCalendarMonth.setText(mCalendar.getCalendarYear() + "年"
                + mCalendar.getCalendarMonth() + "月");
        KCalendarCofig.newInstance().minTime = minTime;
        mChoosNowDate.setVisibility(View.VISIBLE);
        mTvLine.setVisibility(View.VISIBLE);

        if (!TextUtils.isEmpty(startDate) && !TextUtils.isEmpty(endDate)) {
            //根据用户已经选择的时间进行设置日历
            KCalendarCofig.newInstance().nowTimeType = KCalendarCofig.newInstance().nowTimeTypeEnd;
            int years = Integer.parseInt(startDate.substring(0,
                    startDate.indexOf("-")));
            int month = Integer.parseInt(startDate.substring(
                    startDate.indexOf("-") + 1, startDate.lastIndexOf("-")));
            mPopupwindowCalendarMonth.setText(years + "年" + month + "月");

            mCalendar.showCalendar(years, month);
            mCalendar.setCalendarDayBgColor(startDate, KCalendarCofig.newInstance().StartSingBg);
            mCalendar.showCalendar(years, month);
            mCalendar.setCalendarDayBgColor(endDate, KCalendarCofig.newInstance().EndSingBg);
            KCalendarCofig.newInstance().choosStartData = startDate;
            KCalendarCofig.newInstance().choosEndData = endDate;

            int type = compareDate(CalendarDateUtils.str2DateArriveDay(KCalendarCofig.newInstance().choosStartData),
                    CalendarDateUtils.str2DateArriveDay(KCalendarCofig.newInstance().choosEndData));

            if (type == 2) {
                mCalendar.setCalendarDayBgColor(startDate,
                        KCalendarCofig.newInstance().oincideSingBg);
            }

        } else if (!TextUtils.isEmpty(startDate)) {
            //用户仅选择了开始时间设置日历
            KCalendarCofig.newInstance().nowTimeType = KCalendarCofig.newInstance().nowTimeTypeStart;
            int years = Integer.parseInt(startDate.substring(0,
                    startDate.indexOf("-")));
            int month = Integer.parseInt(startDate.substring(
                    startDate.indexOf("-") + 1, startDate.lastIndexOf("-")));
            mPopupwindowCalendarMonth.setText(years + "年" + month + "月");
            mCalendar.showCalendar(years, month);
            mCalendar.setCalendarDayBgColor(startDate, KCalendarCofig.newInstance().StartSingBg);
            KCalendarCofig.newInstance().choosStartData = startDate;
        } else {
            //用户初次使用设置日历
            KCalendarCofig.newInstance().nowTimeType = "";
            String date = CalendarDateUtils.getNowTimeString();
            KCalendarCofig.newInstance().choosStartData = date;
            KCalendarCofig.newInstance().choosEndData = date;
        }

        mTimeMap.put(KCalendarCofig.newInstance().StartTime, KCalendarCofig.newInstance().choosStartData);
        mTimeMap.put(KCalendarCofig.newInstance().EndTime, KCalendarCofig.newInstance().choosEndData);
        if (!isShowing()) {
            show();
        }
    }

    /**
     * 选择区间日历
     *
     * @param startDate
     * @param endDate
     */
    public void chooseSectionTime(String startDate, String endDate) {
        KCalendarCofig.newInstance().TimeSelectType = "2";
        chooseSectionTime(-1, startDate, endDate);
    }

    /**
     * 选择单日期
     *
     * @param nowDate
     */
    public void chooseOnlyOneTime(String nowDate) {
        mOnlyOneTimeNowDate = nowDate;
        KCalendarCofig.newInstance().TimeSelectType = "1";
        chooseOnlyOneTime(nowDate, -1);
    }

    /**
     * 选择单日期
     *
     * @param nowDate
     * @param minTime
     */
    public void chooseOnlyOneTime(String nowDate, long minTime) {
        mTimeMap.clear();
        KCalendarCofig.newInstance().TimeSelectType = "1";
        KCalendarCofig.newInstance().minTime = minTime;

        mChoosNowDate.setVisibility(View.GONE);
        mTvLine.setVisibility(View.GONE);
        mPopupwindowCalendarMonth.setText(mCalendar.getCalendarYear() + "年"
                + mCalendar.getCalendarMonth() + "月");
        KCalendarCofig.newInstance().choosStartData = "";
        KCalendarCofig.newInstance().choosEndData = "";
        if (!TextUtils.isEmpty(nowDate)) {
            int years = Integer.parseInt(nowDate.substring(0,
                    nowDate.indexOf("-")));
            int month = Integer.parseInt(nowDate.substring(
                    nowDate.indexOf("-") + 1, nowDate.lastIndexOf("-")));
            mPopupwindowCalendarMonth.setText(years + "年" + month + "月");
            mCalendar.showCalendar(years, month);

            mCalendar.setCalendarDayBgColor(nowDate, KCalendarCofig.newInstance().OnceSingBg);

        } else {
            String date = CalendarDateUtils.getNowTimeString();
            KCalendarCofig.newInstance().choosStartData = date;
        }
        mTimeMap.put(KCalendarCofig.newInstance().StartTime, KCalendarCofig.newInstance().choosStartData);
        if (!isShowing()) {
            show();
        }
    }


    public int compareDate(Date d1, Date d2) {
        if (d1.getTime() > d2.getTime()) {
            System.out.println("dt1 在dt2后");
            return 1;
        } else if (d1.getTime() < d2.getTime()) {
            System.out.println("dt1在dt2前");
            return -1;
        } else {//相等
            return 2;
        }
    }

    @Override
    public void onClick(View v) {

        int i = v.getId();
        if (i == R.id.popupwindow_calendar_last_month) {
            /**
             * 点击上个月判断是否小于用户设置的最小时间的当前月份
             */
            if (KCalendarCofig.newInstance().minTime > 0) {
                if (CalendarDateUtils.compareMonth(KCalendarCofig.newInstance().minTime, mCalendar.getCalendarMonth(), mCalendar.getCalendarYear())) {
                    mCalendar.lastMonth();
                } else {
                    Toast.makeText(context, KCalendarCofig.newInstance().exceedHint, Toast.LENGTH_LONG).show();
                    return;
                }
            } else {
                mCalendar.lastMonth();
            }

        } else if (i == R.id.popupwindow_calendar_next_month) {
            /**
             * 判断下个月份是否大于当前时间所在月份
             */
            if (CalendarDateUtils.compareMonth(mCalendar.getCalendarMonth(), mCalendar.getCalendarYear())) {
                Toast.makeText(context, KCalendarCofig.newInstance().earlierThanHint, Toast.LENGTH_LONG).show();
                return;
            } else {
                mCalendar.nextMonth();
            }

            /**
             * 选择当天
             */
        } else if (i == R.id.choos_now_date) {
            if (myInterface != null) {
                String date = CalendarDateUtils.getNowTimeString();
                KCalendarCofig.newInstance().choosStartData = date;
                KCalendarCofig.newInstance().choosEndData = date;
                mTimeMap.put(KCalendarCofig.newInstance().StartTime, KCalendarCofig.newInstance().choosStartData);
                mTimeMap.put(KCalendarCofig.newInstance().EndTime, KCalendarCofig.newInstance().choosEndData);
                myInterface.chooseDate(mTimeMap);
            }
            if (isShowing()) {
                dismiss();
            }
        } else if (i == R.id.popupwindow_calendar_bt_enter) {
            /**
             * 选择确定
             */
            if (myInterface != null) {
                if (!TextUtils.isEmpty(KCalendarCofig.newInstance().TimeSelectType) && "2".equals(KCalendarCofig.newInstance().TimeSelectType)) {
                    if (myInterface != null) {
                        mTimeMap.put(KCalendarCofig.newInstance().StartTime, KCalendarCofig.newInstance().choosStartData);
                        mTimeMap.put(KCalendarCofig.newInstance().EndTime, KCalendarCofig.newInstance().choosEndData);
                        myInterface.chooseDate(mTimeMap);
                    }
                } else {
                    if (myInterface != null) {
                        if (TextUtils.isEmpty(KCalendarCofig.newInstance().choosStartData)) {
                            mTimeMap.put(KCalendarCofig.newInstance().StartTime, mOnlyOneTimeNowDate);
                        } else {

                            mTimeMap.put(KCalendarCofig.newInstance().StartTime, KCalendarCofig.newInstance().choosStartData);
                        }
                        myInterface.chooseDate(mTimeMap);
                    }
                }
            }
            if (isShowing()) {
                dismiss();
            }
        }
    }


    /**
     * 更新数据
     */
    private void updaterData() {
        KCalendarCofig.newInstance().choosStartData = "";
        KCalendarCofig.newInstance().choosEndData = "";

    }


    @Override
    public void show() {
        super.show();
    }


    /**
     * 点击日历的监听回调
     *
     * @param row
     * @param col
     * @param dateFormat
     */
    @Override
    public void onCalendarClick(int row, int col, String dateFormat) {
        if (!TextUtils.isEmpty(KCalendarCofig.newInstance().TimeSelectType) && "2".equals(KCalendarCofig.newInstance().TimeSelectType)) {
            chooseTimeSectionMode(row, col, dateFormat);
        } else {
            chooseOnlyOneTime(row, col, dateFormat);
        }
    }

    /**
     * 时间区间模式选择设置的点击时间的处理方法
     *
     * @param row
     * @param col
     * @param dateFormat
     */
    private void chooseTimeSectionMode(int row, int col, String dateFormat) {
        int month = Integer.parseInt(dateFormat.substring(
                dateFormat.indexOf("-") + 1,
                dateFormat.lastIndexOf("-")));

        if (mCalendar.getCalendarMonth() - month == 1//跨年跳转
                || mCalendar.getCalendarMonth() - month == -11) {
            if (KCalendarCofig.newInstance().minTime > 0) {
                if (CalendarDateUtils.compareMonth(KCalendarCofig.newInstance().minTime, mCalendar.getCalendarMonth(), mCalendar.getCalendarYear())) {
                    mCalendar.lastMonth();
                } else {
                    Toast.makeText(context, KCalendarCofig.newInstance().exceedHint, Toast.LENGTH_LONG).show();
                    return;
                }
            } else {
                mCalendar.lastMonth();
            }


        } else if (month - mCalendar.getCalendarMonth() == 1 //跨年跳转
                || month - mCalendar.getCalendarMonth() == -11) {
            if (CalendarDateUtils.compareMonth(mCalendar.getCalendarMonth(), mCalendar.getCalendarYear())) {
                Toast.makeText(context, KCalendarCofig.newInstance().earlierThanHint, Toast.LENGTH_LONG).show();
                return;
            } else {
                mCalendar.nextMonth();
            }

        } else {


            if (TextUtils.isEmpty(KCalendarCofig.newInstance().nowTimeType) || KCalendarCofig.newInstance().nowTimeTypeEnd.equals(KCalendarCofig.newInstance().nowTimeType)) {
                mCalendar.removeAllBgColor();
                mCalendar.setCalendarDayBgColor(dateFormat,
                        KCalendarCofig.newInstance().StartSingBg);
                KCalendarCofig.newInstance().choosStartData = dateFormat;
                KCalendarCofig.newInstance().choosEndData = "";
                KCalendarCofig.newInstance().nowTimeType = KCalendarCofig.newInstance().nowTimeTypeStart;

            } else if (KCalendarCofig.newInstance().nowTimeTypeStart.equals(KCalendarCofig.newInstance().nowTimeType)) {


                int type = compareDate(CalendarDateUtils.str2DateArriveDay(KCalendarCofig.newInstance().choosStartData),
                        CalendarDateUtils.str2DateArriveDay(dateFormat));

                if (type == 1) {

                    mCalendar.removeAllBgColor();
                    mCalendar.setCalendarDayBgColor(dateFormat,
                            KCalendarCofig.newInstance().StartSingBg);
                    KCalendarCofig.newInstance().choosStartData = dateFormat;
                    KCalendarCofig.newInstance().choosEndData = "";
                    KCalendarCofig.newInstance().nowTimeType = KCalendarCofig.newInstance().nowTimeTypeStart;


                } else if (type == -1) {
                    mCalendar.setCalendarDayBgColor(dateFormat,
                            KCalendarCofig.newInstance().EndSingBg);
                    KCalendarCofig.newInstance().choosEndData = dateFormat;
                    KCalendarCofig.newInstance().nowTimeType = KCalendarCofig.newInstance().nowTimeTypeEnd;
                } else if (type == 2) {
                    mCalendar.setCalendarDayBgColor(dateFormat,
                            KCalendarCofig.newInstance().oincideSingBg);
                    KCalendarCofig.newInstance().choosEndData = dateFormat;
                    KCalendarCofig.newInstance().nowTimeType = KCalendarCofig.newInstance().nowTimeTypeEnd;
                }
            }
        }
    }

    private void chooseOnlyOneTime(int row, int col, String dateFormat) {
        int month = Integer.parseInt(dateFormat.substring(
                dateFormat.indexOf("-") + 1,
                dateFormat.lastIndexOf("-")));

        if (mCalendar.getCalendarMonth() - month == 1//跨年跳转
                || mCalendar.getCalendarMonth() - month == -11) {
            if (KCalendarCofig.newInstance().minTime > 0) {
                if (CalendarDateUtils.compareMonth(KCalendarCofig.newInstance().minTime, mCalendar.getCalendarMonth(), mCalendar.getCalendarYear())) {
                    mCalendar.lastMonth();
                } else {
                    Toast.makeText(context, KCalendarCofig.newInstance().exceedHint, Toast.LENGTH_LONG).show();
                    return;
                }
            } else {
                mCalendar.lastMonth();
            }


        } else if (month - mCalendar.getCalendarMonth() == 1 //跨年跳转
                || month - mCalendar.getCalendarMonth() == -11) {
            if (CalendarDateUtils.compareMonth(mCalendar.getCalendarMonth(), mCalendar.getCalendarYear())) {

                Toast.makeText(context, KCalendarCofig.newInstance().earlierThanHint, Toast.LENGTH_LONG).show();
                return;
            } else {
                mCalendar.nextMonth();
            }

        } else {
            mCalendar.removeAllBgColor();
            mCalendar.setCalendarDayBgColor(dateFormat,
                    KCalendarCofig.newInstance().OnceSingBg);
            KCalendarCofig.newInstance().choosStartData = dateFormat;

        }
    }

    /**
     * 选中当前月份的回调
     *
     * @param year
     * @param month
     */
    @Override
    public void onCalendarDateChanged(int year, int month) {
        mPopupwindowCalendarMonth
                .setText(year + "年" + month + "月");
    }

}
