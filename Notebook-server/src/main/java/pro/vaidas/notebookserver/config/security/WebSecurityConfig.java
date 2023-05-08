package pro.vaidas.notebookserver.config.security;

import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.*;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pro.vaidas.notebookserver.business.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

//
//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests((authorize) -> {
//                    authorize
//                            .requestMatchers("/register/**", "/").permitAll()
//                            .requestMatchers("/resources/**", "/resources/static/*.css", "/webjars/**").permitAll()
//                            .requestMatchers("/notes", "/notes*").authenticated();
//                    System.out.println("logging in user");
//                })
//                .formLogin(
//                        form -> form
//                                .loginPage("/")
//                                .loginProcessingUrl("/")
//                                .defaultSuccessUrl("/notes")
//                                .permitAll()
//                ).logout(
//                        logout -> logout
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                .permitAll()
//                );
//        return http.build();
//    }
//
//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user1 = User.withUsername("a@a.a")
//                .password("{bcrypt}$2a$12$DVmfEdtjGsCWVeeBwT1rV.YRegg8xVPhKinbaTdybtaYvU2f8Bkb2")
//                .roles("USER")
//                .build();
//        UserDetails user2 = User.withUsername("user2")
//                .password("a")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withUsername("admin")
//                .password("a")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2, admin);
//    }
}
