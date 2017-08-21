package net.brian.web.italker.push;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import net.brian.web.italker.push.provider.AuthRequestFilter;
import net.brian.web.italker.push.provider.GsonProvider;
import net.brian.web.italker.push.service.AccountService;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Logger;

/**
 * @author brian
 */
public class Application extends ResourceConfig {

    public Application() {
        //注册逻辑处理的包名
        //packages("net.brian.web.italker.push.service");
        packages(AccountService.class.getPackage().getName());

        //注册我们的全局请求拦截器
        register(AuthRequestFilter.class);

        //注册json解析器
        //register(JacksonJsonProvider.class);
        //替换解析器为Gson
        register(GsonProvider.class);

        //注册日志打印输出
        register(Logger.class);
    }

}
