package com.top.top.Delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 作者：ProZoom
 * 时间：2018/4/18
 * 描述：
 */
public abstract class BaseDelegate extends SwipeBackFragment {

    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder=null;
    public abstract Object setLayout();


    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=null;

        if(setLayout() instanceof Integer){
            rootView=inflater.inflate((Integer) setLayout(),container,false);


        }else if(setLayout() instanceof View){
            rootView= (View) setLayout();
        }
        
        if (rootView!=null) {
            mUnbinder= ButterKnife.bind(this,rootView);
            onBindView(savedInstanceState,rootView);
        }


        return rootView;


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
    }
}