package com.example.formation.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource datasource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(datasource)
                .usersByUsernameQuery("select name , password, enabled from Users where name=?")
                .authoritiesByUsernameQuery("select name, role from Users where name=?");
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable();
         httpSecurity.headers().frameOptions().disable();
         httpSecurity.authorizeRequests()
                 .antMatchers("/h2/*").permitAll()
                 .antMatchers("/swagger*").permitAll()
                 .antMatchers("/Users*").authenticated()
                 .and()
                 .formLogin().permitAll();
                /*.antMatchers("/h2/*").permitAll()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .anyRequest().permitAll()
                .and().formLogin();*/


         return httpSecurity.build();
    }


    /*@Bean
    public UserDetailsService userDetailsService(){
        var admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .authorities("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(
                new User("user1","{noop}123", Collections.emptyList()),
                new User("user2","{bcrypt}"+new BCryptPasswordEncoder().encode("123"),Collections.emptyList()),
                admin


        );
    }*/




}
