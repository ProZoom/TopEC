package com.top.top.utils.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者：ProZoom
 * 时间：2018/5/2
 * 描述：
 */
public class BaseTimerTask extends TimerTask {


    private ITimerListener mITimerListener=null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if(mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}
