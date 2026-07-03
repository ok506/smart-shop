package guat.lxy.smartshop.demos.web.config;

import guat.lxy.smartshop.demos.web.security.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Resource
    private UserDetailServiceImpl userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用 NoOpPasswordEncoder 表示明文密码比对
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/index", true)
                .failureUrl("/login?error=true")
                .successHandler((request, response, authentication) -> {
                    System.out.println("✅✅✅ 登录成功: " + authentication.getName());
                    response.sendRedirect("/index");
                })
                .failureHandler((request, response, exception) -> {
                    System.out.println("❌❌❌ 登录失败: " + exception.getMessage());
                    response.sendRedirect("/login?error=true");
                })
                .permitAll()
        );

        http.authorizeRequests(auth -> auth
                .antMatchers("/static/**", "/css/**", "/js/**", "/img/**").permitAll()
                .antMatchers("/login", "/doLogin").permitAll()
                .antMatchers("/index").authenticated()
                .anyRequest().authenticated()
        );

        http.csrf().disable();
        return http.build();
    }
}