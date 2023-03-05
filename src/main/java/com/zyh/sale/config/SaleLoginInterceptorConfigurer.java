package com.zyh.sale.config;

import com.github.pagehelper.PageHelper;
import com.zyh.sale.interceptor.LoginInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author zyh
 * @version 1.0
 * @description: TODO
 * @date 2023/2/3 14:22
 */
//注册拦截器
@Configuration //加载当前拦截器，并注册到容器中
public class SaleLoginInterceptorConfigurer implements WebMvcConfigurer {
    //将自定义拦截器进行注册
    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //自定义拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        //配置白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/register");
        patterns.add("/login");
        patterns.add("/userLogin");
        patterns.add("/index");
        patterns.add("/product");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");

        //完成拦截器注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**") //配置拦截路径
                .excludePathPatterns(patterns); //设置白名单
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //添加访问路径
        registry.addResourceHandler("E:/java_line/项目/sale/src/main/resources/static/images/user/**")
                //：添加的是映射后的真实路径，映射的真实路径末尾必须加 / ,不然映射不到，这个问题困扰了我半天, / 适用于 windows和linux
                .addResourceLocations("file:E:/java_line/项目/sale/src/main/resources/static/images/user/");
    }

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", "mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
