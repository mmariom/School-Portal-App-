package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig   {


   @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

//        http.authorizeHttpRequests().anyRequest().permitAll()
//                .and().formLogin()
//                .and().httpBasic();
//
//        return http.build();




       http.csrf().ignoringAntMatchers("/saveMsg").ignoringAntMatchers("/public/**")
               .and()
               .authorizeRequests()
               .mvcMatchers("/home").permitAll()
               .mvcMatchers("/dashboard").authenticated()
               .mvcMatchers("/displayProfile").authenticated()
               .mvcMatchers("/updateProfile").authenticated()
               .mvcMatchers("/displayMessages").hasRole("ADMIN")
               .mvcMatchers("/admin/**").hasRole("ADMIN")
               .mvcMatchers("/holidays/**").permitAll()
               .mvcMatchers("/contact").permitAll()
               .mvcMatchers("/saveMsg").permitAll()
               .mvcMatchers("/courses").permitAll()
               .mvcMatchers("/about").permitAll()
               .mvcMatchers("/login").permitAll()
               .mvcMatchers("/public/**").permitAll()
               .and().formLogin().loginPage("/login")
               .defaultSuccessUrl("/dashboard")
               .failureUrl("/login?error=true").permitAll()
               .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
               .and().httpBasic();






       return http.build();



    }
    @Bean
    public PasswordEncoder passwordEncoder(){
       return  new BCryptPasswordEncoder();
    }

//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("12345")
//                .roles("USER")
//                .build();
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("54321")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }







    }
