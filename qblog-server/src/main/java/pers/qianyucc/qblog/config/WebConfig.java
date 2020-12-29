package pers.qianyucc.qblog.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import pers.qianyucc.qblog.interceptor.*;

/**
 * @author lijing
 * @e-mail 1413979079@qq.com
 * @date 2020-06-28 22:49
 * @description Web 配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;

    /**
     * 解决 swagger静态资源无法访问的问题
     *
     * @param registry 资源处理程序注册表
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/auth/**");
    }
}
