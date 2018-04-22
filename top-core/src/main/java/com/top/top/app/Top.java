package com.top.top.app;

import android.content.Context;

import java.util.HashMap;

/**
 * 作者：ProZoom
 * 时间：2018/4/8
 * 描述：
 */
public final class Top {


    public static Configurator init(Context context){
        getConfigurator().getTopConfigs().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());

        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfigurations(Object key){
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplication(){
        return (Context) getConfigurator().getTopConfigs().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
