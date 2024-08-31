package com.searchEngine.searchEngine;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.searchEngine.searchEngine.service.TranslationService;

@SpringBootTest
public class TranslationServiceTest {
    @Autowired
    private TranslationService translationService;

    @Test
    public void loadTranslationService() throws IOException{
        this.translationService = new TranslationService();
    }

    @Test
    public void testLoadProperties() throws FileNotFoundException, IOException{
        Properties properties = this.translationService.loadProperties("home", "en");

        assertTrue(properties.containsKey("main_header"));
    }

    @Test
    public void testSaveProperties() throws IOException{
        HashMap<String, String> fields = new HashMap<>();
        fields.put("test", "test");
        this.translationService.saveProperties("test", "pl", fields);

        Properties properties = this.translationService.loadProperties("test", "pl");

        assertTrue(properties.containsKey("test"));
    }

    @Test
    public void testTranslate(){
        String field = this.translationService.translate("main_header", "en", "home");
        assertTrue(field.equals("hellow"));
    }  
}
