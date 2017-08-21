package net.brian.web.italker.push.service;

import net.brian.web.italker.push.bean.db.User;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by with_you on 2017/6/8.
 */
public class BaseService {
    //添加一个上下文注解，该注解会给securityContext赋值
    //具体的值为我们的拦截器中所返回的securityContext
    @Context
    protected SecurityContext securityContext;

    /**
     * //从上下文中直接获取自己信息
     * @return
     */
    protected User getSelf(){
        return (User) securityContext.getUserPrincipal();
    }
}
