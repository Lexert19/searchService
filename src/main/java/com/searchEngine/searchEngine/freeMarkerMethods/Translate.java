package com.searchEngine.searchEngine.freeMarkerMethods;

import java.io.IOException;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.searchEngine.searchEngine.service.TranslationService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class Translate implements TemplateDirectiveModel {

    private final TranslationService translationService;

    public Translate(TranslationService translationService){
        this.translationService = translationService;
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        String field = params.get("f").toString();
        String locale = LocaleContextHolder.getLocale().toString();
        String fileName = params.get("fn").toString();

        String result = translationService.translate(field, locale, fileName);
        env.getOut().write(result);
    }
}