package com.ryzhov_andrei.spring_security_rest_api_app.config;


import com.ryzhov_andrei.spring_security_rest_api_app.model.Role;
import com.ryzhov_andrei.spring_security_rest_api_app.security.jwt.JwtConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private static final String AUTH_ENDPOINT = "/api/v1/auth/**";
    private static final String USERS_ENDPOINT = "/api/v1/users/**";
    private static final String EVENTS_ENDPOINT = "/api/v1/events/**";
    private static final String FILES_ENDPOINT = "/api/v1/files/**";

    private final JwtConfigurer jwtConfigurer;

    @Autowired
    public SecurityConfig(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(AUTH_ENDPOINT).permitAll()
                //-----------------
                .antMatchers(HttpMethod.GET, USERS_ENDPOINT).hasAnyRole(Role.ADMIN.name(), Role.MODERATOR.name(), Role.USER.name())
                .antMatchers(HttpMethod.POST, USERS_ENDPOINT).hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT, USERS_ENDPOINT).hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, USERS_ENDPOINT).hasRole(Role.ADMIN.name())
                //-----------------
                .antMatchers(HttpMethod.GET, EVENTS_ENDPOINT).hasAnyRole(Role.ADMIN.name(), Role.MODERATOR.name(), Role.USER.name())
                .antMatchers(HttpMethod.POST, EVENTS_ENDPOINT).hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT, EVENTS_ENDPOINT).hasAnyRole(Role.ADMIN.name(), Role.MODERATOR.name())
                .antMatchers(HttpMethod.DELETE, EVENTS_ENDPOINT).hasAnyRole(Role.ADMIN.name(), Role.MODERATOR.name())
                //-----------------
                .antMatchers(HttpMethod.GET, FILES_ENDPOINT).hasAnyRole(Role.ADMIN.name(), Role.MODERATOR.name(), Role.USER.name())
                .antMatchers(HttpMethod.POST, FILES_ENDPOINT).hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT, FILES_ENDPOINT).hasAnyRole(Role.ADMIN.name(), Role.MODERATOR.name())
                .antMatchers(HttpMethod.DELETE, FILES_ENDPOINT).hasAnyRole(Role.ADMIN.name(), Role.MODERATOR.name())
                //-----------------
                .anyRequest()
                .authenticated()
                .and()
                .apply(jwtConfigurer);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
