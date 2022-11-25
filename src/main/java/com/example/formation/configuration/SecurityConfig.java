package com.example.formation.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.Locale;

@Configuration
@EnableWebSecurity

public class SecurityConfig {



    @Autowired
    private DataSource datasource;





    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultLocale(Locale.FRENCH);
        messageSource.addBasenames("org/springframework/security/messages"); // my messages will override spring security messages, if message code the same
        return messageSource;
    }
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
                 //.antMatchers("/Users*").permitAll()
                 .and()
                 .formLogin()
                 //.permitAll();
                 //to filter the single authentication
                 .and()
                 .httpBasic()
                 .and()
                 .sessionManagement()
                 .maximumSessions(1)
                 .expiredUrl("/expired")
                 .maxSessionsPreventsLogin(true)
                 .sessionRegistry(sessionRegistry())
                 .and()
                 .and();
         return httpSecurity.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
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
