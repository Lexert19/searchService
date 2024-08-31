package com.searchEngine.searchEngine.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.searchEngine.searchEngine.freeMarkerMethods.Translate;
import com.searchEngine.searchEngine.service.TranslationService;

import jakarta.annotation.PostConstruct;

@Configuration
public class FreeMarkerConfig {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private TranslationService translationService;

    @PostConstruct
    public void init() {
        freeMarkerConfigurer.getConfiguration().setSharedVariable("translate", new Translate(translationService));
    }
}
