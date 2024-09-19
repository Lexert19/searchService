package com.searchEngine.searchEngine.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.searchEngine.searchEngine.service.TranslationService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/admin/content")
public class AdminContentController {
    @Autowired
    private TranslationService translationService;
    
    @GetMapping("/fields/{language}/{file}")
    public String getFieldsList(
        @PathVariable String language,
        @PathVariable String file,
        Model model
    ) throws IOException{
        Properties properties;
        try {
            properties = translationService.loadProperties(file, language);
        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("fields", properties);

        return "admin/content-fields";
    }

    @PostMapping("/fields/{language}/{file}")
    public String editFields(
        @PathVariable String language,
        @PathVariable String file,
        @RequestParam HashMap<String, String> fields,
        Model model
    ) throws IOException {
        Properties properties;
        try {
            properties =  this.translationService.saveProperties(file, language, fields);
        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("fields", properties);

        return "admin/content-fields";
    }
    
}
