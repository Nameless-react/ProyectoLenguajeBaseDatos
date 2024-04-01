package com.bienesRaices;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(
            InterceptorRegistry registro
    ) {
        registro.addInterceptor(
                localeChangeInterceptor()
        );
    }

    /* The following methods are to implement the security topic within the project */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/", "/index", "/error", "/aboutUs", "/properties/{idProperty}",
                        "/signUp/**", "/properties/list", "/js/**", "/webjars/**",
                        "/img/**", "/js/**", "/CSS/**", "/webjars/**")
                .permitAll()
                .requestMatchers(
                        "/user/**",
                        "/seller/**",
                        "/properties/new",
                        "properties/save",
                        "/properties/update/**"
                )
                .hasAnyAuthority("ADMIN", "ROLE_ADMIN")
                .requestMatchers("/contact" ,"/profile/perfil")
                .hasAuthority("ROLE_USER") // Usar hasAuthority en lugar de hasRole
                )
                .formLogin((form) -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/", true)
                )
                .logout((logout) -> logout.permitAll()
                .logoutSuccessUrl("/")
                );

        return http.build();
    }

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build)
            throws Exception {
        build.userDetailsService(userDetailService).passwordEncoder(
                new BCryptPasswordEncoder()
        );
    }

}
