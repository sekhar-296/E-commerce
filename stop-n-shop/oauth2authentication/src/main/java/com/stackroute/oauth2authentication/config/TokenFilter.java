package com.stackroute.oauth2authentication.config;

import com.stackroute.oauth2authentication.OAuth2Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private final TokenStore tokenStore;



    public TokenFilter(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }
    //public String email;
    //public static OAuth2User principal;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authToken=request.getHeader("Authorization");
        if (authToken!=null){
            String token=authToken.split(" ")[1];
            Authentication authentication=tokenStore.getAuth(token);
            System.out.println("authentication is set"+authentication);
            if(authentication!=null){
                System.out.println("setting the suthentiation");
                SecurityContextHolder.getContext().setAuthentication(authentication);
               // principal= (OAuth2User) authentication.getPrincipal();
                new OAuth2Authentication().googleUser((OAuth2User) authentication.getPrincipal());
            }
        }
    }
}
