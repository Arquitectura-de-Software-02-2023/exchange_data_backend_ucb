package edu.ucb.bo.exchange_data_backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class GlobalSecurityConfiguration {

    private final KeycloakJwtTokenConverter keycloakJwtTokenConverter;

    public GlobalSecurityConfiguration(TokenConverterProperties properties) {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter
                = new JwtGrantedAuthoritiesConverter();
        this.keycloakJwtTokenConverter
                = new KeycloakJwtTokenConverter(
                jwtGrantedAuthoritiesConverter,
                properties);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( (authorizeHttpRequests) -> {
            authorizeHttpRequests
                    .requestMatchers(HttpMethod.GET,"/api/v1/exchange/all").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST,"/api/v1/exchange/new").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT,"/api/v1/exchange/update").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE,"/api/v1/exchange/delete").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/api/v1/exchange/user/all").hasRole("USER")
                    .requestMatchers (HttpMethod.OPTIONS, "/**").permitAll();
//                    .anyRequest().permitAll();
        });
        http.oauth2ResourceServer( (oauth2) -> {
            oauth2.jwt( (jwt) -> jwt.jwtAuthenticationConverter(keycloakJwtTokenConverter));
        });
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}