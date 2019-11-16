package com.cdutcm.party.adapter;

import com.cdutcm.party.interceptor.LoginInterceptor;
import com.cdutcm.party.interceptor.RoleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/3/29 15:00 星期四
 * Description:
 */
@Configuration
public class LoginAdapter extends WebMvcConfigurerAdapter {
    @Autowired
    public RoleInterceptor roleInterceptor;//用户角色拦截器
    @Autowired
    public LoginInterceptor loginInterceptor;//用户登录拦截器

    //自定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //用户角色拦截器
        registry.addInterceptor(roleInterceptor).
                addPathPatterns("/**");
        //用户登录拦截器
        registry.addInterceptor(loginInterceptor).
                addPathPatterns("/**").
                excludePathPatterns("/error").
                excludePathPatterns("/").
                excludePathPatterns("/sendCode").
                excludePathPatterns("/register").
                excludePathPatterns("/retrieve").
                excludePathPatterns("/retrieve/save").
                excludePathPatterns("/login");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/imgs/**").addResourceLocations("file:imgs/");
        registry.addResourceHandler("/video/**").addResourceLocations("file:video/");
        super.addResourceHandlers(registry);
    }
}
