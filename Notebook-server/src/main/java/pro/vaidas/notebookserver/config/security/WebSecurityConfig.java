package pro.vaidas.notebookserver.config.security;

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig  {
//
//    @Autowired
//    private UserDetailsService service;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(service)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//
//                .csrf().disable()
////                .csrf().ignoringRequestMatchers("/api/**")
////                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/", "/register").permitAll()
//                .requestMatchers("/resources**", "/resources/static**").permitAll()
//                .requestMatchers("/notes", "/notes/**").permitAll()
////                .requestMatchers("/api**").authenticated()
////                .requestMatchers("/api/v1/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/")
//                .loginProcessingUrl("/")
//                .defaultSuccessUrl("/notes")
//                .permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/no")
//                .and()
//                .oauth2ResourceServer().jwt();
//
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
//}
