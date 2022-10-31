package com.malefashionshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.malefashionshop.security.jwt.AuthEntryPointJwt;
import com.malefashionshop.security.jwt.AuthTokenFilter;
import com.malefashionshop.service.impl.security.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()

                .antMatchers(HttpMethod.GET,"/brands").permitAll()
                .antMatchers("/brands/**").hasRole("ADMIN")

                .antMatchers("/cart/customer/**").hasAnyRole("USER","ADMIN", "MODERATOR")
                .antMatchers("/cart/**").hasAnyRole( "ADMIN", "MODERATOR")

                .antMatchers(HttpMethod.GET,"/categories/**").permitAll()
                .antMatchers("/categories/**").hasAnyRole( "ADMIN", "MODERATOR")

                .antMatchers(HttpMethod.GET,"/color/**").permitAll()
                .antMatchers("/color/**").hasAnyRole( "ADMIN", "MODERATOR")

                .antMatchers("/customer/**").hasAnyRole( "ADMIN", "MODERATOR")

                .antMatchers("/orders/**").hasAnyRole( "ADMIN", "MODERATOR")

                .antMatchers("/orderItems/**").hasAnyRole( "ADMIN", "MODERATOR")

                .antMatchers(HttpMethod.GET,"/products/**").permitAll()
                .antMatchers("/products/**").hasAnyRole( "ADMIN", "MODERATOR")

                .antMatchers(HttpMethod.GET,"/images/**").permitAll()
                .antMatchers("/images/**").hasAnyRole( "ADMIN", "MODERATOR")

                .antMatchers(HttpMethod.DELETE,"/rating/**").hasAnyRole( "ADMIN", "MODERATOR")
                .antMatchers("/rating/**").hasAnyRole( "USER","ADMIN", "MODERATOR")

                .antMatchers("/role/**").hasAnyRole( "ADMIN", "MODERATOR")

                .antMatchers(HttpMethod.GET,"/size/**").permitAll()
                .antMatchers("/size/**").hasAnyRole( "ADMIN", "MODERATOR")

                .antMatchers(HttpMethod.GET,"/tags/**").permitAll()
                .antMatchers("/tags/**").hasAnyRole( "ADMIN", "MODERATOR")

                .antMatchers("/user/**").hasAnyRole( "ADMIN", "MODERATOR")

                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
