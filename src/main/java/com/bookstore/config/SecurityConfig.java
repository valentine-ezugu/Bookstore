package com.bookstore.config;
import com.bookstore.service.impl.UserSecurityService;
import com.bookstore.utility.SecurityUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private  Environment env;

    SecurityUtility securityUtility;

    @Autowired
    private UserSecurityService userSecurityService;

    private BCryptPasswordEncoder passwordEncoder(){

        return securityUtility.passwordEncoder();
    }

    private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/js/**",
            "/image/**",
            "/",
            "/newUser",
            "/ForgetPassword",
            "/login",
            "/fonts/**", //adding fonts helped to downloading glyphicons doc when login in
            "/bookshelf", //so we can browse bookshelf without login
            "/hours",
            "faq",
            "/SearchByCategory"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers(PUBLIC_MATCHERS).permitAll().
                antMatchers( "/bookDetail/**").hasRole("USER").
                antMatchers("/listOfCreditCards/**").hasRole("USER").
                antMatchers( "/shoppingCart/addItem/**").hasRole("USER").
                and().formLogin();

        http
                .csrf().disable().cors().disable()
                .formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
                .and()
                .rememberMe();
    }
    @Configuration
    protected static class AuthenticationConfiguration extends
            GlobalAuthenticationConfigurerAdapter {

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("V").password("A").roles("USER");

        }
    }

    @Bean
    public UserDetailsService userDetailsService(){
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        UserDetails userDetails = (UserDetails)new User("V", "A", Arrays.asList(authority));
        return new InMemoryUserDetailsManager(Arrays.asList(userDetails));
    }


    //authenticate  user name password invoke user details service for confirmation
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
     }
}