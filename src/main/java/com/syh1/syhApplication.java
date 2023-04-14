package com.syh1;

import com.syh1.jwt.JwtAuthenticationFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@MapperScan("com.syh1.mapper")//指定mapper扫描位置
@SpringBootApplication//@SpringBootApplication。这个注解被称为元注解，它结合了 @SpringBootConfiguration、@EnableAutoConfiguration 和 @ComponentScan。
//@EnableAutoConfiguration 告诉Spring Boot根据你添加的jar依赖项 "猜测" 你想如何配置Spring。由于 spring-boot-starter-web 添加了Tomcat和Spring MVC，自动配置会假定你正在开发一个Web应用，并相应地设置Spring。
//SpringApplication 引导我们的应用程序启动Spring，而Spring又会启动自动配置的Tomcat网络服务器。
public class syhApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {//固定用法
        return builder.sources(syhApplication.class);
    }
    @Bean
    public FilterRegistrationBean jwtFilter() {//jwt过滤器
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();//注册filter对象
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();//JwtAuthenticationFliter中的方法
        registrationBean.setFilter(filter);
        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(syhApplication.class, args);
    }
    //main 方法。 这是一个标准的Java入口方法，也就是应用的启动方法。 我们的main方法通过调用 run 方法，把应用委托给Spring Boot的 SpringApplication 类。
    //我们需要将 MyApplication.class 作为参数传递给 run 方法，以告诉 SpringApplication 哪个是主要的Spring组件。 args 数组也被传入，这是命令行参数。
}

