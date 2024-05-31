package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationEntryPoint;
import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig  {
	
	private UserDetailsServiceImpl userDetailsService;
    private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private JwtAuthenticationEntryPoint handler;
	
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint handler,JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.handler = handler;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
    	return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/posts")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/comments")
                                .permitAll()
                                .requestMatchers("/auth/**")
                                .permitAll()
                                .anyRequest().authenticated());

    		
    	httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    	return httpSecurity.build();
    }

     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.exceptionHandling(e -> e.authenticationEntryPoint(handler))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/auth/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/users")
                        .permitAll()
                        //.requestMatchers(HttpMethod.GET,"/users/{tur}")
                        //.permitAll()
                        .requestMatchers(HttpMethod.POST,"/users")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/users/{userId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT,"/users/{userId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/users/{userId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/admin")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,"/admin")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/admin/{adminId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT,"/admin/{adminId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/admin/{adminId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/client")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,"/client")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/client/{clientId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT,"/client/{clientId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/client/{clientId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/coach")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,"/coach")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/message")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,"/message")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/coach/{coachId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT,"/coach/{coachId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/coach/{coachId}")
                        .permitAll()
                        .anyRequest().authenticated()
                ).sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
