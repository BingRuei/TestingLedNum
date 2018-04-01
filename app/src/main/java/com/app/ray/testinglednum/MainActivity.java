package com.app.ray.testinglednum;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer mTimer;
    private LEDView mTime;
    private boolean flash = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTime = (LEDView) this.findViewById(R.id.nb_time);
        mTime.setLedView(80, "888888狂", "948794狂", flash);
        flash = !flash;

        // init timer
        mTimer = new Timer();
        // start timer task
        setTimerTask();
    }

    private void setTimerTask() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                doActionHandler.sendMessage(message);
            }
        }, 1000, 1000/* 表示1000毫秒之後，每隔1000毫秒執行一次 */);
    }

    /**
     * do some action
     */
    private Handler doActionHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int msgId = msg.what;
            switch (msgId) {
                case 1:
                    // do some action
                    mTime.setLedView(80, "888888狂", "948794狂", flash);
                    flash = !flash;
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void finish() {
        super.finish();
        mTimer.cancel();
    }
}
