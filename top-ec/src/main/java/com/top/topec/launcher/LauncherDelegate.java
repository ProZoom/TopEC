package com.top.topec.launcher;

import android.icu.text.MeasureFormat;
import android.icu.text.MessageFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.top.top.Delegate.TopDelegate;
import com.top.top.utils.timer.BaseTimerTask;
import com.top.top.utils.timer.ITimerListener;
import com.top.topec.R;
import com.top.topec.R2;

import java.sql.Time;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：ProZoom
 * 时间：2018/5/2
 * 描述：
 */
public class LauncherDelegate extends TopDelegate implements ITimerListener {


    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView tvLauncherTimer;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {

    }


    int mCount = 5;

    private Timer timer = null;

    private void initTimer() {
        timer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        timer.schedule(task, 0, 1000);
    }


    Unbinder unbinder;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                if (timer != null) {
                    tvLauncherTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));

                    mCount--;
                    if (mCount < 0) {
                        if (timer != null) {
                            timer.cancel();
                            timer = null;
                        }
                    }
                }
            }
        });
    }
}
