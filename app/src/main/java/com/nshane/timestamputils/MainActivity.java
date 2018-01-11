package com.nshane.timestamputils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_getTS)
    Button btnGetTS;
    @BindView(R.id.tv_timeStampShow)
    TextView tvTimeStampShow;
    @BindView(R.id.tv_currentTimeShow)
    TextView tvCurrentTimeShow;
    @BindView(R.id.tv_currentTimePeriod)
    TextView tvCurrentTimePeriod;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.tv_currentTimeZone)
    TextView tvCurrentTimeZone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_getTS)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getTS:
                getTimeStamp();
                break;
            default:
                break;
        }
    }


    /**
     * 若后台返回的时间戳是String类型:
     * Integer.parseInt(String s) Long.parseLong(String s)
     * Float.parseFloat(String s) Double.parseDouble(String s)
     */


    public void getTimeStamp() {
        Long tsLong = System.currentTimeMillis() / 1000;
        String ts = tsLong.toString();

        tvTimeStampShow.setText(ts);
        tvCurrentTimeShow.setText(formatData("yyyy-MM-dd HH:mm:ss", tsLong));

        /**
         * 只获取年月日
         */
//        tvCurrentTimeShow.setText(formatData("yyyy-MM-dd", tsLong));


        SimpleDateFormat sdfTwo = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒E",
                Locale.getDefault());

        long tsLong2 = System.currentTimeMillis();
        tvCurrentTimeZone.setText(sdfTwo.format(tsLong2));


        caculateTimeDifference(tsLong2);
        tvCurrentTimePeriod.setText(getString(time));

        Log.d("cg", "两个时间戳:" + ts + "---" + tsLong.toString() + "----" + tsLong2 + "----" + tsLong2 / 1000);

    }


    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }


    /**
     * 关于计算时间差,时间戳取决于后台的返回,一般为13位数
     */


    private int time;

    public void caculateTimeDifference(long uploadTime) {
        long current = System.currentTimeMillis();
        long difference = current - uploadTime;
        if (difference <= 1000 * 60 * 10) {
            time = R.string.just_recently;
        } else if (difference > 1000 * 60 * 10 && difference <= 1000 * 60 * 30) {
            time = R.string.half_hour;
        } else if (difference > 1000 * 60 * 30 && difference <= 1000 * 60 * 60) {
            time = R.string.one_hour;
        } else if (difference > 1000 * 60 * 60 && difference <= 1000 * 60 * 60 * 2) {
            time = R.string.two_hour;
        } else if (difference > 1000 * 60 * 60 * 2 && difference <= 1000 * 60 * 60 * 3) {
            time = R.string.three_hour;
        } else if (difference > 1000 * 60 * 60 * 3 && difference <= 1000 * 60 * 60 * 24) {
            time = R.string.today;
        } else if (difference > 1000 * 60 * 60 * 24 && difference <= 1000 * 60 * 60 * 24 * 2) {
            time = R.string.yestoday;
        } else if (difference > 1000 * 60 * 60 * 24 * 2 && difference <= 1000 * 60 * 60 * 24 * 3) {
            time = R.string.threedays;
        } else if (difference > 1000 * 60 * 60 * 24 * 3 && difference <= 1000 * 60 * 60 * 24 * 4) {
            time = R.string.fourdays;
        } else if (difference > 1000 * 60 * 60 * 24 * 4 && difference <= 1000 * 60 * 60 * 24 * 30L) {
            time = R.string.month;
        } else if (difference > 1000 * 60 * 60 * 24 * 30L) {
            time = R.string.month_ago;
        }
    }

}
