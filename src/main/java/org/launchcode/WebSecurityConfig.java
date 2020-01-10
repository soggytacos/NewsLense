package org.launchcode;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
This configuration creates a Servlet Filter called springSecurityFilterChain, responsible for protecting the application
URLs and validating credential submission. refer to javatpoint.com/spring-security-java-example. This Java configuration
requires authentication for every URL, creates a login form, allows user to authenticate using form based authentication
allows logout, prevents CSRF attacks, and security header integration.
 */
@EnableWebSecurity
@ComponentScan("org.launchcode")
public class WebSecurityConfig implements WebMvcConfigurer {

    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("administrator").password("admin123").roles("USER").build());
        return manager;
    }

    protected void configure(HttpSecurity http) throws Exception {

        http
                .antMatcher("/")
                .authorizeRequests()
                .anyRequest().hasRole("ADMIN")
                .and()
                .httpBasic();
    }
}
