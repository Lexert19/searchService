package com.searchEngine.searchEngine.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.searchEngine.searchEngine.entity.ApiKey;
import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.service.ApiKeyService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {
    @Autowired
    private ApiKeyService apiKeyService;

    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        if (!requestURI.startsWith("/api")) {
            
            filterChain.doFilter(request, response);
            return;

        }

        String apiKey = request.getHeader("apiKey");
        Optional<ApiKey> key = apiKeyService.getApiKey(apiKey);
        if (key.isPresent()) {

            User userDetails = key.get().getUser();
            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(userToken);

        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        filterChain.doFilter(request, response);
    }

}
