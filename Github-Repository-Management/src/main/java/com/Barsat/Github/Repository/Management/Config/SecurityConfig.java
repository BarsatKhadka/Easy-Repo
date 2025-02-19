package com.Barsat.Github.Repository.Management.Config;

import com.Barsat.Github.Repository.Management.Config.Jwt.JwtFilter;
import com.Barsat.Github.Repository.Management.Config.OAuth.OAuthSuccessionHandler;
import com.Barsat.Github.Repository.Management.Service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyUserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;
    private final OAuthSuccessionHandler oAuthSuccessionHandler;


    private AuthenticationManager authenticationManager;
//

    public SecurityConfig(MyUserDetailsService userDetailsService, JwtFilter jwtFilter,OAuthSuccessionHandler oAuthSuccessionHandler) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
        this.oAuthSuccessionHandler = oAuthSuccessionHandler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {

        //Csrf configurations (Ignoring csrf in public api's)
        http.csrf(csrf -> csrf
//                .disable());
                .ignoringRequestMatchers("/api/auth/public/**" , "/register" ,"/login" ));

        //http session management stateless + giving permit all to public requests.
        http.sessionManagement(Management -> Management.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))

                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/auth/public/**" , "/register" , "/login" , "/oauth2/**" ).permitAll()
                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults()
                .oauth2Login(oauth -> {
                    oauth.successHandler(oAuthSuccessionHandler);

                })

//                .addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));




        //enabling this makes you require to pass authorization header with base64 code
        http.httpBasic(withDefaults());


        //return this by building it.
        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        //read encoded password from database
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        //UserDetails service loads User repository and dao checks if credentials are correct.
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();

                //allowed origins.
                cfg.setAllowedOrigins(Arrays.asList(
                        "http://localhost:3000",
                        "http://localhost:5173",
                        "http://localhost:5174",
                        "http://localhost:4200",
                        "https://easy-repo-oizjf4dox-barsatkhadkas-projects.vercel.app/",
                        "https://easy-repo-six.vercel.app/",
                        "https://easy-repo-six.vercel.app"

                ));

                //CRUD , which methods to allow cors.
                cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
                cfg.setAllowCredentials(true);

                //which http headers can be used while making request
                cfg.setAllowedHeaders(Arrays.asList("*"));


                //these browsers can access.
                cfg.setExposedHeaders(Arrays.asList("*"));

                cfg.setMaxAge(3600L);

                return cfg;
            }
        };
    }

    //Now open for dependency injection
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
