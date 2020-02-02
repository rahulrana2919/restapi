/*
 * 2020.
 * Author: Rahul Rana
 */

package com.embl.restapi.security;

import com.embl.restapi.filters.JwtRequestFilter;
import com.embl.restapi.services.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired private MyUserDetailsService myUserDetailsService;

    @Autowired private JwtRequestFilter jwtRequestFilter;

    @Override protected void configure(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/users", "/h2-console").permitAll()
                .antMatchers("/authenticate")
                .permitAll().anyRequest().authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

    @Override @Bean public AuthenticationManager authenticationManagerBean()
            throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Bean public PasswordEncoder getPasswordEncoder()
    {
        //NoOpPasswordEncoder has been used for POC purposes
        return NoOpPasswordEncoder.getInstance();
    }
}