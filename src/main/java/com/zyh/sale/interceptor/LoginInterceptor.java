package com.zyh.sale.interceptor;

import com.zyh.sale.pojo.User;
import com.zyh.sale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/2/3 14:45
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {
        Object obj = request.getSession().getAttribute("uid");
        if (obj==null){ //判断是否有uid，若没有则返回登录页面，若有则进入页面
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

}
