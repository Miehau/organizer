package org.mmlak.organizer.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }


    //
//
//    @Autowired
//    private DefaultSuccessHandler defaultSuccessHandler;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                // enable in memory based authentication with a user named user and admin
//                .inMemoryAuthentication().withUser("user").password("password").roles("USER")
//                .and().withUser("admin").password("password").roles("USER", "ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors().and().csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/",
//                        "/index.html",
//                        "/error",
//                        "/favicon.ico",
//                        "/**/*.png",
//                        "/**/*.gif",
//                        "/**/*.svg",
//                        "/**/*.jpg",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/aaa")
//                .permitAll()
//                .antMatchers("/auth/**", "/oauth2/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
//                .and()
//                .oauth2Login()
//                .authorizationEndpoint()
//                .baseUri("/oauth2/authorize")
//                .and()
//                .successHandler(defaultSuccessHandler)
//                .redirectionEndpoint()
//                .baseUri("/oauth2/callback/*")
//                .and();
////        .loginPage("/aa");
//    }
//
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        new OAuth2AuthenticationEntryPoint();
//        return (request, response, authException) -> response.sendRedirect("/login");
//    }
}
