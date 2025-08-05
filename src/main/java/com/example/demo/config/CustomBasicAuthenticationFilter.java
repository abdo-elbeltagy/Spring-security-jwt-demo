package com.example.demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class CustomBasicAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if(authHeader!=null || authHeader.startsWith("Basic ")){
            String base64Credentials = authHeader.substring("Basic ".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String parts[] = credentials.split(":",2);
            if(parts.length==2){
                String username = parts[0];

                String password = parts[1];

                if(username.equals("abdo") && password.equals("abdo")){
                    System.out.println("pass "+  password);
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken("abdo is trying to authenticate", null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
                    SecurityContextHolder.getContext().setAuthentication(auth);

                }
            }

        }
        filterChain.doFilter(request,response);
    }
}
