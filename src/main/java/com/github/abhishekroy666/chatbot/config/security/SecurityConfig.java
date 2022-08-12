package com.github.abhishekroy666.chatbot.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Abhishek Roy
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Order(1)
    @Configuration
    public static class ApiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        private static final String[] UNAUTHENTICATED = {"/api/chat", "/api/authenticate"};

        @Autowired
        private UserDetailsService jwtUserDetailsService;

        @Autowired
        private JwtRequestFilter jwtRequestFilter;

        @Autowired
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            // configure AuthenticationManager so that it knows from where to load
            // user for matching credentials
            // Use BCryptPasswordEncoder
            authenticationManagerBuilder.userDetailsService(this.jwtUserDetailsService).passwordEncoder(passwordEncoder());
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .antMatcher("/api/**").cors()
                    .and().csrf()
                    .and().authorizeRequests()
                    .antMatchers(HttpMethod.POST, UNAUTHENTICATED).permitAll()
                    .anyRequest().authenticated()
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
                    .and().addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }

    }

    @Order(2)
    @Configuration
    public static class FormWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        private static final String[] UNAUTHENTICATED = {"/chat"};

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .antMatcher("/**").csrf().ignoringAntMatchers("/h2-console/**")
                    .and().headers().frameOptions().sameOrigin()
                    .and()
                    .authorizeRequests()
                    .antMatchers(UNAUTHENTICATED).permitAll()
                    .anyRequest().authenticated()
                    .and().formLogin().permitAll()
                    .and().logout().permitAll()
                    .and().exceptionHandling().accessDeniedPage("/403");
        }
    }
}
