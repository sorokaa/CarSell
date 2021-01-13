package com.example.CarSell.config;

import com.example.CarSell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()
                    .antMatchers("/", "/main", "/registration", "/help", "/readMore", "/static/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/main")
                    .failureUrl("/login?error").permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                .and()
                    .csrf().disable();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }
}