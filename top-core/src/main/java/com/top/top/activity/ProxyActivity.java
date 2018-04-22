package com.top.top.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.top.top.Delegate.TopDelegate;
import com.top.top.R;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作者：ProZoom
 * 时间：2018/4/18
 * 描述：
 */
public abstract class ProxyActivity extends SupportActivity {

    public abstract TopDelegate setRootDelegate();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContainer(savedInstanceState);
    }

    /**
     * Fragment容器
     *
     * @param saveInstanceState
     */
    private void initContainer(@Nullable Bundle saveInstanceState) {
        final ContentFrameLayout contentFrameLayout = new ContentFrameLayout(this);

        contentFrameLayout.setId(R.id.delegate_container);

        setContentView(contentFrameLayout);

        if (saveInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //垃圾回收
        System.gc();
        System.runFinalization();
    }
}
