package cn.lp.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CalendarTimeListenter {

    private Button mBtnOne;
    private Button mBtn_tow;
    private Button mBtn_three;
    private TextView mTvOneStartTime;
    private TextView mMTvOneEndTime;
    private TextView mTvTowStartTime;
    private TextView mTvTowEndTime;
    private TextView mTvThreeStartTime;
    private CalendarManger mCalendarMangerOne;
    private CalendarManger mCalendarMangerTow;
    private CalendarManger mCalendarMangerThree;
    int nowType = -1;
    long minTime = 1514736000 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBtnOne = findViewById(R.id.btn_one);
        mBtn_tow = findViewById(R.id.btn_tow);
        mBtn_three = findViewById(R.id.btn_three);
        mBtnOne.setOnClickListener(this);
        mBtn_tow.setOnClickListener(this);
        mBtn_three.setOnClickListener(this);

        mTvOneStartTime = findViewById(R.id.tv_one_start_time);
        mMTvOneEndTime = findViewById(R.id.tv_one_end_time);

        mTvTowStartTime = findViewById(R.id.tv_tow_start_time);
        mTvTowEndTime = findViewById(R.id.tv_tow_end_time);

        mTvThreeStartTime = findViewById(R.id.tv_three_start_time);
        initData();
    }

    private void initData() {
        String endDate = ExpressTimeUtils.getNowTime();
        String startData = ExpressTimeUtils.getNowTimeBefore(ExpressTimeUtils.noeDayMillisecond * 3);
        mTvOneStartTime.setText(startData);
        mMTvOneEndTime.setText(endDate);

        mTvTowStartTime.setText(startData);
        mTvTowEndTime.setText(endDate);

        mTvThreeStartTime.setText(startData);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                if (mCalendarMangerOne == null) {
                    mCalendarMangerOne = new CalendarManger();
                }
                nowType = 1;
                String startTime = mTvOneStartTime.getText().toString();
                String endTime = mMTvOneEndTime.getText().toString();
                mCalendarMangerOne.getTimeQuantum(this, minTime, startTime, endTime, this);
                break;
            case R.id.btn_tow:
                if (mCalendarMangerTow == null) {
                    mCalendarMangerTow = new CalendarManger();
                }
                nowType = 2;
                String startTimeTow = mTvTowStartTime.getText().toString();
                String endTimeTow = mTvTowEndTime.getText().toString();
                mCalendarMangerTow.getTimeQuantum(this, startTimeTow, endTimeTow, this);
                break;
            case R.id.btn_three:
                nowType = 3;
                if (mCalendarMangerThree == null) {
                    mCalendarMangerThree = new CalendarManger();
                }
                String startTimeThree = mTvThreeStartTime.getText().toString();
                mCalendarMangerThree.getTimePoint(this, minTime, startTimeThree, this);

                break;
        }
    }

    @Override
    public void chooseDate(Map<String, String> data) {
        String startData = data.get(KCalendarCofig.newInstance().StartTime);
        String endDate = data.get(KCalendarCofig.newInstance().EndTime);
        switch (nowType) {
            case 1:
                mTvOneStartTime.setText(startData);
                mMTvOneEndTime.setText(endDate);
                break;
            case 2:
                mTvTowStartTime.setText(startData);
                mTvTowEndTime.setText(endDate);
                break;
            case 3:
                mTvThreeStartTime.setText(startData);
                break;
        }

    }
}
