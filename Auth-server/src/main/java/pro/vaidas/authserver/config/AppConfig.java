package pro.vaidas.authserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pro.vaidas.authserver.repository.UserRepository;
import pro.vaidas.authserver.service.UserFromDbService;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepository repository;
    private final UserFromDbService service;

    @Bean
    public UserDetailsService userDetailsService(){
        return username ->
                repository.getUserByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
    }

    // DaoAuthenticationProvider responsible for getting UserDetails, encoding password, etc
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // which userDeSer to use in order to fetch details about user -
        // could be several
        authProvider.setUserDetailsService(userDetailsService());

        // password encoder
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuhtManager - responsible to manage auth - preimplemented
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
