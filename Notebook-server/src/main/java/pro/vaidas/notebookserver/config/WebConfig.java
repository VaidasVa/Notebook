package pro.vaidas.notebookserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
//import pro.vaidas.notebookserver.web.controller.filter.CsrfHeaderFilter;


//@Configuration
//public class WebConfig {
//
//    @Bean
//    public SecurityFilterChain configure (HttpSecurity http) throws Exception{
////        http.httpBasic();
//        http.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
//        System.out.println("http in WebCONFIG");
//        return http.build();
//    }
//
//}
