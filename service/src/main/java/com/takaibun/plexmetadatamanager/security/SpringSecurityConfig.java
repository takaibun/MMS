package com.takaibun.plexmetadatamanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author takaibun
 */
@Configuration
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置安全过滤器链
     *
     * @param http 安全过滤器
     * @return 安全过滤器链
     * @throws Exception 异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 配置请求授权规则
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/login", "/signup", "/actuator/**").permitAll()
                .anyRequest().authenticated()
        );

        // 配置登录和注销功能
        http.formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/")
        ).logout((logout) -> logout.logoutSuccessUrl("/login")
        );

        // 配置跨域资源共享（CORS）支持
        http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configure(http));

        // 其他可能的配置包括：CSRF、JWT、OAuth2等

        return http.build();
    }
}
