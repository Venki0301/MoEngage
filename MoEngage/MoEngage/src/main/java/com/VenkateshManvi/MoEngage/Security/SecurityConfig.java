package com.VenkateshManvi.MoEngage.Security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Important for decoding saved bcrypt passwords
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        // Custom query for users
        manager.setUsersByUsernameQuery(
                "SELECT username, password,true FROM users WHERE username = ?"
        );

//         Skip authorities (roles) for now, if not needed
        manager.setAuthoritiesByUsernameQuery(
                "SELECT username, 'ROLE_USER' FROM users WHERE username = ?"
        );

        return manager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                                configurer
                                        .requestMatchers("/dogs/index","/dogs/signup","/dogs/save","/css/**", "/js/**", "/assets/**","/img/**","/logos/**","/team/**").permitAll()
                                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/dogs/LoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/dogs/home", true)
                                .permitAll()
                )
//                to logout from home page
                .logout(logout -> logout
                        .logoutUrl("/logout") // optional, defaults to /logout
                        .logoutSuccessUrl("/digital/LoginPage?logout") // redirect after logout
                        .invalidateHttpSession(true) // clears the session
                        .clearAuthentication(true)   // removes the authentication
                        .deleteCookies("JSESSIONID") // deletes session cookie
                        .permitAll()
                )
                .exceptionHandling(configurer->
                        configurer.accessDeniedPage("/digital/access-denied"));
        return http.build();
    }
}
