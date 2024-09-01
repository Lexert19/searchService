package com.searchEngine.searchEngine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.searchEngine.searchEngine.freeMarkerMethods.Translate;
import com.searchEngine.searchEngine.service.TranslationService;

import freemarker.template.TemplateModelException;
import jakarta.annotation.PostConstruct;

@Configuration
public class FreeMarkerConfig {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private TranslationService translationService;
    @Value("${google.recaptcha.public}")
    private String recaptchaKey;

    @PostConstruct
    public void init() throws TemplateModelException {
        freeMarkerConfigurer.getConfiguration().setSharedVariable("translate", new Translate(translationService));
        freeMarkerConfigurer.getConfiguration().setSharedVariable("recaptchaKey", recaptchaKey);
        
    }
}
