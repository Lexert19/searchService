package com.searchEngine.searchEngine.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {
    public HashMap<String, Properties> translations;

    public TranslationService() throws IOException {
        this.translations = new HashMap<>();
        this.loadAllProperties();
    }

    public Properties loadProperties(String fileName, String language) throws FileNotFoundException, IOException {
        File file = new File(
                System.getProperty("user.dir") + "/translations" + "/" + fileName + "_" + language + ".properties");

        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(file)) {
            properties.load(input);
        }

        return properties;
    }

    public Properties saveProperties(String fileName, String language, Map<String, String> properties) throws IOException {
        String fullFileName = fileName + "_" + language + ".properties";
        File translationsFile = new File(System.getProperty("user.dir") + "/translations" + "/" + fullFileName);

        Properties props = new Properties();
        props.putAll(properties);

        try (OutputStream out = new FileOutputStream(translationsFile)) {
            props.store(out, "Properties for language: " + language);
        }

        return props;

    }

    public Map<String, Properties> loadAllProperties() throws IOException {
        File dir = new File(System.getProperty("user.dir") + "/translations");

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("The provided path is not a directory.");
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".properties"));

        if (files != null) {
            for (File file : files) {
                try (InputStream input = new FileInputStream(file)) {
                    String fileName = file.getName().split("\\.")[0];
                    Properties properties;
                    if (this.translations.containsKey(fileName)) {
                        properties = this.translations.get(fileName);
                    } else {
                        properties = new Properties();
                    }
                    properties.clear();
                    properties.load(input);
                    this.translations.putIfAbsent(fileName, properties);
                }
            }
        }

        return this.translations;

    }

    public String translate(String field, String locale, String fileName) {
        Properties properties = translations.get(fileName + "_" + locale);
        return properties.getProperty(field);
    }
}
