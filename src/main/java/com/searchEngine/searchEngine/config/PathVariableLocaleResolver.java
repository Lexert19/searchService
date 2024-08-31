package com.searchEngine.searchEngine.config;

import java.util.Locale;

import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PathVariableLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String path = request.getRequestURI();
        String[] parts = path.split("/");
        if (parts.length > 1) {
            return new Locale(parts[1]); 
        }
        return Locale.getDefault();
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        // Nie implementujemy, ponieważ locale jest ustawiane przez ścieżkę URL
    }
}