package com.Barsat.Github.Repository.Management.Config;

import com.Barsat.Github.Repository.Management.Service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {

        //Csrf configurations (Ignoring csrf in public api's)
        http.csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/auth/public/**"));

        //http session management stateless + giving permit all to public requests.
        http.sessionManagement(Management -> Management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/auth/public/**").permitAll()
                        .anyRequest().authenticated())
//                .addFilterBefore(new JwtTokenAuthentication() , BasicAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        //return this by building it.
        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        //UserDetails service loads User repository and dao checks if credentials are correct.
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }



    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();

                //allowed origins.
                cfg.setAllowedOrigins(Arrays.asList(
                        "http://localhost:3000",
                        "http://localhost:5173",
                        "http://localhost:4200"
                ));

                //CRUD , which methods to allow cors.
                cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                cfg.setAllowCredentials(true);

                //all headers allowed for frontend to send to backend.
                cfg.setAllowedHeaders(List.of(""));

                //frontend will get this headers as a response.
                cfg.setExposedHeaders(List.of("Authorization" , "Content-Type"));

                cfg.setMaxAge(3600L);

                return cfg;
            }
        };
    }

    //Now open for dependency injection
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
