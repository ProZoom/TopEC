package com.top.topec;

import com.top.top.Delegate.TopDelegate;
import com.top.top.activity.ProxyActivity;
import com.top.topec.launcher.LauncherDelegate;

/**
 * 作者：ProZoom
 * 时间：2018/6/4
 * 描述：
 */
public class ExampleActivity extends ProxyActivity {

    @Override
    public TopDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
