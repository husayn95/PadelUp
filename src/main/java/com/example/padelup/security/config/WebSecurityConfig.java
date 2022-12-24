package com.example.padelup.security.config;

import com.example.padelup.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   private final AppUserService appUserService;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;
   @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider() {
      DaoAuthenticationProvider provider =
              new DaoAuthenticationProvider();
      provider.setPasswordEncoder(bCryptPasswordEncoder);
      provider.setUserDetailsService(appUserService);
      return provider;
   }
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
              .csrf().disable()
              .authorizeRequests()
              .antMatchers("/api/v1/registeruser/**")
              .permitAll()
              .anyRequest()
              .authenticated().and()
              .formLogin();
   }
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.authenticationProvider(daoAuthenticationProvider());
   }

}