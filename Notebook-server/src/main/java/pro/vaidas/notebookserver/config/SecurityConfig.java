//package pro.vaidas.notebookserver.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import pro.vaidas.notebookserver.handler.AccessDeniedHandlerCustom;
//
//@Configuration
////@EnableWebSecurity
////@RequiredArgsConstructor
//public class SecurityConfig {
//
////    private final JwtAuthFilter jwtAuthFiler;
////    private final AuthenticationProvider authenticationProvider;
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf(csrf -> csrf.disable())
////                .authorizeHttpRequests( request ->
////                        request
////                                .requestMatchers("/").permitAll()
////                                .anyRequest().permitAll())
////                .exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler()));
//////                .sessionManagement(session -> session
//////                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//////                .authenticationProvider(authenticationProvider)
//////                .addFilterBefore(jwtAuthFiler, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    }
//
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        return new AccessDeniedHandlerCustom();
//    }
//}