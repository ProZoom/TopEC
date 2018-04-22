package com.top.top.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 作者：ProZoom
 * 时间：2018/4/3
 * 描述：
 */
public class Configurator {


    private static final HashMap<Object,Object> TOP_CONFIGS=new HashMap<>();

    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList<>();

    public Configurator() {
        TOP_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);

    }

    /**
     * 线程安全的懒汉模式
     * @return
     */
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }


    final HashMap<Object,Object> getTopConfigs(){
        return TOP_CONFIGS;
    }

    private static class Holder{

        private static final Configurator INSTANCE=new Configurator();

    }

    public final void configure(){

        initIcons();
        TOP_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }


    public final Configurator withApiHost(String host){
        TOP_CONFIGS.put(ConfigType.API_HOST,host);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady= (boolean) TOP_CONFIGS.get(ConfigType.CONFIG_READY.name());

        if(!isReady){
            throw new RuntimeException("Configuration is not ready,call configuration!");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key){
        checkConfiguration();
        return (T) TOP_CONFIGS.get(key);
    }



    private void initIcons(){
        if(ICONS.size()>0){
            final Iconify.IconifyInitializer iconifyInitializer= Iconify.with(ICONS.get(0));
            for (int i=0;i<ICONS.size();i++){
                iconifyInitializer.with(ICONS.get(i));
            }
        }
    }


    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }
}
