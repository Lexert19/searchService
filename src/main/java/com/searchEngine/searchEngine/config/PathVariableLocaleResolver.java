package com.searchEngine.searchEngine.config;

import java.util.Locale;

import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PathVariableLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getParameter("lang");
        if (lang != null && !lang.isEmpty()) {
            return new Locale(lang); 
        }
        return new Locale("en");
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        // Nie implementujemy, ponieważ locale jest ustawiane przez ścieżkę URL
    }
}