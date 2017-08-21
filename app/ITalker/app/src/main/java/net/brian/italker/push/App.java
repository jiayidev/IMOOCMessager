package net.brian.italker.push;

import com.igexin.sdk.PushManager;

import net.brian.italker.common.app.Application;
import net.brian.italker.factory.Factory;

/**
 * Created by with_you on 2017/5/31.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 调用Factory进行初始化
        Factory.setup();
        // 推送进行初始化
        PushManager.getInstance().initialize(this);
    }
}
