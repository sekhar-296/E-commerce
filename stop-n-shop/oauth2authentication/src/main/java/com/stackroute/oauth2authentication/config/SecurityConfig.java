package com.stackroute.oauth2authentication.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.oauth2authentication.rabbitmq.domain.UserDTO;
import com.stackroute.oauth2authentication.rabbitmq.rabbitconfig.Producer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ObjectMapper mapper;
    private final TokenFilter tokenFilter;
    private final TokenStore tokenStore;

    public SecurityConfig(ObjectMapper mapper, TokenFilter tokenFilter, TokenStore tokenStore,Producer producer) {
        this.mapper = mapper;
        this.tokenFilter = tokenFilter;
        this.tokenStore = tokenStore;
        this.producer=producer;
    }
   Producer producer;




    @Override
    public void configure(HttpSecurity http)throws Exception{
        System.out.println("hello");
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers().permitAll().anyRequest().authenticated().and()
                .oauth2Login()
			    .defaultSuccessUrl("http://localhost:4200/",true)
                .authorizationEndpoint()
                .authorizationRequestRepository(new InMemoryRequestRepository())
                .and()
                .successHandler(this::successHandler)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(this::authenticationEntryPoint);
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    public void successHandler(HttpServletRequest request, HttpServletResponse response,
                               Authentication authentication) throws IOException {
        System.out.println("checking");
        String token = tokenStore.generateToken(authentication);
      //  response.getWriter().write(mapper.writeValueAsString(Collections.singletonMap("accessToken",token)));
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();

        Map<String, Object> map = new HashMap<>();
        map.put("email", principal.getAttribute("email"));
        map.put("accessToken", token);
        UserDTO user=new UserDTO(principal.getAttribute("email").toString(),principal.getAttribute("name").toString());
        producer.sendMessageToRabbitMq(user);
        response.getWriter().write(mapper.writeValueAsString(Collections.singletonMap("map", map)));

    }
    public void authenticationEntryPoint(HttpServletRequest request, HttpServletResponse response,
                                         AuthenticationException authException) throws IOException {
        System.out.println("checking 1");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(mapper.writeValueAsString(Collections.singletonMap("error","unauthenticated")));
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
