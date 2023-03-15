package com.ryzhov_andrei.spring_security_rest_api_app.config;


import com.ryzhov_andrei.spring_security_rest_api_app.security.jwt.JwtConfigurer;
import com.ryzhov_andrei.spring_security_rest_api_app.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final JwtTokenProvider jwtTokenProvider;

    private static final String AUTH_ENDPOINT = "/api/v1/auth/**";
    private static final String USERS_ENDPOINT = "/api/v1/users/**";
    private static final String FILES_ENDPOINT = "/api/v1/files/**";
    private static final String EVENTS_ENDPOINT = "/api/v1/events/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_ENDPOINT).permitAll()
                //----------------------------------------------------------------
                .antMatchers(HttpMethod.GET, USERS_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, USERS_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, USERS_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, USERS_ENDPOINT).hasRole("ADMIN")
                //----------------------------------------------------------------
                .antMatchers(HttpMethod.GET, FILES_ENDPOINT).hasRole("USER")
                .antMatchers(HttpMethod.POST, FILES_ENDPOINT).hasRole("MODERATOR")
                .antMatchers(HttpMethod.PUT, FILES_ENDPOINT).hasRole("MODERATOR")
                .antMatchers(HttpMethod.DELETE, FILES_ENDPOINT).hasRole("MODERATOR")
                //----------------------------------------------------------------
                .antMatchers(HttpMethod.GET, EVENTS_ENDPOINT).hasRole("USER")
                .antMatchers(HttpMethod.POST, EVENTS_ENDPOINT).hasRole("MODERATOR")
                .antMatchers(HttpMethod.PUT, EVENTS_ENDPOINT).hasRole("MODERATOR")
                .antMatchers(HttpMethod.DELETE, EVENTS_ENDPOINT).hasRole("MODERATOR")
                //----------------------------------------------------------------
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
