package com.greenGekko.configs;

import com.greenGekko.servicies.user_service.AuthSuccessHandler;
import com.greenGekko.servicies.user_service.UserDetailServiceImpl;
import com.greenGekko.servicies.user_service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Slf4j
//@PropertySource("classpath:/application.yml")
public class SecurityConfig {

    private final DSLContext dslContext;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("Worked bean securityFilterChain(HttpSecurity http)");
        return http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/static/css/**").permitAll()
                    .antMatchers("/static/bootstrap/css/**").permitAll()
                    .antMatchers("/static/js/**").permitAll()
                    .antMatchers("/static/images/**").permitAll()
                    .antMatchers("/registration").permitAll()
                    .antMatchers("/home").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .permitAll().successHandler(authSuccessHandler(redirectStrategy(), this.dslContext))
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/home").permitAll()
                .and().build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        log.info("Worked bean bCryptPasswordEncoder()");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailService(UsersService usersService){
        log.info("Worked bean userDetailService(userService)");
        return new UserDetailServiceImpl(usersService);
    }

    @Bean
    public AuthenticationManager authenticationManager(UsersService usersService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService(usersService));
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        log.info("Worked bean authenticationManager(UsersService usersService)");
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public AuthSuccessHandler authSuccessHandler(RedirectStrategy redirectStrategy, DSLContext dslContext) {
        log.info("Worked bean authSuccessHandler(RedirectStrategy redirectStrategy)");
        return new AuthSuccessHandler(redirectStrategy, dslContext);
    }

    @Bean
    public RedirectStrategy redirectStrategy(){
        return new DefaultRedirectStrategy();
    }

}
