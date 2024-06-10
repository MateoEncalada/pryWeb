package com.rungroop.login.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("Configuring security filter chain");

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> {
                auth
                    .requestMatchers("/login", "/register", "/register/save", "/clients", "/css/**", "/js/**").permitAll()
                    .anyRequest().authenticated();
                System.out.println("Authorized requests configured");
            })
            .formLogin(form -> {
                form
                    .loginPage("/login")
                    .defaultSuccessUrl("/clients", true)
                    .loginProcessingUrl("/login")
                    .failureUrl("/login?error=true")
                    .permitAll();
                System.out.println("Form login configured");
            })
            .logout(logout -> {
                logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .permitAll();
                System.out.println("Logout configured");
            });

        return http.build();
    }
}



// package com.rungroop.login.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {


//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/login", "/register", "/clients", "/css/**", "/js/**").permitAll()
//                 .anyRequest().authenticated()
//             )
//             .formLogin(form -> form
//                 .loginPage("/login")
//                 .defaultSuccessUrl("/clients", true)
//                 .loginProcessingUrl("/login")
//                 .failureUrl("/login?error=true")
//                 .permitAll()
//             )
//             .logout(logout -> logout
//                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                 .permitAll()
//             );

//         return http.build();
//     }

// }


